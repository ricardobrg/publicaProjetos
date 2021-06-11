import { Component, Input, OnInit } from '@angular/core';
import { VerificarPermissoes } from 'src/app/services/auth/verificarPermissoes';

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class CardComponent implements OnInit {

  @Input() image: string
  @Input() title: string
  @Input() url1: string
  @Input() url2: string
  @Input() sub1: string
  @Input() sub2: string
  @Input() modal1: string
  @Input() modal2: string

  constructor() { }

  ngOnInit(): void {}
  public verificarRole(rolesFuncionalidade: number): boolean{
    const rolesUsuario =JSON.parse(sessionStorage.getItem('accessLevel'))
    return VerificarPermissoes.temPermissao(rolesFuncionalidade, rolesUsuario)
  }
}
