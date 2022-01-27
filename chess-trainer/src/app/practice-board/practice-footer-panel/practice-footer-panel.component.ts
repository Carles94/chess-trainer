import { Component, EventEmitter, OnInit, Output } from '@angular/core';

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
}
