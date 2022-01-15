import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
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
  lineName: string = '';
  public WHITE = WHITE;
  public BLACK = BLACK;

  constructor(public dialogRef: MatDialogRef<CreateLineDialogComponent>, private fb: FormBuilder) {
    this.openingNameControl = new FormControl('', [Validators.required]);
    this.lineNameControl = new FormControl('', [Validators.required]);
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
}
