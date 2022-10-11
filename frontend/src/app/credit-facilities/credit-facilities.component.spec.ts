import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreditFacilitiesComponent } from './credit-facilities.component';

describe('CreditFacilitiesComponent', () => {
  let component: CreditFacilitiesComponent;
  let fixture: ComponentFixture<CreditFacilitiesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreditFacilitiesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CreditFacilitiesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
