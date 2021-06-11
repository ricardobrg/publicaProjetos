import { Component, OnDestroy, OnInit } from '@angular/core';
import { RoleService } from '../role.service';
import { ResponseRoles } from '../role.model';
import { Subject } from 'rxjs';

@Component({
  selector: 'app-role-role-list',
  templateUrl: './role-list.component.html',
  styleUrls: ['./role-list.component.css']
})
export class RoleListComponent implements OnInit, OnDestroy {

  responseRoles: ResponseRoles[]; //qqr coisa - mudar para any

  /**
   * Adding an event emmiter for rendering the
   * DataTable everytime a a request is made
   */
  dtTrigger: Subject<any> = new Subject<any>();

  constructor(private roleService: RoleService) { }

  ngOnInit(): void {
    if (!localStorage.token) {
      window.location.href= "/login";
    }

    this.dtTrigger.next();
    this.roleService.getRoles()
      .subscribe((res) => {
        this.responseRoles = res;

        /**
        * The .next() method is used for rendering the table 
        * right after getting all collaborators from database
        */
        this.dtTrigger.next();
      });
  }

  delete(id:number) {
    if (confirm("Tem certeza que deseja excluir o elemento?")) {
      this.roleService.deleteRole(id).subscribe((response) => {
        window.location.reload();
      });
    }
  }

  /**
   * This method removes the event emmiter 
   * from datatables when the page is destroyed
   */
  ngOnDestroy(): void {
    this.dtTrigger.unsubscribe();
  }
}