import { HttpClient } from '@angular/common/http';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { BoardComponent } from '../common/component/board/board.component';
import { Line } from '../common/model/class/line';
import { INITIAL_FEN } from '../common/model/constant/constant';
import { IBoard } from '../common/model/interface/board';
import { DeleteMoveBody } from '../common/model/interface/delete-move-body';
import { Move } from '../common/model/interface/move';
import { MoveEvent } from '../common/model/interface/move-event';
import { Position } from '../common/model/interface/position';
import { PostMoveBody } from '../common/model/interface/post-move-body';
import { MovePipe } from '../common/pipe/move.pipe';

@Component({
  selector: 'app-edition-board',
  templateUrl: './edition-board.component.html',
  styleUrls: ['./edition-board.component.scss'],
})
export class EditionBoardComponent implements OnInit {
  @ViewChild(BoardComponent)
  board!: IBoard;

  public currentPosition: Position;

  public moveList: Move[] = [];

  lineUuid = '53f93e7c-4d34-433a-b0d2-824f134a9829';

  constructor(private readonly movePipe: MovePipe, public dialog: MatDialog, private http: HttpClient) {
    this.currentPosition = {
      fenPosition: INITIAL_FEN,
      previousFenPosition: INITIAL_FEN,
      moveList: [],
    };
  }

  ngOnInit(): void {
    const fenParameter: string = this.replaceAll(INITIAL_FEN, '/', '_');
    this.http
      .request('GET', `http://localhost:8080/chess-trainer/position/${this.lineUuid}/${fenParameter}`, {
        responseType: 'json',
      })
      .subscribe((position: any) => {
        this.currentPosition = position;
      });
  }

  public handleUndo(): void {
    const fenParameter: string = this.replaceAll(this.currentPosition.previousFenPosition, '/', '_');
    this.http
      .request('GET', `http://localhost:8080/chess-trainer/position/${this.lineUuid}/${fenParameter}`, {
        responseType: 'json',
      })
      .subscribe((position: any) => {
        this.currentPosition = position;
      });
    this.board.undo();
  }

  public handleReset(): void {
    const fenParameter: string = this.replaceAll(INITIAL_FEN, '/', '_');
    this.http
      .request('GET', `http://localhost:8080/chess-trainer/position/${this.lineUuid}/${fenParameter}`, {
        responseType: 'json',
      })
      .subscribe((position: any) => {
        this.currentPosition = position;
      });
    this.board.reset();
  }

  public handleReverse(): void {
    this.board.reverse();
  }

  public handlePieceMoved(event: MoveEvent): void {
    const body: PostMoveBody = {
      moveEvent: { ...event, moveToShow: this.movePipe.transform(event) },
      currentPosition: this.currentPosition,
      lineUuid: this.lineUuid,
    };
    this.http
      .post('http://localhost:8080/chess-trainer/move', body, {
        responseType: 'json',
      })
      .subscribe((position: any) => {
        this.currentPosition = position;
      });
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
    const deleteBody: DeleteMoveBody = {
      move: move,
      currentPosition: this.currentPosition,
      lineUuid: this.lineUuid,
    };
    this.http
      .request('DELETE', 'http://localhost:8080/chess-trainer/move', {
        responseType: 'json',
        body: deleteBody,
      })
      .subscribe((position: any) => {
        this.currentPosition = position;
      });
  }

  public handleNextMove(move: Move): void {
    this.board.move(move.moveToSend);
  }

  public handleUndoUntilAlternative(): void {
    console.log('TODO handle until alternative');
  }

  private replaceAll(str: string, find: string, replace: string): string {
    return str.replace(new RegExp(this.escapeRegExp(find), 'g'), replace);
  }
  // Makes the string to find safer
  private escapeRegExp(string: string) {
    return string.replace(/[.*+\-?^${}()|[\]\\]/g, '\\$&'); // $& means the whole matched string
  }
}
