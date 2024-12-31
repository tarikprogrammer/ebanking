import { Component } from '@angular/core';
import {FooterComponent} from "../../components/UI DEFAULT/footer/footer.component";
import {NavComponent} from "../../components/UI DEFAULT/nav/nav.component";
import {TemporaryCardComponent} from "../../components/UI AGENT/temporary-card/temporary-card.component";
import {ProfileComponent} from "../../components/UI AGENT/profile/profile.component";

@Component({
  selector: 'app-profile-agent',
  standalone: true,
  imports: [
    FooterComponent,
    NavComponent,
    TemporaryCardComponent,
    ProfileComponent,
    ProfileComponent
  ],
  templateUrl: './profile-agent.component.html',
  styleUrl: './profile-agent.component.css'
})
export class ProfileAgentComponent {

}
