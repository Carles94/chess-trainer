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

  public existsPosition(FENPosition: string): boolean {
    return this.positionList.some((element) => this.compareFEN(element.positionFEN, FENPosition));
  }

  private compareFEN(positionFEN: string, other: string): boolean {
    return positionFEN.startsWith(other.slice(0, -4));
  }

  public deletePosition(FENPosition: string) {
    this.positionList = this.positionList.filter((element) => !this.compareFEN(element.positionFEN, FENPosition));
  }
}
