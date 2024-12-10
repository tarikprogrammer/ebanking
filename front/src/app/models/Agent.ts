

export class Agent{
  email?:String
  phone?:string
  password?:string

  constructor(email?: string, phone?: string, password?: string) {
    this.email = email;
    this.phone = phone;
    this.password = password;
  }

}
