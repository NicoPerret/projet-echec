export class WebSocketChat {
  public get message(): string {
    return this._message;
  }
  public set message(value: string) {
    this._message = value;
  }
  public get user(): string {
    return this._user;
  }
  public set user(value: string) {
    this._user = value;
  }
  // user: string;
  // message:string;

  constructor(private _user: string, private _message: string) {
    // this.user=user;
    // this.message=message;
  }
}
