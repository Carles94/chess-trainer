import { Component, OnInit, ViewChild } from '@angular/core';
import { BoardComponent } from '../common/component/board/board.component';
import { Line } from '../common/model/class/line';
import { INITIAL_FEN } from '../common/model/constant/constant';
import { IBoard } from '../common/model/interface/board';
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
  public currentPosition: Position;

  constructor() {
    // Only if there are no position in the line
    this.currentPosition = {
      // FEN of the starting position
      positionFEN: INITIAL_FEN,
      previousFENPosition: INITIAL_FEN,
      moveList: [],
    };
    this.line = new Line('name', [this.currentPosition]);
  }

  ngOnInit(): void {}

  public handleUndo(): void {
    this.currentPosition = this.line.getPositionByFEN(this.currentPosition.previousFENPosition);
    this.board.undo();
  }

  public handleReset(): void {
    this.currentPosition = this.line.getPositionByFEN(INITIAL_FEN);
    this.board.reset();
  }

  public handleReverse(): void {
    this.board.reverse();
  }

  public handlePieceMoved(event: MoveEvent): void {
    const currentMove: Move = {
      positionFENAfter: event.fen,
      moveToSend: event.move,
      moveToShow: '',
    };
    if (!this.currentPosition.moveList.some((move) => move.moveToSend === currentMove.moveToSend)) {
      this.currentPosition.moveList.push(currentMove);
    }
    if (this.line.existsPosition(event.fen)) {
      this.currentPosition = this.line.getPositionByFEN(event.fen);
    } else {
      this.currentPosition = {
        positionFEN: event.fen,
        previousFENPosition: this.currentPosition.positionFEN,
        moveList: [],
      };
      this.line.positionList.push(this.currentPosition);
    }
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
