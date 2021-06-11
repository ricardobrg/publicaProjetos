import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subject } from 'rxjs';
import { Collaborator } from 'src/app/models/collaborator/collaborator';
import { CollaboratorService } from 'src/app/services/collaborator/collaborator.service';

@Component({
  selector: 'app-listcollaborator',
  templateUrl: './listcollaborator.component.html',
  styleUrls: ['./listcollaborator.component.css']
})
export class ListcollaboratorComponent implements OnInit, OnDestroy {

  dtOptions: DataTables.Settings = {}
  dtTrigger: Subject<any> = new Subject();

  entries: Collaborator[]

  selectedCollab: Collaborator

  constructor(private collaboratorService: CollaboratorService) { }

  ngOnInit(): void {
    this.collaboratorService.get().subscribe(response => {
      this.entries = response
      console.log(response)
      this.dtTrigger.next();
    })
  }

  ngOnDestroy(): void {
    this.dtTrigger.unsubscribe();
  }

  edit(id){
    window.location.href="http://localhost:4200/collaborator/edit?id="+id
  }

  delete(){
    this.collaboratorService.delete(this.selectedCollab.id)
  }

  selectId(collab){
    this.selectedCollab = collab;
  }
}
