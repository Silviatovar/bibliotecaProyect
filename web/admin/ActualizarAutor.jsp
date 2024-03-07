<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="../css/header.jsp" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Actualizar Autor</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- Agrega el enlace al archivo CSS de Bootstrap -->
</head>
<body>
    <div class="container mt-5">
        <h1>Actualizar Autor</h1>

        <c:if test="${not empty error}">
            <p style="color: red;">${error}</p>
        </c:if>

        <c:if test="${not empty autor}">
            <form action="ActualizarAutor" method="post">
                <input type="hidden" name="idAutor" value="${autor.id}" class="form-control mb-2">
                
                <div class="form-group">
                    <label for="nombre">Nombre:</label>
                    <input type="text" id="nombre" name="nombre" value="${autor.nombre}" class="form-control" required>
                </div>

                <div class="form-group">
                    <label for="apellidos">Apellidos:</label>
                    <input type="text" id="apellidos" name="apellidos" value="${autor.apellidos}" class="form-control" required>
                </div>

                <!-- Puedes agregar más campos según tus necesidades -->

                <button type="submit" class="btn btn-primary mt-3">Actualizar Autor</button>
            </form>

            <br>

            <a href="ListaAutores" class="btn btn-secondary">Cancelar</a>
        </c:if>
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
