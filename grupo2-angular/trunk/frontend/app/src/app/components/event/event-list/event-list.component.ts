import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subject } from 'rxjs';
import { EventService } from '../event.service';

@Component({
  selector: 'app-event-list',
  templateUrl: './event-list.component.html',
  styleUrls: ['./event-list.component.css']
})
export class EventListComponent implements OnInit, OnDestroy {

  eventList : any[];

  /**
   * Adding an event emmiter for rendering the
   * DataTable everytime a a request is made
   */
  dtTrigger: Subject<any> = new Subject<any>();

  constructor(private eventService: EventService) { }

  ngOnInit(): void {
    this.eventService.getEvents().subscribe((response) =>{
      this.eventList = response;
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

  formatDate(data:any){
    let dateObject = new Date(Date.parse(data));
    return `${dateObject.getDate()}/${dateObject.getMonth()+1}/${dateObject.getFullYear()} - 
            ${dateObject.getHours()}:${dateObject.getMinutes()<10?'0'+dateObject.getMinutes():dateObject.getMinutes()}`;
  }
}
