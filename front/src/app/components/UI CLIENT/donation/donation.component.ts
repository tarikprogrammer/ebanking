import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule} from "@angular/forms";
import {JsonPipe} from "@angular/common";
import {TranslatePipe} from "@ngx-translate/core";
import {ClientService} from "../../../services/client.service";
import Swal from "sweetalert2";

@Component({
  selector: 'app-donation',
  standalone: true,
    imports: [
        FormsModule,
        JsonPipe,
        ReactiveFormsModule,
        TranslatePipe
    ],
  templateUrl: './donation.component.html',
  styleUrl: './donation.component.css'
})
export class DonationComponent  implements OnInit{

  client = sessionStorage.getItem('client');
  clientData:any;
  form!:FormGroup;
  responseExist:boolean=false;

  constructor(private clientservice:ClientService,private fb:FormBuilder) {
  }

  ngOnInit() {
    if(this.client){
      this.clientData = JSON.parse(this.client);
    }


    this.form = this.fb.group({
      rib:[""],
      choixCompte:["compte bancaire"],
      price:[""],
      fname:[this.clientData.fname],
      lname:[this.clientData.lname],
      email:[this.clientData.email]
    })
  }


  giveDonation(){
    this.responseExist=true;

    this.clientservice.giveDonation(this.form).subscribe({
      next:(response:any)=>{
        this.responseExist=false;

        if(response == true){
          Swal.fire({
            title: "Donation  a été bien effectuée",
            icon: "success",
            draggable: true
          });
        }else{
          Swal.fire({
            title: "Donation n’a pas été effectuée correctement",
            icon: "error",
            draggable: true
          });
        }
      },error:(err:any)=>{
        this.responseExist=false;
      }
    })
  }

}
