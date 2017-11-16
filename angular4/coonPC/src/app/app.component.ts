import {AfterViewInit, Component, ElementRef, QueryList, ViewChild, ViewChildren} from '@angular/core';
import {ShareDataServiceService} from './share-data-service.service';
import {Child1Component} from './child1/child1.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements AfterViewInit{
  title = 'app';
  myState = 'Very Good';
  myName = 'Geforce';
  memSize = '11GB';
  aText = 'A';
  bText = 'B';
  shareText = 'Share Text';
  elementRef: ElementRef;

  @ViewChild(Child1Component) child1: Child1Component;
  @ViewChildren(Child1Component) childs: QueryList<Child1Component>;
  @ViewChild('text') text: ElementRef;
  @ViewChildren('text') texts: QueryList<ElementRef>;

  constructor(public shareDataService: ShareDataServiceService, ef: ElementRef) {
    this.elementRef = ef;
    console.log(this.elementRef);
  }

  ngAfterViewInit() {
    // 이때 @ViewChild, @ViewChildren이 변수에 바인딩 된다.
  }

  emptyText(emptyTextType: string) {
    if (emptyTextType === 'A') {
      this.aText = '';
    } else if (emptyTextType === 'B') {
      this.bText = '';
    }
  }

  clickShareText() {
    this.shareDataService.setShareData(this.shareText);
  }

  callPublic() {
    this.child1.publicLetItBe();
    this.childs.map(f => f.publicLetItBe());
  }

  callPrivate() {
    console.log('아예 안나오네');
  }

  testTemplateVal() {
    console.log(this.text);
    console.log(this.texts);
  }

  upKeyGo3(event, num: number) {
    console.log(num);
  }

}
