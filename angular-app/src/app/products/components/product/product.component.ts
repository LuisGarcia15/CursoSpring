import { Component, OnInit } from '@angular/core';
import { ProductService } from '../../services/product.service';
import { Product } from '../../models/product';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { FormComponent } from '../form.component';

@Component({
  selector: 'app-product',
  standalone: true,
  /*colocamos un componente hijo de un formulario en este componente de product.

RECUERDA
para que el componente pueda existir, debemos importar el componente hijo
en el componente padre en el archivo ts y colocar su selector del componente
hijo en el archivo html del componente padre, en este caso FormComponent como
import*/
  imports: [CommonModule, RouterOutlet, FormComponent],
  templateUrl: './product.component.html',
  styleUrl: './product.component.css'
})
export class ProductComponent implements OnInit{
    products:Product[] = [];
  /*Un constructor de un componente siempre se define como
  constructor. un párametro que se defina su scope, será tambien
  un atributo de clase*/

  /*Para actualizar un registro, en el componente de la tabla, creamos
  un atributo que guardará el producto seleccionado*/
  productSelected: Product = new Product();
  /*Se inicializa con los valores predeterminados del modelo producto*/
  constructor(private service: ProductService){}

  /*Método que se ejecuta cuando se crea el componente.
  ES UN MÉTODO DE LA INTERFAZ OnInit*/
  ngOnInit():void{
    this.service.findAll().subscribe(products => {
      this.products = products;
    })
    /*Con subscribe podemos manejar las peticiones, desde cancelarla
    hasta tratar los elementos obtenidos*/
  }

  /*Método que maneja el producto creado recientemente enviado por el
  componente hijo, como envia un producto, el parámetro a recibir es
  un objeto producto*/
  addProduct(product: Product){
    /*primera forma de agregar un elemento a la tabla front end
    Utilizar, en el arreglo existente, la función push y pasar el objeto
    existente*/
    if(product.id > 0){
      /*gracias a service, actualiza los datos en la BD y posteriormente,
      actualiza los datos en el bckend*/
      this.service.update(product).subscribe(productUpdated => {
        this.products = this.products.map(prod =>{
          if(prod.id == product.id){
            return {... productUpdated}
          }
          return prod;
        });
      });
      /*La función map es inmutable, no modifica exactamente
      un objeto, sino que crea una nueva instancia de esta y
      en esa nueva instancia guarda los elementos modificados.
      esa nueva instancia se pasa a products*/
    }else{
      /*Crea un nuevo registro en la BD y posteriormente crea un
      nuevo registro y lo añade a la tabla del backend*/
      this.service.create(product).subscribe(productNew => {
        this.products.push(product);
      })
      /*segunda forma de agregar un elemento a la tabla front end
      Esparcimos todos los objetos de un array anteriormente
      existente, y agregamos un nuevo elemento a ese arreglo.
      Similar a react, donde en react solo existe esta forma*/
      //this.products = [... this.products, {...product, id: new Date().getTime()}]
    }
    this.productSelected = new Product();
  }

  /*Crearemos tambien un método para, dado el producto seleccionado, pasar
  ese registro al atributo que definimos para guardar un registro*/
  onUpdateProduct(productRow: Product){
    /*Una lista de una tabla en Angular es inmutable, por lo que lo
    correcto es, apartir del objeto Producto, hacer una copia del
    producto y la copia del producto pasarla al atributo
    productSelected*/
    this.productSelected = {...productRow};
  }

  onRemoveProduct(id: number): void{
    this.products = this.products.filter(product => product.id != id);
  }
}
