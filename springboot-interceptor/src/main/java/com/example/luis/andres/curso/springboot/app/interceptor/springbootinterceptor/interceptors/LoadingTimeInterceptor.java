package com.example.luis.andres.curso.springboot.app.interceptor.springbootinterceptor.interceptors;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
//API de JSON de Spring
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component("timeInterceptor")
/*Es un componente pues se va a inyectar en una clase de configuración
 * como Interceptor e indicar en que parte de la App se utiliza. Que parte
 * de los endpoints se utilizan
*/
public class LoadingTimeInterceptor implements HandlerInterceptor{

    private static final Logger logger = LoggerFactory.getLogger(LoadingTimeInterceptor.class);
    /*Esta clase se utiliza para registrar mensajes de registro durante la ejecución de una aplicación JavaEsta clase 
    se utiliza para registrar mensajes de registro durante la ejecución de una aplicación Java*/

    /*El párametro de nombre handler representa el controlador que manejará la solicitud HTTP, es por eso que podemos
     * obtener el nombre del endpoint que se esta llamando
    */
    
    @SuppressWarnings("null")
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
            HandlerMethod controller = ((HandlerMethod)handler);
            logger.info("LoadingTimeInterceptor: pretHandle() entrando... " + controller.getMethod().getName());
            long start = System.currentTimeMillis();
            request.setAttribute("start", start);
            Random random = new Random();
            int delay = random.nextInt(500);
            Thread.sleep(delay);
            //return true;
            Map<String, String> json = new HashMap<>();
            json.put("error", "No tienes acceso a este recurso");
            json.put("date", new Date().toString());
            ObjectMapper mapper = new ObjectMapper();
            /*
            *es una clase proporcionada por la biblioteca Jackson que se utiliza
            *para convertir objetos Java en JSON y viceversa. Jackson es una de 
            *las bibliotecas de serialización y deserialización JSON más 
            *populares en el ecosistema Java.*/
            String jsonString = mapper.writeValueAsString(json);
            //Permite convertir un elemento JSON econ esa estructura en un String
            response.setContentType("application/json");
            response.setStatus(401);
            response.getWriter().write(jsonString);
            /*getWritter() se utiliza para generar una instancia de PrintWriter
            *esta una clase en Java que proporciona métodos convenientes para escribir 
            *texto en un flujo de salida, como archivos, sockets o, en el contexto 
            *de aplicaciones web, en el cuerpo de una respuesta HTTP
            *
            *.write() se utiliza en este método para escribir directamente en el 
            *cuerpo de la respuesta HTTP*/
            return false;
    }
    
    @SuppressWarnings("null")
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            @Nullable ModelAndView modelAndView) throws Exception {
        long end = System.currentTimeMillis();
        long start = (long)request.getAttribute("start");
        long result = end - start;
        logger.info("Tiempo transcurrido: " + result + " milisegundos!");
        logger.info("LoadingTimeInterceptor: postHandle() saliendo... " + ((HandlerMethod)handler).getMethod().getName());
    }
            
}
