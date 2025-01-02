import {Language} from "./Language";


export class Agent{
  email?:String
  phone?:string
  password?:string
  name?:string

  constructor(email?: string, phone?: string, password?: string,name?:string) {
    this.email = email;
    this.phone = phone;
    this.password = password;
    this.name = name;
  }

}
