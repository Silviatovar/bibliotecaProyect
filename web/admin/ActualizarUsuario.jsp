<%@ page import="modelo.entidades.Usuario" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../css/header.jsp" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Editar Usuario</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- Agrega el enlace al archivo CSS de Bootstrap -->
</head>
<body>
    <div class="container mt-5">
        <h1 class="mb-4">Actualizar Usuario</h1>
    

        <c:if test="${not empty error}">
            <p style="color: red;">${error}</p>
        </c:if>

        <c:if test="${ empty usuario}">
            <form method="post">
                <input type="hidden" name="idUsuario" value="${usuario.usuarioID}" class="form-control mb-2">
                
                <div class="form-group">
                    <label for="nombre">Nombre:</label>
                    <input type="text" id="nombre" name="nombre" value="${usuario.nombre}" class="form-control" required>
                </div>

                <div class="form-group">
                    <label for="apellidos">Apellidos:</label>
                    <input type="text" id="apellidos" name="apellidos" value="${usuario.apellidos}" class="form-control" required>
                </div>

                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="email" id="email" name="email" value="${usuario.email}" class="form-control" required>
                </div>

                <div class="form-group">
                    <label for="contrasena">Contraseña:</label>
                    <input type="password" id="contrasena" name="contrasena" class="form-control">
                </div>

                <div class="form-group">
                    <label for="domicilio">Domicilio:</label>
                    <input type="text" id="domicilio" name="domicilio" value="${usuario.domicilio}" class="form-control" required>
                </div>

                <button type="submit" class="btn btn-primary mt-3">Actualizar Usuario</button>
            </form>
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
