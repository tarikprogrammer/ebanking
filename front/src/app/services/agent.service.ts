import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Agent} from "../models/Agent";
import {formatDate} from "@angular/common";


@Injectable({
  providedIn: 'root'
})
export class AgentService {
  URL_API ="http://localhost:8081/agent-api"
  constructor(private http:HttpClient) { }

    login(agent:Agent){
      return this.http.post(this.URL_API+"/login",{email:agent.email,password:agent.password});
    }

    sendOtp(agent:Agent){
    return this.http.post(this.URL_API+"/sendLink",{email:agent.email});
    }

    verifyOtp(otp:string,email:string){
       return this.http.post(this.URL_API+"/sendOtp",{email:email,otp:otp})
    }
}
