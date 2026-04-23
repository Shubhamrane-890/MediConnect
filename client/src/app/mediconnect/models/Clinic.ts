export class Clinic {
  clinicId: number;
  clinicName: string;
  location: string;
  doctorId: number;
  contactNumber: string;
  establishedYear: number;

  constructor(
    clinicId: number,
    clinicName: string,
    location: string,
    doctorId: number,
    contactNumber: string,
    establishedYear: number
  ) {
    this.clinicId = clinicId;
    this.clinicName = clinicName;
    this.location = location;
    this.doctorId = doctorId;
    this.contactNumber = contactNumber;
    this.establishedYear = establishedYear;
  }

  logAttributes(): void {
    Object.entries(this).forEach(([key, value]) => {
      console.log(`${key}:`, value);
    });
  }
}