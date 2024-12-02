import { Component } from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-hearder',
  standalone: true,
  imports: [],
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
