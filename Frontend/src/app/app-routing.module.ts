import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuthGuardService } from './auth-guard.service';
import { LoginComponent } from './authenticate/login/login.component';
import { CustomerComponent } from './customer/customer.component';
import { CreateAccountComponent } from './employee/create-account/create-account.component';
import { CreateCustomerComponent } from './employee/create-customer/create-customer.component';
import { EmployeeComponent } from './employee/employee.component';
import { ViewCustomerComponent } from './employee/view-customer/view-customer.component';
import { WelcomeComponent } from './welcome/welcome.component';


const routes: Routes = [
  {path: '',component: WelcomeComponent},
  {path: 'loginCustomer',component: LoginComponent},
  {path: 'loginEmployee',component: LoginComponent},
  {path: 'createCustomer', component: CreateCustomerComponent, canActivate:[AuthGuardService]},
  {path: 'viewCustomer/:id', component:ViewCustomerComponent, canActivate:[AuthGuardService]},
  {path: 'createAccount/:id', component: CreateAccountComponent, canActivate:[AuthGuardService]},
  {path: 'employeedashboard', component: EmployeeComponent, canActivate:[AuthGuardService]},
  {path: 'customerdashboard', component: CustomerComponent, canActivate:[AuthGuardService]},
  {path: '**', redirectTo: ''},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
