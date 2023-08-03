import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { environment } from '../../../environments/environment';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  latestClaims : any[];
  approvedClaims : any[];
  constructor(private httpClient: HttpClient) { }

  getLatestClaims(){
    this.httpClient.get(environment.claimsService + '/claim/status/SURVEYED')
    .subscribe((data:any)=>{
      this.latestClaims = data;
    });
  }

  ngOnInit(): void {
    this.getLatestClaims();
    this.getApprovedClaims();
  }

  approveClaim(taskId : string){
    this.httpClient.post(environment.claimsService + '/claim/approve/'+taskId, null)
    .subscribe((data:any)=>{
      this.getLatestClaims();
      this.getApprovedClaims();
    });
  }

  getApprovedClaims(){
    this.httpClient.get(environment.claimsService + '/claim/status/APPROVED')
    .subscribe((data:any)=>{
      this.approvedClaims = data;
    });
  }

}
