import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { Child1Component } from './child1/child1.component';
import {ShareDataServiceService} from './share-data-service.service';
import {Child2Component} from './child1/child2/child2.component';

@NgModule({
  declarations: [
    AppComponent,
    Child1Component,
    Child2Component
  ],
  imports: [
    BrowserModule,
    FormsModule
  ],
  providers: [ShareDataServiceService],
  bootstrap: [AppComponent]
})
export class AppModule { }
