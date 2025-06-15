import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientsActionsComponent } from './clients-actions.component';

describe('ClientsActionsComponent', () => {
  let component: ClientsActionsComponent;
  let fixture: ComponentFixture<ClientsActionsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ClientsActionsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ClientsActionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
