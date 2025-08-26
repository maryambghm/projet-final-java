import { Component } from '@angular/core';
import { UserService } from '../../utils/services/user-service';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../../utils/services/auth-service';

@Component({
  selector: 'app-login-form',
  imports: [FormsModule, RouterLink],
  templateUrl: './login-form.html',
  styleUrl: './login-form.css'
})
export class LoginForm {
  email ="";
  password = "";

constructor (private auth: AuthService, private router: Router){}

  onLoginHandler() {
    this.auth.login({ email: this.email, password: this.password })
      .subscribe({
        next: res => {
          console.log('Token reçu:', res.token);
          this.router.navigate([""])
        },
        error: err => {
          console.error('Erreur de login:', err);
        }
      });
  }

}
