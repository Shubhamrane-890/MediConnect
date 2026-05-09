import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';

interface LoginResponse {
  token?: string;
  roles?: string | string[];
  role?: string | string[];
  userId?: number | string;
  user_id?: number | string;
  doctorId?: number | string;
  doctor_id?: number | string;
  patientId?: number | string;
  patient_id?: number | string;
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
        const token = res.token ?? '';
        const roles = (res.roles ?? res.role) ?? '';
        const userId = (res.userId ?? res.user_id) ?? '';
        const doctorId = (res.doctorId ?? res.doctor_id) ?? '';
        const patientId = (res.patientId ?? res.patient_id) ?? '';

        if (token) localStorage.setItem('token', token);
        if (roles) {
          const roleStr = Array.isArray(roles) ? roles.join(',') : roles;
          localStorage.setItem('role', roleStr);
        }
        if (userId) localStorage.setItem('user_id', String(userId));
        if (doctorId) localStorage.setItem('doctor_id', String(doctorId));
        if (patientId) localStorage.setItem('patient_id', String(patientId));

       this.errorMessage = null;
this.successMessage = 'Login successful!';

const role = localStorage.getItem('role');

if (role?.includes('DOCTOR')) {
  this.router.navigate(['/mediconnect/doctor']);
} else if (role?.includes('PATIENT')) {
  this.router.navigate(['/mediconnect/patient']);
} else {
  this.router.navigate(['/mediconnect']);
}
      },
      error: () => {
        this.successMessage = null;
        this.errorMessage = 'Login failed';
      },
    });
  }
}