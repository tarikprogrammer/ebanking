import { Component } from '@angular/core';
import {FooterComponent} from "../../components/UI DEFAULT/footer/footer.component";
import {NavComponent} from "../../components/UI DEFAULT/nav/nav.component";
import {ProfileforclientComponent} from "../../components/UI CLIENT/profileforclient/profileforclient.component";
import {RechargePaiementComponent} from "../../components/UI CLIENT/recharge-paiement/recharge-paiement.component";

@Component({
  selector: 'app-recharge-paiement-page',
  standalone: true,
  imports: [
    FooterComponent,
    NavComponent,
    ProfileforclientComponent,
    RechargePaiementComponent
  ],
  templateUrl: './recharge-paiement-page.component.html',
  styleUrl: './recharge-paiement-page.component.css'
})
export class RechargePaiementPageComponent {

}
