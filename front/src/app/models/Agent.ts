import {Language} from "./Language";


export class Agent{
  email?:String
  phone?:string
  password?:string
  language?:Language

  constructor(email?: string, phone?: string, password?: string,language?:Language) {
    this.email = email;
    this.phone = phone;
    this.password = password;
    this.language = language;
  }

}
