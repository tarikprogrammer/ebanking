import { Component } from '@angular/core';
import {FooterComponent} from "../../components/UI DEFAULT/footer/footer.component";
import {NavComponent} from "../../components/UI DEFAULT/nav/nav.component";
import {ProfileforclientComponent} from "../../components/UI CLIENT/profileforclient/profileforclient.component";
import {PayerFactureFormComponent} from "../../components/UI CLIENT/payer-facture-form/payer-facture-form.component";

@Component({
  selector: 'app-payer-facture-page',
  standalone: true,
  imports: [
    FooterComponent,
    NavComponent,
    ProfileforclientComponent,
    PayerFactureFormComponent
  ],
  templateUrl: './payer-facture-page.component.html',
  styleUrl: './payer-facture-page.component.css'
})
export class PayerFacturePageComponent {

}
