import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { RoleService } from 'src/app/services/role/role.service';

@Component({
  selector: 'app-registerrole',
  templateUrl: './registerrole.component.html',
  styleUrls: ['./registerrole.component.css']
})
export class RegisterroleComponent implements OnInit {

  roleForm!: FormGroup

  constructor(private formBuilder: FormBuilder,
    private roleService: RoleService,
  ) { }

  ngOnInit(): void {

    this.roleForm = this.formBuilder.group({
      name: '',
      accessLevel: ''
    })

  }

  onSubmit() {
    this.roleService.post(this.roleForm.value)
  }
}
