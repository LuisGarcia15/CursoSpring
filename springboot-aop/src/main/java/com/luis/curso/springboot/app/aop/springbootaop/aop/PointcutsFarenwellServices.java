package com.luis.curso.springboot.app.aop.springbootaop.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PointcutsFarenwellServices {

    @Pointcut("execution(String com.luis.curso.springboot.app.aop.springbootaop.services.GreetingService.*(..))")
    public void pointCutClass(){}
    /*Un advice obtendra el contexto del pointcut que debe interceptar del contenedor
     * de Spring, pues una clase de Pointcuts es un componente de Spring y un Aspecto.
     * 
     * En el advice se colocara el nombre de la clase de los pointcuts, seguido
     * de un punto y terminando con el nombre de método, en este caso "pointCuClass()"
     * siempre y cuando se encuentre en el mismo paquete, de no ser así, se debe
     * incluir el paquete seguido de todo lo demás arriba descrito.
     * Debe incluir los ().
     * Los métodos Poincuts deben ser publicos, a menos que se encuentren en la misma
     * clase de definición de los advices
    */

}
