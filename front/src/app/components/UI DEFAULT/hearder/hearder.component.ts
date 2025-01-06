import { Component } from '@angular/core';
import {Router, RouterLink} from "@angular/router";
import {TranslatePipe} from "@ngx-translate/core";
import {DefaultUiAgentUiService} from "../../../services/default-ui-agent-ui.service";
import {SlickCarouselModule} from "ngx-slick-carousel";

@Component({
  selector: 'app-hearder',
  standalone: true,
  imports: [
    TranslatePipe,
    RouterLink,
    SlickCarouselModule
  ],
  templateUrl: './hearder.component.html',
  styleUrl: './hearder.component.css'
})
export class HearderComponent {
  client = sessionStorage.getItem('client');
  agent = sessionStorage.getItem('agent');
  goToAgent = sessionStorage.getItem('goToAgent')
  frg:String="";
  goToClient = sessionStorage.getItem('goToClient')
  constructor(private router:Router,private comm:DefaultUiAgentUiService) {
  }


  slideConfig = {
    slidesToShow: 1,
    slidesToScroll: 1,
    arrows: true,
    dots: true,
    autoplay: true,
    autoplaySpeed: 2000
  };

  login(){
    this.router.navigateByUrl('login');
  }

  ajouterClient() {
    this.comm.addClient=true;
    this.router.navigateByUrl("");
  }
  goTo(fragment:any) {

    document.getElementById(fragment)?.scrollIntoView();
    this.frg=fragment;
  }

  transaction() {
    this.router.navigateByUrl("client/transaction")
  }
}
