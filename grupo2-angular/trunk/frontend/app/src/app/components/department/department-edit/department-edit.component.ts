import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { DepartmentService } from '../department.service';


@Component({
  selector: 'app-department-edit',
  templateUrl: './department-edit.component.html',
  styleUrls: ['./department-edit.component.css']
})
export class DepartmentEditComponent implements OnInit {

  departmentId :any;

  form = new FormGroup({
    name: new FormControl(),
  });

  constructor(private route:ActivatedRoute,
              private departmentService: DepartmentService) { }

  ngOnInit(): void {
    if (!localStorage.token) {
      window.location.href= "/login";
    }

    this.departmentId = this.route.snapshot.paramMap.get('id');
    this.departmentService.getWithId(parseInt(this.departmentId)).subscribe((response) =>{
      this.form.controls.name.setValue(response.name);
    });
  }

  submitForm() {
    this.departmentService.editDepartment(this.departmentId, this.form.value).subscribe(() =>{
      window.location.href = '/department-list';
    });
  }

}
