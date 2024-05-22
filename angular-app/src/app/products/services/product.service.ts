import { Injectable } from '@angular/core';
import { Product } from '../models/product';
import { Observable, map, of } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { observableToBeFn } from 'rxjs/internal/testing/TestScheduler';
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

  private url: string = 'http://localhost:8080/products';

  constructor(private http: HttpClient) { }

  /*Las funciones/observables son flujos cuando se conectan al backend y de forma
  programática podemos cancelar la petición por cualquier situación.
  Son asincronos*/
  findAll(): Observable<Product[]> {
    /* Transforma un dato en un objeto reactivo y observable*/
    //return of(this.products);

    /*<Product[]>
    Convierte el json en un objeto Product[] pero es redundante
    ya que en la linea 40, al final ya estamos definiendo que esos
    objetos se conviertan  aun arreglo de Product*/
    return this.http.get<Product[]>(this.url).pipe(
      map((response: any) => response._embedded.products as Product[]),
    );
  }

  create(product: Product): Observable<Product>{
    /*<Product>
    Convierte el json product en un objeto Product*/
    return this.http.post<Product>(this.url, product);
  }

  update(product: Product): Observable<Product>{
    /*<Product>
    Convierte el json product en un objeto Product*/
    return this.http.put<Product>(`${this.url}/${product.id}`, product);
  }

  remove(id: number): Observable<void>{
    return this.http.delete<void>(`${this.url}/${id}`)
  }
}
