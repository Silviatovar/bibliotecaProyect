<%@ page import="java.util.List" %>
<%@ page import="modelo.entidades.Autor" %>
<%@ page import="modelos.ModeloAutores" %>
<%@include file="../css/header.jsp" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de Autores</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/principal.css">
    <!-- Agrega la referencia a Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-3">
        <h2>Lista de Autores</h2>

        <table class="table">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Apellidos</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<Autor> autores = (List<Autor>) request.getAttribute("listaAutores");
                    if (autores != null) {
                        for (Autor autor : autores) {
                %>
                            <tr>
                                <td><%=autor.getId()%></td>
                                <td><%= autor.getNombre() %></td>
                                <td><%= autor.getApellidos() %></td>
                                 <td>
                            <a href="<%= request.getContextPath() %>/ActualizarAutor?isbn=<%= autor.getId()%>" class="btn btn-warning btn-sm">Editar</a>
                            <a href="<%= request.getContextPath() %>/EliminarAutor?isbn=<%= autor.getId()%>" class="btn btn-danger btn-sm">Eliminar</a>
                        </td>
                            </tr>
                <%
                        }
                    } else {
                %>
                        <tr>
                            <td colspan="4">No se encontraron autores.</td>
                        </tr>
                <%
                    }
                %>
            </tbody>
        </table>

        <br>

        <a href="<%= request.getContextPath() %>/CrearAutor" class="btn btn-primary">Crear Nuevo Autor</a>
    </div>

 <footer class="fixed-bottom mt-5">
        <p class="text-center">&copy; 2024 Tu Biblioteca</p>
    </footer>

    <!-- Agrega la referencia a Bootstrap JS y Popper.js (para el funcionamiento de Bootstrap) -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
