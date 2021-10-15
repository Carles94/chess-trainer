import { INITIAL_FEN } from '../constant/constant';
import { Position } from '../interface/position';

export class Line {
  name: string;
  positionList: Position[];
  constructor(name: string, positionList: Position[]) {
    this.name = name;
    this.positionList = positionList;
  }

  public getPositionByFEN(FENPosition: string): Position {
    const result = this.positionList.find((element) => this.compareFEN(element.positionFEN, FENPosition));
    if (result) {
      return result;
    } else {
      throw "Element don't exists";
    }
  }

  private compareFEN(positionFEN: string, other: string): boolean {
    return positionFEN.startsWith(other.slice(0, -4));
  }
}
