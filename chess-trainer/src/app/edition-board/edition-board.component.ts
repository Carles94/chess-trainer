import { HttpClient } from '@angular/common/http';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { BoardComponent } from '../common/component/board/board.component';
import { ReplaceMoveConfirmationDialogComponent } from '../common/component/replace-move-confirmation-dialog/replace-move-confirmation-dialog.component';
import { Line } from '../common/model/class/line';
import { INITIAL_FEN } from '../common/model/constant/constant';
import { IBoard } from '../common/model/interface/board';
import { DeleteMoveBody } from '../common/model/interface/delete-move-body';
import { Move } from '../common/model/interface/move';
import { MoveEvent } from '../common/model/interface/move-event';
import { Position } from '../common/model/interface/position';
import { PostMoveBody } from '../common/model/interface/post-move-body';
import { MovePipe } from '../common/pipe/move.pipe';
import { HttpUtils } from '../common/utils/http-utils';

@Component({
  selector: 'app-edition-board',
  templateUrl: './edition-board.component.html',
  styleUrls: ['./edition-board.component.scss'],
})
export class EditionBoardComponent implements OnInit {
  @ViewChild(BoardComponent)
  board!: IBoard;

  public currentPosition: Position;

  private positionStack: string[] = [];

  lineUuid = '53f93e7c-4d34-433a-b0d2-824f134a9829';

  constructor(private readonly movePipe: MovePipe, public dialog: MatDialog, private http: HttpClient) {
    this.currentPosition = {
      fenPosition: INITIAL_FEN,
      moveList: [],
    };
  }

  ngOnInit(): void {
    HttpUtils.getPosition(this.lineUuid, INITIAL_FEN, this.http).subscribe((position: any) => {
      this.currentPosition = position;
      this.positionStack.push(INITIAL_FEN);
    });
  }

  public handleUndo(): void {
    // Only undo if we are not in the initial position
    if (this.positionStack.length !== 1) {
      this.positionStack.pop();
      const previousPosition: string = this.positionStack[this.positionStack.length - 1];
      HttpUtils.getPosition(this.lineUuid, previousPosition, this.http).subscribe((position: any) => {
        this.currentPosition = position;
      });
      this.board.undo();
    }
  }

  public handleReset(): void {
    HttpUtils.getPosition(this.lineUuid, INITIAL_FEN, this.http).subscribe((position: any) => {
      this.currentPosition = position;
      this.positionStack = [position.fenPosition];
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
    HttpUtils.postMoveEvent(body, this.http).subscribe((position: any) => {
      if (position) {
        this.currentPosition = position;
        this.positionStack.push(position.fenPosition);
      } else {
        this.dialog.open(ReplaceMoveConfirmationDialogComponent);
        this.board.undo();
      }
    });
  }

  public handleDeleteMove(move: Move): void {
    const deleteBody: DeleteMoveBody = {
      move: move,
      currentPosition: this.currentPosition,
      lineUuid: this.lineUuid,
    };
    HttpUtils.deleteMove(deleteBody, this.http).subscribe((position: any) => {
      this.currentPosition = position;
    });
  }

  public handleNextMove(move: Move): void {
    this.board.move(move.moveToSend);
  }

  public handleUndoUntilAlternative(): void {
    console.log('TODO handle until alternative');
  }
}
