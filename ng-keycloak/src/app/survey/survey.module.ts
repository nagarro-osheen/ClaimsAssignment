import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomeComponent } from './home/home.component';
import { SurveyRoutingModule } from './survey-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { SurveyProcessDialogComponent } from './survey-process/survey-process.component';

@NgModule({
  declarations: [
    HomeComponent,
    SurveyProcessDialogComponent
  ],
  imports: [
    CommonModule,
    SurveyRoutingModule,
    HttpClientModule, 
    FormsModule
  ],
  entryComponents: [
    SurveyProcessDialogComponent
  ],
})
export class SurveyModule { }
