<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <!-- Agrega la referencia al archivo de estilo de Bootstrap -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
            <link rel="stylesheet" type="text/css" href="css/principal.css">
    </head>
    <body>
        
            <c:if test="${sessionScope.usuario ne null}">
                <%@include file="../css/headerUsuario.jsp" %>
            </c:if>
        
        <c:if test="${not empty listaPrestamos && empty sessionScope.usuario}">
            <!-- Agrega clases de Bootstrap a la tabla -->
            <table class="table mt-3 table-striped table-bordered">
                <thead class="thead-dark">
                    <tr>
                        <th scope="col">ID</th>
                        <th scope="col">Usuario</th>
                        <th scope="col">Fecha Prestamo</th>
                        <th scope="col">Fecha Devolucion</th>
                        <th scope="col">Libro</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="prestamo" items="${listaPrestamos}">
                        <tr>
                            <td><c:out value="${prestamo.id}" /></td>
                            <td><c:out value="${prestamo.usuario.nombre}" /></td>
                            <td><c:out value="${prestamo.fecha_prestamo}" /></td>
                            <td><c:out value="${prestamo.fecha_devolucion}" /></td>
                            <td><c:out value="${prestamo.libro}" /></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>

        <c:if test="${empty listaPrestamos}">
            <p class="mt-3"><fmt:message key="menuPrincipal.noBooks" /></p>
        </c:if>

        <!-- Agrega los scripts de Bootstrap al final del cuerpo del documento -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>
