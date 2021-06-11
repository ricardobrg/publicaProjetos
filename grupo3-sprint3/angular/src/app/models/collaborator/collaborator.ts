export interface Collaborator {
    id:number
    contact:{
        email:string,
        telephone:string
        id:number
    }
    endereco: {
        cep:string
        id:number
    }
    role: {
        id:number
    }
    name:string
    cpf:string
    pis:string
    admissionDate:string
    lastVacationDate:string
    salary:number
    workHours:number
}