import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { DepartmentService } from '../../department/department.service';
import { RoleService } from '../role.service';

@Component({
  selector: 'app-role-form',
  templateUrl: './role-form.component.html',
  styleUrls: ['./role-form.component.css']
})
export class RoleFormComponent implements OnInit {
  
  departments: any[];
  id: any;

  /**
   * Define fields that will be used for adding on edit Role
   */
  roleForm = new FormGroup({
    name: new FormControl(),
    department: new FormControl('null'),
    sal: new FormControl(),
    accessLevel: new FormControl('BASIC'), 
  });

  constructor(private roleService: RoleService, private departmentService: DepartmentService) { }

  ngOnInit(): void {
    this.departmentService.getDepartments().subscribe((data) => {
      this.departments = data;
    });
  }

  saveData() {
    let newRole = this.roleForm.value;
    this.formatData(newRole);
    this.roleService.saveRole(newRole).subscribe((res)=>{
      console.log(res);
    });
  }

  formatData(data: any) {
    data.department==='null'?data.department=null:data.department = {id: parseInt(data.department)};
  }

}
