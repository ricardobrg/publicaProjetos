import { Manager } from "../department/department.model";

export interface Contact {
    email: string;
    phone: string;
}

export interface Address {
    cep: string;
    uf: string;
    city: string;
    locality: string;
    district: string;
    street: string;
    number: number;
    complement: string;
}

export interface Collaborator {
    name: string;
    cpf: string;
    pis: string;
    role: {id: number, name: string, accessLevel: number},
    //department: {id: number, name:string};
    admissionDate: string;
    inVacation: boolean;
    lastVacation: string;
    contact: Contact;
    address: Address;

}
