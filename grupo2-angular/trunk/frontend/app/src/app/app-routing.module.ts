import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

/* COLLABORATOR FRAMES */
import {CollabListComponent} from './components/collaborator/collab-list/collab-list.component';
import { CollabAddComponent } from './components/collaborator/collab-add/collab-add.component';

import { vacationComponent } from './components/vacation/vacation.component';
import { PayrollComponent } from './components/payroll/payroll.component';

/* ROLES FRAMES */
import { RoleFormComponent } from './components/role/role-form/role-form.component';
import { RoleListComponent } from './components/role/role-list/role-list.component';
import { RoleEditComponent } from './components/role/role-edit/role-edit.component';

import { SproviderFormComponent } from './components/sprovider/sprovider-form/sprovider-form.component';
import { SproviderListComponent } from './components/sprovider/sprovider-list/sprovider-list.component';
import { SproviderEditComponent } from './components/sprovider/sprovider-edit/sprovider-edit.component';

/* DEPARTMENTS FRAMES */
import { DepartmentListComponent } from './components/department/department-list/department-list.component';
import { AddDepartmentComponent } from './components/department/add-department/add-department.component';
import { DepartmentEditComponent } from './components/department/department-edit/department-edit.component';

/* EVENT FRAMES */
import { EventListComponent } from './components/event/event-list/event-list.component';

import { PointListComponent } from './components/point/point-list/point-list.component';
import { PointAddComponent } from './components/point/point-add/point-add.component';

import { LoginComponent } from './components/login/login.component';

import { ProfileListComponent } from './components/profile/profile-list/profile-list.component';
import { ProfilePointComponent } from './components/profile/profile-point/profile-point.component';





const routes: Routes = [
  // Collaborators endpoints
  { path: 'collab-list', component: CollabListComponent },
  { path: 'collab-add', component: CollabAddComponent },

  // Vacation and payroll endpoints
  { path: 'vacation', component: vacationComponent },
  { path: 'payroll', component: PayrollComponent },

  // roles enpoints
  { path: 'role-list', component: RoleListComponent },
  { path: 'role-form', component: RoleFormComponent },
  { path: 'role-edit/:id', component: RoleEditComponent },

  // Service Provider endpoitns
  { path: 'sprovider-list', component: SproviderListComponent },
  { path: 'sprovider-form', component: SproviderFormComponent },
  { path: 'sprovider-edit/:id', component: SproviderEditComponent },

  // departments endpoints
  { path: 'department-list', component: DepartmentListComponent },
  { path: 'department-add', component: AddDepartmentComponent },
  { path: 'department-edit/:id', component: DepartmentEditComponent },
  
  //point endpoints 
  { path: 'point-list', component: PointListComponent },
  { path: 'point-add', component: PointAddComponent },

  // EVENTS ENDPOINTS
  { path: 'event-list', component: EventListComponent },


  //profile endpoints 
  { path: 'profile-list', component: ProfileListComponent },
  { path: 'profile-point', component: ProfilePointComponent },

  { path: 'login', component: LoginComponent },
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
