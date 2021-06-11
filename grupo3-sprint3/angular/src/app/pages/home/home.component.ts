import { Component, OnInit } from '@angular/core';
import { VerificarPermissoes } from 'src/app/services/auth/verificarPermissoes';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
    const rolesUsuario =JSON.parse(sessionStorage.getItem('accessLevel'))

    if(VerificarPermissoes.temPermissao(rolesUsuario, 0)){
      window.location.href = "http://localhost:4200/login"
    }

  }
}
