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

    HttpUtils.getPosition(this.lineUuid, fenParameter, this.http).subscribe((position: any) => {
      this.currentPosition = position;
    });
  }

  public handleUndo(): void {
    const fenParameter: string = this.replaceAll(this.currentPosition.previousFenPosition, '/', '_');
    HttpUtils.getPosition(this.lineUuid, fenParameter, this.http).subscribe((position: any) => {
      this.currentPosition = position;
    });
    this.board.undo();
  }

  public handleReset(): void {
    const fenParameter: string = this.replaceAll(INITIAL_FEN, '/', '_');
    HttpUtils.getPosition(this.lineUuid, fenParameter, this.http).subscribe((position: any) => {
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
    HttpUtils.postMoveEvent(body, this.http).subscribe((position: any) => {
      this.currentPosition = position;
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

  private replaceAll(str: string, find: string, replace: string): string {
    return str.replace(new RegExp(this.escapeRegExp(find), 'g'), replace);
  }
  // Makes the string to find safer
  private escapeRegExp(string: string) {
    return string.replace(/[.*+\-?^${}()|[\]\\]/g, '\\$&'); // $& means the whole matched string
  }
}
