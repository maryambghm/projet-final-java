import { Component, inject } from '@angular/core';
import { AuthService } from '../../utils/services/auth-service';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-home',
  imports: [RouterLink],
  templateUrl: './home.html',
  styleUrl: './home.css'
})
export class Home {
 apiService = inject(AuthService);

    get token(): string | null {
    return this.apiService.getToken(); // relu à chaque CD
  }
}
