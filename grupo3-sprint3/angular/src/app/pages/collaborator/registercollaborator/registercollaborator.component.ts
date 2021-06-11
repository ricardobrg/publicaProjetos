import { preserveWhitespacesDefault } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Collaborator } from 'src/app/models/collaborator/collaborator';
import { Role } from 'src/app/models/role/role';
import { CollaboratorService } from 'src/app/services/collaborator/collaborator.service';
import { RoleService } from 'src/app/services/role/role.service';

@Component({
  selector: 'app-registercollaborator',
  templateUrl: './registercollaborator.component.html',
  styleUrls: ['./registercollaborator.component.css']
})
export class RegistercollaboratorComponent implements OnInit {

  collaboratorForm!: FormGroup
  roles: Role[]

  constructor(private formBuilder: FormBuilder, 
    private collaboratorService: CollaboratorService,
    private roleService: RoleService,
    ) { 
      
    }

  collaborator: Collaborator

  ngOnInit(): void {
    const contact = this.formBuilder.group({
      email: '',
      telephone: ''
    })
    const endereco = this.formBuilder.group({
      cep: ''
    })
    const role = this.formBuilder.group({
      id: 2
    })

    this.collaboratorForm = this.formBuilder.group({
      contact: contact,
      endereco: endereco,
      role: role,
      name: '',
      cpf: '',
      pis: '',
      admissionDate: '',
      pwdHash: '',
      salary: 0,
      workHours: 0,
    })

    this.roleService.get().subscribe(response => {
      this.roles = response
    })

  }

  onSubmit() {
    this.collaboratorService.post(this.collaboratorForm.value)
    this.roleService.get().subscribe(response => console.log(response))
  }


}
