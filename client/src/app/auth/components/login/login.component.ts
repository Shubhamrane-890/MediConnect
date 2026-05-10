import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';

interface LoginResponse {
  token?: string;
  role?: string;
  user_id?: number;
  doctor_id?: number;
  patient_id?: number;
}

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent implements OnInit {

  loginForm!: FormGroup;
  errorMessage: string | null = null;
  successMessage: string | null = null;

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      username: ['', [Validators.required, Validators.pattern(/^[a-zA-Z0-9]+$/)]],
      password: ['', [Validators.required, Validators.minLength(8)]],
    });
  }

  onSubmit(): void {
    if (this.loginForm.invalid) {
      this.loginForm.markAllAsTouched();
      this.errorMessage = 'Please fill out all fields correctly.';
      this.successMessage = null;
      return;
    }

    const { username, password } = this.loginForm.value;

    this.authService.login({ username, password }).subscribe({
      next: (res: LoginResponse) => {

        // ✅ Store auth data
        if (res.token) localStorage.setItem('token', res.token);
        if (res.role) localStorage.setItem('role', res.role);
        if (res.user_id) localStorage.setItem('user_id', String(res.user_id));
        if (res.doctor_id) localStorage.setItem('doctor_id', String(res.doctor_id));
        if (res.patient_id) localStorage.setItem('patient_id', String(res.patient_id));

        this.errorMessage = null;
        this.successMessage = 'Login successful!';

        // ✅ ✅ ONLY CORRECT REDIRECT
        // mediconnect → dashboard (via routing)
        this.router.navigate(['/mediconnect']);
      },
      error: () => {
        this.successMessage = null;
        this.errorMessage = 'Invalid username or password';
      }
    });
  }
}