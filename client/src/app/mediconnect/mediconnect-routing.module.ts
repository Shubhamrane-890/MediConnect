import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { DashboardComponent } from './components/dashboard/dashboard.component';
import { ClinicCreateComponent } from './components/cliniccreate/cliniccreate.component';
import { AppointmentCreateComponent } from './components/appointment/appointment.component';

const routes: Routes = [
  { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'clinic', component: ClinicCreateComponent },
  { path: 'appointment', component: AppointmentCreateComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MediconnectRoutingModule {}