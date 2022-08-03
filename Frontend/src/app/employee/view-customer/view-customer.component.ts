import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { EmpService } from '../emp.service';

@Component({
  selector: 'app-view-customer',
  templateUrl: './view-customer.component.html',
  styleUrls: ['./view-customer.component.css']
})
export class ViewCustomerComponent implements OnInit {

  custDetails;
  constructor(private empService:EmpService,
    private route:ActivatedRoute) { }

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      var id = params.get('id');
      console.log(id);
    });
    let id=this.route.snapshot.params.id;
    console.log(id);
    this.custDetails = this.empService.viewCustomer(id).subscribe((val)=>{
      this.custDetails = val;
      console.log(this.custDetails);
    });
  }

}
