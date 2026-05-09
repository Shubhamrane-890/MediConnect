import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { MediconnectRoutingModule } from './mediconnect-routing.module';
import { SharedModule } from '../shared/shared.module';

import { PatientCreateComponent } from './components/patientcreate/patientcreate.component';
import { DoctorCreateComponent } from './components/doctorcreate/doctorcreate.component';
import { ClinicCreateComponent } from './components/cliniccreate/cliniccreate.component';
import { AppointmentCreateComponent } from './components/appointment/appointment.component';

/* ✅ THESE WERE MISSING */
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { DoctorEditComponent } from './components/doctoredit/doctoredit.component';
import { PatientEditComponent } from './components/patientedit/patientedit.component';

@NgModule({
  declarations: [
    PatientCreateComponent,
    DoctorCreateComponent,
    ClinicCreateComponent,
    AppointmentCreateComponent,

    DashboardComponent,
    DoctorEditComponent,
    PatientEditComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    SharedModule,
    MediconnectRoutingModule
  ]
})
export class MediconnectModule {}