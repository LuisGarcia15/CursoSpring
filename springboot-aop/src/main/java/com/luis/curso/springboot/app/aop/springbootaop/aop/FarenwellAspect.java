package com.luis.curso.springboot.app.aop.springbootaop.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)
/*Order
 *Permite ordenar la ejecución de aspectos
 *
 *El orden 1 indica que es el primero en entrar y ultimo en salir.
 * Las ordenes menores tienen mayor prioridad, por lo que un aspecto
 * con orden 3 se ejecuta primero que un aspeecto con orden 2, y un
 * apecto con orden 2 se ejecuta primero que un aspecto de orden 1
*/
@Component
@Aspect
public class FarenwellAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before("PointcutsFarenwellServices.pointCutClass()")
    public void loggerBefore(JoinPoint joinPoint){
        /*La clase JoinPoint provee de la metadata del método que intercepto
         * el advice, tal metadata como nombre del método, sus parámetros, y todo
         * lo que tenga que ver con el método interceptado. Siempre debe existir
         * ese parámetro en advices
        */
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        logger.info(
            "@Before|FarenwellAspect[Nombre de método[" + method + "] invocados con los parámetros {" + args +"}]");
    }

    @After("PointcutsFarenwellServices.pointCutClass()")
    public void loggerAfter(JoinPoint joinPoint){
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        logger.info(
            "@After|FarenwellAspect[Nombre de método[" + method + "] con argumentos {" + args +"}]");
    }
}
