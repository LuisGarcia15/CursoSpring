package com.luis.curso.springboot.calendar.interceptor.springbootinterceptor2.interceptors;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component()
public class CalendarInterceptor implements HandlerInterceptor{

    @Value("${config.calendat.open}")
    private Integer open;
    @Value("${config.calendat.close}")
    private Integer close;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        Calendar calendar = Calendar.getInstance();
        /*Obtenemos una instancia singlentton de un Calendario*/
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        System.out.println(hour);
        if(hour >= this.open && hour < this.close){
            StringBuilder message = new StringBuilder(
                "Bienvenidos al horario de atención al cliente ");
            message.append("Atendemos desde las ");
            /*La principal diferencia entre StringBuilder y String es que StringBuilder es mutable,
             lo que significa que puedes cambiar su contenido sin crear un nuevo objeto cada vez 
             que realizas una modificación.

            Aquí hay algunas razones por las que se utiliza la clase StringBuilder:

            ** Eficiencia en la manipulación de cadenas: Cuando necesitas realizar muchas operaciones 
            de concatenación o modificación en una cadena, usar StringBuilder es más eficiente que 
            concatenar cadenas directamente mediante el operador +. Esto se debe a que String es 
            inmutable, por lo que cada vez que concatenas cadenas con el operador +, se crean nuevos 
            objetos String. StringBuilder proporciona métodos para modificar el contenido de la cadena
             de manera eficiente sin crear objetos adicionales.
            ** Construcción dinámica de cadenas: Si estás construyendo una cadena de manera dinámica, 
            es decir, no conoces su longitud exacta de antemano, StringBuilder es útil porque puede 
            ajustar automáticamente su capacidad interna según sea necesario para manejar la cantidad
            de datos.
            ** Mejora del rendimiento: En operaciones que involucran muchas manipulaciones de cadenas, 
            el uso de StringBuilder puede mejorar significativamente el rendimiento en comparación 
            con concatenar cadenas directamente.*/
            message.append(this.open);   
            message.append(" hrs."); 
            message.append("hasta las "); 
            message.append(this.close);
            message.append(" hrs.");
            message.append("Gracias por su visita");
            request.setAttribute("message", message.toString());          
            return true;
        }
        ObjectMapper mapper = new ObjectMapper();
        /*es una clase proporcionada por la biblioteca Jackson que se utiliza
        *para convertir objetos Java en JSON y viceversa. Jackson es una de 
        *las bibliotecas de serialización y deserialización JSON más 
        *populares en el ecosistema Java.*/
        Map<String,Object> data = new HashMap<>();
        StringBuilder message = new StringBuilder(
            "Cerrado. Fuera del horatio de atención ");
        message.append("por favor visitenos desde las ");
        message.append(this.open);
        message.append(" y las ");
        message.append(this.close);
        message.append(" hrs. Gracias!");
        data.put("date", new Date());
        data.put("message", message.toString());
        response.setContentType("application/json");
        /*La línea de código response.setContentType("application/json"); en un 
        contexto de desarrollo con Spring generalmente se utiliza para establecer
        el tipo de contenido de la respuesta HTTP generada por el servidor.
        
        El método setContentType() en Java recibe un solo parámetro, que es una cadena 
        de texto representando el tipo de contenido que se enviará en la respuesta HTTP.
         Este parámetro especifica el tipo MIME (Multipurpose Internet Mail Extensions) 
         del contenido.

         Por lo tanto, aparte de "application/json", que se usa para indicar que el 
         contenido es en formato JSON, puedes especificar otros tipos de contenido 
         según las necesidades de tu aplicación. Algunos ejemplos comunes incluyen:

         "text/html" para contenido HTML.
         "text/plain" para contenido de texto sin formato.
         "application/xml" para contenido XML.
         "image/jpeg", "image/png", etc., para contenido de imágenes en formatos JPEG, PNG, etc.
         "application/pdf" para contenido en formato PDF.
         "application/octet-stream" para contenido binario genérico.
         Estos son solo algunos ejemplos, pero hay muchos otros tipos MIME que puedes 
         utilizar dependiendo de los requisitos de tu aplicación. Es importante especificar 
         el tipo MIME correcto para que el cliente pueda interpretar correctamente el 
         contenido de la respuesta HTTP.
         */
        response.setStatus(401);
        response.getWriter().write(mapper.writeValueAsString(message) + " | " + new Date().toString());
        /*mapper.writeValueAsString(): Permite serializar cualquier elemento
         * java a un String
        */

        /*Estamos enviando datos a la respuesta HTTP pues es el caso cuando esta cerrado,
         * se envia el codigo de estatus y un mensaje en el body de la response
        */
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            @Nullable ModelAndView modelAndView) throws Exception {
    }
    
}
