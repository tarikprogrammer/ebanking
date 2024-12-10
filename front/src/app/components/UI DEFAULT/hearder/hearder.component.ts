import { Component } from '@angular/core';
import {Router, RouterLink} from "@angular/router";
import {TranslatePipe} from "@ngx-translate/core";

@Component({
  selector: 'app-hearder',
  standalone: true,
  imports: [
    TranslatePipe,
    RouterLink
  ],
  templateUrl: './hearder.component.html',
  styleUrl: './hearder.component.css'
})
export class HearderComponent {

  constructor(private router:Router) {
  }
  login(){
    this.router.navigateByUrl('login');
  }
}
