import { Component } from '@angular/core';
import {FooterComponent} from "../../components/UI DEFAULT/footer/footer.component";
import {NavComponent} from "../../components/UI DEFAULT/nav/nav.component";
import {ProfileComponent} from "../../components/UI AGENT/profile/profile.component";
import {PaiementComponent} from "../../components/UI CLIENT/paiement/paiement.component";

@Component({
  selector: 'app-paiement-page',
  standalone: true,
  imports: [
    FooterComponent,
    NavComponent,
    ProfileComponent,
    PaiementComponent
  ],
  templateUrl: './paiement-page.component.html',
  styleUrl: './paiement-page.component.css'
})
export class PaiementPageComponent {

}
