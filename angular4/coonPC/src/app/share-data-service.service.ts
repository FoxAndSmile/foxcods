import {EventEmitter, Injectable} from '@angular/core';

@Injectable()
export class ShareDataServiceService {

  private shareData: string;

  // 서비스를 사용한 동기화를 위해 등록
  changeShareData: EventEmitter<any> = new EventEmitter<any>();

  constructor() { }

  printShareData() {
    console.log('ShareData = ' + this.shareData);
  }

  setShareData(shareData) {
    this.shareData = shareData;
    this.changeShareData.emit({});
  }

}
