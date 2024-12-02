import { Component } from '@angular/core';
import {HearderComponent} from "../../components/hearder/hearder.component";
import {FacturesLitsComponent} from "../../components/factures-lits/factures-lits.component";
import {NavComponent} from "../../components/nav/nav.component";
import {FeautureComponent} from "../../components/feauture/feauture.component";
import {AgentComponent} from "../../components/agent/agent.component";
import {
  FREQUENTLYASKEDQUESTIONSComponent
} from "../../components/frequently-asked-questions/frequently-asked-questions.component";
import {FooterComponent} from "../../components/footer/footer.component";

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    HearderComponent,
    FacturesLitsComponent,
    NavComponent,
    FeautureComponent,
    AgentComponent,
    FREQUENTLYASKEDQUESTIONSComponent,
    FooterComponent
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

}
