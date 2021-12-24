import { Component, Input, OnInit, Output, EventEmitter } from '@angular/core';
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
  @Output('selectedLine')
  selectedLineEmitter: EventEmitter<Line> = new EventEmitter();

  constructor() {}

  ngOnInit(): void {}

  handleSelectOpening(event: any): void {
    this.selectedOpening = event.option.value.name;
    this.selectedLineList = this.openingList[0].lineList;
  }

  handleSelectLine(event: any): void {
    this.selectedLineEmitter.emit(event.option.value);
  }

  resetSelection(): void {
    this.selectedLineList = [];
    this.selectedOpening = '';
  }
}
