import { Component } from '@angular/core';
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
export class CryptoComponent {

}
