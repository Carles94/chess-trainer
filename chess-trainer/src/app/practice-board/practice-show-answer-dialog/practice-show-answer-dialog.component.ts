import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-practice-show-answer-dialog',
  templateUrl: './practice-show-answer-dialog.component.html',
  styleUrls: ['./practice-show-answer-dialog.component.scss'],
})
export class PracticeShowAnswerDialogComponent implements OnInit {
  public answer: string = '';

  constructor(@Inject(MAT_DIALOG_DATA) public data: any) {}

  ngOnInit(): void {
    this.answer = this.data.answer.substring(2);
  }
}
