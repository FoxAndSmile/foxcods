import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  curVal = 0;
  manualVal = 0;
  static LIMIT_CNT = 100;

  colorByValue() {
    if (this.curVal > 0) {
      return 'green';
    } else if (this.curVal < 0) {
      return 'red';
    } else {
      return 'gray';
    }
  }

  inc() {
    const tempVal = this.curVal + 1;
    if (this.checkLimitCnt(tempVal)) {
      this.curVal = tempVal;
    }
  }

  dec() {
    const tempVal = this.curVal - 1;
    if (this.checkLimitCnt(tempVal)) {
      this.curVal = tempVal;
    }
  }

  setValueForcibly() {
    if (this.checkLimitCnt(this.manualVal)) {
      this.curVal = this.manualVal;
      this.manualVal = 0;
    }
  }

  checkLimitCnt(v) {
    if (Math.abs(v) < AppComponent.LIMIT_CNT) {
      return true;
    }

    const target = v > 0 ? '증가' : '감소';
    alert(`더이상 ${target}할 수 없다`);
    return false;
  }

}
