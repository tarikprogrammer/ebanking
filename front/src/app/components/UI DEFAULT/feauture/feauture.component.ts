import { Component } from '@angular/core';
import {TranslatePipe} from "@ngx-translate/core";
import {RouterLink} from "@angular/router";

@Component({
  selector: 'app-feauture',
  standalone: true,
  imports: [
    TranslatePipe,
    RouterLink
  ],
  templateUrl: './feauture.component.html',
  styleUrl: './feauture.component.css'
})
export class FeautureComponent {

}
