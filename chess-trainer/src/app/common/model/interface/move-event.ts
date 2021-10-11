export interface MoveEvent {
  check: boolean;
  checkmate: boolean;
  color: string;
  fen: string;
  move: string;
  piece: string;
  stalemate: boolean;
  capture: boolean;
}
