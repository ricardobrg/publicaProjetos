import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { DepartmentService } from '../department.service';


@Component({
  selector: 'app-add-department',
  templateUrl: './add-department.component.html',
  styleUrls: ['./add-department.component.css']
})
export class AddDepartmentComponent implements OnInit {

  /**
   * Define fields that will be used for adding department
   */
  form = new FormGroup({
    name: new FormControl(),
  });
  
  constructor(private departmentService: DepartmentService) { }

  ngOnInit(): void {
    if (!localStorage.token) {
      window.location.href= "/login";
    }
  }

  saveForm() {
    this.departmentService.insertDepartment(this.form.value).subscribe(data=>{
      console.log(data);
      window.location.href = "/department-list";
    });
  }
}
