import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule} from "@angular/forms";
import {AgentService} from "../../../services/agent.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-enter-otp-to-validate',
  standalone: true,
  imports: [
    ReactiveFormsModule
  ],
  templateUrl: './enter-otp-to-validate.component.html',
  styleUrl: './enter-otp-to-validate.component.css'
})
export class EnterOtpToValidateComponent implements OnInit{
  email:any=sessionStorage.getItem('email');
  formOtp!:FormGroup;
  otp:string="";
  constructor(private agentService:AgentService,private fb:FormBuilder,private router:Router) {
  }
  ngOnInit(): void {
   this.formOtp = this.fb.group({
     number1:"",
     number2:"",
     number3:"",
     number4:"",

   })
  }

  verifyOtp() {
    this.otp = this.formOtp.get('number1')?.value + this.formOtp.get('number2')?.value +this.formOtp.get('number3')?.value +this.formOtp.get('number4')?.value;
    this.agentService.verifyOtp(this.otp,this.email).subscribe(
      (response)=>{
        if (response ==true){
          this.router.navigateByUrl("/newPassword")
        }
      },(error)=>{
        console.log(error)
      }
    )

  }
}
