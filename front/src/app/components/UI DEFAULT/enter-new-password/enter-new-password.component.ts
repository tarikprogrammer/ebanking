import {Component, OnInit} from '@angular/core';
import {TranslatePipe} from "@ngx-translate/core";
import {FormBuilder, FormGroup, ReactiveFormsModule} from "@angular/forms";
import {HttpClient} from "@angular/common/http";
import {AgentService} from "../../../services/agent.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-enter-new-password',
  standalone: true,
  imports: [
    TranslatePipe,
    ReactiveFormsModule
  ],
  templateUrl: './enter-new-password.component.html',
  styleUrl: './enter-new-password.component.css'
})
export class EnterNewPasswordComponent  implements OnInit{
  email = sessionStorage.getItem('email');
  form!:FormGroup;

  constructor(private fb:FormBuilder,private agentService:AgentService,private router:Router) {
  }

  ngOnInit(): void {
    this.form = this.fb.group({
      email:this.email,
      password:'',
      confirmPassword:''
    })
  }

  updatePassword(){

    // if agent reset password
    this.agentService.updatePasswordforAgent(this.form).subscribe({
      next:(response:any)=>{
        console.log("reset password for agent "+response)
        // go to home page agent
        sessionStorage.setItem("goToAgent",'true');
        sessionStorage.setItem("agent",JSON.stringify(response));
        console.log(response)
        this.router.navigateByUrl("");
      },
      error:(err:any)=>{
        // show erros logs

        console.log(err)
      }
    })

    // if client reset password
  }
}
