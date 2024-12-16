import {Component, OnInit} from '@angular/core';
import {NavComponent} from "../../components/UI DEFAULT/nav/nav.component";
import {HearderComponent} from "../../components/UI DEFAULT/hearder/hearder.component";
import {ReviewComponent} from "../../components/UI AGENT/review/review.component";
import {EditClientComponent} from "../../components/UI AGENT/edit-client/edit-client.component";
import {DefaultUiAgentUiService} from "../../services/default-ui-agent-ui.service";
import {TemporaryCardComponent} from "../../components/UI AGENT/temporary-card/temporary-card.component";
import {Router} from "@angular/router";

@Component({
  selector: 'app-review-client',
  standalone: true,
  imports: [
    NavComponent,
    HearderComponent,
    ReviewComponent,
    EditClientComponent,
    TemporaryCardComponent
  ],
  templateUrl: './review-client.component.html',
  styleUrl: './review-client.component.css'
})
export class ReviewClientComponent implements OnInit{

  constructor(public conn:DefaultUiAgentUiService,private router:Router) {
  }

  ngOnInit(): void {
    /*if(!this.conn.addClient && !this.conn.reviewClient && !this.conn.addTemporaryCrad){
      this.router.navigateByUrl("")
    }*/
  }
}
