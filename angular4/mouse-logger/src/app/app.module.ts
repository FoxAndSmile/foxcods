import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { MouseTrakingAreaComponent } from './mouse-traking-area/mouse-traking-area.component';
import {MyDefaultLoggerService} from './my-default-logger.service';
import {LogLevel} from './log-level.enum';
import {LOG_LEVEL_TOKEN} from './app.token';

@NgModule({
  declarations: [
    AppComponent,
    MouseTrakingAreaComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [MyDefaultLoggerService, {provide: LOG_LEVEL_TOKEN, useValue: LogLevel.INFO}],
  bootstrap: [AppComponent]
})
export class AppModule { }
