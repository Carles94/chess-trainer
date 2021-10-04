import { Component, EventEmitter, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-edition-footer-panel',
  templateUrl: './edition-footer-panel.component.html',
  styleUrls: ['./edition-footer-panel.component.scss'],
})
export class EditionFooterPanelComponent implements OnInit {
  @Output('undo')
  public undoEmitter: EventEmitter<any> = new EventEmitter();

  constructor() {}

  ngOnInit(): void {}

  public handleUndo(): void {
    this.undoEmitter.emit();
  }
}
