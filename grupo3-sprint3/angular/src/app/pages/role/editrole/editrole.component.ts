import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Role } from 'src/app/models/role/role';
import { RoleService } from 'src/app/services/role/role.service';

@Component({
  selector: 'app-editrole',
  templateUrl: './editrole.component.html',
  styleUrls: ['./editrole.component.css']
})
export class EditroleComponent implements OnInit {

  roleForm!: FormGroup
  role: Role


  constructor(private formBuilder: FormBuilder,
    private roleService: RoleService,
  ) { }

  ngOnInit(): void {

    this.roleService.getOne(window.location.search.replace("?id=","")).subscribe(response => {
      this.role = response
      this.roleForm = this.formBuilder.group({
        name: this.role.name,
        accessLevel: this.role.accessLevel
      })
    })

  }

  onSubmit() {
    this.roleService.put(this.roleForm.value, window.location.search.replace("?id=",""))
  }
}
