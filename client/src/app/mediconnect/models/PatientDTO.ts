export class PatientDTO {
  patientId: number;
  username: string;
  password: string;
  fullName: string;
  dateOfBirth: Date;
  contactNumber: string;
  email: string;
  address: string;

  constructor(
    patientId: number,
    username: string,
    password: string,
    fullName: string,
    dateOfBirth: Date,
    contactNumber: string,
    email: string,
    address: string
  ) {
    this.patientId = patientId;
    this.username = username;
    this.password = password;
    this.fullName = fullName;
    this.dateOfBirth = dateOfBirth;
    this.contactNumber = contactNumber;
    this.email = email;
    this.address = address;
  }

  logAttributes(): void {
    Object.entries(this).forEach(([key, value]) => {
      console.log(`${key}: ${value instanceof Date ? value.toISOString() : value}`);
    });
  }
}