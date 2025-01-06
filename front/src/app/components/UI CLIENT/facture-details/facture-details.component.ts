import { Component } from '@angular/core';
import {ClientService} from "../../../services/client.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-facture-details',
  standalone: true,
  imports: [],
  templateUrl: './facture-details.component.html',
  styleUrl: './facture-details.component.css'
})
export class FactureDetailsComponent {


  showtotal:boolean=false;

  constructor(public clientservice:ClientService,private router:Router) {
  }

  payer() {
    this.router.navigateByUrl("client/payerFacture/details/finish")
  }


  onRadioChange(event: Event): void {
    const isChecked = (event.target as HTMLInputElement).checked;
    if (isChecked) {
     this.showtotal = true;
    } else {
      this.showtotal = false;
    }
  }
}
