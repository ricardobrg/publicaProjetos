import { Component, OnInit } from '@angular/core';
import { Subject } from 'rxjs';
import { ResponsePoints } from '../../point/point.model';
import { PointService } from '../../point/point.service';

@Component({
  selector: 'app-profile-point',
  templateUrl: './profile-point.component.html',
  styleUrls: ['./profile-point.component.css']
})
export class ProfilePointComponent implements OnInit {

  responsePoints: ResponsePoints[];
  


  dtTrigger: Subject < any > = new Subject<any>();
  constructor(private pointService: PointService) {
    
   }
  
  
  ngOnInit(): void {
    let objeto: any = JSON.parse(localStorage.getItem('loggedObject') || '{}');
    console.log(objeto);
  
    this.dtTrigger.next();
    this.pointService.getCollaboratorPoints(objeto.id)
      .subscribe((res) => {
        this.responsePoints = res;
  
        /**
         * The .next() method is used for rendering the table 
         * right after getting all points from database
         */
        this.dtTrigger.next();
      });
  }
  /**
   * This method removes the event emmiter 
   * from datatables when the page is destroyed
   */
  ngOnDestroy(): void {
    this.dtTrigger.unsubscribe();
  }

}
