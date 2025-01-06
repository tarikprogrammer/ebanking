import {Component, OnInit} from '@angular/core';
import {TranslatePipe} from "@ngx-translate/core";
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule} from "@angular/forms";
import {JsonPipe} from "@angular/common";
import {ClientService} from "../../../services/client.service";
import Swal from "sweetalert2";

@Component({
  selector: 'app-recharge-paiement',
  standalone: true,
  imports: [
    TranslatePipe,
    ReactiveFormsModule,
    FormsModule,
    JsonPipe
  ],
  templateUrl: './recharge-paiement.component.html',
  styleUrl: './recharge-paiement.component.css'
})
export class RechargePaiementComponent  implements OnInit{
   client = sessionStorage.getItem('client');
   clientData:any;
   form!:FormGroup;
   responseExist:boolean=false;
   constructor(private fb:FormBuilder,private clientservice:ClientService) {
   }

  ngOnInit(): void {

     if(this.client){
       this.clientData = JSON.parse(this.client);
     }


    this.form = this.fb.group({
      montant:"",
      choixCompte:"compte bancaire",
      rib:[""]
    })

  }



  accheteRecharge(){
     this.responseExist = true;
     this.clientservice.acheterRecharge(this.form).subscribe({
       next:(response)=>{
         this.responseExist =false;

         if(response == true){
           Swal.fire({
             title: "Recharge mobile a été bien effectuée",
             icon: "success",
             draggable: true
           });
         }else{
           Swal.fire({
             title: "Recharge mobile n’a pas été effectuée correctement",
             icon: "error",
             draggable: true
           });
         }

       },error:(err)=>{
         this.responseExist =false;
         console.log(err)
       }
     })
  }
}
