import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../authenticate/auth.service';

@Component({
  selector: 'app-header-work',
  templateUrl: './header-work.component.html',
  styleUrls: ['./header-work.component.css']
})
export class HeaderWorkComponent implements OnInit {
  href: any;
  val: boolean=false;

  constructor(private authService:AuthService,
    private router: Router) { }

  ngOnInit() {
    this.href= this.router.url;
    if(this.href=='/employeedashboard'){
      this.val=true;
    }
  }

  onLogout() {
    this.authService.logout();
    this.router.navigate(['/']);
  }

}
