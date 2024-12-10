import { Component } from '@angular/core';
import {TranslatePipe} from "@ngx-translate/core";

@Component({
  selector: 'app-souscriptions',
  standalone: true,
    imports: [
        TranslatePipe
    ],
  templateUrl: './souscriptions.component.html',
  styleUrl: './souscriptions.component.css'
})
export class SouscriptionsComponent {

}
