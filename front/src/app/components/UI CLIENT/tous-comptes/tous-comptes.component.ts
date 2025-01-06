import {Component, OnInit} from '@angular/core';
import {CurrencyPipe} from "@angular/common";
import {ClientService} from "../../../services/client.service";

@Component({
  selector: 'app-tous-comptes',
  standalone: true,
  imports: [
    CurrencyPipe
  ],
  templateUrl: './tous-comptes.component.html',
  styleUrl: './tous-comptes.component.css'
})
export class TousComptesComponent  implements OnInit{

  client = sessionStorage.getItem('client');
  clientData:any;
  accounts:any[]=[];
constructor(private clientservice:ClientService) {
}

  ngOnInit(): void {
    if(this.client){
      this.clientData = JSON.parse(this.client);
    }
    if(this.client){
      this.clientData = JSON.parse(this.client);
    }

    this.clientservice.getAccountNormaux(this.clientData.id).subscribe({
      next:(response:any)=>{
        this.accounts = response;
        console.log("accounts "+this.accounts)
      }
    })
  }

  showProcess(iban:string){
  this.clientservice.ibanCurrent = iban;
    this.clientservice.showActions=true;
  }

  showProcessDepot(iban: any) {
    this.clientservice.ibanCurrent = iban;
    this.clientservice.depotActions=true;

  }
}
