import { HttpClient } from '@angular/common/http';
import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';
import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { BoardComponent } from '../common/component/board/board.component';
import { INITIAL_FEN } from '../common/model/constant/constant';
import { IBoard } from '../common/model/interface/board';
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

  lineUuid = '';

  constructor(private http: HttpClient, private route: ActivatedRoute) {}

  ngOnInit(): void {
    let possibleUuid = this.route.snapshot.queryParamMap.get('lineUuid');
    this.lineUuid = possibleUuid ? possibleUuid : '';
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
    // Update position using the move
    HttpUtils.updatePosition(body, this.http).subscribe((position: any) => {
      this.currentPosition = position;
    });
  }
}
