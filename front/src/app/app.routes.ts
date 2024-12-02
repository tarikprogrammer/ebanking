import { Routes } from '@angular/router';
import {HomeComponent} from "./pages/home/home.component";
import {LoginComponent} from "./components/login/login.component";
import {
  EnterEmailToResetPasswordComponent
} from "./components/enter-email-to-reset-password/enter-email-to-reset-password.component";
import {EnterNewPasswordComponent} from "./components/enter-new-password/enter-new-password.component";
import {EnterOtpToValidateComponent} from "./components/enter-otp-to-validate/enter-otp-to-validate.component";

export const routes: Routes = [
  {path:'',component:HomeComponent},
  {path:'login',component:LoginComponent},
  {path:'forgotPassword',component:EnterEmailToResetPasswordComponent},
  {path:'resetPassword',component:EnterNewPasswordComponent},
  {path:'otp',component:EnterOtpToValidateComponent}
];
