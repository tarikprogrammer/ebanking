import { Component } from '@angular/core';
import {TranslatePipe} from "@ngx-translate/core";

@Component({
  selector: 'app-contact',
  standalone: true,
  imports: [
    TranslatePipe
  ],
  templateUrl: './contact.component.html',
  styleUrl: './contact.component.css'
})
export class ContactComponent {
  isContactByEmail : boolean = false;
  isContactByPhone :boolean = false;
  contactByEmail() {
    this.isContactByEmail = true;
  }

  contactByPhone(){
    this.isContactByPhone = true;
  }
}
