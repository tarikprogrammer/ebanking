import { Component } from '@angular/core';
import {OverviewComponent} from "../section profile agent/overview/overview.component";
import {RouterLink, RouterLinkActive, RouterOutlet} from "@angular/router";
import {TranslatePipe} from "@ngx-translate/core";

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [
    OverviewComponent,
    RouterOutlet,
    RouterLink,
    RouterLinkActive,
    TranslatePipe
  ],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css'
})
export class ProfileComponent {

}
