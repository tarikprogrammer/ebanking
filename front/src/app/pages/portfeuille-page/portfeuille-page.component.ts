import { Component } from '@angular/core';
import {FooterComponent} from "../../components/UI DEFAULT/footer/footer.component";
import {NavComponent} from "../../components/UI DEFAULT/nav/nav.component";
import {ProfileforclientComponent} from "../../components/UI CLIENT/profileforclient/profileforclient.component";
import {PortefeuilleComponent} from "../../components/UI CLIENT/portefeuille/portefeuille.component";
import {RetraitComponent} from "../../components/UI CLIENT/retrait/retrait.component";
import {ClientService} from "../../services/client.service";
import {DepotComponent} from "../../components/UI CLIENT/depot/depot.component";

@Component({
  selector: 'app-portfeuille-page',
  standalone: true,
  imports: [
    FooterComponent,
    NavComponent,
    ProfileforclientComponent,
    PortefeuilleComponent,
    RetraitComponent,
    DepotComponent
  ],
  templateUrl: './portfeuille-page.component.html',
  styleUrl: './portfeuille-page.component.css'
})
export class PortfeuillePageComponent {

  constructor(public clientservice:ClientService) {
  }

}
