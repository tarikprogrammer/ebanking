import {Component, OnInit} from '@angular/core';
import {TranslatePipe} from "@ngx-translate/core";
import {Agent} from "../../../../models/Agent";

@Component({
  selector: 'app-overview',
  standalone: true,
  imports: [
    TranslatePipe
  ],
  templateUrl: './overview.component.html',
  styleUrl: './overview.component.css'
})
export class OverviewComponent implements OnInit {
  agent = sessionStorage.getItem('agent');
  agentData?:Agent;

  ngOnInit() {
    this.agentData = new Agent();
    if(this.agent){
      this.agentData.name = JSON.parse(this.agent).name;
      this.agentData.email = JSON.parse(this.agent).email;
     this.agentData.phone = JSON.parse(this.agent).phone;
    }
  }
}
