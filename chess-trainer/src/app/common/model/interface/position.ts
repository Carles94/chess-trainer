import { Move } from './move';

export interface Position {
  FENPosition: string;
  previousFENPosition: string;
  moveList: Move[];
}
