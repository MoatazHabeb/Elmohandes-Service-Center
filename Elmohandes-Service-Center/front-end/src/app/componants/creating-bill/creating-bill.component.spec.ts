import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreatingBillComponent } from './creating-bill.component';

describe('CreatingBillComponent', () => {
  let component: CreatingBillComponent;
  let fixture: ComponentFixture<CreatingBillComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreatingBillComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreatingBillComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
