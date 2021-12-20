import { Component, Input, OnInit } from '@angular/core';
import { Line } from 'src/app/common/model/interface/line';
import { Opening } from 'src/app/common/model/interface/opening';

@Component({
  selector: 'app-show-openings',
  templateUrl: './show-openings.component.html',
  styleUrls: ['./show-openings.component.scss'],
})
export class ShowOpeningsComponent implements OnInit {
  @Input()
  openingList: Opening[] = [];
  selectedOpening = '';
  selectedLineList: Line[] = [];

  constructor() {}

  ngOnInit(): void {}

  handleSelectOpening(event: any): void {
    console.log(event);
    this.selectedOpening = event.option.value.name;
    this.selectedLineList = this.openingList[0].lineList;
  }

  handleSelectLine(event: any): void {
    console.log(event);
    //TODO redirect ?
  }

  resetSelection(): void {
    this.selectedLineList = [];
    this.selectedOpening = '';
  }
}
