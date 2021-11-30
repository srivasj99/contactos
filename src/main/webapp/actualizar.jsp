<%-- 
    Document   : actualizar.jsp
    Created on : 22-nov-2021, 17:41:38
    Author     : DAW-A
--%>
<%
    String mensaje = (String) request.getAttribute("mensaje");
    String operacion = (String) request.getAttribute("operacion");
    String valorSubmit = "";
    if(operacion.equals("insertarDatos")){
         valorSubmit = "Insertar";
    }else{
        valorSubmit = "Actualizar";
    }
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Actualizar contacto</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    </head>
    <body>
        <div class="container">
        <h1>Contacto</h1>
        <form action="Servlet?op=<%=operacion%>" method="post">
            <div class="row">
                <div class="col-12 col-sm-6 col-lg-3">        
                    <p>ID: <input type="text" value="${contacto.id}" name="id" readonly></p>
                </div>
                <div class="col-12 col-sm-6 col-lg-3">
                    <p>Nombre: <input type="text" value="${contacto.nombre}" name="nombre"></p>
                </div>
                <div class="col-12 col-sm-6 col-lg-3">
                    <p>Teléfono <input type="text" value="${contacto.telefono}" name="telefono"></p>
                </div>
            </div>
            <input type="submit" value="<%=valorSubmit%> Contacto">
        </form>
        </div>
    </body>
</html>
