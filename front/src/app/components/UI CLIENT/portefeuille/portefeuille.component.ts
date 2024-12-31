import { Component } from '@angular/core';
import {RouterLink, RouterLinkActive, RouterOutlet} from "@angular/router";

@Component({
  selector: 'app-portefeuille',
  standalone: true,
  imports: [
    RouterOutlet,
    RouterLink,
    RouterLinkActive
  ],
  templateUrl: './portefeuille.component.html',
  styleUrl: './portefeuille.component.css'
})
export class PortefeuilleComponent {

}
