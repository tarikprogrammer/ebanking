import { Component } from '@angular/core';
import {HearderComponent} from "../../components/UI DEFAULT/hearder/hearder.component";
import {FacturesLitsComponent} from "../../components/UI DEFAULT/factures-lits/factures-lits.component";
import {NavComponent} from "../../components/UI DEFAULT/nav/nav.component";
import {FeautureComponent} from "../../components/UI DEFAULT/feauture/feauture.component";
import {AgentComponent} from "../../components/UI DEFAULT/agent/agent.component";
import {
  FREQUENTLYASKEDQUESTIONSComponent
} from "../../components/UI DEFAULT/frequently-asked-questions/frequently-asked-questions.component";
import {FooterComponent} from "../../components/UI DEFAULT/footer/footer.component";
import {AddNewClientComponent} from "../../components/UI AGENT/add-new-client/add-new-client.component";
import {SouscriptionsComponent} from "../../components/UI AGENT/souscriptions/souscriptions.component";
import {ConsultAccountComponent} from "../../components/UI AGENT/consult-account/consult-account.component";
import {DefaultUiAgentUiService} from "../../services/default-ui-agent-ui.service";
import {LearnMoreForWlcComponent} from "../../components/UI DEFAULT/learn-more-for-wlc/learn-more-for-wlc.component";
import {TransactionsSecComponent} from "../../components/UI DEFAULT/transactions-sec/transactions-sec.component";

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
    FooterComponent,
    AddNewClientComponent,
    SouscriptionsComponent,
    ConsultAccountComponent,
    LearnMoreForWlcComponent,
    TransactionsSecComponent
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
  constructor(public comm:DefaultUiAgentUiService) {
  }

    protected readonly Date = Date;
}
