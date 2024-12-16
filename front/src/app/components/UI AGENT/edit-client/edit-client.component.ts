import { Component } from '@angular/core';
import {RouterLink} from "@angular/router";
import {TranslatePipe} from "@ngx-translate/core";
import {DefaultUiAgentUiService} from "../../../services/default-ui-agent-ui.service";

@Component({
  selector: 'app-edit-client',
  standalone: true,
    imports: [
        RouterLink,
        TranslatePipe
    ],
  templateUrl: './edit-client.component.html',
  styleUrl: './edit-client.component.css'
})
export class EditClientComponent {
   constructor(private conn:DefaultUiAgentUiService) {
   }


   addTemporaryCardByAgent(){
     this.conn.addTemporaryCrad=true;
     this.conn.editClient=false;
   }
}
