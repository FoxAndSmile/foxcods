import {AfterViewInit, Component, OnInit} from '@angular/core';

import {I18nSupportService} from '../i18n-support.service';

@Component({
  selector: 'app-welcome-msg',
  templateUrl: './welcome-msg.component.html',
  styleUrls: ['./welcome-msg.component.css']
})
export class WelcomeMsgComponent implements OnInit, AfterViewInit {
  private static CHK_KEYUP_WAIT_SEC = 5000;
  private valid = false;
  userName = ''
  welcomeMsg = '';

  // 인자에 public을 넣는 순간 알아서 속성이 생성이 되어 있다!!
  constructor(public i18nSupporter: I18nSupportService) {
  }

  showWelcomeMsg() {
    this.welcomeMsg = this.i18nSupporter.getWelcomeMsgByCode(this.userName);
  }

  ngOnInit() {
  }

  onChange() {
    this.valid = this.userName.length > 0;
  }

  ngAfterViewInit() {
    const checkTouchedFn = () => {
      if (this.valid) {
        return;
      }
      alert('이름을 입력해 주세요.');
    };

    setTimeout(checkTouchedFn, WelcomeMsgComponent.CHK_KEYUP_WAIT_SEC);
  }



}
