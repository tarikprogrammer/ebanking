import { Component } from '@angular/core';
import {TranslatePipe} from "@ngx-translate/core";

@Component({
  selector: 'app-temporary-card',
  standalone: true,
    imports: [
        TranslatePipe
    ],
  templateUrl: './temporary-card.component.html',
  styleUrl: './temporary-card.component.css'
})
export class TemporaryCardComponent {

}
