import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {ShareDataServiceService} from '../share-data-service.service';

@Component({
  selector: 'app-child1',
  templateUrl: './child1.component.html',
  styleUrls: ['./child1.component.css']
})
export class Child1Component implements OnInit {

  private beatles = 'Let it be';

  // @Input을 사용해서 부모에게서 속성으로 값을 전달을 받을 수 있다.
  @Input() myState;
  @Input() myName;
  @Input() aText;
  @Input() bText;
  @Input()
  set memSize(memSize: string) {
    console.log('Memory Size : ' + memSize);
  }

  @Output() onEmptyText = new EventEmitter<string>();

  constructor(public shareDataService: ShareDataServiceService) {
  }

  ngOnInit() {
    console.log('parent myState = ' + this.myState);
    console.log('parent myName = ' + this.myName);
    console.log('parent aText = ' + this.aText);
    console.log('parent bText = ' + this.bText);

    this.shareDataService.changeShareData.subscribe(_ => this.printShareData());
  }

  emptyA() {
    console.log('click Empty A');
    this.onEmptyText.emit('A');
  }

  emptyB() {
    console.log('click Empty B');
    this.onEmptyText.emit('B');
  }

  printShareData() {
    this.shareDataService.printShareData();
  }

  private privateLetItBe() {
    console.log(this.beatles);
  }

  publicLetItBe() {
    console.log(this.beatles);
  }


}
