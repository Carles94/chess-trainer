import { Component, OnInit, ViewChild } from '@angular/core';
import { BoardComponent } from '../common/component/board/board.component';
import { INITIAL_FEN } from '../common/model/constant/constant';
import { IBoard } from '../common/model/interface/board';
import { Line } from '../common/model/interface/line';
import { Move } from '../common/model/interface/move';
import { MoveEvent } from '../common/model/interface/move-event';
import { Position } from '../common/model/interface/position';

@Component({
  selector: 'app-edition-board',
  templateUrl: './edition-board.component.html',
  styleUrls: ['./edition-board.component.scss'],
})
export class EditionBoardComponent implements OnInit {
  @ViewChild(BoardComponent)
  board!: IBoard;

  private line: Line;
  private currentPosition: Position;

  constructor() {
    // Only if there are no position in the line
    this.currentPosition = {
      // FEN of the starting position
      currentPositionFEN: INITIAL_FEN,
      moveList: [],
    };
    this.line = {
      name: 'name',
      positionList: [this.currentPosition],
    };
  }

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

  public handlePieceMoved(event: MoveEvent): void {
    const move: Move = {
      positionAfter: event.fen,
      moveToSend: event.move,
      moveToShow: '',
    };
    this.currentPosition.moveList.push(move);
    // Only if position don't exist in line
    this.currentPosition = {
      currentPositionFEN: event.fen,
      moveList: [],
    };
    this.line.positionList.push(this.currentPosition);
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
