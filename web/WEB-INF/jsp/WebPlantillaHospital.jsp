<%-- 
    Document   : WebPlantillaHospital
    Created on : 17-sep-2015, 9:46:42
    Author     : NachoP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt"
          prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        
        <body>      
            
        <h1 style="color:blue">
            Hospital <c:out value="${empleados.hospital.nombre}"/>
        </h1>
        <h2>
            Direccion: <c:out value="${empleados.hospital.direccion}"/>
        </h2>
        <h2>
            Teléfono <c:out value="${empleados.hospital.telefono}"/>
        </h2>       
        <h2>
            Camas: <c:out value="${empleados.hospital.camas}"/>
        </h2>        
        
        <hr/>
        
        <h3>
            Plantilla del hospital
        </h3>
        <ul>
            <c:forEach items="${empleados.plantilla}"
                       var="plantilla">
                <li>
                    <c:out value="${plantilla}"/>
                </li>
            </c:forEach>
        </ul>

        <hr/>
        
        <h3>
            Doctores del hospital
        </h3>
        <ul>
            <c:forEach items="${empleados.doctores}"
                       var="doctor">
                <li>
                    <c:out value="${doctor}"/>
                </li>
            </c:forEach>
        </ul>
    </body>
    
</html>
