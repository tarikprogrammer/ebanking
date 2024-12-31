import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {FormGroup} from "@angular/forms";


@Injectable({
  providedIn: 'root'
})
export class ClientService {

  URL_PATH = "http://localhost:8084/client-api/"
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

  openAccountByAgent(client:FormGroup,accountType:string){
    return this.http.post(this.URL_PATH+ `client/openAccount/${accountType}`,client.value);
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
}
