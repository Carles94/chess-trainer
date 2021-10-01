import { Component } from '@angular/core';
import { HistoryMove } from 'ngx-chess-board';
import { ColorInput, PieceTypeInput } from 'ngx-chess-board/lib/utils/inputs/piece-type-input';

@Component({
  selector: 'app-child',
  template: '',
})
export class NgxChessBoardViewStubComponent {
  reset(): void {}
  reverse(): void {}
  undo(): void {}
  getMoveHistory(): HistoryMove[] {
    return [];
  }
  setFEN(fen: string): void {}
  move(coords: string): void {}
  getFEN(): string {
    return '';
  }
  setPGN(pgn: string): void {}
  addPiece(pieceTypeInput: PieceTypeInput, colorInput: ColorInput, coords: string): any {}
}
