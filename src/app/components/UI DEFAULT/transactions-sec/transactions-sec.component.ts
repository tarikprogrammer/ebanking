import { Component } from '@angular/core';
import {RouterLink} from "@angular/router";
import {TranslatePipe} from "@ngx-translate/core";

@Component({
  selector: 'app-transactions-sec',
  standalone: true,
    imports: [
        RouterLink,
        TranslatePipe
    ],
  templateUrl: './transactions-sec.component.html',
  styleUrl: './transactions-sec.component.css'
})
export class TransactionsSecComponent {

}
