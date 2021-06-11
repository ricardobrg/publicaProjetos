import { Component, OnInit } from '@angular/core';
import { Subject } from 'rxjs';
import { ResponseDepartments } from '../department.model';
import { DepartmentService } from '../department.service';

@Component({
  selector: 'app-department-list',
  templateUrl: './department-list.component.html',
  styleUrls: ['./department-list.component.css']
})
export class DepartmentListComponent implements OnInit {

  responseDepartments : ResponseDepartments[];
  
  dtTrigger: Subject<any> = new Subject<any>();
  constructor(private departmentService: DepartmentService) { }
  
  ngOnInit(): void {
    if (!localStorage.token) {
      window.location.href= "/login";
    }

    this.departmentService.getDepartments()
      .subscribe((res) => {
        this.responseDepartments = res;
        
        /**
         * The .next() method is used for rendering the table 
         * right after getting all collaborators from database
         */
        this.dtTrigger.next();
      });
    }

  delete(id:number) {
    if (confirm("Tem certeza que deseja excluir o elemento?")) {
      this.departmentService.deleteDepartment(id).subscribe((response) => {
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



