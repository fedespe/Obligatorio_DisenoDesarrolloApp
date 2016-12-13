<%-- 
        
    Es importante ejecutar el ejemplo desde aqui (http://localhost:8084/contador/) 
    Este redirect hace que las cookies de la sesion de Tomcat no den problemas en Opera y Firefox.
    Sin el redirect dan problemas al parecer por que el servidor no tiene un nombre del tipo host.dominio.
    Si se ejecuta directo contador.html y en consola de NetBeans dice que se perdio la sesion, se soluciona dando refresh en el navegador, 
    a partir del primer refresh anda bien N veces, hasta que se cierre el navegador y se vuelva a abrir. 
    En Chrome no da problemas de este tipo.
    IE es el unico que no sporta Server Sent Envents de HTML5...(No me sorprende...)
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%response.sendRedirect("login.html"); return;%>