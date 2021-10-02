import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditionFooterPanelComponent } from './edition-footer-panel.component';

describe('EditionFooterPanelComponent', () => {
  let component: EditionFooterPanelComponent;
  let fixture: ComponentFixture<EditionFooterPanelComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditionFooterPanelComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EditionFooterPanelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
