import {Component, OnInit} from '@angular/core';
import {TranslatePipe} from "@ngx-translate/core";
import {Router, RouterLink} from "@angular/router";
import {DefaultUiAgentUiService} from "../../../services/default-ui-agent-ui.service";
import {ClientService} from "../../../services/client.service";
import {NgClass} from "@angular/common";

@Component({
  selector: 'app-consult-account',
  standalone: true,
  imports: [
    TranslatePipe,
    RouterLink,
    NgClass
  ],
  templateUrl: './consult-account.component.html',
  styleUrl: './consult-account.component.css'
})
export class ConsultAccountComponent implements OnInit{
 page:any=0;
 list:any;
 pageTotal :any;
 numbers:any;
  totalElements:any;
  constructor(private clientservice:ClientService,private rouuter:Router,private conn:DefaultUiAgentUiService) {
    this.clientservice.findclients(this.page).subscribe({
      next:(response:any)=>{
        this.list = response.content;
        this.totalElements = response.totalElements;
        this.pageTotal = response.totalPages;

        this.numbers = Array(this.pageTotal ).fill(0).map((x,i)=>i);
      }
    })
  }

  ngOnInit() {

  }

  decrementPage(page: any) {
    if(this.page-1 >=0){
      page = this.page -1;
      this.page = this.page -1;
    }
    this.clientservice.findclients(page).subscribe({
      next:(response:any)=>{
        this.list = response.content;
        console.log(this.list.content)
        this.numbers = Array(response.totalPages).fill(0).map((x,i)=>i);
      }
    })

  }

  incrementPage(page: any) {
   if(this.page+1 <=this.pageTotal && this.list.length==4){
     page = this.page +1;
     this.page = this.page +1;
     this.clientservice.findclients(page).subscribe({
       next:(response:any)=>{
         this.list = response.content;
         console.log(this.list.content)
         this.numbers = Array(response.totalPages).fill(0).map((x,i)=>i);
       }
     })
   }

  }




  changeVisibility(email: any) {
    this.clientservice.changeVisibiliy(email).subscribe({
      next:(response)=>{
        console.log(response)
        location.reload()
        this.conn.manageAccount=true;
        this.conn.openAccount=false;
        this.rouuter.navigateByUrl("");
      }
    })
  }
}
