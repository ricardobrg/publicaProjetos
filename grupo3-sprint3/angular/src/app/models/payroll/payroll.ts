export interface Payroll {
    id: number,
    collaborator:{
        cpf:string
    },
    discounts: Array<{
        name:string
        value:number
        type:number
    }>,
    initDate:string,
    finalDate:string,
    extraHourPayment: number,
    netSalary: number
}