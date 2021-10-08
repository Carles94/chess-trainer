import { Component, OnInit, ViewChild } from '@angular/core';
import { BoardComponent } from '../common/component/board/board.component';
import { IBoard } from '../common/model/interface/board';

@Component({
  selector: 'app-edition-board',
  templateUrl: './edition-board.component.html',
  styleUrls: ['./edition-board.component.scss'],
})
export class EditionBoardComponent implements OnInit {
  @ViewChild(BoardComponent)
  board!: IBoard;

  constructor() {}

  ngOnInit(): void {}

  public handleUndo(): void {
    this.board.undo();
  }

  public handleReset(): void {
    this.board.reset();
  }

  public handleReverse(): void {
    this.board.reverse();
  }

  public handleDeleteMove(): void {
    console.log('TODO handle delete move');
  }

  public handleNextMove(): void {
    console.log('TODO handle next move');
  }

  public handleUndoUntilAlternative(): void {
    console.log('TODO handle until alternative');
  }
}
