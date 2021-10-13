import { INITIAL_FEN } from '../constant/constant';
import { Position } from '../interface/position';

export class Line {
  name: string;
  positionList: Position[];
  constructor(name: string, positionList: Position[]) {
    this.name = name;
    this.positionList = positionList;
  }
  //TODO
  public getPositionByFEN(FENPosition: string): Position {
    return {
      positionFEN: INITIAL_FEN,
      previousFENPosition: INITIAL_FEN,
      moveList: [],
    };
  }
}
