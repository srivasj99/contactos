<%@page import="java.util.List"%>
<%@page import="modelo.Contacto"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Start Page</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listado</title>
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    </head>
    <body>
        <%
        List<Contacto> listadoContactos = (List<Contacto>) request.getAttribute("listado");
        String mensaje = (String) request.getAttribute("mensaje");
        int num_paginas = (int) request.getAttribute("num_paginas");
        %>
        <div class="container">
        <h1>Listado de Contactos</h1>
        <%if(mensaje != null){%>
        <h2 class="alert alert-success"><%=mensaje%></h2>
        <%}%>
            <table class="table">
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Teléfono</th>
                </tr>
                <% for (Contacto listadoContacto : listadoContactos) { %>
                <tr>
                    <td>
                        <%= listadoContacto.getId()%>
                    </td>
                    <td>
                        <%= listadoContacto.getNombre() %>
                    </td>
                    <td>
                        <%= listadoContacto.getTelefono() %>
                    </td>
                    <td><a href="Servlet?op=borrar&id=<%=listadoContacto.getId()%>" onclick="return Confirmation()">Borrar</a></td>
                    <td><a href="Servlet?op=actualizar&id=<%=listadoContacto.getId()%>">Actualizar</a></td>
                </tr>
                <%}%>
            </table>
                <p>Mostrando página ${pagina} de ${num_paginas}</p>
                <%
                    for (int i = 1; i <= num_paginas; i++) {%>
                    <a href="Servlet?op=listar&pagina=<%=i%>"><%=i%></a>
                    <%}%>
                
            <a href="Servlet?op=insertar" class="btn btn-primary">Insertar</a>
        </div>
            <script>
                function Confirmation(){
                    if(confirm("Esta seguro de eliminar el registro?") == true){
                        alert("El registro ha sido eliminado correctamente!!!");
                        return true;
                    }else{
                        return false;
                    }
                }
            </script>
    </body>
</html>
