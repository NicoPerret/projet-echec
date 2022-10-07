import { HistoriquePartie } from './historique-partie';

export class Utilisateur {
  public get mdp(): string | undefined {
    return this._mdp;
  }
  public set mdp(value: string | undefined) {
    this._mdp = value;
  }
  public get email(): string | undefined {
    return this._email;
  }
  public set email(value: string | undefined) {
    this._email = value;
  }
  public get prenom(): string | undefined {
    return this._prenom;
  }
  public set prenom(value: string | undefined) {
    this._prenom = value;
  }
  public get nom(): string | undefined {
    return this._nom;
  }
  public set nom(value: string | undefined) {
    this._nom = value;
  }
  public get historiqueparties(): HistoriquePartie[] | undefined {
    return this._historiqueparties;
  }
  public set historiqueparties(value: HistoriquePartie[] | undefined) {
    this._historiqueparties = value;
  }
  public get elo(): number | undefined {
    return this._elo;
  }
  public set elo(value: number | undefined) {
    this._elo = value;
  }
  public get pseudo(): string | undefined {
    return this._pseudo;
  }
  public set pseudo(value: string | undefined) {
    this._pseudo = value;
  }
  public get id(): number | undefined {
    return this._id;
  }
  public set id(value: number | undefined) {
    this._id = value;
  }
  constructor(
    private _pseudo?: string,

    private _nom?: string,
    private _prenom?: string,

    private _email?: string,
    private _mdp?: string,
    private _id?: number,
    private _elo?: number,
    private _historiqueparties?: HistoriquePartie[]
  ) {}
}
