import { Component } from '@angular/core';
import {NavComponent} from "../../components/UI DEFAULT/nav/nav.component";
import {HearderComponent} from "../../components/UI DEFAULT/hearder/hearder.component";
import {LearnMoreForWlcComponent} from "../../components/UI DEFAULT/learn-more-for-wlc/learn-more-for-wlc.component";

@Component({
  selector: 'app-learn-more-page',
  standalone: true,
  imports: [
    NavComponent,
    HearderComponent,
    LearnMoreForWlcComponent
  ],
  templateUrl: './learn-more-page.component.html',
  styleUrl: './learn-more-page.component.css'
})
export class LearnMorePageComponent {

}
