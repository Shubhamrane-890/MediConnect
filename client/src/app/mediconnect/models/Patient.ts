export class Patient {
  patientId: number;
  fullName: string;
  dateOfBirth: Date;
  contactNumber: string;
  email: string;
  address: string;

  constructor(
    patientId: number,
    fullName: string,
    dateOfBirth: Date,
    contactNumber: string,
    email: string,
    address: string
  ) {
    this.patientId = patientId;
    this.fullName = fullName;
    this.dateOfBirth = dateOfBirth;
    this.contactNumber = contactNumber;
    this.email = email;
    this.address = address;
  }

  logAttributes(): void {
    Object.entries(this).forEach(([key, value]) => {
      console.log(`${key}:`, value);
    });
  }
}




