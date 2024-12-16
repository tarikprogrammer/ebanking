import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {TranslatePipe} from "@ngx-translate/core";
import {AgentService} from "../../../services/agent.service";
import {FormBuilder, FormGroup, ReactiveFormsModule} from "@angular/forms";
import {Agent} from "../../../models/Agent";
import {NgClass} from "@angular/common";

@Component({
  selector: 'app-enter-email-to-reset-password',
  standalone: true,
  imports: [
    TranslatePipe,
    ReactiveFormsModule,
    NgClass
  ],
  templateUrl: './enter-email-to-reset-password.component.html',
  styleUrl: './enter-email-to-reset-password.component.css'
})
export class EnterEmailToResetPasswordComponent implements OnInit{
   form!:FormGroup;
   agent!:Agent;
   loginViolation:any = {};
   isLoader:boolean=false;



  ngOnInit(): void {
    this.form = this.fb.group({
      email:""
    })
  }
   constructor(private router:Router,private agentservice:AgentService,private fb:FormBuilder) {
    this.agent = new Agent();
   }

   toOtpPage(){
     this.router.navigateByUrl('otp');
   }

   sendOTP(){
    this.isLoader=true;
    this.agent.email=this.form.get('email')?.value;
       this.agentservice.sendOtp(this.agent).subscribe(
         (response:any)=>{
           if(!this.loginViolation.error){
             this.isLoader=false;
             sessionStorage.setItem("email",this.agent.email as string);
             this.router.navigateByUrl("/otp")
           }
         },(error:any)=>{
           this.loginViolation =error;
           this.isLoader=false;
           // check for this email for client
           console.log(this.loginViolation)
         }
       )
   }

   disableError(){
     this.loginViolation.error =null;
   }



}
