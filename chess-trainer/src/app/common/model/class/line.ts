import { INITIAL_FEN } from '../constant/constant';
import { Position } from '../interface/position';

export class Line {
  name: string;
  positionList: Position[];
  color: string;
  constructor(name: string, color: string, positionList: Position[]) {
    this.name = name;
    this.positionList = positionList;
    this.color = color;
  }

  public getPositionByFEN(FENPosition: string): Position {
    const result = this.positionList.find((element) => this.compareFEN(element.FENPosition, FENPosition));
    if (result) {
      return result;
    } else {
      throw "Element don't exists";
    }
  }

  public existsPosition(FENPosition: string): boolean {
    return this.positionList.some((element) => this.compareFEN(element.FENPosition, FENPosition));
  }

  public deletePosition(FENPosition: string) {
    this.positionList = this.positionList.filter((element) => !this.compareFEN(element.FENPosition, FENPosition));
  }

  public canAddMove(moveColor: string, FENPosition: string): boolean {
    if (this.existsPosition(FENPosition) && this.color === moveColor) {
      const positionToAddMove = this.getPositionByFEN(FENPosition);
      return positionToAddMove.moveList.length === 0;
    }
    return true;
  }

  private compareFEN(positionFEN: string, other: string): boolean {
    return positionFEN.startsWith(other.slice(0, -4));
  }
}
