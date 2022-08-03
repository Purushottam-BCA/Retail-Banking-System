import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../authenticate/auth.service';

@Component({
  selector: 'app-header-cust',
  templateUrl: './header-cust.component.html',
  styleUrls: ['./header-cust.component.css']
})
export class HeaderCustComponent implements OnInit {

  constructor(private authService:AuthService,
    private router:Router) { }

  ngOnInit() {
  }

  onLogout() {
    this.authService.logout();
    this.router.navigate(['/']);
  }

}
