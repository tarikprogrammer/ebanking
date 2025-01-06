import { Component } from '@angular/core';
import {TranslatePipe} from "@ngx-translate/core";
import {Router, RouterLink} from "@angular/router";

@Component({
  selector: 'app-paiement',
  standalone: true,
  imports: [
    TranslatePipe,
    RouterLink
  ],
  templateUrl: './paiement.component.html',
  styleUrl: './paiement.component.css'
})
export class PaiementComponent {



  constructor(private router:Router) {
  }

  goToRecharge() {
    this.router.navigateByUrl("client/recharges")
  }

  goToFacture() {
    this.router.navigateByUrl("client/payerFacture")
  }

  goToDonation() {
    this.router.navigateByUrl("client/helpOthers")
  }
}
