export interface Contact {
    id: number;
    phone: string;
    email: string;
}

export interface Address {
    id: number;
    cep: string;
    locality: string;
    uf: string;
    district: string;
    street: string;
    complement: string;
}

export interface Manager {
    id: number;
    name: string;
    contact: Contact;
    address: Address;
    cpf: string;
    pis?: any;
    admissionDate?: any;
    lastVacation?: any;
    vacationSize: string;
    inVacation: boolean;
    canVacation: boolean;
    extraSalary: number;
    grossSalary: number;
    workHours: number;
    user?: any;
    password?: any;
    role?: any;
    discounts: any[];
}

export interface ResponseDepartments {
    id: number;
    name: string;
    manager: Manager;
}

