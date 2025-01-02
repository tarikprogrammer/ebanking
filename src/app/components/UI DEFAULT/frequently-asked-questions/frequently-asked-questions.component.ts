import { Component } from '@angular/core';
import {TranslatePipe} from "@ngx-translate/core";

@Component({
  selector: 'app-frequently-asked-questions',
  standalone: true,
  imports: [
    TranslatePipe
  ],
  templateUrl: './frequently-asked-questions.component.html',
  styleUrl: './frequently-asked-questions.component.css'
})
export class FREQUENTLYASKEDQUESTIONSComponent {

}
