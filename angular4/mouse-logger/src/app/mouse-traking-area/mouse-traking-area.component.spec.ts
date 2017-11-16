import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MouseTrakingAreaComponent } from './mouse-traking-area.component';

describe('MouseTrakingAreaComponent', () => {
  let component: MouseTrakingAreaComponent;
  let fixture: ComponentFixture<MouseTrakingAreaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MouseTrakingAreaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MouseTrakingAreaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
