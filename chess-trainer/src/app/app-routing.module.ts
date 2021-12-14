import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EditionBoardComponent } from './edition-board/edition-board.component';
import { ManageOpeningComponent } from './manage-opening/manage-opening.component';

const routes: Routes = [
  { path: 'edition-board', component: EditionBoardComponent },
  { path: 'manage-opening', component: ManageOpeningComponent },
  { path: '', redirectTo: '/edition-board', pathMatch: 'full' }, // default path
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
