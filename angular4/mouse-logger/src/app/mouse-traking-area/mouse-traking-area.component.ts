import {Component, OnInit} from '@angular/core';
import {LogLevel} from '../log-level.enum';
import {MyDefaultLoggerService} from '../my-default-logger.service';
import {LOG_LEVEL_TOKEN} from '../app.token';

@Component({
  selector: 'mpl-mouse-traking-area',
  templateUrl: './mouse-traking-area.component.html',
  styleUrls: ['./mouse-traking-area.component.css'],
  providers: [MyDefaultLoggerService, {provide: LOG_LEVEL_TOKEN, useValue: LogLevel.INFO}]
})
export class MouseTrakingAreaComponent implements OnInit {
  logLevel: LogLevel = LogLevel.INFO;

  constructor(private logger: MyDefaultLoggerService) {
  }

  ngOnInit() {
  }

  captureMousePos(event: MouseEvent) {
    this.logger.debug('click event occured');
    const pos = [event.clientX, event.clientY];
    this.logger.info(`x: ${pos[0]}, y: ${pos[1]}`);
  }

}
