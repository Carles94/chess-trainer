import { Move } from './move';

export interface Position {
  positionFEN: string;
  previousFENPosition: string;
  moveList: Move[];
}
