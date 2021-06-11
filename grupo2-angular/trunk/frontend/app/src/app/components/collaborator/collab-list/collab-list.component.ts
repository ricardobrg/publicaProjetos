import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subject } from 'rxjs';
import { CollaboratorService } from '../collaborator.service';

@Component({
  selector: 'app-collab-list',
  templateUrl: './collab-list.component.html',
  styleUrls: ['./collab-list.component.css']
})
export class CollabListComponent implements OnInit, OnDestroy {

  collabs: any[];
  
  /**
   * Adding an event emmiter for rendering the
   * DataTable everytime a a request is made
   */
  dtTrigger: Subject<any> = new Subject<any>();

  constructor(private collaboratorService: CollaboratorService) { }

  ngOnInit(): void {
    if (!localStorage.token) {
      window.location.href = '/login';
    }
    

    this.dtTrigger.next();
    this.collaboratorService.getCollabs()
      .subscribe((res) => {
        this.collabs = res;
        
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

  generatePayroll(id : number){
    if (confirm("Deseja geral a folha de pagamento deste colaborador?")) {
      this.collaboratorService.generatePayroll(id).subscribe((response) => {
        window.location.reload();
      });
    }
  }

  deleteCollab() {
    if (confirm("Tem certeza que deseja apagar o colaborador? ")) {
    // TODO: create collab service
    // TODO: Rerender datatable
    }
  }
}

