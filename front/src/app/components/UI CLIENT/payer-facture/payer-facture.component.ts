import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule} from "@angular/forms";
import {TranslatePipe} from "@ngx-translate/core";
import {ClientService} from "../../../services/client.service";
import {JsonPipe} from "@angular/common";
import Swal from "sweetalert2";

@Component({
  selector: 'app-payer-facture',
  standalone: true,
  imports: [
    FormsModule,
    ReactiveFormsModule,
    TranslatePipe,
    JsonPipe
  ],
  templateUrl: './payer-facture.component.html',
  styleUrl: './payer-facture.component.css'
})
export class PayerFactureComponent implements OnInit{
  client = sessionStorage.getItem('client');
  clientData:any;
  form!:FormGroup;
  responseExist:boolean=false;

  constructor(private clientservice:ClientService,private fb:FormBuilder) {
  }
  ngOnInit(): void {


    if(this.client){
      this.clientData = JSON.parse(this.client);
    }

    this.form = this.fb.group({
      rib:[""],
      choixCompte:"compte bancaire",
      price:[this.clientservice.currentFacture.price],
      factureCode:[this.clientservice.currentFacture.factureCode]
    })
  }


  payer(){
    this.responseExist=true;
    this.clientservice.payerFacture(this.form,this.form.get('rib')?.value).subscribe({
      next:(response)=>{
        this.responseExist=false;
        if(response == true){
          Swal.fire({
            title: "La facture  été payée",
            icon: "success",
            draggable: true
          });
        }else{
          Swal.fire({
            title: "La facture n’a pas été payée",
            icon: "error",
            draggable: true
          });
        }
      },error:(err)=>{
        this.responseExist=false;
        Swal.fire({
          title: "La facture n’a pas été payée",
          icon: "error",
          draggable: true
        });
      }
    })
  }

}
