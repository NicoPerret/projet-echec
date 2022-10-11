import { Utilisateur } from './utilisateur';
import { Probleme } from 'src/app/projet/model/probleme';
export class ResultatProbleme {
  public get date(): Date | undefined {
    return this._date;
  }
  public set date(value: Date | undefined) {
    this._date = value;
  }
  public get probleme(): Probleme | undefined{
    return this._probleme;
  }
  public set probleme(value: Probleme | undefined) {
    this._probleme = value;
  }
  public get utilisateur(): Utilisateur | undefined {
    return this._utilisateur;
  }
  public set utilisateur(value: Utilisateur | undefined) {
    this._utilisateur = value;
  }
  public get id(): number | undefined {
    return this._id;
  }
  public set id(value: number | undefined) {
    this._id = value;
  }
  constructor(
    private _id?: number,
    private _utilisateur?: Utilisateur,
    private _probleme?: Probleme,
    private _date?: Date,
  ) {}
}
