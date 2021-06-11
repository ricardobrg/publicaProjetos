import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, CanActivateChild, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { VerificarPermissoes } from './verificarPermissoes';

@Injectable({
  providedIn: 'root'
})
export class AuthguardService implements CanActivateChild, CanActivate {

  constructor(private router: Router) { }

  public canActivate(activated: ActivatedRouteSnapshot): Observable<boolean> {
    return this.checarRota(activated)
  }

  public canActivateChild(childRoute: ActivatedRouteSnapshot): Observable<boolean> {
    return this.checarRota(childRoute)
  }

  protected checarRota(activated: ActivatedRouteSnapshot): Observable<boolean> {
    if (typeof activated.data['roles'] !== undefined && activated.data['roles'].length){
      const rolesRota = activated.data['roles']
      const roles = JSON.parse(sessionStorage.getItem('accessLevel'))

      return new Observable<boolean>(subscriber => {
        if(!VerificarPermissoes.temPermissao(rolesRota, roles)) {
          subscriber.next(false)
          this.router.navigate(['/acesso-negado'])
        }else{
          subscriber.next(true)
        }
      })
    }
    return new Observable<boolean>(subscriber => subscriber.next(true))
  }

}
