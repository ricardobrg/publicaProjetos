export interface Provider {
    id:number,
    contact:{
        id:number,
        email:string,
        telephone:string
    }
    endereco: {
        id:number,
        cep:string
    }
    name:string
    cnpj:string
    socialReason:string
    description:string
}