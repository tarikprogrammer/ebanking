import { Component } from '@angular/core';
import {RouterLink, RouterLinkActive, RouterOutlet} from "@angular/router";
import {TranslatePipe} from "@ngx-translate/core";

@Component({
  selector: 'app-portefeuille',
  standalone: true,
  imports: [
    RouterOutlet,
    RouterLink,
    RouterLinkActive,
    TranslatePipe
  ],
  templateUrl: './portefeuille.component.html',
  styleUrl: './portefeuille.component.css'
})
export class PortefeuilleComponent {

}
