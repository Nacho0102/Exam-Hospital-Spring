<%-- 
    Document   : WebListadoHospitales
    Created on : 17-sep-2015, 9:02:34
    Author     : NachoP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  uri="http://java.sun.com/jstl/core_rt"
           prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Listado de Hospitales</h1>
        <c:forEach items="${hospitales}"
                   var="fila">
        
        <ul>
            
            <li>Hospital: <c:out value="${fila.nombre}"/></li>
                
            <ul>
                <li>
                    Dirección: <c:out value="${fila.direccion}"/>
                </li>
                <li>
                    Teléfono: <c:out value="${fila.telefono}"/>
                </li>
                <li>
                     <a href='WebResumenHospital.htm?hospitalcod=<c:out value="${fila.hospitalcod}"/>'>Resumen Hospital</a> 
                </li>
                <li>
                     <a href='WebPlantillaHospital.htm?hospitalcod=<c:out value="${fila.hospitalcod}"/>'>Empleados del Hospital</a> 
                </li>
                
            </ul>
            
        </ul>
        
        </c:forEach>
    </body>
</html>
