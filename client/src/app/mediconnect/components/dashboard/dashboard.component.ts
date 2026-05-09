import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
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
    doctorDetails: any;
    patientDetails: any;
    doctors: Doctor[] = [];
    clinics: Clinic[] = [];
    appointments: Appointment[] = [];
    patients: Patient[] = [];
    role!: string | null;
    userId!: number;
    doctorId!: number;
    patientId!: number;
    selectedClinicId: number | undefined;
    selectClinicAppointments: Appointment[] = [];

    constructor(private mediconnectService: MediConnectService, private router: Router) { }

    ngOnInit(): void {
        this.role = localStorage.getItem("role");
        this.userId = Number(localStorage.getItem("user_id"));
        this.doctorId = Number(localStorage.getItem("doctor_id"));
        this.patientId = Number(localStorage.getItem("patient_id"));
       
        if (this.role === 'DOCTOR') {
            this.loadDoctorData();
        } else {
            this.loadPatientData();
        }
    }

    loadDoctorData(): void {
        this.mediconnectService.getDoctorById(this.doctorId).subscribe({
            next: (response) => this.doctorDetails = response,
            error: (err) => console.log('Error loading doctor', err)
        });

        this.mediconnectService.getClinicsByDoctorId(this.doctorId).subscribe({
            next: (response) => {
                this.clinics = response;
                this.appointments = []; 
                this.clinics.forEach(clinic => {
                    this.mediconnectService.getAppointmentsByClinic(clinic.clinicId).subscribe({
                        next: (res) => this.appointments = [...this.appointments, ...res]
                    });
                });
            },
            error: (err) => console.log('Error loading clinics', err)
        });

        this.mediconnectService.getAllPatients().subscribe({
            next: (response) => this.patients = response,
            error: (err) => console.log('Error loading patients', err)
        });
    }

    loadPatientData(): void {
        this.mediconnectService.getPatientById(this.patientId).subscribe({
            next: (res) => this.patientDetails = res
        });
        this.mediconnectService.getAppointmentsByPatient(this.patientId).subscribe({
            next: (res) => this.appointments = res
        });
    }

    onClinicSelect(clinic: Clinic): void {
        this.selectedClinicId = clinic.clinicId;
    }

    acceptAppointment(appointment: Appointment): void {
        if (confirm('Accept this appointment?')) {
            appointment.status = 'Accepted';
            this.mediconnectService.updateAppointment(appointment).subscribe({
                next: () => this.loadDoctorData()
            });
        }
    }

    cancelAppointment(appointment: Appointment): void {
        if (confirm('Cancel this appointment?')) {
            appointment.status = "Cancelled";
            this.mediconnectService.updateAppointment(appointment).subscribe({
                next: () => this.loadDoctorData()
            });
        }
    }

    // Navigation methods
    navigateToEditDoctor(): void { this.router.navigate(['mediconnect/doctor/edit', this.doctorId]); }
    navigateToEditClinic(id: number): void { this.router.navigate(['mediconnect/clinic/edit', id]); }
    navigateToEditPatient(): void { this.router.navigate(['mediconnect/patient/edit', this.patientId]); }
    
    // DELETE DOCTOR
    deleteDoctor(): void {
        if (confirm('Are you sure you want to delete your doctor profile?')) {
            this.mediconnectService.deleteDoctor(this.doctorId).subscribe({
                next: () => {
                    alert('Profile deleted successfully.');
                    localStorage.clear(); // Clear session
                    this.router.navigate(['/auth/login']); // Redirect to login
                },
                error: (error) => console.error('Error deleting doctor:', error)
            });
        }
    }

    // DELETE CLINIC
    deleteClinic(clinicId: number): void {
        if (confirm('Are you sure you want to delete this clinic?')) {
            this.mediconnectService.deleteClinic(clinicId).subscribe({
                next: () => {
                    alert('Clinic deleted successfully.');
                    this.loadDoctorData(); // Refresh the list
                },
                error: (error) => console.error('Error deleting clinic:', error)
            });
        }
    }

    // DELETE PATIENT
    deletePatient(): void {
        if (confirm('Are you sure you want to delete your patient profile?')) {
            this.mediconnectService.deletePatient(this.patientId).subscribe({
                next: () => {
                    alert('Profile deleted successfully.');
                    localStorage.clear();
                    this.router.navigate(['/auth/login']);
                },
                
                error: (error) => console.error('Error deleting patient:', error)
            });
        }
    }
}