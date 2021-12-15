import { Position } from './position';

export interface Line {
  //TODO add uuid
  name: string;
  positionList: Position[];
  color: string;
}
