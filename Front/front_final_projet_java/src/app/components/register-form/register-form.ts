import { Component, OnDestroy, OnInit } from '@angular/core';
import {
  FormArray,
  FormControl,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { UserType } from '../../utils/types/user-type';
import { UserService } from '../../utils/services/user-service';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-register-form',
  imports: [FormsModule, ReactiveFormsModule, RouterLink],
  templateUrl: './register-form.html',
  styleUrl: './register-form.css',
})
export class RegisterForm implements OnInit, OnDestroy {
  constructor(private userService: UserService, private router: Router) {}
  ngOnInit(): void {
  }
  ngOnDestroy(): void {
  }
  onSubmit(): void {
    if (this.registerForm.valid) {
      const newUser: UserType = this.registerForm.value;

      this.userService.createUser(newUser).subscribe({
        next: (user) => {
          console.log('Utilisateur créé :', user);
          this.router.navigate(["/login"]);
        },
        error: (err) => {
          console.error('Erreur lors de la création ', err);
        },
      });
    } else {
      console.warn('Le formulaire est invalide ');
      this.registerForm.markAllAsTouched();
    }
  }

  registerForm: FormGroup = new FormGroup({
    email: new FormControl('', [Validators.required]),
    lastname: new FormControl('', [Validators.required]),
    firstname: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required]),
    avatar: new FormControl('', [Validators.required]),
  });
}
