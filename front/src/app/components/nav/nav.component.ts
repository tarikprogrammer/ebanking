import { Component } from '@angular/core';
import {TranslatePipe, TranslateService} from "@ngx-translate/core";
import {Router} from "@angular/router";

@Component({
  selector: 'app-nav',
  standalone: true,
  imports: [
    TranslatePipe
  ],
  templateUrl: './nav.component.html',
  styleUrl: './nav.component.css'
})
export class NavComponent {
  isSelected:boolean = false;
  lang:string="https://upload.wikimedia.org/wikipedia/commons/thumb/8/83/Flag_of_the_United_Kingdom_%283-5%29.svg/2560px-Flag_of_the_United_Kingdom_%283-5%29.svg.png";
  constructor(private transalte :TranslateService,private rouuter:Router) {
    this.transalte.setDefaultLang('en');
  }

  selectfR(value:string) {
    if(value == 'fr'){
      this.lang ="https://img.freepik.com/vecteurs-libre/illustration-du-drapeau-france_53876-27099.jpg?semt=ais_hybrid";
    }else{
      this.lang="https://upload.wikimedia.org/wikipedia/commons/thumb/8/83/Flag_of_the_United_Kingdom_%283-5%29.svg/2560px-Flag_of_the_United_Kingdom_%283-5%29.svg.png";
    }
    this.isSelected=false;
  }

  select() {
    this.isSelected =!this.isSelected;
  }
  login(){
    this.rouuter.navigateByUrl('login');
  }
}
