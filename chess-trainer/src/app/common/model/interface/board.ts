export interface IBoard {
  pieceMoved(event: any): void;
  move(move: string): void;
  undo(): void;
  reverse(): void;
  reset(): void;
}
