import { Move } from './move';

export interface Position {
  fenPosition: string;
  previousFenPosition: string;
  moveList: Move[];
}
