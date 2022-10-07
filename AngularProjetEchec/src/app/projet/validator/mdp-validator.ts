import { AbstractControl, ValidationErrors, ValidatorFn } from '@angular/forms';

export class MdpValidator {
  public static pasIdentique(mdp: string): ValidatorFn {
    return (control: AbstractControl): ValidationErrors | null => {
      return control.value === mdp ? null : { pasIdentique: true };
    };
  }
}
