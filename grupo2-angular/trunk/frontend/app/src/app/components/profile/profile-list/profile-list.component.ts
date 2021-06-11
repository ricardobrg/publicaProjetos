import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Subject } from 'rxjs';
import { Collaborator } from '../profile.model';
import { ProfileService } from '../profile.service';

@Component({
  selector: 'app-profile-list',
  templateUrl: './profile-list.component.html',
  styleUrls: ['./profile-list.component.css']
})
export class ProfileListComponent implements OnInit {

  collaborator : Collaborator;


  
  dtTrigger: Subject<Collaborator> = new Subject<Collaborator>();
  constructor(private profileService: ProfileService) { }

  profile: any;

  
  fillProfile(){
    this.profileService.getProfile(); 
  }
  
  ngOnInit(): void {
    
    this.profileService.getProfile().subscribe((res: Collaborator) => {
      this.profile = res
      console.log(res);
    })
  }

  /**
   * This method removes the event emmiter 
   * from datatables when the page is destroyed
   */
  ngOnDestroy(): void {
    this.dtTrigger.unsubscribe();
  }

}
