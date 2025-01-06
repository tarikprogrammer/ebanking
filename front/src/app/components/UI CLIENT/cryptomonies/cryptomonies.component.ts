import {Component, OnInit} from '@angular/core';
import {CurrencyPipe, JsonPipe} from "@angular/common";
import {ClientService} from "../../../services/client.service";

@Component({
  selector: 'app-cryptomonies',
  standalone: true,
  imports: [
    CurrencyPipe,
    JsonPipe
  ],
  templateUrl: './cryptomonies.component.html',
  styleUrl: './cryptomonies.component.css'
})
export class CryptomoniesComponent implements OnInit{
  client = sessionStorage.getItem('client');
  clientData:any;
  crypto:any[]=[];


  constructor(private clientService:ClientService ) {
    if(this.client){
      this.clientData = JSON.parse(this.client);
    }

    this.clientService.getCrypto(this.clientData.id).subscribe({
      next:(response:any)=>{
       this.crypto = response;
      }
    })
  }

  ngOnInit() {

  }

  showProcessDepot(iban: any) {
    this.clientService.ibanCurrent=iban;
    this.clientService.depotActions=true;

  }

  vendreProcess(iban: any) {
    this.clientService.ibanCurrent=iban;
    this.clientService.showActions=true;

  }
}
