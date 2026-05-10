// ✅ FIX 1: UPDATE Clinic MODEL
// src/app/mediconnect/models/Clinic.ts

import { Doctor } from './Doctor';

export interface Clinic {
  clinicId?: number;        // ✅ optional
  clinicName: string;
  location: string;
  contactNumber: string;
  establishedYear: number;
  doctor?: Doctor;          // ✅ populated by backend
  doctorId?: number;        // ✅ used only while creating
}