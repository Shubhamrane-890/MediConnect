import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.scss']
})
export class LogoutComponent {

  constructor(
    private authService: AuthService, // Handles logout logic
    private router: Router             // Used for navigation
  ) {}

  logout(): void {
    // Clear authentication data
    this.authService.logout();

    // Redirect user to login/auth page
    this.router.navigate(['/auth/login']);
  }
}