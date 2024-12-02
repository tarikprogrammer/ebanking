import { Component } from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-enter-email-to-reset-password',
  standalone: true,
  imports: [],
  templateUrl: './enter-email-to-reset-password.component.html',
  styleUrl: './enter-email-to-reset-password.component.css'
})
export class EnterEmailToResetPasswordComponent {
   constructor(private router:Router) {
   }

   toOtpPage(){
     this.router.navigateByUrl('otp');
   }
}
