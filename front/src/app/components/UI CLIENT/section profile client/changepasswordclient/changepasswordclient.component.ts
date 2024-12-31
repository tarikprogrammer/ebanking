import { Component } from '@angular/core';
import {TranslatePipe} from "@ngx-translate/core";
import {Agent} from "../../../../models/Agent";

@Component({
  selector: 'app-changepasswordclient',
  standalone: true,
    imports: [
        TranslatePipe
    ],
  templateUrl: './changepasswordclient.component.html',
  styleUrl: './changepasswordclient.component.css'
})
export class ChangepasswordclientComponent {
  client = sessionStorage.getItem('client');
 clientData:any

  ngOnInit() {
    if(this.client){
    this.clientData = JSON.parse(this.client);
    }
  }
}
