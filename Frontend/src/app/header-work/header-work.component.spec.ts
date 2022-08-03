import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HeaderWorkComponent } from './header-work.component';

describe('HeaderWorkComponent', () => {
  let component: HeaderWorkComponent;
  let fixture: ComponentFixture<HeaderWorkComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HeaderWorkComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HeaderWorkComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
