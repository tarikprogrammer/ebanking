import {Component, OnInit} from '@angular/core';
import {TranslateModule, TranslatePipe, TranslateService} from "@ngx-translate/core";
import {Router, RouterLink} from "@angular/router";
import {DefaultUiAgentUiService} from "../../../services/default-ui-agent-ui.service";

@Component({
  selector: 'app-nav',
  standalone: true,
  imports: [
    TranslatePipe,
    TranslateModule,
    RouterLink
  ],
  templateUrl: './nav.component.html',
  styleUrl: './nav.component.css'
})
export class NavComponent  implements OnInit{
  isSelected:boolean = false;
  frg:String="";
  client = sessionStorage.getItem('client');
  agent = sessionStorage.getItem('agent');
  goToAgent = sessionStorage.getItem('goToAgent')
  goToClient = sessionStorage.getItem('goToClient');
  currentLang  = localStorage.getItem('lang');
  isShowProfile:boolean =false;
  lang?:string
  constructor(private translate:TranslateService,private rouuter:Router,private conn:DefaultUiAgentUiService) {
    if (this.currentLang == 'fr'){
      this.lang="https://img.freepik.com/vecteurs-libre/illustration-du-drapeau-france_53876-27099.jpg?semt=ais_hybrid";
      this.translate.use('fr');
    }
    if (this.currentLang == 'en'){
      this.lang="https://upload.wikimedia.org/wikipedia/commons/thumb/8/83/Flag_of_the_United_Kingdom_%283-5%29.svg/2560px-Flag_of_the_United_Kingdom_%283-5%29.svg.png";
      this.translate.use('en');
    }

    console.log('constructor ')

  }

  ngOnInit() {
    console.log('init')
  }


  selectfR(value:string) {
    if(value == 'fr'){
      this.lang ="https://img.freepik.com/vecteurs-libre/illustration-du-drapeau-france_53876-27099.jpg?semt=ais_hybrid";
      this.translate.use('fr')
      localStorage.setItem('lang','fr')
    }
    if(value == 'en'){
      this.lang="https://upload.wikimedia.org/wikipedia/commons/thumb/8/83/Flag_of_the_United_Kingdom_%283-5%29.svg/2560px-Flag_of_the_United_Kingdom_%283-5%29.svg.png";
      this.translate.use('en');
      localStorage.setItem('lang','en')
    }
    this.isSelected=false;
  }

  select() {
    this.isSelected =!this.isSelected;
  }
  login(){
    this.rouuter.navigateByUrl('login');
  }

  goTo(fragment:any) {

    document.getElementById(fragment)?.scrollIntoView();
    this.frg=fragment;
  }

  showProfile() {
    this.isShowProfile=!this.isShowProfile;
  }

  setHome() {
    this.conn.addClient=false;
    this.conn.manageAccount=false;
    this.conn.openAccount=false;
  }

  goToOpenAccount() {
    this.conn.openAccount=true;
    this.conn.manageAccount=false;
    this.isShowProfile=false;
    this.rouuter.navigateByUrl("");
  }

  goToManageAccount() {
    this.conn.manageAccount=true;
    this.conn.openAccount=false;
    this.isShowProfile=false;
    this.rouuter.navigateByUrl("");
  }
  logout(){
    sessionStorage.removeItem("client");
    sessionStorage.removeItem("agent");
    sessionStorage.removeItem("goToAgent");
    sessionStorage.removeItem("goToClient");
    location.reload()
    this.rouuter.navigateByUrl("");

  }
}
