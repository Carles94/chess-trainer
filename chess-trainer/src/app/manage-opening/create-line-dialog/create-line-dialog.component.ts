import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-create-line-dialog',
  templateUrl: './create-line-dialog.component.html',
  styleUrls: ['./create-line-dialog.component.scss'],
})
export class CreateLineDialogComponent implements OnInit {
  constructor(public dialogRef: MatDialogRef<CreateLineDialogComponent>) {}

  ngOnInit(): void {}
}
