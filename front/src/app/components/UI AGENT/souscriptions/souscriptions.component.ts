import {Component, OnInit} from '@angular/core';
import {TranslatePipe} from "@ngx-translate/core";
import {ClientService} from "../../../services/client.service";
import {FormBuilder, FormGroup, ReactiveFormsModule} from "@angular/forms";

@Component({
  selector: 'app-souscriptions',
  standalone: true,
  imports: [
    TranslatePipe,
    ReactiveFormsModule
  ],
  templateUrl: './souscriptions.component.html',
  styleUrl: './souscriptions.component.css'
})
export class SouscriptionsComponent implements OnInit{

  client!:FormGroup;
  responseExist:boolean =false;
  constructor(private clientservice:ClientService,private fb:FormBuilder) {
  }
  ngOnInit(): void {

    this.client = this.fb.group({
      fname: [''],
      lname: [''],
      email: [''],
      phone: [''],
      accountType:['Hssab1']
    })
  }

  openAccount() {
    this.responseExist = true;

    this.clientservice.openAccountByAgent(this.client,this.client.get('accountType')?.value).subscribe(
      {
        next:(respnse:any)=>{
          this.responseExist = false;
         if(respnse == true){
           alert("account has been opened successfully")
         }
         else {
           alert("account has not  been opened , try again !!")
         }
        },
        error:(err:any)=>{
          this.responseExist = false;
          alert("account has not  been opened , try again !!")
        }
      }
    )
  }
}
