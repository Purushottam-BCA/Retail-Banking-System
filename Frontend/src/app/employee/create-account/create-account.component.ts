import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { EmpService } from '../emp.service';

@Component({
  selector: 'app-create-account',
  templateUrl: './create-account.component.html',
  styleUrls: ['./create-account.component.css']
})
export class CreateAccountComponent implements OnInit {
  id: string;
  createmsg: string;
  errormsg: string;
  constructor(private route:ActivatedRoute,
    private empService:EmpService) { }

  ngOnInit() {
    this.route.paramMap.subscribe((params)=>{
      this.id=params.get('id');
    })
  }

  createAccount(f){
    let account={
      "customerId":this.id,
      "currentBalance":f.value.Amount,
      "accountType":f.value.accountType,
      "openingDate": f.value.dob,
      "ownerName": f.value.ownername
    };
    this.empService.createAccount(account,this.id).subscribe((val)=>{
      console.log(val);
      this.createmsg="Created Account";
    },error=>{
      console.log(error);
      this.errormsg="Error in Account Creation"
    })
  }

}
