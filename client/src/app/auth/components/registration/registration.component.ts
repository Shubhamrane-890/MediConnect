import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.scss'],
})
export class RegistrationComponent implements OnInit {

  registrationForm!: FormGroup;
  errorMessage: string | null = null;
  selectedRole: string | null = null;

  showSuccessPopup = false;

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.registrationForm = this.formBuilder.group({
      username: ['', [Validators.required, Validators.pattern(/^[a-zA-Z0-9]+$/)]],
      password: ['', [
        Validators.required,
        Validators.pattern(/^(?=.*[A-Z])(?=.*\d).{8,}$/)
      ]],
      role: ['', Validators.required],
      fullName: ['', Validators.required],
      contactNumber: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      specialty: [''],
      yearsOfExperience: [''],
      dateOfBirth: [''],
      address: [''],
    });
  }

  onRoleChange(event: Event): void {
    this.selectedRole = (event.target as HTMLSelectElement).value;
  }

  onSubmit(): void {
    if (this.registrationForm.invalid) {
      this.errorMessage = 'Please fill out all fields correctly.';
      return;
    }

    this.authService.createUser(this.registrationForm.value).subscribe({
      next: () => {
        this.showSuccessPopup = true;
        this.errorMessage = null;
      },
      error: (err) => {
        if (typeof err?.error === 'string') {
          this.errorMessage = err.error;
        } else if (err?.status === 0) {
          this.errorMessage = 'Server is unreachable. Please try again.';
        } else {
          this.errorMessage = 'Registration failed. Please try again.';
        }
      }
    });
  }

  goToLogin(): void {
    this.router.navigate(['/auth/login']);
  }

  closePopup(): void {
    this.showSuccessPopup = false;
  }

  resetForm(): void {
    this.registrationForm.reset();
    this.selectedRole = null;
    this.errorMessage = null;
  }
}
