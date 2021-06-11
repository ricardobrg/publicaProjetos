import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Collaborator } from 'src/app/models/collaborator/collaborator';
import { Role } from 'src/app/models/role/role';
import { CollaboratorService } from 'src/app/services/collaborator/collaborator.service';
import { RoleService } from 'src/app/services/role/role.service';

@Component({
  selector: 'app-editcollaborator',
  templateUrl: './editcollaborator.component.html',
  styleUrls: ['./editcollaborator.component.css']
})
export class EditcollaboratorComponent implements OnInit {

  collaboratorForm!: FormGroup
  collaborator: Collaborator
  roles: Role[]

  constructor(private formBuilder: FormBuilder,
    private roleService: RoleService,
    private collabService: CollaboratorService
  ) { }

  ngOnInit(): void {

    this.collabService.getOne(window.location.search.replace("?id=","")).subscribe(response => {
      this.collaborator = response
      const contact = this.formBuilder.group({
        email: this.collaborator.contact.email,
        telephone: this.collaborator.contact.telephone,
        id: this.collaborator.contact.id
      })
      const endereco = this.formBuilder.group({
        cep: this.collaborator.endereco.cep,
        id: this.collaborator.endereco.id
      })
      const role = this.formBuilder.group({
        id: this.collaborator.role.id
      })
      this.collaboratorForm = this.formBuilder.group({
        contact: contact,
        endereco: endereco,
        role: role,
        name: this.collaborator.name,
        cpf: this.collaborator.cpf,
        pis: this.collaborator.pis,
        admissionDate: this.collaborator.admissionDate,
        salary: this.collaborator.salary,
        pwdHash: '',
        workHours: this.collaborator.workHours
      })
    })

    this.roleService.get().subscribe(response => {
      this.roles = response
    })

  }

  onSubmit() {
    this.collabService.put(this.collaboratorForm.value, window.location.search.replace("?id=",""))
  }

}
