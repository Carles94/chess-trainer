import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditionVerticalPanelComponent } from './edition-vertical-panel.component';

describe('EditionVerticalPanelComponent', () => {
  let component: EditionVerticalPanelComponent;
  let fixture: ComponentFixture<EditionVerticalPanelComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditionVerticalPanelComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EditionVerticalPanelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
