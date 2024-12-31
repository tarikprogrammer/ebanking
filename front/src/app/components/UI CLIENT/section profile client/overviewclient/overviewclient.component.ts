import {Component, OnInit} from '@angular/core';
import {TranslatePipe} from "@ngx-translate/core";

@Component({
  selector: 'app-overviewclient',
  standalone: true,
  imports: [
    TranslatePipe
  ],
  templateUrl: './overviewclient.component.html',
  styleUrl: './overviewclient.component.css'
})
export class OverviewclientComponent implements OnInit{
  client = sessionStorage.getItem('client');
  clientData:any

  ngOnInit() {
    if(this.client){
      this.clientData = JSON.parse(this.client);
    }
  }
}
