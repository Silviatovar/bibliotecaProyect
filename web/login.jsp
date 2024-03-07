<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@include file="css/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Biblioteca - Inicio de Sesión</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/principal.css">
</head>
<body>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card mt-5">
                <div class="card-header text-center">
                    <h2>Inicio de Sesión</h2>
                </div>
                <div class="card-body">
                    <form method="post">
                        <div class="form-group">
                            <label for="email">e-mail</label>
                            <input type="email" name="email" class="form-control" required="">
                        </div>
                        <div class="form-group">
                            <label for="password">Contraseña</label>
                            <input type="password" name="password" class="form-control" required="">
                        </div>
                        <div class="form-group">
                            <input type="submit" value="Iniciar Sesión" class="btn btn-primary btn-block">
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
