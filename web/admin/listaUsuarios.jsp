<%@ page import="java.util.List" %>
<%@ page import="modelo.entidades.Usuario" %>
<%@ page import="modelos.ModeloUsuarios" %>
<%@include file="../css/header.jsp" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Lista de Usuarios</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/principal.css">
</head>
<body>
    <div class="container mt-5">
        <h2>Lista de Usuarios</h2>

        <table class="table table-bordered">
            <thead class="thead-dark">
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Apellidos</th>
                    <th>Email</th>
                    <th>Domicilio</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <% List<Usuario> usuarios = ModeloUsuarios.getUsuarios(); %>
                <% for (Usuario usuario : usuarios) { %>
                    <tr>
                        <td><%= usuario.getUsuarioID() %></td>
                        <td><%= usuario.getNombre() %></td>
                        <td><%= usuario.getApellidos() %></td>
                        <td><%= usuario.getEmail() %></td>
                        <td><%= usuario.getDomicilio() %></td>
                        <td>
                            <a class="btn btn-primary" href="<%= request.getContextPath() %>/ActualizarUsuario?idUsuario=<%= usuario.getUsuarioID() %>">Editar</a>
                            <a class="btn btn-danger" href="<%= request.getContextPath() %>/EliminarUsuario?idUsuario=<%= usuario.getUsuarioID() %>">Eliminar</a>
                        </td>
                    </tr>
                <% } %>
            </tbody>
        </table>

        <br>

        <a class="btn btn-success" href="<%= request.getContextPath() %>/CrearUsuario">Registrar Nuevo Usuario</a>
    </div>



    <!-- Enlaces a los archivos JavaScript de Bootstrap (puedes descargarlos o usar los enlaces CDN) -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
