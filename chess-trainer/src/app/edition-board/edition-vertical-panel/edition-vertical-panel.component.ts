import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { MatSelectionListChange } from '@angular/material/list';
import { Move } from 'src/app/common/model/interface/move';
import { MoveEvent } from 'src/app/common/model/interface/move-event';

@Component({
  selector: 'app-edition-vertical-panel',
  templateUrl: './edition-vertical-panel.component.html',
  styleUrls: ['./edition-vertical-panel.component.scss'],
})
export class EditionVerticalPanelComponent implements OnInit {
  @Input()
  public moveList: Move[];
  @Output('deleteMove')
  public deleteMoveEmitter: EventEmitter<Move> = new EventEmitter();

  @Output('nextMove')
  public nextMoveEmitter: EventEmitter<Move> = new EventEmitter();

  private selectedMove!: Move;

  constructor() {
    this.moveList = [];
  }

  ngOnInit(): void {}

  public handleDeleteMove(): void {
    this.deleteMoveEmitter.emit(this.selectedMove);
  }

  public handleNextMove(): void {
    this.nextMoveEmitter.emit(this.selectedMove);
  }

  public handleSelectMove(selectedMoveEvent: any): void {
    this.selectedMove = selectedMoveEvent.options[0].value;
  }
}
