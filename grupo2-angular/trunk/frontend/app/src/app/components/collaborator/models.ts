export interface Contact {
    email: string;
    phone: string;
}

export interface Address {
    cep: string;
    uf: string;
    city: string;
    district: string;
    street: string;
    number: number;
    complement: string;
}

export interface Collaborator {
    name: string;
    cpf: string;
    admissionDate: string;
    role: {id: number, name: string, accessLevel: number};
}
