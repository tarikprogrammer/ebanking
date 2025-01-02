import {Component, OnInit} from '@angular/core';
import {TranslatePipe} from "@ngx-translate/core";
import {NgStyle} from "@angular/common";
import {FormBuilder, FormGroup, ReactiveFormsModule} from "@angular/forms";
import {ClientService} from "../../../services/client.service";
import {error} from "jquery";

@Component({
  selector: 'app-add-new-client',
  standalone: true,
  imports: [
    TranslatePipe,
    NgStyle,
    ReactiveFormsModule
  ],
  templateUrl: './add-new-client.component.html',
  styleUrl: './add-new-client.component.css'
})
export class AddNewClientComponent implements OnInit{
  colorTimelineStep1:string="#000"
  colorTimelineStep2:string="#000"
  colorTimelineStep3:string="#000"
  isStep2:boolean=false;
  isStep1:boolean =true;
  isStep3:boolean=false;

  formclientStep1!:FormGroup;
  formclientStep2!:FormGroup;
  formclientStep3!:FormGroup;
  formclientGlobal!:FormGroup;
  fileSelected!:File;
  alertMessageForEmail?:string;
  responseExist:boolean = false;


  constructor(private fb:FormBuilder,private clientservice:ClientService) {
  }
  ngOnInit() {
    this.formclientStep1 = this.fb.group({
      fname: [''],
      lname: [''],
      email: [''],
      confirmEmail: [''],
    });
    this.formclientStep2 = this.fb.group({
      phone: [''],
      address: [''],
      IdentityDocument: ['CIN'],
      NIdentityDocument: [''],
    });
    this.formclientStep3 = this.fb.group({
      TaxIdentificationNumber: ['undefined'],
      dateBirth: [''],
      CommercialRegisterRegistrationNumber: ['undefined'],

    });


  }

  step1() {
    this.clientservice.verifyEmail(this.formclientStep1.get('email')?.value).subscribe({
      next:(response:any)=>{
        this.responseExist = false;
        if (response == true){
          this.alertMessageForEmail = "email exist"
        }else{
          this.colorTimelineStep1="#00d7c0"
          this.isStep2=true;
          this.isStep1=false;
        }
      }
    }) ;
  }

  backStep1() {
    this.colorTimelineStep1="#000"
    this.isStep2=false;
    this.isStep1=true
  }
  step2() {
    this.colorTimelineStep2="#00d7c0"
    this.isStep3=true;
    this.isStep2=false;
  }

  backStep2() {
    this.colorTimelineStep2 = "#000"
    this.isStep3 = false;
    this.isStep2 = true
  }




  OnSelectedFile(event: any) {
    this.fileSelected = event.target.files[0];
  }


  create() {
    this.formclientGlobal = this.fb.group({
      ...this.formclientStep1.controls,
      ...this.formclientStep2.controls,
      ...this.formclientStep3.controls
    })
    this.responseExist = true;
    this.clientservice.createClientByAgent(this.formclientGlobal.value,this.fileSelected).subscribe(
      {
        next:(response:any)=>{
          if(response == true){
            this.responseExist =false;
            // le client a ete cree avec succes
            alert("client created ")
          }
        },
      error :(err:any)=>{
        alert("client not created ")
          console.log(err);
      }
      }
    )
  }

  changeError() {
    this.alertMessageForEmail =""
  }
}
