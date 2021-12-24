import { Position } from './position';

export interface Line {
  uuid: string;
  name: string;
  positionList: Position[];
  color: string;
}
