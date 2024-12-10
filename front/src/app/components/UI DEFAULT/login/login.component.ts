import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {TranslatePipe} from "@ngx-translate/core";
import {AgentService} from "../../../services/agent.service";
import {FormBuilder, FormGroup, ReactiveFormsModule} from "@angular/forms";
import {Agent} from "../../../models/Agent";
import {NgClass} from "@angular/common";

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    TranslatePipe,
    ReactiveFormsModule,
    NgClass
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit{
   form!:FormGroup;
    agent!:Agent  ;
    loginErrors?:any={};
    loginSuccess?:any={};
    error?:any = {};

  ngOnInit(): void {
    this.form =this.fb.group({
      email:"",
      password:""
    })
  }

   constructor(private router:Router,private agentService:AgentService,private fb:FormBuilder) {
     this.agent = new Agent();
   }

  forgotPass(){
     this.router.navigateByUrl('forgotPassword')
   }

   login(){
    this.agent.email=this.form.get("email")?.value;
    this.agent.password=this.form.get("password")?.value;

    this.agentService.login(this.agent).subscribe(
      (response:any)=>{
        this.loginSuccess= response;
        console.log(this.loginSuccess);
      },(error:any)=>{
        this.loginErrors = error?.error?.errors || {}
        this.error = error;
        console.log(this.error)
      }

    )

   }


  disableErrorsPassword() {
    this.loginErrors.password=null;
    this.error.error = null;
  }
  disableErrorsEmail() {
    this.loginErrors['email']=null;
    this.error.error = null;
  }
}
