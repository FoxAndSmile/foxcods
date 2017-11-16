import {Component, Input, OnChanges, OnDestroy, OnInit, SimpleChanges} from '@angular/core';

@Component({
  selector: 'app-sample-component',
  templateUrl: './sample-component.component.html',
  styleUrls: ['./sample-component.component.css']
})
export class SampleComponentComponent implements OnInit, OnDestroy, OnChanges {

  @Input() exContent: string;
  @Input() myNum: number;
  @Input() myStr: string;

  constructor() {
    // alert(`hasBindedContent? ${this.chkExistence(this.exContent)}`);
  }

  // 컴포넌트가 초기화를 마치고 완전상 상태를 갖출 시점에 ngOnInit이 호출된다.
  ngOnInit() {
    // alert(`hasBindedContent? ${this.chkExistence(this.exContent)}`);
  }

  // 컴포넌트가 View에서 제거되기 직전에 호출된다.
  ngOnDestroy(): void {
    alert('Destroyed...');
  }

  ngOnChanges(changes: SimpleChanges): void {
    for (const propName in changes) {
      const change = changes[propName];

      // 최초 값이 변경된 것이라면
      if (change.isFirstChange()) {
        console.log(`first change: ${propName}`);
      } else {
        console.log(`prev: ${change.previousValue}, current: ${change.currentValue}`);
      }
    }
  }


  // 인자가 undefined가 아니면 true, 아니면 false
  chkExistence(v: string) {
    return v !== undefined;
  }

}
