import { Component } from '@angular/core';
import {TranslatePipe} from "@ngx-translate/core";

@Component({
  selector: 'app-consult-account',
  standalone: true,
    imports: [
        TranslatePipe
    ],
  templateUrl: './consult-account.component.html',
  styleUrl: './consult-account.component.css'
})
export class ConsultAccountComponent {

}
