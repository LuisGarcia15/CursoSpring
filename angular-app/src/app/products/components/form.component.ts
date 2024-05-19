import { Component, EventEmitter, Input, Output } from '@angular/core';
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
  @Input() product : Product = {
    /*Así como pasamos registros del comonente hijo al componente padre
    con la función @Output, con la función @Input en el atributo
    que guarda productos guardará los registros selccionados en el
    componente padres*/
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