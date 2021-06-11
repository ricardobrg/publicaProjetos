import { Component, OnInit } from '@angular/core';
import { Subject } from 'rxjs';
import { ResponsePoints } from '../point.model';
import { PointService } from '../point.service';

@Component({
  selector: 'app-point-list',
  templateUrl: './point-list.component.html',
  styleUrls: ['./point-list.component.css']
})
export class PointListComponent implements OnInit {

  responsePoints: ResponsePoints[];
  


dtTrigger: Subject < any > = new Subject<any>();
constructor(private pointService: PointService) {
  
 }


ngOnInit(): void {
  let objeto: any = JSON.parse(localStorage.getItem('loggedObject') || '{}');
  console.log(objeto);

  this.dtTrigger.next();
  this.pointService.getPoints()
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

delete (idCollaborator: number, idPoint: number) {
  let loggedCollab: any = localStorage.getItem('loggedObject');
  if (confirm("Tem certeza que deseja excluir o elemento?")) {
    this.pointService.deletePoint(loggedCollab.id, idPoint).subscribe(res => {
      window.location.reload();
  
      // tirar da array
      this.responsePoints = this.responsePoints.filter((element, index, array) => {
        if (element.id === idPoint) return false;
        return true;
        
      })
  
    })

    };
  }
 
}

