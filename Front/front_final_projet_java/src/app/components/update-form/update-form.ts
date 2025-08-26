// update-form.ts
import { Component, OnInit, inject, signal } from '@angular/core';
import {
  Validators,
  AbstractControl,
  ValidationErrors,
  ReactiveFormsModule,
  NonNullableFormBuilder,
} from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { AuthService } from '../../utils/services/auth-service';
import { UserService } from '../../utils/services/user-service';

/* ---------- Validator: password === confirmPassword (ou vide) ----------- */
function passwordMatchValidator(group: AbstractControl): ValidationErrors | null {
  const p = group.get('password')?.value as string | undefined;
  const c = group.get('confirmPassword')?.value as string | undefined;
  if (!p && !c) return null;               // pas de changement de mdp
  return p === c ? null : { passwordMismatch: true };
}

/* ---------- Util: extraire l'id depuis le JWT --------------------------- */
function getUserIdFromToken(token: string | null): string | null {
  if (!token) return null;
  try {
    const payload = JSON.parse(atob(token.split('.')[1] || ''));
    return payload?.id ?? payload?.userId ?? payload?.user_id ?? payload?.sub ?? null;
  } catch {
    return null;
  }
}

@Component({
  selector: 'app-update-form',
  standalone: true,
  imports: [ReactiveFormsModule, RouterModule],
  templateUrl: './update-form.html',
  styleUrl: './update-form.css',
})
export class UpdateForm implements OnInit {
  /* DI moderne */
  private fb     = inject(NonNullableFormBuilder);
  private users  = inject(UserService);
  private auth   = inject(AuthService);
  private router = inject(Router);

  /* UI state (signals) */
  loading = signal(false);
  saving  = signal(false);
  errorMsg = signal<string | null>(null);
  successMsg = signal<string | null>(null);

  userId: string | null = null;

  /* Typed reactive form (nonNullable) */
  readonly updateForm = this.fb.group(
    {
      avatar: ['', Validators.required],
      firstname: ['', Validators.required],
      lastname: ['', Validators.required],
      password: this.fb.control(''),          // optionnel
      confirmPassword: this.fb.control(''),   // optionnel
    },
    { validators: passwordMatchValidator }
  );

  ngOnInit(): void {
    // La route est déjà protégée par canActivate: [isLoggedGuard]
    // Ici: chargement du profil et patch du form
    this.userId = getUserIdFromToken(this.auth.getToken());
    if (!this.userId) { this.router.navigate(['/login']); return; } // filet de sécu

    this.loading.set(true);
    this.users.getUser(this.userId)
      .pipe(takeUntilDestroyed())
      .subscribe({
        next: (u) => {
          this.updateForm.patchValue({
            avatar: u?.avatar ?? '',
            firstname: u?.firstname ?? '',
            lastname:  u?.lastname  ?? '',
          });
          this.loading.set(false);
        },
        error: (e) => {
          console.error(e);
          this.errorMsg.set('Impossible de charger le profil.');
          this.loading.set(false);
        }
      });
  }

  onSubmit(): void {
    if (!this.userId || this.updateForm.invalid) {
      this.updateForm.markAllAsTouched();
      return;
    }

    const { password, confirmPassword, ...rest } = this.updateForm.getRawValue();
    const payload: any = { ...rest };
    if (password) payload.password = password;  // n’envoie le mdp que s’il change

    this.saving.set(true);
    this.users.updateUser(this.userId, payload)
      .pipe(takeUntilDestroyed())
      .subscribe({
        next: () => {
          this.successMsg.set('Profil mis à jour.');
          this.saving.set(false);
          this.router.navigate(['/dashboard']);
        },
        error: (e) => {
          console.error(e);
          this.errorMsg.set('La mise à jour a échoué.');
          this.saving.set(false);
        }
      });
  }
}
