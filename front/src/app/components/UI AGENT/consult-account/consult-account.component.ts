import {Component, OnInit} from '@angular/core';
import {TranslatePipe} from "@ngx-translate/core";
import {RouterLink} from "@angular/router";
import {DefaultUiAgentUiService} from "../../../services/default-ui-agent-ui.service";

@Component({
  selector: 'app-consult-account',
  standalone: true,
  imports: [
    TranslatePipe,
    RouterLink
  ],
  templateUrl: './consult-account.component.html',
  styleUrl: './consult-account.component.css'
})
export class ConsultAccountComponent implements OnInit{

  constructor(private conn:DefaultUiAgentUiService) {
  }

  ngOnInit() {

  }

  reviewClientByAgent(){
    this.conn.reviewClient=true;
    this.conn.editClient=false;
    this.conn.addTemporaryCrad=false;
  }

  editClientByAgent(){
    this.conn.reviewClient=false;
    this.conn.editClient=true;
    this.conn.addTemporaryCrad=false;
  }


}
