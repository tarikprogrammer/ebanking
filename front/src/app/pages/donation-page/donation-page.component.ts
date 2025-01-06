import { Component } from '@angular/core';
import {FactureDetailsComponent} from "../../components/UI CLIENT/facture-details/facture-details.component";
import {FooterComponent} from "../../components/UI DEFAULT/footer/footer.component";
import {NavComponent} from "../../components/UI DEFAULT/nav/nav.component";
import {DonationComponent} from "../../components/UI CLIENT/donation/donation.component";

@Component({
  selector: 'app-donation-page',
  standalone: true,
  imports: [
    FactureDetailsComponent,
    FooterComponent,
    NavComponent,
    DonationComponent
  ],
  templateUrl: './donation-page.component.html',
  styleUrl: './donation-page.component.css'
})
export class DonationPageComponent {

}
