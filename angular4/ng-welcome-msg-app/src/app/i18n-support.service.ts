import { Injectable } from '@angular/core';
import {LANG_METADATA} from './lang-metadata';

@Injectable()
export class I18nSupportService {
  private welcomeMsg;
  public langCode = 'ko';

  constructor() {
  }

  getWelcomeMsgByCode(userName: string) {
    const langData = LANG_METADATA.find(lang => lang.code === this.langCode);
    return `${langData.msg}, ${userName}`;
  }

}
