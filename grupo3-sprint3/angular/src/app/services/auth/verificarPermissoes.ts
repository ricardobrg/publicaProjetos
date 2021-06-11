export class VerificarPermissoes{

    public static temPermissao(roles: number, permissoesDoUsuario: number):boolean{

            if(permissoesDoUsuario >= roles){
                return true
            }

        return false
    }

}