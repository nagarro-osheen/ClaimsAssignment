import { HttpClient } from '@angular/common/http';
import { Component, OnInit, Inject } from '@angular/core';

import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { environment } from '../../../environments/environment';
import { SurveyProcessDialogComponent } from '../survey-process/survey-process.component';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  latestClaims : any[];
  constructor(private httpClient: HttpClient, public dialog: MatDialog) { }

  getLatestClaims(){
    this.httpClient.get(environment.claimsService + '/claim/status/CREATED')
    .subscribe((data:any)=>{
      this.latestClaims = data;
    });
  }
  ngOnInit(): void {
    this.getLatestClaims();
  }


  openDialog(claimObj: any): void {
    const dialogRef = this.dialog.open(SurveyProcessDialogComponent, {
      width: '250px',
      data: {claim: claimObj},
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      this.getLatestClaims();
    });
  }

}
