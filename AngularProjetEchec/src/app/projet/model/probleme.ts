export class Probleme {
  public get difficulte(): number | undefined {
    return this._difficulte;
  }
  public set difficulte(value: number | undefined) {
    this._difficulte = value;
  }
  public get traitAuBlanc(): boolean | undefined {
    return this._traitAuBlanc;
  }
  public set traitAuBlanc(value: boolean | undefined) {
    this._traitAuBlanc = value;
  }
  public get listeDeplacements(): string | undefined {
    return this._listeDeplacements;
  }
  public set listeDeplacements(value: string | undefined) {
    this._listeDeplacements = value;
  }
  public get fenDepart(): string | undefined {
    return this._fenDepart;
  }
  public set fenDepart(value: string | undefined) {
    this._fenDepart = value;
  }
  public get id(): number | undefined {
    return this._id;
  }
  public set id(value: number | undefined) {
    this._id = value;
  }
  constructor(
    private _id?: number,
    private _fenDepart?: string,
    private _listeDeplacements?: string,
    private _traitAuBlanc?: boolean,
    private _difficulte?: number
  ) {}
}
