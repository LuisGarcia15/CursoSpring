import { Injectable } from '@angular/core';
import { Product } from '../models/product';
/*Clase que se va a conectar a la BD y obtener registros*/
@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private products: Product[] = [
    {
      id:1,
      name: 'Telefono Samsung',
      description: 'Samsung 2024',
      price: 399
    },
    {
      id:2,
      name: 'Telefono Apple',
      description: 'iPhone 2024',
      price: 399
    }
  ];
  constructor() { }
}
