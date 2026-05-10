import { Component, OnInit } from '@angular/core';
import { MediConnectService } from '../../services/mediconnect.service';
import { Clinic } from '../../models/Clinic';
import { Appointment } from '../../models/Appointment';
import { Patient } from '../../models/Patient';
import { Doctor } from '../../models/Doctor';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

  role: string | null = localStorage.getItem('role');

  doctorId = 0;
  patientId = 0;

  doctorDetails?: Doctor;
  patientDetails?: Patient;

  clinics: Clinic[] = [];
  appointments: Appointment[] = [];
  patients: Patient[] = [];

  constructor(private service: MediConnectService) {}

  ngOnInit(): void {
    this.doctorId = Number(localStorage.getItem('doctor_id'));
    this.patientId = Number(localStorage.getItem('patient_id'));

    if (this.role === 'DOCTOR') {
      this.loadDoctorDashboard();
    }

    if (this.role === 'PATIENT') {
      this.loadPatientDashboard();
    }
  }

  loadDoctorDashboard(): void {
    this.service.getDoctorById(this.doctorId).subscribe(d => {
      this.doctorDetails = d;
    });

    this.service.getClinicsByDoctorId(this.doctorId).subscribe(c => {
      this.clinics = c;

      // ✅ Auto-load appointments for first clinic
      if (c.length > 0 && c[0].clinicId) {
        this.onClinicSelect(c[0]);
      }
    });

    this.service.getAllPatients().subscribe(p => {
      this.patients = p;
    });
  }

  loadPatientDashboard(): void {
    this.service.getPatientById(this.patientId).subscribe(p => {
      this.patientDetails = p;
    });

    this.service.getAppointmentsByPatient(this.patientId).subscribe(res => {
      this.appointments = res;
    });
  }

  onClinicSelect(clinic: Clinic): void {
    if (!clinic.clinicId) {
      this.appointments = [];
      return;
    }

    this.service.getAppointmentsByClinic(clinic.clinicId).subscribe(res => {
      this.appointments = res;
    });
  }

  deleteClinic(clinicId: number): void {
    this.service.deleteClinic(clinicId).subscribe(() => {
      this.clinics = this.clinics.filter(c => c.clinicId !== clinicId);
      this.appointments = [];
    });
  }

  navigateToEditClinic(clinicId: number): void {
    // add routing later if needed
  }
}
