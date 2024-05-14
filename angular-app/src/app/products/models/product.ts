/*Modelo similar a una clase POJO para guardar atributos
de una tabla de una BD*/
export class Product{
    /*Los valores en un model, debe ser incializados o,
    con !, definir que posteiormente se van a inicializar*/
    id!: number;
    name: string = '';
    description: string = '';
    price!: number;
}