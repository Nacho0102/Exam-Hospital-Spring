<%-- 
    Document   : WebResumenHospital
    Created on : 17-sep-2015, 9:03:17
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
         <h1>
            Resumen del hospital: <c:out value="${datos.nombreHospital}"/>
        </h1>

        <h2 style="color:blue">
            Empleados del hospital: <c:out value="${datos.empleadosHospital}"/>
        </h2>
        
        <h2 style="color:blue">
            Plantilla: <c:out value="${datos.personasPlantilla}"/>
        </h2>
        
        <h2 style="color:blue">
            Doctores: <c:out value="${datos.personasDoctor}"/>            
        </h2>
        
        <h2 style="color:red">
            Salario completo de los empleados: <c:out value="${datos.sumaSalarial} €" />   
        </h2>        
    </body>
</html>
