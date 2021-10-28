import { Pipe, PipeTransform } from '@angular/core';
import { WHITE } from '../model/constant/constant';
import { MoveEvent } from '../model/interface/move-event';

@Pipe({
  name: 'move',
})
export class MovePipe implements PipeTransform {
  transform(moveEvent: MoveEvent): string {
    //TODO case two pieces can go  same square
    const endSquare = moveEvent.move.slice(-2);
    let moveNumber = moveEvent.color === WHITE ? moveEvent.fen.slice(-1) : parseInt(moveEvent.fen.slice(-1)) - 1;
    const dots = moveEvent.color === WHITE ? '.' : '...';
    let pieceLetter = this.selectPieceLetter(moveEvent);
    const captureSimbol = moveEvent.capture ? 'x' : '';
    const endDecorator = moveEvent.checkmate ? '#' : moveEvent.check ? '+' : '';
    let moveToShow = this.selectMoveToShow(pieceLetter, captureSimbol, endSquare, endDecorator, moveEvent);
    return moveNumber + dots + moveToShow;
  }

  private selectMoveToShow(
    pieceLetter: string,
    captureSimbol: string,
    endSquare: string,
    endDecorator: string,
    moveEvent: MoveEvent
  ) {
    // Normal move
    let moveToShow = pieceLetter + captureSimbol + endSquare + endDecorator;
    // Castle move
    if (moveEvent.piece === 'King' && (moveEvent.move === 'e1g1' || moveEvent.move === 'e8g8')) {
      moveToShow = 'O-O';
    } else if (moveEvent.piece === 'King' && (moveEvent.move === 'e1c1' || moveEvent.move === 'e8c8')) {
      moveToShow = 'O-O-O';
    }
    return moveToShow;
  }

  private selectPieceLetter(moveEvent: MoveEvent) {
    let pieceLetter = '';
    if (moveEvent.piece !== 'Pawn') {
      if (moveEvent.piece === 'Knight') {
        pieceLetter = 'N';
      } else {
        pieceLetter = moveEvent.piece[0];
      }
    } else if (moveEvent.capture) {
      pieceLetter = moveEvent.move[0];
    }
    return pieceLetter;
  }
}
