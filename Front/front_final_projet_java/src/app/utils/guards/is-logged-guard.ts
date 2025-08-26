import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../services/auth-service';

export const isLoggedGuard: CanActivateFn = (route, state) => {
  const apiService = inject(AuthService)
  const router = inject(Router)
  const token = apiService.getToken();

  if(!token) {
    router.navigate(["/login"])
    return false
  }

  return true;
};
