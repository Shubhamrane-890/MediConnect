import { Doctor } from './Doctor';
import { Clinic } from './Clinic';

export class User {
  userId: number;
  username: string;
  password: string;
  role: string; // 'DOCTOR' or 'PATIENT'
  doctor?: Doctor; // Reference if user is a doctor
  clinic?: Clinic; // Reference if user is linked to a clinic

  constructor(
    userId: number,
    username: string,
    password: string,
    role: string,
    doctor?: Doctor,
    clinic?: Clinic
  ) {
    this.userId = userId;
    this.username = username;
    this.password = password;
    this.role = role;
    this.doctor = doctor;
    this.clinic = clinic;
  }

  // logAttributes(): void {
  //   Object.entries(this).forEach(([key, value]) => {
  //     console.log(`${key}:`, value);
  //   });
  // }
}
