import { Component } from '@angular/core';
import {TranslatePipe} from "@ngx-translate/core";
import {RouterLink, RouterLinkActive} from "@angular/router";

@Component({
  selector: 'app-agent',
  standalone: true,
  imports: [
    TranslatePipe,
    RouterLink,
    RouterLinkActive
  ],
  templateUrl: './agent.component.html',
  styleUrl: './agent.component.css'
})
export class AgentComponent {

}
