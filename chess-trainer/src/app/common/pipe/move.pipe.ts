import { Pipe, PipeTransform } from '@angular/core';
import { MoveEvent } from '../model/interface/move-event';

@Pipe({
  name: 'move',
})
export class MovePipe implements PipeTransform {
  transform(moveEvent: MoveEvent): string {
    //TODO refactor + case two pieces can go  same square
    const endSquare = moveEvent.move.slice(-2);
    let moveNumber = moveEvent.color === 'white' ? moveEvent.fen.slice(-1) : parseInt(moveEvent.fen.slice(-1)) - 1;
    const dots = moveEvent.color === 'white' ? '.' : '...';
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
    const captureSimbol = moveEvent.capture ? 'x' : '';
    const endDecorator = moveEvent.checkmate ? '#' : moveEvent.check ? '+' : '';
    let moveToShow = pieceLetter + captureSimbol + endSquare + endDecorator;
    if (moveEvent.piece === 'King' && (moveEvent.move === 'e1g1' || moveEvent.move === 'e8g8')) {
      moveToShow = 'O-O';
    } else if (moveEvent.piece === 'King' && (moveEvent.move === 'e1c1' || moveEvent.move === 'e8c8')) {
      moveToShow = 'O-O-O';
    }
    return moveNumber + dots + moveToShow;
  }
}
