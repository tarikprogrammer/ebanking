import {Component, OnInit} from '@angular/core';
import {CurrencyPipe} from "@angular/common";

@Component({
  selector: 'app-crypto',
  standalone: true,
    imports: [
        CurrencyPipe
    ],
  templateUrl: './crypto.component.html',
  styleUrl: './crypto.component.css'
})
export class CryptoComponent implements OnInit{




  client = sessionStorage.getItem('client');
  clientData:any;


  ngOnInit(): void {
    if(this.client){
      this.clientData = JSON.parse(this.client);
    }
  }
}
