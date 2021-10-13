import {
  ChangeDetectionStrategy,
  ChangeDetectorRef,
  Component,
  EventEmitter,
  OnInit,
  Output,
  ViewChild,
} from '@angular/core';
import { NgxChessBoardView } from 'ngx-chess-board';
import { IBoard } from '../../model/interface/board';
import { MoveEvent } from '../../model/interface/move-event';

@Component({
  selector: 'app-board',
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class BoardComponent implements OnInit, IBoard {
  @ViewChild('board', { static: false })
  board!: NgxChessBoardView;

  @Output('pieceMoved')
  pieceMovedEmitter: EventEmitter<MoveEvent> = new EventEmitter<MoveEvent>();

  constructor(private changeDetectorRef: ChangeDetectorRef) {}

  ngOnInit(): void {
    this.changeDetectorRef.detectChanges();
    this.reset();
  }

  //TODO add comments and tests
  /**
   * Capture when a piece is moved  (triggered by all moves on the board)
   * @param event sent by the board
   */
  public pieceMoved(event: any) {
    const moveChange: MoveEvent = {
      check: event.check,
      stalemate: event.stalemate,
      checkmate: event.checkmate,
      color: event.color,
      fen: event.fen,
      move: event.move,
      piece: event.piece,
      capture: event.x,
    };
    console.log('Piece Moved');
    this.pieceMovedEmitter.emit(moveChange);
  }

  /**
   * Make a move on the board
   * @param move with format 'e2e4'
   */
  public move(move: string): void {
    this.board.move(move);
  }

  public undo(): void {
    this.board.undo();
    this.changeDetectorRef.detectChanges();
  }

  public reverse(): void {
    this.board.reverse();
    this.changeDetectorRef.detectChanges();
  }
  public reset(): void {
    this.board.reset();
  }
}
