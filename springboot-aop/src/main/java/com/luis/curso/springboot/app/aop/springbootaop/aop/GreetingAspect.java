package com.luis.curso.springboot.app.aop.springbootaop.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(2)
@Aspect
/* Declara una clase como aspecto para Spring que tendra
 * advices que se ejecutaran cuando el pointcut se ejecute
*/
@Component
/*Para que un Aspect intercepte un código, debe ser un componente
 * en el contenedor de Spring
*/
public class GreetingAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(String com.luis.curso.springboot.app.aop.springbootaop.services.GreetingService.*(..))")
    /*Pointcut
     * Se puede definir un método que contenda la anotación @Pointcut para escribir
     * un pointcut que utilicen varios advices, de esta forma podemos optimizar mejor
     * el código
    */
    public void pointCut(){}

    /*execution(* com.luis.curso.springboot.app.aop.springbootaop.*.*(..))
     * Indica que se ejecuta en cualquier método a nivel de carpeta principal
     * del proyecto, cualquier método y cualquier método que regrese cualquier
     * cosa
    */
    @Before("pointCut()")
    /* Before(pointcut)
     *Contexto donde se interceptará la ejecución de un código. Indica
     * cuando interceptará el método pasado como pointcut
     * Incluye el pointcut que será el lugar donde se intercepta, siempre
     * el pointcut se coloca la función execution() como String, dentro como
     * parámetro el lugar o algoritmo que intercepta.
     * 
     * El método que intercepta debe: 
     * 1-. iniciar por el tipo que retorna el método
     * 2-. el páquete donde se encuentra el método que interceptará
     * 3-. el nombre de la clase
     * 4-. el nombre del método
     * 
     * Puede definirse con Expresiones regulares el pointcut, por ejemplo
     * con el simbolo "*" se aplica a todo
     * 
    */
    //Advice
    public void loggerBefore(JoinPoint joinPoint){
        /*La clase JoinPoint provee de la metadata del método que intercepto
         * el advice, tal metadata como nombre del método, sus parámetros, y todo
         * lo que tenga que ver con el método interceptado. Siempre debe existir
         * ese parámetro en advices
        */
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        logger.info(
            "@Before|GreetingAspect[Nombre de método[" + method + "] con argumentos {" + args +"}]");
    }

    //Advice
        /*Se ejecuta luego de ejecutarse el método ya sea de manera satisfactoria o no */
    @After("pointCut()")
    public void loggerAfter(JoinPoint joinPoint){
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        logger.info(
            "@After|GreetingAspect[Nombre de método[" + method + "] con argumentos {" + args +"}]");
    }

    //Advice: Especialización de @After, se ejecuta antes de un advice anotado con @After
    /*Solo se ejecuta si un método se ejecuta sin problemas*/
    @AfterReturning("execution(String com.luis.curso.springboot.app.aop.springbootaop.services.GreetingService.*(..))")
    public void loggerAfterReturning(JoinPoint joinPoint){
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        logger.info(
            "@AfterReturning|GreetingAspect[Nombre de método[" + method + "] con argumentos {" + args +"}]");
    }
    //Advice
    /*Se ejecuta si y solo si luego de llamar a un método, lanza una excepción*/
    @AfterThrowing("pointCut()")
    public void loggerAfterThrowing(JoinPoint joinPoint){
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        logger.info(
            "@AfterThrowing|GreetingAspect[Nombre de método[" + method + "] con argumentos {" + args +"}]");
    }

    @Around("pointCut()")
    /*Se ejecuta antes que @Before @After si la llamada sale exitosa. Si intercepta
     * una exepción, se ejecuta al final
    */
    public Object loggerAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        /*Se pasa como parámetro por defecto el contexto del método pero en
         * ejecución
    */
        String method = proceedingJoinPoint.getSignature().getName();
        String args = Arrays.toString(proceedingJoinPoint.getArgs());
        Object result = null;
        try {
            this.logger.info("@Around|GreetingAspect[El método " + method + " con argumentos del"  + 
            " método]" + args);
            result = proceedingJoinPoint.proceed();
            //Devuelve/Ejecuta el método interceptado
            //Todo antes es aquello que se ejecuta antes del método
            //Todo antes es aquello que se ejecuta despues del método
            this.logger.info("@Around|GreetingAspect[El método " + method + " retorna el resultado " 
            + result + "]");
            return result;
        } catch (Throwable e) {
            logger.error("@Around|GreetingAspect[ Error en la llamada del método: " + method + "()]", e);
            throw e;
        }
    }
}
