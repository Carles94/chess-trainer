import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-manage-opening',
  templateUrl: './manage-opening.component.html',
  styleUrls: ['./manage-opening.component.scss'],
})
export class ManageOpeningComponent implements OnInit {
  public lineUuid: string = '53f93e7c-4d34-433a-b0d2-824f134a9829';
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
}
