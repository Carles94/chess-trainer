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

  public getPositionByFen(fenPosition: string): Position {
    const result = this.positionList.find((element) => this.compareFen(element.fenPosition, fenPosition));
    if (result) {
      return result;
    } else {
      throw "Element don't exists";
    }
  }

  public existsPosition(fenPosition: string): boolean {
    return this.positionList.some((element) => this.compareFen(element.fenPosition, fenPosition));
  }

  public deletePosition(fenPosition: string) {
    this.positionList = this.positionList.filter((element) => !this.compareFen(element.fenPosition, fenPosition));
  }

  public canAddMove(moveColor: string, fenPosition: string): boolean {
    if (this.existsPosition(fenPosition) && this.color === moveColor) {
      const positionToAddMove = this.getPositionByFen(fenPosition);
      return positionToAddMove.moveList.length === 0;
    }
    return true;
  }

  private compareFen(fenPosition: string, other: string): boolean {
    return fenPosition.startsWith(other.slice(0, -4));
  }
}
