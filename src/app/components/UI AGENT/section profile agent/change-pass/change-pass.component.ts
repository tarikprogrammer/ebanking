import {Component, OnInit} from '@angular/core';
import {TranslatePipe} from "@ngx-translate/core";
import {AgentService} from "../../../../services/agent.service";
import {FormBuilder, FormGroup, ReactiveFormsModule} from "@angular/forms";

@Component({
  selector: 'app-change-pass',
  standalone: true,
  imports: [
    TranslatePipe,
    ReactiveFormsModule
  ],
  templateUrl: './change-pass.component.html',
  styleUrl: './change-pass.component.css'
})
export class ChangePassComponent implements OnInit{

  agent = sessionStorage.getItem('agent');
 showSuccess:boolean =false;
  showError:boolean =false;
  form!:FormGroup;
  constructor(private agentService:AgentService,private fb:FormBuilder) {
  }

  ngOnInit() {
    this.form = this.fb.group({
      email:JSON.parse(this.agent? this.agent:'').email,
      password:'',
      confirmPassword:''
    })
  }

  updatePasswordAgent(){
    this.agentService.updatePasswordforAgent(this.form).subscribe({
      next:(response)=>{
        // updated
        this.form = this.fb.group({
          password:'',
          confirmPassword:''
        })
        this.showSuccess =true;
        setTimeout(()=>{
          this.showSuccess =false;
        },1500)

      },
      error:(err:any)=>{
        // error
        this.form = this.fb.group({
          password:'',
          confirmPassword:''
        })
        this.showError =true;
        setTimeout(()=>{
          this.showError =false;
        },1500)
        console.log(err)
      }
    })
  }

}
