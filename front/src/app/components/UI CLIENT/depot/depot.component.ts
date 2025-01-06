import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule} from "@angular/forms";
import {TranslatePipe} from "@ngx-translate/core";
import {ClientService} from "../../../services/client.service";
import {JsonPipe} from "@angular/common";
import Swal from "sweetalert2";

@Component({
  selector: 'app-depot',
  standalone: true,
  imports: [
    FormsModule,
    ReactiveFormsModule,
    TranslatePipe,
    JsonPipe
  ],
  templateUrl: './depot.component.html',
  styleUrl: './depot.component.css'
})
export class DepotComponent implements OnInit{

  client = sessionStorage.getItem('client');
  clientData:any;
  form!:FormGroup;
  responseExist:boolean=false;


  constructor(private fb:FormBuilder,private clientservice:ClientService) {
  }

  ngOnInit() {

    if(this.client){
      this.clientData = JSON.parse(this.client);
    }
    // rib sender
    this.form = this.fb.group({
      ribSender:[],
      choixCompte:["compte bancaire"],
      amount:[""],
      fname:[this.clientData.fname],
      lname:[this.clientData.lname],
      email:[this.clientData.email],
      ribReceiver:[this.clientservice.ibanCurrent]
    })

  }


  dismissActions(){
    this.clientservice.showActions=false;
    this.clientservice.depotActions=false;
  }


  retraitMoney() {
    this.responseExist =true;


    this.clientservice.sendMoney(this.form).subscribe({
      next:(response:any)=>{
        this.responseExist=false;
        if(response == true){
          Swal.fire({
            title: "Transaction  a été bien effectuée",
            icon: "success",
            draggable: true
          });
          location.reload()
        }else{
          Swal.fire({
            title: "Transaction n’a pas été effectuée correctement",
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
