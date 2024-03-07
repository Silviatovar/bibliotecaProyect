<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="../css/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/principal.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <meta charset="UTF-8">
    <title>Crear Libro</title>
</head>
<body>
    <div class="container mt-3">
        <h1>Crear Libro</h1>

        <c:if test="${not empty error}">
            <p style="color: red;">${error}</p>
        </c:if>

        <form action="CrearLibro" method="post">
            <div class="form-group">
                <label for="isbn">ISBN:</label>
                <input type="text" class="form-control" id="isbn" name="isbn" required>
            </div>

            <div class="form-group">
                <label for="titulo">Título:</label>
                <input type="text" class="form-control" id="titulo" name="titulo" required>
            </div>

            <div class="form-group">
                <label for="fechaEdicion">Fecha de Edición:</label>
                <input type="text" class="form-control" id="fechaEdicion" name="fechaEdicion" required>
            </div>

            <div class="form-group">
                <label for="autores">Autores (Separados por comas):</label>
                <input type="text" class="form-control" id="autores" name="autores" required>
            </div>

            <div class="form-group">
                <label for="generos">Géneros (Separados por comas):</label>
                <input type="text" class="form-control" id="generos" name="generos" required>
            </div>

            <div class="form-group">
                <label for="imagen">URL de la Imagen:</label>
                <input type="text" class="form-control" id="imagen" name="imagen" required>
            </div>

            <div class="form-group">
                <label for="cantidad">Cantidad:</label>
                <input type="number" class="form-control" id="cantidad" name="cantidad" required>
            </div>

            <!-- Puedes agregar más campos según tus necesidades -->

            <br>

            <button type="submit" class="btn btn-primary">Crear Libro</button>
        </form>
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
