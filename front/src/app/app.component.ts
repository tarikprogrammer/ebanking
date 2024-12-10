import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {HearderComponent} from "./components/UI DEFAULT/hearder/hearder.component";
import {TranslateModule, TranslateService} from "@ngx-translate/core";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, HearderComponent,TranslateModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'front';
  constructor(private translate: TranslateService) {

  }
}
