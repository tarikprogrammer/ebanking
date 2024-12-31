import { Component } from '@angular/core';
import {CurrencyPipe} from "@angular/common";

@Component({
  selector: 'app-tous-comptes',
  standalone: true,
  imports: [
    CurrencyPipe
  ],
  templateUrl: './tous-comptes.component.html',
  styleUrl: './tous-comptes.component.css'
})
export class TousComptesComponent {

}
