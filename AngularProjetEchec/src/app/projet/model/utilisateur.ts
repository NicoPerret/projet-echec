import { HistoriquePartie } from './historique-partie';

export class Utilisateur {
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
    private _id?: number,
    private _pseudo?: string,
    private _elo?: number,
    private _historiqueparties?: HistoriquePartie[]
  ) {}
}
