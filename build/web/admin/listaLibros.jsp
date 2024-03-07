<%@page import="modelo.entidades.Genero"%>
<%@page import="modelo.entidades.Autor"%>
<%@page import="modelo.entidades.Libro"%>
<%@page import="java.util.List"%>
<%@page import="modelos.ModeloLibros"%>
<%@include file="../css/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de Libros</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- Agrega el enlace al archivo CSS de Bootstrap -->
    <link rel="stylesheet" type="text/css" href="css/principal.css">
</head>
<body>
    <div class="container mt-5">
        <h2>Lista de Libros</h2>

        <table class="table table-bordered">
            <thead class="thead-dark">
                <tr>
                    <th>ISBN</th>
                    <th>Título</th>
                    <th>Autores</th>
                    <th>Géneros</th>
                    <th>Fecha de Edición</th>
                    <th>Cantidad</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <% List<Libro> libros = ModeloLibros.getLibros(); %>
                <% for (Libro libro : libros) { %>
                    <tr>
                        <td><%= libro.getIsbn() %></td>
                        <td><%= libro.getTitulo() %></td>
                        <td>
                            <%-- Itera sobre la lista de autores y muestra cada autor --%>
                            <ul>
                                <% for (Autor autor : libro.getAutores()) { %>
                                    <li><%= autor.getNombre()%></li>
                                <% } %>
                            </ul>
                        </td>
                        <td>
                            <%-- Itera sobre la lista de géneros y muestra cada género --%>
                            <ul>
                                <% for (Genero genero : libro.getGeneros()) { %>
                                    <li><%= genero.getNombre() %></li>
                                <% } %>
                            </ul>
                        </td>
                        <td><%= libro.getFechaEdicion() %></td>
                        <td><%= libro.getCantidad() %></td>
                        <td>
                            <a href="<%= request.getContextPath() %>/ActualizarLibro?isbn=<%= libro.getIsbn() %>" class="btn btn-warning btn-sm">Editar</a>
                            <a href="<%= request.getContextPath() %>/EliminarLibro?isbn=<%= libro.getIsbn() %>" class="btn btn-danger btn-sm">Eliminar</a>
                        </td>
                    </tr>
                <% } %>
            </tbody>
        </table>

        <br>

        <a href="<%= request.getContextPath() %>/CrearLibro" class="btn btn-primary">Registrar Nuevo Libro</a>
    </div>

<footer class="fixed-bottom mt-5">
        <p class="text-center">&copy; 2024 Tu Biblioteca</p>
    </footer>

    <!-- Agrega los scripts de Bootstrap al final del documento -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.0.7/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
