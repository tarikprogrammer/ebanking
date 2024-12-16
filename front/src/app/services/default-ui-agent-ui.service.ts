import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DefaultUiAgentUiService {
  addClient:boolean=false;
  openAccount:boolean=false;
  manageAccount:boolean=false;
  editClient:boolean=false;
  reviewClient:boolean=false;
  addTemporaryCrad:boolean=false;
  constructor() { }
}
