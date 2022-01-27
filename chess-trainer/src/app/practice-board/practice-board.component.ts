import { HttpClient } from '@angular/common/http';
import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';
import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { BoardComponent } from '../common/component/board/board.component';
import { INITIAL_FEN, WHITE } from '../common/model/constant/constant';
import { IBoard } from '../common/model/interface/board';
import { Move } from '../common/model/interface/move';
import { MoveEvent } from '../common/model/interface/move-event';
import { Position } from '../common/model/interface/position';
import { PostMoveBody } from '../common/model/interface/post-move-body';
import { HttpUtils } from '../common/utils/http-utils';

@Component({
  selector: 'app-practice-board',
  templateUrl: './practice-board.component.html',
  styleUrls: ['./practice-board.component.scss'],
})
export class PracticeBoardComponent implements OnInit {
  @ViewChild(BoardComponent)
  board!: IBoard;

  public currentPosition!: Position;

  public isVariantEnded: boolean = false;

  lineUuid = '';

  lineColor = '';

  constructor(private http: HttpClient, private route: ActivatedRoute) {}

  ngOnInit(): void {
    let possibleUuid = this.route.snapshot.queryParamMap.get('lineUuid');
    this.lineUuid = possibleUuid ? possibleUuid : '';
    let possibleColor = this.route.snapshot.queryParamMap.get('color');
    this.lineColor = possibleColor ? possibleColor : '';

    HttpUtils.getPosition(this.lineUuid, INITIAL_FEN, this.http).subscribe((position: any) => {
      this.currentPosition = position;
    });
  }

  public handlePieceMoved(event: MoveEvent): void {
    const body: PostMoveBody = {
      moveEvent: { ...event },
      currentPosition: this.currentPosition,
      lineUuid: this.lineUuid,
    };
    let oldCorrectAnswers: number = this.currentPosition.correctAnswers;
    if (event.color === this.lineColor) {
      // Line color move
      HttpUtils.updatePosition(body, this.http).subscribe((position: any) => {
        this.currentPosition = position;
        if (this.currentPosition.correctAnswers > oldCorrectAnswers) {
          // The answer is good
          HttpUtils.getPosition(this.lineUuid, event.fen, this.http).subscribe((nextPosition: any) => {
            this.currentPosition = nextPosition;
            if (this.currentPosition.moveList.length) {
              let nextMove: Move =
                this.currentPosition.moveList[Math.floor(Math.random() * this.currentPosition.moveList.length)];
              this.board.move(nextMove.moveToSend);
            } else {
              // The line is ended
              this.isVariantEnded = true;
            }
          });
        } else {
          // The answer is bad
          this.board.undo();
        }
      });
    } else {
      // Other color move
      HttpUtils.getPosition(this.lineUuid, event.fen, this.http).subscribe((nextPosition: any) => {
        this.currentPosition = nextPosition;
        if (!this.currentPosition.moveList.length) {
          this.isVariantEnded = true;
        }
      });
    }
  }

  public handleShowAnswer() {
    //  TODO
    console.log('Show answer');
  }

  public handleNextVariant() {
    this.board.reset();
    HttpUtils.getPosition(this.lineUuid, INITIAL_FEN, this.http).subscribe((position: any) => {
      this.currentPosition = position;
    });
  }

  public handleReverse() {
    this.board.reverse();
  }
}
