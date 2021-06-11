import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subject } from 'rxjs';
import { Role } from 'src/app/models/role/role';
import { RoleService } from 'src/app/services/role/role.service';

@Component({
  selector: 'app-listrole',
  templateUrl: './listrole.component.html',
  styleUrls: ['./listrole.component.css']
})
export class ListroleComponent implements OnInit, OnDestroy {

  dtOptions: DataTables.Settings = {}
  dtTrigger: Subject<any> = new Subject();

  entries: Role[]

  selectedRole: Role

  constructor(private roleService: RoleService) { }

  ngOnInit(): void {
    this.roleService.get().subscribe(response => {
      this.entries = response
      console.log(response)
      this.dtTrigger.next();
    })
  }

  ngOnDestroy(): void {
    this.dtTrigger.unsubscribe();
  }

  edit(id){
    window.location.href="http://localhost:4200/role/edit?id="+id
  }

  delete(){
    this.roleService.delete(this.selectedRole.id)
  }

  selectId(role){
    this.selectedRole = role
  }

}
