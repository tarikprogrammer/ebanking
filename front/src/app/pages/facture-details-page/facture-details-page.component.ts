import { Component } from '@angular/core';
import {FooterComponent} from "../../components/UI DEFAULT/footer/footer.component";
import {NavComponent} from "../../components/UI DEFAULT/nav/nav.component";
import {PayerFactureFormComponent} from "../../components/UI CLIENT/payer-facture-form/payer-facture-form.component";
import {FactureDetailsComponent} from "../../components/UI CLIENT/facture-details/facture-details.component";

@Component({
  selector: 'app-facture-details-page',
  standalone: true,
  imports: [
    FooterComponent,
    NavComponent,
    PayerFactureFormComponent,
    FactureDetailsComponent
  ],
  templateUrl: './facture-details-page.component.html',
  styleUrl: './facture-details-page.component.css'
})
export class FactureDetailsPageComponent {

}
