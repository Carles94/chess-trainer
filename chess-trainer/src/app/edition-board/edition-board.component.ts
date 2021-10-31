import { HttpClient } from '@angular/common/http';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Observable } from 'rxjs';
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

  line: Line | undefined;

  public currentPosition: Position;

  constructor(private readonly movePipe: MovePipe, public dialog: MatDialog, private http: HttpClient) {
    // Only if there are no position in the line
    this.currentPosition = {
      FENPosition: INITIAL_FEN,
      previousFENPosition: INITIAL_FEN,
      moveList: [],
    };
    //this.line = new Line('name', WHITE, [this.currentPosition]);
  }

  ngOnInit(): void {
    this.http
      .request('GET', `http://localhost:8080/chess-trainer/position/get`, {
        responseType: 'json',
      })
      .subscribe((position) => console.log(position));
  }

  public handleUndo(): void {
    //this.currentPosition = this.line.getPositionByFEN(this.currentPosition.previousFENPosition);
    this.board.undo();
  }

  public handleReset(): void {
    //this.currentPosition = this.line.getPositionByFEN(INITIAL_FEN);
    this.board.reset();
  }

  public handleReverse(): void {
    this.board.reverse();
  }

  public handlePieceMoved(event: MoveEvent): void {
    console.log(event);
    this.http
      .post('http://localhost:8080/chess-trainer/move', event, {
        responseType: 'json',
      })
      .subscribe((position) => console.log(position));
    /* const currentMove: Move = {
      positionFENAfter: event.fen,
      moveToSend: event.move,
      moveToShow: this.movePipe.transform(event),
    };
    // The move don't exists
    if (!this.currentPosition.moveList.some((move) => move.moveToSend === currentMove.moveToSend)) {
      this.currentPosition.moveList.push(currentMove);
      //TODO test that
      // Si es jugada de  blanco y existe otra jugada se debe mostrar un  mensaje de error
      if (!this.line.canAddMove(event.color, this.currentPosition.FENPosition)) {
        const dialogRef = this.dialog.open(ReplaceMoveConfirmationDialogComponent);
        dialogRef.afterClosed().subscribe((result) => {
          console.log(`Dialog result: ${result}`);
        });
        return;
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
    }*/
  }

  public handleDeleteMove(move: Move): void {
    this.currentPosition.moveList = [
      ...this.currentPosition.moveList.filter((move) => move.moveToSend !== move.moveToSend),
    ];
    this.http
      .request('DELETE', 'http://localhost:8080/chess-trainer/move', {
        responseType: 'json',
        body: move,
      })
      .subscribe((position) => console.log(position));
    //this.line.deletePosition(moveEvent.positionFENAfter);
  }

  public handleNextMove(move: Move): void {
    this.board.move(move.moveToSend);
  }

  public handleUndoUntilAlternative(): void {
    console.log('TODO handle until alternative');
  }
}
