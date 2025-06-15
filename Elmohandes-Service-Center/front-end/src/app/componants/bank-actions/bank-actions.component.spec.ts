import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BankActionsComponent } from './bank-actions.component';

describe('BankActionsComponent', () => {
  let component: BankActionsComponent;
  let fixture: ComponentFixture<BankActionsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BankActionsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BankActionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
