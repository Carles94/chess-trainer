import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { BoardComponent } from '../common/component/board/board.component';
import { ReplaceMoveConfirmationDialogComponent } from '../common/component/replace-move-confirmation-dialog/replace-move-confirmation-dialog.component';
import { Line } from '../common/model/class/line';
import { INITIAL_FEN, WHITE } from '../common/model/constant/constant';
import { IBoard } from '../common/model/interface/board';
import { Move } from '../common/model/interface/move';
import { MoveEvent } from '../common/model/interface/move-event';
import { Position } from '../common/model/interface/position';
import { MovePipe } from '../common/pipe/move.pipe';

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

  constructor(private readonly movePipe: MovePipe, public dialog: MatDialog) {
    // Only if there are no position in the line
    this.currentPosition = {
      FENPosition: INITIAL_FEN,
      previousFENPosition: INITIAL_FEN,
      moveList: [],
    };
    this.line = new Line('name', WHITE, [this.currentPosition]);
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
    console.log(event);
    const currentMove: Move = {
      positionFENAfter: event.fen,
      moveToSend: event.move,
      moveToShow: this.movePipe.transform(event),
    };
    // Si es jugada de  blanco y existe otra jugada se debe mostrar un  mensaje de error
    //https://material.angular.io/components/dialog/examples
    // The move don't exists
    if (!this.currentPosition.moveList.some((move) => move.moveToSend === currentMove.moveToSend)) {
      this.currentPosition.moveList.push(currentMove);
      if (!this.line.canAddMove(event.color, event.fen)) {
        const dialogRef = this.dialog.open(ReplaceMoveConfirmationDialogComponent);
        dialogRef.afterClosed().subscribe((result) => {
          console.log(`Dialog result: ${result}`);
        });
      }
    }
    // The position after the move exists
    if (this.line.existsPosition(event.fen)) {
      this.currentPosition = this.line.getPositionByFEN(event.fen);
    } else {
      this.currentPosition = {
        FENPosition: event.fen,
        previousFENPosition: this.currentPosition.FENPosition,
        moveList: [],
      };
      this.line.positionList.push(this.currentPosition);
    }
  }

  public handleDeleteMove(moveEvent: Move): void {
    this.currentPosition.moveList = [
      ...this.currentPosition.moveList.filter((move) => move.moveToSend !== moveEvent.moveToSend),
    ];
    this.line.deletePosition(moveEvent.positionFENAfter);
  }

  public handleNextMove(move: Move): void {
    this.board.move(move.moveToSend);
  }

  public handleUndoUntilAlternative(): void {
    console.log('TODO handle until alternative');
  }
}
