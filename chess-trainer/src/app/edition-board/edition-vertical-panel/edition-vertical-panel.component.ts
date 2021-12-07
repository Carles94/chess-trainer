import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Move } from 'src/app/common/model/interface/move';

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

  public selectedMove: Move | undefined;

  constructor() {
    this.moveList = [];
  }

  ngOnInit(): void {}

  public handleDeleteMove(): void {
    if (this.selectedMove) {
      this.deleteMoveEmitter.emit(this.selectedMove);
      this.selectedMove = undefined;
    }
  }

  public handleNextMove(): void {
    if (this.selectedMove) {
      this.nextMoveEmitter.emit(this.selectedMove);
      this.selectedMove = undefined;
    }
  }

  public handleSelectMove(selectedMoveEvent: any): void {
    this.selectedMove = selectedMoveEvent.options[0].value;
  }
}
