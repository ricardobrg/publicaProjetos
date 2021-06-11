import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { DepartmentService } from '../../department/department.service';
import { RoleService } from '../role.service';

@Component({
  selector: 'app-role-edit',
  templateUrl: './role-edit.component.html',
  styleUrls: ['./role-edit.component.css']
})
export class RoleEditComponent implements OnInit {

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

  constructor(private roleService: RoleService, private departmentService: DepartmentService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.paramMap.get('id');
    this.departmentService.getDepartments().subscribe((data) => {
      this.departments = data;     

      this.roleService.getRole(this.id).subscribe((response) =>{
        this.roleForm.setValue({
          name: response.name,
          sal: response.sal,
          accessLevel: response.accessLevel,
          department: response.department?response.department.id:null,
        });
      });
    });
  }
  
  saveEditForm() {
    let updatedRole = this.roleForm.value;
    this.formatData(updatedRole);
    this.roleService.updateRole(this.id, updatedRole).subscribe(res => {
      window.location.href = "/role-list";
    });
  }

  formatData(data:any) {
    data.department === 'null'?data.department=null:data.department = {id:data.department};
  }
}
