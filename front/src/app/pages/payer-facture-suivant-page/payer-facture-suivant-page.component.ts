import { Component } from '@angular/core';
import {FooterComponent} from "../../components/UI DEFAULT/footer/footer.component";
import {NavComponent} from "../../components/UI DEFAULT/nav/nav.component";
import {ProfileforclientComponent} from "../../components/UI CLIENT/profileforclient/profileforclient.component";
import {PayerFacturePageComponent} from "../payer-facture-page/payer-facture-page.component";
import {PayerFactureComponent} from "../../components/UI CLIENT/payer-facture/payer-facture.component";

@Component({
  selector: 'app-payer-facture-suivant-page',
  standalone: true,
  imports: [
    FooterComponent,
    NavComponent,
    ProfileforclientComponent,
    PayerFacturePageComponent,
    PayerFactureComponent
  ],
  templateUrl: './payer-facture-suivant-page.component.html',
  styleUrl: './payer-facture-suivant-page.component.css'
})
export class PayerFactureSuivantPageComponent {

}
