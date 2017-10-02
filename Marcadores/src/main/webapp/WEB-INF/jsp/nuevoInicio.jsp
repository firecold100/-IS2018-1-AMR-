<%-- 
    Document   : nuevoInicio
    Created on : 2/10/2017, 03:33:54 PM
    Author     : alan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
        <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/iniciocss.css"/> ">
        <title>Nuevo Home</title>
    </head>
    
    <body>
        <div class="contenedor">
            <div id="iniciarSesion">
                <form>
                    <input id="usuario" name="usuario">
                    <input id="contrasena" name="contrasena">
                    <button type="submit" class="btn btn-primary btn-lg active"
                </form>
            </div>
                
        </div>
        
    </body>
</html>
