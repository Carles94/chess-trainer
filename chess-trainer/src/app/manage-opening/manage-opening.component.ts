import { Component, OnInit } from '@angular/core';
import { Line } from '../common/model/interface/line';

@Component({
  selector: 'app-manage-opening',
  templateUrl: './manage-opening.component.html',
  styleUrls: ['./manage-opening.component.scss'],
})
export class ManageOpeningComponent implements OnInit {
  public lineUuid: string = '';
  constructor() {}

  ngOnInit(): void {}

  handleRemove(): void {
    console.log('Remove opening');
  }

  handleAdd(): void {
    console.log('Add opening');
  }

  handleRename(): void {
    console.log('Rename opening');
  }

  handleCopy(): void {
    console.log('Copy opening');
  }

  handleSelectLine(line: Line) {
    this.lineUuid = line.uuid;
  }
}
