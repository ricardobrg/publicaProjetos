import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DataTablesModule } from 'angular-datatables';
import { ReactiveFormsModule } from '@angular/forms';
import { FormsModule } from '@angular/forms';

// Template Components
import { SidebarComponent } from './components/sidebar/sidebar.component';
import { ContentComponent } from './components/content/content.component';
import { HeaderComponent } from './components/header/header.component';
import { TemplateComponent } from './components/template/template.component';

//Vacation Component
import { vacationComponent } from './components/vacation/vacation.component';

//Payroll and Discount components
import { PayrollComponent } from './components/payroll/payroll.component';

/* COLLABORATOR COMPONENTS */
import { CollabListComponent } from './components/collaborator/collab-list/collab-list.component';
import { CollabAddComponent } from './components/collaborator/collab-add/collab-add.component';

/* DEPARTMENT COMPONENTS */
import { DepartmentListComponent } from './components/department/department-list/department-list.component';
import { DepartmentEditComponent } from './components/department/department-edit/department-edit.component';
import { AddDepartmentComponent } from './components/department/add-department/add-department.component';

/* ROLE COMPONENTS */
import { RoleListComponent } from './components/role/role-list/role-list.component';
import { RoleFormComponent } from './components/role/role-form/role-form.component';
import { RoleEditComponent } from './components/role/role-edit/role-edit.component';

/* SERVICE PROVIDER COMPONENTS */
import { SproviderListComponent } from './components/sprovider/sprovider-list/sprovider-list.component';
import { SproviderFormComponent } from './components/sprovider/sprovider-form/sprovider-form.component';
import { SproviderEditComponent } from './components/sprovider/sprovider-edit/sprovider-edit.component';

/* POINT COMPONENTS */ 
import { PointListComponent } from './components/point/point-list/point-list.component';
import { PointAddComponent } from './components/point/point-add/point-add.component';

/* GENERAL COMPONENTS */
import { LoginComponent } from './components/login/login.component';

import { ProfileListComponent } from './components/profile/profile-list/profile-list.component';
import { ProfilePointComponent } from './components/profile/profile-point/profile-point.component';

/* EVENTS COMPONENTS */
import { EventListComponent } from './components/event/event-list/event-list.component';


@NgModule({
  declarations: [
    // Template components
    AppComponent,
    HeaderComponent,
    TemplateComponent,
    SidebarComponent,
    ContentComponent,
    LoginComponent,
    
    //Vacation component
    vacationComponent,

    //Payroll and Discount components
    PayrollComponent,
    
    //Departments components
    DepartmentListComponent,
    AddDepartmentComponent,
    DepartmentEditComponent,

    // Collaborator Components
    CollabListComponent,
    CollabAddComponent,

    //Profile component
    ProfileListComponent,
    ProfilePointComponent,

    //Role components
    RoleListComponent,
    RoleFormComponent,
    RoleEditComponent,

    //Service Provider components
    SproviderListComponent,
    SproviderFormComponent,



    //Point compoments 
    PointListComponent,
    PointAddComponent,
    RoleEditComponent,
    DepartmentEditComponent,
    SproviderEditComponent,
    DepartmentEditComponent,
    DepartmentEditComponent,
    ProfilePointComponent,
    // EVENTS COMPONENTS
    EventListComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    DataTablesModule,
    ReactiveFormsModule,
    FormsModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
