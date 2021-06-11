import { Component, OnDestroy, OnInit } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { VacationService } from './vacation.service';

@Component({
  selector: 'app-vacation',
  templateUrl: './vacation.component.html',
  styleUrls: ['./vacation.component.css']
})
export class vacationComponent implements OnInit {

  vacations: any[];
  
  /**
   * Adding an event emmiter for rendering the
   * DataTable everytime a a request is made
   */
  dtTrigger: Subject<any> = new Subject<any>();

  constructor(private vacationService: VacationService) { }

  ngOnInit(): void {
    this.dtTrigger.next();
    this.vacationService.getvacations()
      .subscribe((res: any[]) => {
        this.vacations = res;
        console.log(this.vacations);
        /**
         * The .next() method is used for rendering the table 
         * right after getting all collaborators from database
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
