import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {HearderComponent} from "./components/hearder/hearder.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, HearderComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'front';
}
