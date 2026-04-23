export class Appointment {
  appointmentId: number;
  patientId: number;
  clinicId: number;
  appointmentDate: Date;
  status: string;
  purpose: string;

  constructor(
    appointmentId: number,
    patientId: number,
    clinicId: number,
    appointmentDate: Date,
    status: string,
    purpose: string
  ) {
    this.appointmentId = appointmentId;
    this.patientId = patientId;
    this.clinicId = clinicId;
    this.appointmentDate = appointmentDate;
    this.status = status;
    this.purpose = purpose;
  }

  logAttributes(): void {
    Object.entries(this).forEach(([key, value]) => {
      console.log(`${key}:`, value);
    });
  }
}