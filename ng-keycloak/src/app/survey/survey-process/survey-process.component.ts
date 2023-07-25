import { HttpClient } from '@angular/common/http';
import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { environment } from '../../../environments/environment';

@Component({
  selector: 'app-survey-process',
  templateUrl: './survey-process.component.html',
  styleUrls: ['./survey-process.component.scss']
})
export class SurveyProcessDialogComponent implements OnInit {


  constructor(public dialogRef: MatDialogRef<SurveyProcessDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private httpClient: HttpClient) { }
  
    amount: any = 0; 

  ngOnInit(): void {
  
  }

  processClaim(){
    this.httpClient.post(environment.claimsService + '/claim/survey', {
      claimId: this.data.claim.uuid,
      amount: this.amount
    })
    .subscribe((data:any)=>{
      this.dialogRef.close();
    });
  }
  

}
