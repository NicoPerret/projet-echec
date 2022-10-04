import { Utilisateur } from './utilisateur';
export class HistoriquePartie {
  public get vainqueur(): Utilisateur | undefined {
    return this._vainqueur;
  }
  public set vainqueur(value: Utilisateur | undefined) {
    this._vainqueur = value;
  }
  public get date(): Date | undefined {
    return this._date;
  }
  public set date(value: Date | undefined) {
    this._date = value;
  }
  public get listCoups(): string | undefined {
    return this._listCoups;
  }
  public set listCoups(value: string | undefined) {
    this._listCoups = value;
  }
  public get messages(): string | undefined {
    return this._messages;
  }
  public set messages(value: string | undefined) {
    this._messages = value;
  }
  public get j2(): Utilisateur | undefined {
    return this._j2;
  }
  public set j2(value: Utilisateur | undefined) {
    this._j2 = value;
  }
  public get id(): number | undefined {
    return this._id;
  }
  public set id(value: number | undefined) {
    this._id = value;
  }
  public get j1(): Utilisateur | undefined {
    return this._j1;
  }
  public set j1(value: Utilisateur | undefined) {
    this._j1 = value;
  }
  constructor(
    private _id?: number,
    private _j1?: Utilisateur,
    private _j2?: Utilisateur,
    private _messages?: string,
    private _listCoups?: string,
    private _date?: Date,
    private _vainqueur?: Utilisateur
  ) {}
}
