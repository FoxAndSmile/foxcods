import { Component } from '@angular/core';
import {MyDefaultLoggerService} from './my-default-logger.service';
import {LogLevel} from './log-level.enum';

@Component({
  selector: 'mpl-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'mpl';

  constructor() {

  }

}
