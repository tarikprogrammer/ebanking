import { Component } from '@angular/core';
import {TranslatePipe} from "@ngx-translate/core";

@Component({
  selector: 'app-factures-lits',
  standalone: true,
    imports: [
        TranslatePipe
    ],
  templateUrl: './factures-lits.component.html',
  styleUrl: './factures-lits.component.css'
})
export class FacturesLitsComponent {

}
