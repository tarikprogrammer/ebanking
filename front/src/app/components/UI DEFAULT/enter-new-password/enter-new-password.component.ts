import { Component } from '@angular/core';
import {TranslatePipe} from "@ngx-translate/core";

@Component({
  selector: 'app-enter-new-password',
  standalone: true,
  imports: [
    TranslatePipe
  ],
  templateUrl: './enter-new-password.component.html',
  styleUrl: './enter-new-password.component.css'
})
export class EnterNewPasswordComponent {

}
