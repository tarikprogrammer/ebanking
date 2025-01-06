import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule} from "@angular/forms";
import {TranslatePipe} from "@ngx-translate/core";
import {ClientService} from "../../../services/client.service";
import {JsonPipe} from "@angular/common";
import Swal from "sweetalert2";

@Component({
  selector: 'app-transaction',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    TranslatePipe,
    JsonPipe
  ],
  templateUrl: './transaction.component.html',
  styleUrl: './transaction.component.css'
})
export class TransactionComponent implements OnInit{


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
      ribSender:[""],
      choixCompte:["compte bancaire"],
      amount:[""],
      fname:[this.clientData.fname],
      lname:[this.clientData.lname],
      email:[this.clientData.email],
      ribReceiver:[""]

    })
  }

  sendMoney(){
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
