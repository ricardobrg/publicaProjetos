import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { CollaboratorService } from '../../collaborator/collaborator.service';
import { PointService } from '../point.service';

@Component({
  selector: 'app-point-add',
  templateUrl: './point-add.component.html',
  styleUrls: ['./point-add.component.css']
})
export class PointAddComponent implements OnInit {

  collaborators: any[];
  loggedName: string = (localStorage.loggedObject).name;

  /**
   * Define fields that will be used for adding collaborator
   */



  constructor(
    private pointService: PointService,
  ) { }

  ngOnInit(): void {
    if (!localStorage.token) {
      window.location.href = '/';
    }
  }

  saveForm() {
    this.pointService.addPoint(JSON.parse(localStorage.loggedObject).id).subscribe(data=>{
      console.log(data);
      window.location.href = "/point-list";
    });
  }

}
