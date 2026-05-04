import { Component, OnInit } from '@angular/core';
import { Appointment } from '../../models/Appointment';
import { Clinic } from '../../models/Clinic';
import { Doctor } from '../../models/Doctor';
import { Patient } from '../../models/Patient';
import { MediConnectService } from '../../services/mediconnect.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss'],
})
export class DashboardComponent implements OnInit {
  doctorDetails?: Doctor;
  patientDetails?: Patient;

  doctors: Doctor[] = [];
  clinics: Clinic[] = [];
  patients: Patient[] = [];
  appointments: Appointment[] = [];

  role: string | null = null;
  userId: number = 0;
  doctorId: number = 0;
  patientId: number = 0;

  selectedClinicId: number | undefined;
  selectClinicAppointments: Appointment[] = [];

  errorMessage: string | null = null;
  successMessage: string | null = null;

  constructor(private mediconnectService: MediConnectService) {}

  ngOnInit(): void {
    this.role = localStorage.getItem('role');
    this.userId = Number(localStorage.getItem('user_id') || 0);
    this.doctorId = Number(localStorage.getItem('doctor_id') || 0);
    this.patientId = Number(localStorage.getItem('patient_id') || 0);

    if (this.doctorId > 0 && this.role === 'DOCTOR') {
      this.loadDoctorData();
    }
    if (this.patientId > 0 && this.role !== 'DOCTOR') {
      this.loadPatientData();
    }
  }

  loadDoctorData(): void {
    this.errorMessage = null;

    this.mediconnectService.getDoctorById(this.doctorId).subscribe({
      next: (d: Doctor) => (this.doctorDetails = d),
      error: () => (this.errorMessage = 'Failed to fetch doctor details'),
    });

    this.mediconnectService.getClinicsByDoctorId(this.doctorId).subscribe({
      next: (c: Clinic[]) => (this.clinics = c),
      error: () => (this.errorMessage = 'Failed to fetch clinics'),
    });

    this.mediconnectService.getAllPatients().subscribe({
      next: (p: Patient[]) => (this.patients = p),
      error: () => (this.errorMessage = 'Failed to fetch patients'),
    });
  }

  loadAppointments(clinicId: number): void {
    this.errorMessage = null;
    this.mediconnectService.getAppointmentsByClinic(clinicId).subscribe({
      next: (a: Appointment[]) => {
        this.selectClinicAppointments = a;
        this.appointments = a;
      },
      error: () => (this.errorMessage = 'Failed to fetch appointments'),
    });
  }

  // ✅ Needed by template
  onClinicSelect(clinic: Clinic): void {
    this.selectedClinicId = clinic.clinicId;
    this.loadAppointments(this.selectedClinicId);
  }

  // ✅ Stubs to satisfy template buttons if present
  navigateToEditDoctor(): void { /* route or no-op for now */ }
  navigateToEditClinic(id: number): void { /* route or no-op for now */ }

  loadPatientData(): void {
    this.errorMessage = null;

    this.mediconnectService.getPatientById(this.patientId).subscribe({
      next: (p: Patient) => (this.patientDetails = p),
      error: () => {
        this.patientDetails = undefined;
        this.errorMessage = 'Failed to fetch patient details';
      },
    });

    this.mediconnectService.getAllClinics().subscribe({
      next: (c: Clinic[]) => (this.clinics = c),
      error: () => (this.errorMessage = 'Failed to fetch clinics'),
    });

    this.mediconnectService.getAllDoctors().subscribe({
      next: (d: Doctor[]) => (this.doctors = d),
      error: () => (this.errorMessage = 'Failed to fetch doctors'),
    });

    this.mediconnectService.getAppointmentsByPatient(this.patientId).subscribe({
      next: (a: Appointment[]) => { this.appointments = a; },
      error: () => (this.errorMessage = 'Failed to fetch appointments'),
    });
  }
}