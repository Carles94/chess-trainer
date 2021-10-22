import { Pipe, PipeTransform } from '@angular/core';
import { MoveEvent } from '../model/interface/move-event';

@Pipe({
  name: 'move',
})
export class MovePipe implements PipeTransform {
  transform(moveEvent: MoveEvent): string {
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
    }
    const captureSimbol = moveEvent.capture ? 'x' : '';
    const endDecorator = moveEvent.checkmate ? '#' : moveEvent.check ? '+' : '';
    return moveNumber + dots + pieceLetter + captureSimbol + endSquare + endDecorator;
  }
}
