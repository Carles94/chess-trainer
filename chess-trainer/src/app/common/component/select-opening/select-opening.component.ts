import { Component, OnInit } from '@angular/core';
import { BLACK, WHITE } from '../../model/constant/constant';
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

  public selectedColor = '';

  constructor() {
    this.openingList.push({
      uuid: '',
      name: 'name',
      lineList: [],
      color: WHITE,
    });
  }

  ngOnInit(): void {}

  handleSelectColor(event: any): void {
    console.log(event);
    this.selectedColor = event.option.value;
  }
}
