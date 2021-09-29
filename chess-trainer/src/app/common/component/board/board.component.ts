import { ChangeDetectorRef, Component, EventEmitter, OnInit, Output, ViewChild } from '@angular/core';
import { NgxChessBoardView } from 'ngx-chess-board';
import { MoveChange } from '../../model/move-change';

@Component({
  selector: 'app-board',
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.scss'],
})
export class BoardComponent implements OnInit {
  @ViewChild('board', { static: false })
  board!: NgxChessBoardView;

  @Output('pieceMoved')
  pieceMovedEmitter: EventEmitter<MoveChange> = new EventEmitter<MoveChange>();

  constructor(private changeDetectorRef: ChangeDetectorRef) {}

  ngOnInit(): void {
    this.changeDetectorRef.detectChanges();
    this.reset();
  }

  //TODO add comments and tests
  public pieceMoved(event: any) {
    const moveChange: MoveChange = {
      check: event.check,
      stalemate: event.stalemate,
      checkmate: event.checkmate,
      color: event.color,
      fen: event.fen,
      move: event.move,
      piece: event.piece,
      capture: event.x,
    };
    this.pieceMovedEmitter.emit(moveChange);
  }

  public move(move: string): void {
    this.board.move(move);
  }

  public undo(): void {
    this.board.undo();
  }

  public reverse(): void {
    this.board.reverse();
  }
  public reset(): void {
    this.board.reset();
  }
}
