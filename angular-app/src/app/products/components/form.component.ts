import { Component, EventEmitter, Output } from '@angular/core';
import { Product } from '../models/product';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'product-form',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './form.component.html',
  styleUrl: './form.component.css'
})
export class FormComponent {

  //Product es una variable que hace referencia a un modelo, perfecto
  //para mapear valores de tablas a atributos de un objeto
  product : Product = {
    id: 0,
    name: '',
    description: '',
    price: 0
  };

    /*Decorador de Angular, permite emitir data desde el componente hijo hacia el componente
    padre*/
    @Output() newProductEvent = new EventEmitter();

    onSubmit(): void{
      //Emite el objeto producto al componente padre
      this.newProductEvent.emit(this.product);
      console.log(this.product);
    }
}
