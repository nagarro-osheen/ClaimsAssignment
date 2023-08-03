import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from 'src/app/auth/service/auth.service';
import { environment } from '../../../environments/environment';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  constructor(
    private formBuilder: FormBuilder, 
    private authService : AuthService,
    private httpClient : HttpClient) { }

    contractForm: FormGroup;
    formSubmitted: boolean = false;

    latestClaims: any [];
  ngOnInit(): void {
    this.contractForm = this.formBuilder.group({
      username: [this.authService.getUserName(), [Validators.required]],
      // Validators.pattern(this.patternForAlphaNumAndSpecialChars)
      
      policyNumber: ['', [Validators.required]]
    });
    this.getLatestClaims();
  }

  getLatestClaims(){
    this.httpClient.get(environment.claimsService + '/claim/user/'+ this.authService.getUserName())
    .subscribe((data:any)=>{
      this.latestClaims = data;
    });
  }

  submitContractDetails() {
    // If scheduling type code has been changed, then definately changedSavedValueMsg, else if
      console.log(this.contractForm.value);
      this.httpClient.post(environment.claimsService + '/claim/create', this.contractForm.value)
      .subscribe(
        (data:any)=>{
        alert("created successfully with id: "+ data['uuid']);
        this.formSubmitted = true;
        this.contractForm.markAsPristine();
        this.getLatestClaims();
      });
  }


}
