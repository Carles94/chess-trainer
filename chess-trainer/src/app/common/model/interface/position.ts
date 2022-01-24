import { Move } from './move';

export interface Position {
  fenPosition: string;
  moveList: Move[];
  totalAnswers: number;
  correctAnswers: number;
}
