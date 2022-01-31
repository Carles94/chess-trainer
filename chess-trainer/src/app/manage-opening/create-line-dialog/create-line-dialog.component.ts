import { Component, Inject, OnInit } from '@angular/core';
import {
  AbstractControl,
  FormBuilder,
  FormControl,
  FormGroup,
  ValidationErrors,
  ValidatorFn,
  Validators,
} from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { BLACK, WHITE } from 'src/app/common/model/constant/constant';
import { CreateLineBody } from 'src/app/common/model/interface/create-line-body';

@Component({
  selector: 'app-create-line-dialog',
  templateUrl: './create-line-dialog.component.html',
  styleUrls: ['./create-line-dialog.component.scss'],
})
export class CreateLineDialogComponent implements OnInit {
  form: FormGroup;
  openingNameControl: FormControl;
  lineNameControl: FormControl;
  colorControl: FormControl;
  public WHITE = WHITE;
  public BLACK = BLACK;
  private lineNames: string[];

  constructor(
    public dialogRef: MatDialogRef<CreateLineDialogComponent>,
    private fb: FormBuilder,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {
    this.lineNames = data.lineNames;
    this.openingNameControl = new FormControl('', [Validators.required]);
    this.lineNameControl = new FormControl('', [Validators.required, this.lineNameDoNotExistValidator()]);
    this.colorControl = new FormControl('', [Validators.required]);
    this.form = fb.group({
      openingName: this.openingNameControl,
      lineName: this.lineNameControl,
      color: this.colorControl,
    });
  }

  ngOnInit(): void {}

  submit(form: FormGroup) {
    let result: CreateLineBody = {
      openingName: form.value.openingName,
      lineColor: form.value.color,
      lineName: form.value.lineName,
    };
    this.dialogRef.close(result);
  }

  lineNameDoNotExistValidator(): ValidatorFn {
    // TODO see how to custom the message name
    return (control: AbstractControl): ValidationErrors | null => {
      const isForbiddenName = this.lineNames.includes(control.value);
      return isForbiddenName ? { lineNameControl: { value: control.value } } : null;
    };
  }

  get lineName(): AbstractControl | null {
    console.log(this.form.get('lineName'));
    return this.form.get('lineName');
  }
}
