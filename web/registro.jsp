<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Biblioteca - Registro</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/principal.css">
</head>
<body>
    <c:if test="${sessionScope.usuario eq null}">
        <%@include file="css/header.jsp" %>
    </c:if>
    <c:if test="${sessionScope.usuario ne null}">
        <%@include file="css/headerUsuario.jsp" %>
    </c:if>
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card">
                <div class="card-header text-center">
                    <h1>Registro de Usuario</h1>
                </div>
                <div class="card-body">
                    <form method="post" action="Registro">
                        <div class="form-group">
                            <label for="nombre">Nombre</label>
                            <input type="text" name="nombre" class="form-control" required="">
                        </div>
                        <div class="form-group">
                            <label for="apellidos">Apellidos</label>
                            <input type="text" name="apellidos" class="form-control" required="">
                        </div>
                        <div class="form-group">
                            <label for="email">e-mail</label>
                            <input type="email" name="email" class="form-control" required="">
                        </div>
                        <div class="form-group">
                            <label for="domicilio">Domicilio</label>
                            <input type="text" name="domicilio" class="form-control" required="">
                        </div>
                        <div class="form-group">
                            <label for="password">Contraseña</label>
                            <input type="password" name="password" class="form-control" required="">
                        </div>
                        <div class="form-group text-center">
                            <input type="submit" value="Registrar" class="btn btn-primary">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<c:if test="${not empty error}">
    <div class="container mt-3">
        <div class="alert alert-danger" role="alert">
            ${error}
        </div>
    </div>
</c:if>

<footer class="fixed-bottom mt-5">
    <p class="text-center">&copy; 2024 Tu Biblioteca</p>
</footer>

<!-- Agrega las bibliotecas de Bootstrap JS y Popper.js si las necesitas -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>
