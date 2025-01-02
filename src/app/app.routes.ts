import {ExtraOptions, Routes} from '@angular/router';
import {HomeComponent} from "./pages/home/home.component";
import {LoginComponent} from "./components/UI DEFAULT/login/login.component";
import {
  EnterEmailToResetPasswordComponent
} from "./components/UI DEFAULT/enter-email-to-reset-password/enter-email-to-reset-password.component";
import {EnterNewPasswordComponent} from "./components/UI DEFAULT/enter-new-password/enter-new-password.component";
import {EnterOtpToValidateComponent} from "./components/UI DEFAULT/enter-otp-to-validate/enter-otp-to-validate.component";
import {ContactPageComponent} from "./pages/learn-more-page/contactPage.component";
import {ReviewClientComponent} from "./pages/review-client/review-client.component";
import {ProfileAgentComponent} from "./pages/profile-agent/profile-agent.component";
import {OverviewComponent} from "./components/UI AGENT/section profile agent/overview/overview.component";
import {ChangePassComponent} from "./components/UI AGENT/section profile agent/change-pass/change-pass.component";
import {ReportComponent} from "./components/UI AGENT/section profile agent/report/report.component";
import {ProfileClientComponent} from "./pages/profile-client/profile-client.component";
import {
  OverviewclientComponent
} from "./components/UI CLIENT/section profile client/overviewclient/overviewclient.component";
import {
  ChangepasswordclientComponent
} from "./components/UI CLIENT/section profile client/changepasswordclient/changepasswordclient.component";
import {PaiementPageComponent} from "./pages/paiement-page/paiement-page.component";
import {PortfeuillePageComponent} from "./pages/portfeuille-page/portfeuille-page.component";
import {TousComptesComponent} from "./components/UI CLIENT/tous-comptes/tous-comptes.component";
import {CryptoComponent} from "./components/UI CLIENT/crypto/crypto.component";

export const routes: Routes = [
  {path:'',component:HomeComponent},
  {path:'login',component:LoginComponent},
  {path:'forgotPassword',component:EnterEmailToResetPasswordComponent},
  {path:'resetPassword',component:EnterNewPasswordComponent},
  {path:'otp',component:EnterOtpToValidateComponent},
  {path:'contact',component:ContactPageComponent},
  {path:'newPassword',component:EnterNewPasswordComponent},
  {path:'card',component:ReviewClientComponent},
  {path:'edit',component:ReviewClientComponent},
  {path:'addCard',component:ReviewClientComponent},
  {path:'profile',component:ProfileAgentComponent,children:[
      {path:"overview",component:OverviewComponent},
      {path:"changePasswordAgent",component:ChangePassComponent},
      {path:"reportAgent",component:ReportComponent}
    ]},
  {path:'profileClient',component:ProfileClientComponent,children:[
      {path:"overviewClient",component:OverviewclientComponent},
      {path:"changePasswordClient",component:ChangepasswordclientComponent},
    ]},

  {path:"client/paiement",component:PaiementPageComponent},
  {path:"client/portefeuille",component:PortfeuillePageComponent,children:[
      {path:"allcompte",component: TousComptesComponent},
      {path:"crypto",component: CryptoComponent},
    ]},
  {path:"**",redirectTo:"",pathMatch:"full"}

];

export const routerOptions:ExtraOptions={
  scrollPositionRestoration:"enabled",
  anchorScrolling:"enabled"
}
