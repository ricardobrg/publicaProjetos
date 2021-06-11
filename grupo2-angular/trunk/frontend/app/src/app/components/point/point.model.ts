export interface Collaborator {
    id: number;
    name: string;
    contact?: any;
    address?: any;
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

export interface ResponsePoints {
    id: number;
    collaborator: Collaborator;
    date: string;
    dayMinute: number;
}
