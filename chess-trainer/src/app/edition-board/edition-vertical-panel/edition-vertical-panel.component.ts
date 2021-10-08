import { Component, EventEmitter, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-edition-vertical-panel',
  templateUrl: './edition-vertical-panel.component.html',
  styleUrls: ['./edition-vertical-panel.component.scss'],
})
export class EditionVerticalPanelComponent implements OnInit {
  @Output('deleteMove')
  public deleteMoveEmitter: EventEmitter<any> = new EventEmitter();

  @Output('nextMove')
  public nextMoveEmitter: EventEmitter<any> = new EventEmitter();

  constructor() {}

  ngOnInit(): void {}

  public handleDeleteMove(): void {
    this.deleteMoveEmitter.emit();
  }

  public handleNextMove(): void {
    this.nextMoveEmitter.emit();
  }
}
