export interface Role {
    id: number;
    name: string;
    department: Department;
    sal: number;
    accessLevel: string;
}

export interface Department {
    id: number;
    name: string;
}

export interface ResponseRoles {
    id: number;
    name: string;
    department: Department;
    sal: number;
    accessLevel: string;
}

//Modelos de Response para o Get
export interface ResponseRole {
    data: Role;
}

//Modelos de Request e Response para o Update
export interface RequestUpdate {
    name: string;
    department: Department;
    sal: number;
    accessLevel: string;
}

export interface ResponseUpdate {
    name: string;
    department: Department;
    sal: number;
    accessLevel: string;
}