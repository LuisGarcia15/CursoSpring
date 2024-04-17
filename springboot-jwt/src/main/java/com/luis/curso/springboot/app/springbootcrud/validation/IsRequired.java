package com.luis.curso.springboot.app.springbootcrud.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = RequiredValidation.class)
/*Constraint
 * con el valor validateBy:
 * enlaza el constraint a la clase que verifica el contraint por
 * anotaciones. De hecho validateBy indica que clase validadora
 * valida este constraint. cuando evalua el atributo description
 * viene a esta "anotacion" y verifica la anotacion
 * constraint para saber que clase verifica este constraint.
 * Pasa a RequiredValidation para evaluar Description, si refulta false
 * retorna el message definido
*/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
//INDICA en que contextos se va a aplicar la anotacion interface
//Este target, este constraint puede ser aplicado a campos de clase
//y métodos
public @interface IsRequired {
    /*@interface
     * Permite definir una anotación personalizada, es por eso
     * que el atributo description de Product tiene una anotacion llamada
     * con el mismo nombre de esta "clase"
    */
    String message() default "Requerido usando anotaciones";
    //Son atributos que tienen todos las anotaciones de validacion
    //Por ende solo se necesita crear varios RequiredValidation para
    //Evaluar el tipo de campo distinto
	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}

/*@Retention 
en Java se utiliza para especificar cómo y durante cuánto tiempo una anotación personalizada 
(con @interface) estará disponible y se conservará durante la ejecución del programa.

La anotación @Retention tiene una enumeración RetentionPolicy que se utiliza para indicar el 
nivel de retención de la anotación. Los posibles valores de RetentionPolicy son:

RetentionPolicy.SOURCE: Esta es la retención más baja. Las anotaciones con esta retención se 
conservan solo en el código fuente y no se incluyen en el bytecode generado. Son útiles durante 
la compilación para realizar verificaciones estáticas.

RetentionPolicy.CLASS: Las anotaciones con esta retención se conservan en el archivo de clase 
(.class) pero no están disponibles en tiempo de ejecución. Son útiles durante el tiempo de 
compilación y pueden ser procesadas por herramientas de análisis estático.

RetentionPolicy.RUNTIME: Las anotaciones con esta retención se conservan en tiempo de ejecución 
y están disponibles para ser leídas a través de la reflexión en tiempo de ejecución. Esta es 
la retención más alta y se utiliza cuando necesitas procesar las anotaciones durante la ejecución 
del programa.*/
