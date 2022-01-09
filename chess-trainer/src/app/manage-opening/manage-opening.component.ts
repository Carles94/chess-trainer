import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { WHITE } from '../common/model/constant/constant';
import { CreateLineBody } from '../common/model/interface/create-line-body';
import { Line } from '../common/model/interface/line';
import { HttpUtils } from '../common/utils/http-utils';

@Component({
  selector: 'app-manage-opening',
  templateUrl: './manage-opening.component.html',
  styleUrls: ['./manage-opening.component.scss'],
})
export class ManageOpeningComponent implements OnInit {
  public lineUuid: string = '';
  constructor(private http: HttpClient) {}

  ngOnInit(): void {}

  handleRemove(): void {
    console.log('Remove opening');
  }

  handleAdd(): void {
    console.log('Add line');
    let body: CreateLineBody = {
      lineColor: WHITE,
      lineName: 'lineName',
      openingName: 'openingName',
    };
    HttpUtils.postCreateLine(body, this.http).subscribe((line) => console.log(line));
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
