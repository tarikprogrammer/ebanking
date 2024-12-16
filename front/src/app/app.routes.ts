import {ExtraOptions, Routes} from '@angular/router';
import {HomeComponent} from "./pages/home/home.component";
import {LoginComponent} from "./components/UI DEFAULT/login/login.component";
import {
  EnterEmailToResetPasswordComponent
} from "./components/UI DEFAULT/enter-email-to-reset-password/enter-email-to-reset-password.component";
import {EnterNewPasswordComponent} from "./components/UI DEFAULT/enter-new-password/enter-new-password.component";
import {EnterOtpToValidateComponent} from "./components/UI DEFAULT/enter-otp-to-validate/enter-otp-to-validate.component";
import {LearnMorePageComponent} from "./pages/learn-more-page/learn-more-page.component";
import {ReviewClientComponent} from "./pages/review-client/review-client.component";
import {EditClientComponent} from "./components/UI AGENT/edit-client/edit-client.component";

export const routes: Routes = [
  {path:'',component:HomeComponent},
  {path:'login',component:LoginComponent},
  {path:'forgotPassword',component:EnterEmailToResetPasswordComponent},
  {path:'resetPassword',component:EnterNewPasswordComponent},
  {path:'otp',component:EnterOtpToValidateComponent},
  {path:'more',component:LearnMorePageComponent},
  {path:'newPassword',component:EnterNewPasswordComponent},
  {path:'review',component:ReviewClientComponent},
  {path:'edit',component:ReviewClientComponent},
  {path:'addCard',component:ReviewClientComponent},
 /* {path:"",redirectTo:"",pathMatch:"full"}*/

];

export const routerOptions:ExtraOptions={
  scrollPositionRestoration:"enabled",
  anchorScrolling:"enabled"
}
