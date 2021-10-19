import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { NgxChessBoardModule } from 'ngx-chess-board';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BoardComponent } from './common/component/board/board.component';
import { EditionBoardComponent } from './edition-board/edition-board.component';
import { MatCardModule } from '@angular/material/card';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatDividerModule } from '@angular/material/divider';
import { MatListModule } from '@angular/material/list';
import { EditionVerticalPanelComponent } from './edition-board/edition-vertical-panel/edition-vertical-panel.component';
import { EditionFooterPanelComponent } from './edition-board/edition-footer-panel/edition-footer-panel.component';

@NgModule({
  declarations: [
    AppComponent,
    BoardComponent,
    EditionBoardComponent,
    EditionVerticalPanelComponent,
    EditionFooterPanelComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgxChessBoardModule.forRoot(),
    MatCardModule,
    MatGridListModule,
    MatDividerModule,
    MatListModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
