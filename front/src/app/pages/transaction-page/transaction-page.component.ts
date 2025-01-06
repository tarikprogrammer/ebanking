import { Component } from '@angular/core';
import {FactureDetailsComponent} from "../../components/UI CLIENT/facture-details/facture-details.component";
import {FooterComponent} from "../../components/UI DEFAULT/footer/footer.component";
import {NavComponent} from "../../components/UI DEFAULT/nav/nav.component";
import {TransactionComponent} from "../../components/UI CLIENT/transaction/transaction.component";

@Component({
  selector: 'app-transaction-page',
  standalone: true,
  imports: [
    FactureDetailsComponent,
    FooterComponent,
    NavComponent,
    TransactionComponent
  ],
  templateUrl: './transaction-page.component.html',
  styleUrl: './transaction-page.component.css'
})
export class TransactionPageComponent {

}
