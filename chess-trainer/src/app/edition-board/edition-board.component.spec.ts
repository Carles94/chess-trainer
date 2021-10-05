import { NO_ERRORS_SCHEMA } from '@angular/core';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MatCardModule } from '@angular/material/card';
import { MatGridListModule } from '@angular/material/grid-list';
import { BoardStubComponent } from '../common/test/board-stub.component';

import { EditionBoardComponent } from './edition-board.component';

describe('EditionBoardComponent', () => {
  let component: EditionBoardComponent;
  let fixture: ComponentFixture<EditionBoardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      providers: [MatCardModule, MatGridListModule],
      declarations: [EditionBoardComponent, BoardStubComponent],
      schemas: [NO_ERRORS_SCHEMA],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EditionBoardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should handle undo', () => {
    // Arrange
    component.board = new BoardStubComponent();
    let boardSpy = jest.spyOn(component.board, 'undo');
    // Act
    component.handleUndo();
    // Assert
    expect(boardSpy).toHaveBeenCalled();
  });

  it('should handle reset', () => {
    // Arrange
    component.board = new BoardStubComponent();
    let boardSpy = jest.spyOn(component.board, 'reset');
    // Act
    component.handleReset();
    // Assert
    expect(boardSpy).toHaveBeenCalled();
  });
});
