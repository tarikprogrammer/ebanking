import {Component, OnInit} from '@angular/core';
import {TranslatePipe} from "@ngx-translate/core";
import {FormBuilder, FormGroup, ReactiveFormsModule} from "@angular/forms";
import {ClientService} from "../../../services/client.service";
import Swal from 'sweetalert2';

@Component({
  selector: 'app-temporary-card',
  standalone: true,
  imports: [
    TranslatePipe,
    ReactiveFormsModule
  ],
  templateUrl: './temporary-card.component.html',
  styleUrl: './temporary-card.component.css'
})
export class TemporaryCardComponent implements OnInit{


  tempraryCard!:FormGroup;
  responseExist:boolean=false;

  constructor(private fb:FormBuilder,public clientservice:ClientService) {
  }

  ngOnInit(): void {
    this.tempraryCard = this.fb.group({
      fname:[""],
      lname:[""],
      email:[""],
      choixCompte:[""],
      solde:[""],
      expireAt:[""],
      phone:['+'+this.clientservice.currentClient.phone]

    })
  }



  openTemporaryCard(){
     this.responseExist = true;
    this.clientservice.createTemporaryCard(this.tempraryCard).subscribe({
      next:(response:any)=>{
        this.responseExist=false;

        if(response == true){
          Swal.fire({
            title: "Temporary Card has been created successfully",
            icon: "success",
            draggable: true
          });
        }else{
          Swal.fire({
            title: "Temporary Card has not  been created ",
            icon: "error",
            draggable: true
          });
        }
      }
    })
  }

}
