<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../css/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="../css/principal.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <meta charset="UTF-8">
    <title>Crear Género</title>
</head>
<body>
    <div class="container mt-3">
        <h1>Crear Género</h1>

        <c:if test="${not empty error}">
            <p style="color: red;">${error}</p>
        </c:if>

        <form method="post">
            <div class="form-group">
                <label for="nombre">Nombre del Género:</label>
                <input type="text" class="form-control" id="nombre" name="nombre" required>
            </div>

            <br>

            <button type="submit" class="btn btn-primary">Crear Género</button>
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
