import { Line } from './line';

export interface Opening {
  uuid: string;
  name: string;
  lineList: Line[];
  color: string;
}
