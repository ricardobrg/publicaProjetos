import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { DataTablesModule } from 'angular-datatables';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SigninComponent } from './pages/signin/signin.component';
import { HomeComponent } from './pages/home/home.component';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { MenuhomeComponent } from './components/menuhome/menuhome.component';
import { CardComponent } from './components/card/card.component';
import { BaterpontoComponent } from './components/modal/baterponto/baterponto.component';
import { RegistercollaboratorComponent } from './pages/collaborator/registercollaborator/registercollaborator.component';
import { RegisterpayrollComponent } from './pages/payroll/registerpayroll/registerpayroll.component';
import { ListpayrollComponent } from './pages/payroll/listpayroll/listpayroll.component';
import { ListcollaboratorComponent } from './pages/collaborator/listcollaborator/listcollaborator/listcollaborator.component';
import { RegisterroleComponent } from './pages/role/registerrole/registerrole.component';
import { ListworkentryComponent } from './pages/work-entries/listworkentry/listworkentry.component';
import { RegisterproviderComponent } from './pages/provider/registerprovider/registerprovider.component';
import { ListroleComponent } from './pages/role/listrole/listrole.component';
import { ListproviderComponent } from './pages/provider/listprovider/listprovider.component';
import { EditroleComponent } from './pages/role/editrole/editrole.component';
import { EditcollaboratorComponent } from './pages/collaborator/editcollaborator/editcollaborator.component';
import { EditproviderComponent } from './pages/provider/editprovider/editprovider.component';
import { EventsComponent } from './components/events/events.component';


@NgModule({
  declarations: [
    AppComponent,
    SigninComponent,
    HomeComponent,
    HeaderComponent,
    FooterComponent,
    MenuhomeComponent,
    CardComponent,
    BaterpontoComponent,
    RegistercollaboratorComponent,
    RegisterpayrollComponent,
    RegisterroleComponent,
    ListpayrollComponent,
    ListcollaboratorComponent,
    RegisterpayrollComponent,
    ListworkentryComponent,
    RegisterproviderComponent,
    ListroleComponent,
    ListproviderComponent,
    EditroleComponent,
    EditcollaboratorComponent,
    EditproviderComponent,
    EventsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    DataTablesModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
