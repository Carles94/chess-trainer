import { Move } from './move';

export interface Position {
  currentPositionFEN: string;
  moveList: Move[];
}
