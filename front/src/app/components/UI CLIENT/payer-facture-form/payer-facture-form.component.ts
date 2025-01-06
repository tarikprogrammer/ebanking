import {Component, OnInit} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {TranslatePipe} from "@ngx-translate/core";
import {ClientService} from "../../../services/client.service";
import Swal from "sweetalert2";
import {Router} from "@angular/router";

@Component({
  selector: 'app-payer-facture-form',
  standalone: true,
    imports: [
        FormsModule,
        ReactiveFormsModule,
        TranslatePipe
    ],
  templateUrl: './payer-facture-form.component.html',
  styleUrl: './payer-facture-form.component.css'
})
export class PayerFactureFormComponent {
  responseExist:boolean=false;
  constructor(private clientservice:ClientService,private router:Router) {
  }

  getFacture(){
    /*this.responseExist = true;*/
    let codeFacture = document.getElementById("code") as HTMLInputElement
    this.clientservice.getFacture(codeFacture.value).subscribe({
      next:(response)=>{
        this.responseExist=false;
        this.clientservice.currentFacture = response;
        this.router.navigateByUrl("client/payerFacture/details")

      },error:(err:any)=>{
        this.responseExist=false;
        Swal.fire({
          title: "code facture n'existe pas",
          icon: "error",
          draggable: true
        });
      }
    })
  }

}
