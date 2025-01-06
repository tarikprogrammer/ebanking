import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {FormGroup} from "@angular/forms";


@Injectable({
  providedIn: 'root'
})
export class ClientService {

  URL_PATH = "http://localhost:8084/client-api/"
  URL_PATH_ACCOUNT="http://localhost:8082/api/accounts"

  currentFacture:any;
  showActions:boolean=false;
  depotActions:boolean=false;
  ibanCurrent:any;

  currentClient:any;
  constructor(private http:HttpClient) { }

  createClientByAgent(client:any,file:File){
    const formData = new FormData();
    formData.append("client",JSON.stringify(client));
    formData.append("file",file);
    return this.http.post(this.URL_PATH+"client/save",formData)
  }


  verifyEmail(email:any){
    return this.http.get(this.URL_PATH+`client/${email}`)
  }

  openAccountByAgent(account:FormGroup,email:string){
    return this.http.post(this.URL_PATH_ACCOUNT+ `/openAccountByAgent/${email}`,account.value);
  }

  findclients(page:any){
    return this.http.get(this.URL_PATH+`client/clients/${page}`)
  }

  changeVisibiliy(email:any){
    return this.http.get(this.URL_PATH+`client/locked/${email}`)
  }


  login(email:string,password:string){
    return this.http.post(this.URL_PATH+"client/login",{email:email,password:password})
  }

  getAccounts(clientId:any){
    return this.http.get(this.URL_PATH_ACCOUNT+`/getClientID/${clientId}`)
  }

  createTemporaryCard(form:FormGroup){

    return this.http.post(this.URL_PATH_ACCOUNT+"/createTemporaryCard",form.value)
  }


  acheterRecharge(form:FormGroup){
    return this.http.post(this.URL_PATH_ACCOUNT+"/payerRecharge",form.value)
  }

  getFacture(code:any){
    return this.http.get(this.URL_PATH_ACCOUNT+`/factures/${code}`)
  }


  payerFacture(form:FormGroup,rib:any){
    return this.http.post(this.URL_PATH_ACCOUNT+`/payerFacture/${rib}`,form.value)
  }

  giveDonation(form:FormGroup){
    return this.http.post(this.URL_PATH_ACCOUNT+"/donattion",form.value)
  }


  sendMoney(form:FormGroup){
    return this.http.post(this.URL_PATH_ACCOUNT+"/transaction",form.value)
  }


  getCrypto(clientId:any){
    return this.http.get(this.URL_PATH_ACCOUNT+`/getClientID/crypto/${clientId}`)
  }

  getAccountNormaux(clientId:any){
    return this.http.get(this.URL_PATH_ACCOUNT+`/getClientID/normal/${clientId}`)
  }
}
