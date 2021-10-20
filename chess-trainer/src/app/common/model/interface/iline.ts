import { Position } from './position';

export interface ILine {
  name: string;
  positionList: Position[];
  getPositionByFEN(FENPosition: string): Position;
}
