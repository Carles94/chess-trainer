import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Position } from 'src/app/common/model/interface/position';

@Component({
  selector: 'app-practice-footer-panel',
  templateUrl: './practice-footer-panel.component.html',
  styleUrls: ['./practice-footer-panel.component.scss'],
})
export class PracticeFooterPanelComponent implements OnInit {
  @Output('showAnswer')
  public showAnswerEmitter: EventEmitter<any> = new EventEmitter();
  @Output('nextVariant')
  public nextVariantEmitter: EventEmitter<any> = new EventEmitter();
  @Output('reverse')
  public reverseEmitter: EventEmitter<any> = new EventEmitter();

  @Input()
  public isVariantEnded: boolean = false;

  @Input()
  public isFailedOneAttempt: boolean = false;

  @Input()
  set position(value: Position) {
    this._position = value;
    if (value) {
      this.correctPercentage = this.calculatePercentage();
    }
  }

  public correctPercentage: number = 0;

  private _position!: Position;

  constructor() {}

  ngOnInit(): void {}

  public handleShowAnswer() {
    this.showAnswerEmitter.emit();
  }

  public handleNextVariant() {
    this.nextVariantEmitter.emit();
  }

  public handleReverse() {
    this.reverseEmitter.emit();
  }

  public calculatePercentage(): number {
    if (this._position.totalAnswers > 0) {
      return Math.round((this._position.correctAnswers / this._position.totalAnswers) * 100);
    }
    return 0;
  }
}
