import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { NgxChessBoardModule } from 'ngx-chess-board';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BoardComponent } from './board/board.component';

@NgModule({
  declarations: [AppComponent, BoardComponent],
  imports: [BrowserModule, AppRoutingModule, NgxChessBoardModule.forRoot()],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
