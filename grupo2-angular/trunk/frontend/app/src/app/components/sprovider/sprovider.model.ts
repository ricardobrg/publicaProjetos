export interface SProvider {
    id: number;
    name: string;
    contact: Contact;
    address: Address;
    cnpj: string;
    socialReason: string;
    description: string;
    price: number;
}

export interface Contact {
    email: string;
    phone: string;
}

export interface Address {
    cep: string;
    uf: string;
    locality: string;
    district: string;
    street: string;
    number: number;
    complement: string;
}

export interface ResponseProviders {
    id: number;
    name: string;
    contact: Contact;
    address: Address;
    cnpj: string;
    socialReason: string;
    description: string;
    price: number;
}

//Modelos de Request e Response para o Update
export interface RequestUpdate {
    name: string;
    contact: Contact;
    address: Address;
    cnpj: string;
    socialReason: string;
    description: string;
    price: number;
}

export interface ResponseUpdate {
    contact: Contact;
    address: Address;
    description: string;
    price: number;
}