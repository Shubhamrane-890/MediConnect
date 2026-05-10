import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MediConnectService } from '../../services/mediconnect.service';
import { Clinic } from '../../models/Clinic';

@Component({
  selector: 'app-cliniccreate',
  templateUrl: './cliniccreate.component.html',
  styleUrls: ['./cliniccreate.component.scss']
})
export class ClinicCreateComponent implements OnInit {

  clinicForm!: FormGroup;

  doctorId!: number;
  doctorName = '';

  // ✅ used in template
  currentYear = new Date().getFullYear();

  // ✅ popup + messages
  showSuccessPopup = false;
  errorMessage: string | null = null;

  constructor(
    private formBuilder: FormBuilder,
    private mediconnectService: MediConnectService
  ) {}

  ngOnInit(): void {
    this.doctorId = Number(localStorage.getItem('doctor_id'));

    this.clinicForm = this.formBuilder.group({
      doctorName: [{ value: '', disabled: true }],
      clinicName: ['', [Validators.required, Validators.minLength(2)]],
      location: ['', Validators.required],
      contactNumber: ['', [Validators.required, Validators.pattern('^[0-9]{10}$')]],
      establishedYear: [
        null,
        [
          Validators.required,
          Validators.min(1900),
          Validators.max(this.currentYear)
        ]
      ]
    });

    if (this.doctorId) {
      this.mediconnectService.getDoctorById(this.doctorId).subscribe({
        next: (doc) => {
          this.doctorName = doc.fullName;
          this.clinicForm.patchValue({ doctorName: this.doctorName });
        }
      });
    }
  }

  get clinicName() { return this.clinicForm.get('clinicName'); }
  get location() { return this.clinicForm.get('location'); }
  get contactNumber() { return this.clinicForm.get('contactNumber'); }
  get establishedYear() { return this.clinicForm.get('establishedYear'); }

  resetForm(): void {
    this.clinicForm.reset();
    this.clinicForm.patchValue({ doctorName: this.doctorName });
    this.errorMessage = null;
  }

  onSubmit(): void {
    if (this.clinicForm.invalid) {
      this.clinicForm.markAllAsTouched();
      return;
    }

    // ✅ Partial<Clinic> payload
    const payload: Partial<Clinic> = {
      clinicName: this.clinicForm.value.clinicName,
      location: this.clinicForm.value.location,
      contactNumber: this.clinicForm.value.contactNumber,
      establishedYear: this.clinicForm.value.establishedYear,
      doctorId: this.doctorId
    };

    this.mediconnectService.addClinic(payload).subscribe({
      next: () => {
        this.showSuccessPopup = true;
        this.errorMessage = null;
        this.resetForm();
      },
      error: (error: HttpErrorResponse) => {
        this.errorMessage =
          error.status === 400
            ? 'Bad request. Please check your input.'
            : `Server error: ${error.status}`;
      }
    });
  }

  closePopup(): void {
    this.showSuccessPopup = false;
  }
}