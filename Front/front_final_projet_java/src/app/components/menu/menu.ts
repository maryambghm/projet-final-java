import { Component, inject } from '@angular/core';
import { RouterLink } from '@angular/router';
import { AuthService } from '../../utils/services/auth-service';

@Component({
  selector: 'app-menu',
  imports: [RouterLink],
  templateUrl: './menu.html',
  styleUrl: './menu.css'
})
export class Menu {
  apiService = inject(AuthService);

    get token(): string | null {
    return this.apiService.getToken(); // relu à chaque CD
  }

  logout() {
    this.apiService.logout();
  }
}
