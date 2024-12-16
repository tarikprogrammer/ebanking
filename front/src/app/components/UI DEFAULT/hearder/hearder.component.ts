import { Component } from '@angular/core';
import {Router, RouterLink} from "@angular/router";
import {TranslatePipe} from "@ngx-translate/core";
import {DefaultUiAgentUiService} from "../../../services/default-ui-agent-ui.service";

@Component({
  selector: 'app-hearder',
  standalone: true,
  imports: [
    TranslatePipe,
    RouterLink
  ],
  templateUrl: './hearder.component.html',
  styleUrl: './hearder.component.css'
})
export class HearderComponent {
  client = sessionStorage.getItem('client');
  agent = sessionStorage.getItem('agent');
  goToAgent = sessionStorage.getItem('goToAgent')
  goToClient = sessionStorage.getItem('goToClient')
  constructor(private router:Router,private comm:DefaultUiAgentUiService) {
  }
  login(){
    this.router.navigateByUrl('login');
  }

  ajouterClient() {
    this.comm.addClient=true;
    this.router.navigateByUrl("");
  }
}
