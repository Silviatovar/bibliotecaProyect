<%@page import="modelo.entidades.Genero"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../css/header.jsp" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de Géneros</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/principal.css">
    <!-- Agrega la referencia a Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-3">
        <h2>Lista de Géneros</h2>

        <table class="table">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Acciones</th>
                 
                </tr>
            </thead>
            <tbody>
                <c:forEach var="genero" items="${listaGeneros}">
                    <tr>
                        <td>${genero.id}</td>
                        <td>${genero.nombre}</td>
                        <td>
                            <a href="<%= request.getContextPath() %>/ActualizarGenero?idGenero=${genero.id}" class="btn btn-warning btn-sm">Editar</a>
                            <a href="<%= request.getContextPath() %>/EliminarGenero?idGenero=${genero.id}" class="btn btn-danger btn-sm">Eliminar</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <br>

        <a href="<%= request.getContextPath() %>/CrearGenero" class="btn btn-primary">Crear Nuevo Género</a>
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
