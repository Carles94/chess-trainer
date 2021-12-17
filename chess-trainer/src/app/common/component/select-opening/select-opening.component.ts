import { Component, OnInit } from '@angular/core';
import { BLACK, WHITE } from '../../model/constant/constant';
import { Line } from '../../model/interface/line';
import { Opening } from '../../model/interface/opening';

@Component({
  selector: 'app-select-opening',
  templateUrl: './select-opening.component.html',
  styleUrls: ['./select-opening.component.scss'],
})
export class SelectOpeningComponent implements OnInit {
  public WHITE = WHITE;
  public BLACK = BLACK;

  public openingList: Opening[] = [];
  public lineList: Line[] = [];

  public selectedColor = '';
  public selectedOpening = '';

  constructor() {
    this.openingList.push({
      uuid: '',
      name: 'opening',
      lineList: [
        {
          name: 'line',
          positionList: [],
          color: WHITE,
        },
      ],
      color: WHITE,
    });
  }

  ngOnInit(): void {}

  handleSelectColor(event: any): void {
    console.log(event);
    this.selectedColor = event.option.value;
    this.lineList = [];
    this.selectedOpening = '';
  }

  handleSelectOpening(event: any): void {
    console.log(event);
    this.selectedOpening = event.option.value.name;
    this.lineList = this.openingList[0].lineList;
  }

  handleSelectLine(event: any): void {
    console.log(event);
  }
}
