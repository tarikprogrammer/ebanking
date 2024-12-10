import { Component } from '@angular/core';
import {TranslatePipe} from "@ngx-translate/core";
import {NgStyle} from "@angular/common";

@Component({
  selector: 'app-add-new-client',
  standalone: true,
  imports: [
    TranslatePipe,
    NgStyle
  ],
  templateUrl: './add-new-client.component.html',
  styleUrl: './add-new-client.component.css'
})
export class AddNewClientComponent {
  colorTimelineStep1:string="#000"
  colorTimelineStep2:string="#000"
  colorTimelineStep3:string="#000"
  isStep2:boolean=false;
  isStep1:boolean =true;
  isStep3:boolean=false;
  step1() {
  this.colorTimelineStep1="#00d7c0"
    this.isStep2=true;
    this.isStep1=false;
  }

  backStep1() {
    this.colorTimelineStep1="#000"
    this.isStep2=false;
    this.isStep1=true
  }
  step2() {
    this.colorTimelineStep2="#00d7c0"
    this.isStep3=true;
    this.isStep2=false;
  }

  backStep2() {
    this.colorTimelineStep2 = "#000"
    this.isStep3 = false;
    this.isStep2 = true
  }





  create() {

  }
}
