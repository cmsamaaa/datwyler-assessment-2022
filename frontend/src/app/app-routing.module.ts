import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {WelcomeComponent} from "./welcome/welcome.component";
import {LoginFormComponent} from "./login-form/login-form.component";
import {ErrorComponent} from "./error/error.component";
import {LogoutComponent} from "./logout/logout.component";
import {ApplicantsComponent} from "./applicants/applicants.component";
import {CreditFacilitiesComponent} from "./credit-facilities/credit-facilities.component";
import {LoansComponent} from "./loans/loans.component";
import {LoanFormComponent} from "./loan-form/loan-form.component";

const routes: Routes = [
  { path: '', component: LoginFormComponent},
  { path: 'login', component: LoginFormComponent},
  { path: 'welcome', component: WelcomeComponent},
  { path: 'applicants', component: ApplicantsComponent},
  { path: 'creditFacilities', component: CreditFacilitiesComponent},
  { path: 'loans/:applicantId', component: LoansComponent},
  { path: 'openLoan/:applicantId/:creditId', component: LoanFormComponent},
  { path: 'logout', component: LogoutComponent},
  { path: '**', component: ErrorComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
