<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../css/header.jsp" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Actualizar Libro</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- Agrega el enlace al archivo CSS de Bootstrap -->
</head>
<body>
    <div class="container mt-5">
        <h1>Actualizar Libro</h1>

        <c:if test="${not empty libro}">
            <form action="${request.contextPath}/ActualizarLibro" method="post">
                <input type="hidden" name="isbn" value="${libro.isbn}" class="form-control mb-2">

                <div class="form-group">
                    <label for="titulo">Título:</label>
                    <input type="text" id="titulo" name="titulo" value="${libro.titulo}" class="form-control" required>
                </div>

                <div class="form-group">
                    <label for="fechaEdicion">Fecha de Edición:</label>
                    <input type="text" id="fechaEdicion" name="fechaEdicion" value="${libro.fechaEdicion}" class="form-control" required>
                </div>

                <div class="form-group">
                    <label for="autores">Autores:</label>
                    <input type="text" id="autores" name="autores" value="${libro.autores}" class="form-control" required>
                </div>

                <div class="form-group">
                    <label for="generos">Géneros:</label>
                    <input type="text" id="generos" name="generos" value="${libro.generos}" class="form-control" required>
                </div>

                <div class="form-group">
                    <label for="imagen">URL de la Imagen:</label>
                    <input type="text" id="imagen" name="imagen" value="${libro.imagen}" class="form-control" required>
                </div>

                <div class="form-group">
                    <label for="cantidad">Cantidad:</label>
                    <input type="text" id="cantidad" name="cantidad" value="${libro.cantidad}" class="form-control" required>
                </div>

                <button type="submit" class="btn btn-primary mt-3">Actualizar Libro</button>
            </form>

            <br>

            <a href="${request.contextPath}/ListaLibros" class="btn btn-secondary">Cancelar</a>
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
