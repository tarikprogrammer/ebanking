import { Component } from '@angular/core';
import {NavComponent} from "../../components/UI DEFAULT/nav/nav.component";
import {HearderComponent} from "../../components/UI DEFAULT/hearder/hearder.component";
import {LearnMoreForWlcComponent} from "../../components/UI DEFAULT/learn-more-for-wlc/learn-more-for-wlc.component";
import {ContactComponent} from "../../components/UI DEFAULT/contact/contact.component";
import {FooterComponent} from "../../components/UI DEFAULT/footer/footer.component";

@Component({
  selector: 'app-learn-more-page',
  standalone: true,
  imports: [
    NavComponent,
    HearderComponent,
    LearnMoreForWlcComponent,
    ContactComponent,
    FooterComponent
  ],
  templateUrl: './contactPage.component.html',
  styleUrl: './contactPage.component.css'
})
export class ContactPageComponent {

}
