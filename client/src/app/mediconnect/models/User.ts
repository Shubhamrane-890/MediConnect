export class User {
  userId: number;
  username: string;
  password: string;
  role: string;
  patientId?: number;
  doctorId?: number;

  constructor(
    userId: number,
    username: string,
    password: string,
    role: string,
    patientId?: number,
    doctorId?: number
  ) {
    this.userId = userId;
    this.username = username;
    this.password = password;
    this.role = role;
    this.patientId = patientId;
    this.doctorId = doctorId;
  }

  logAttributes(): void {
    Object.entries(this).forEach(([key, value]) => {
      console.log(`${key}: ${value}`);
    });
  }
}