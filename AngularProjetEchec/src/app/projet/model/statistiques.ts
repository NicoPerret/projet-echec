import { Utilisateur } from './utilisateur';
export class Statistiques {
  public get partieJouees(): number | undefined {
    return this._partieJouees;
  }
  public set partieJouees(value: number | undefined) {
    this._partieJouees = value;
  }
  public get tauxVictoire(): number | undefined {
    return this._tauxVictoire;
  }
  public set tauxVictoire(value: number | undefined) {
    this._tauxVictoire = value;
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
    private _tauxVictoire?: number,
    private _partieJouees?: number
  ) {}
}
