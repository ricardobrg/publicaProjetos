import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SigninComponent } from './pages/signin/signin.component';
import { HomeComponent } from './pages/home/home.component';
import { EditcollaboratorComponent } from './pages/collaborator/editcollaborator/editcollaborator.component';
import { ListcollaboratorComponent } from './pages/collaborator/listcollaborator/listcollaborator/listcollaborator.component';
import { RegistercollaboratorComponent } from './pages/collaborator/registercollaborator/registercollaborator.component';
import { ListpayrollComponent } from './pages/payroll/listpayroll/listpayroll.component';
import { RegisterpayrollComponent } from './pages/payroll/registerpayroll/registerpayroll.component';
import { EditproviderComponent } from './pages/provider/editprovider/editprovider.component';
import { ListproviderComponent } from './pages/provider/listprovider/listprovider.component';
import { RegisterproviderComponent } from './pages/provider/registerprovider/registerprovider.component';
import { EditroleComponent } from './pages/role/editrole/editrole.component';
import { ListroleComponent } from './pages/role/listrole/listrole.component';
import { RegisterroleComponent } from './pages/role/registerrole/registerrole.component';
import { ListworkentryComponent } from './pages/work-entries/listworkentry/listworkentry.component';
import { EventComponent } from './pages/event/event.component';

const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full'},
  { path: 'login', component: SigninComponent},
  { path: 'home', component: HomeComponent},
  { path: 'payroll/register', component: RegisterpayrollComponent},
  { path: 'payroll/list', component: ListpayrollComponent},
  { path: 'role/register', component: RegisterroleComponent},
  { path: 'role/list', component: ListroleComponent},
  { path: 'role/edit', component: EditroleComponent},
  { path: 'collaborator/register', component: RegistercollaboratorComponent},
  { path: 'collaborator/edit', component: EditcollaboratorComponent},
  { path: 'provider/register', component: RegisterproviderComponent},
  { path: 'provider/edit', component: EditproviderComponent},
  { path: 'provider/list', component: ListproviderComponent},
  { path: 'collaborator/list', component: ListcollaboratorComponent},
  { path: 'event', component: EventComponent},
  { path: 'work-entries/list', component: ListworkentryComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
