import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavBarComponent {

  role: string | null = localStorage.getItem('role');

  constructor(private router: Router) {}

  logout(): void {
    localStorage.clear(); // or clear only token/role if required
    this.router.navigate(['/auth/login']);
  }
}
