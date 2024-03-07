<%@page import="modelo.entidades.Libro"%>
<%@page import="modelo.entidades.Autor"%>
<%@page import="modelo.entidades.Genero"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="usuario" value="${sessionScope.usuario}" />
<fmt:setLocale value="en_EN" />
<fmt:bundle basename="bundles.menuPrincipal">
    <!DOCTYPE html>
    <html lang="es">
        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title><fmt:message key="title" /></title>
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
            <div class="container mt-3">
                <h2><fmt:message key="menuPrincipal.title" /></h2>
                <c:if test="${not empty sessionScope.usuario}">
                    <c:choose>
                        <c:when test="${usuario.getRol() eq 'ADMIN'}">
                            <p><fmt:message key="adminMessage" /></p>
                            <div class="card-deck">
                                <div class="card">
                                    <div class="card-body">
                                        <h5 class="card-title"><fmt:message key="menuPrincipal.manageUsers" /></h5>
                                        <p class="card-text">Administrar usuarios.</p>
                                        <a href="<c:url value='/admin/listaUsuarios.jsp' />" class="btn btn-primary">Ir a Usuarios</a>
                                    </div>
                                </div>
                                <div class="card">
                                    <div class="card-body">
                                        <h5 class="card-title"><fmt:message key="menuPrincipal.manageBooks" /></h5>
                                        <p class="card-text">Administrar libros.</p>
                                        <a href="<c:url value='/admin/listaLibros.jsp' />" class="btn btn-primary">Ir a Libros</a>
                                    </div>
                                </div>
                                <div class="card">
                                    <div class="card-body">
                                        <h5 class="card-title"><fmt:message key="menuPrincipal.manageAuthors" /></h5>
                                        <p class="card-text">Administrar autores.</p>
                                        <a href="<c:url value='/admin/listaAutores.jsp' />" class="btn btn-primary">Ir a Autores</a>
                                    </div>
                                </div>
                                <div class="card">
                                    <div class="card-body">
                                        <h5 class="card-title"><fmt:message key="menuPrincipal.manageGenres" /></h5>
                                        <p class="card-text">Administrar géneros.</p>
                                        <a href="ListaGeneros" class="btn btn-primary">Ir a Géneros</a>
                                    </div>
                                </div>
                            </div>
                        </c:when>
                        <c:when test="${usuario.getRol() eq 'EMPLEADO'}">
                            <p><fmt:message key="menuPrincipal.employeeMessage" /></p>
                            <div class="card">
                                <div class="card-body">
                                    <h5 class="card-title"><fmt:message key="menuPrincipal.manageLoans" /></h5>
                                    <p class="card-text">Administrar préstamos.</p>
                                    <a href="<c:url value='/empleado/listaPrestamos.jsp' />" class="btn btn-primary">Ir a Préstamos</a>
                                </div>
                            </div>
                        </c:when>
                        <c:when test="${usuario.getRol() eq 'SOCIO'}">
                            <div class="card">
                                <div class="card-body">
                                    <h5 class="card-title"><fmt:message key="menuPrincipal.manageMyLoans" /></h5>
                                    <p class="card-text">Administrar mis préstamos.</p>
                                    <a href="PanelUsuario" class="btn btn-primary">Ir a Mis Préstamos</a>
                                </div>
                            </div>
                        </c:when>
                    </c:choose>
                </c:if>
                <c:if test="${not empty listaLibros && empty sessionScope.usuario}">
                    <table class="table mt-3">
                        <thead>
                            <tr>
                                <th scope="col">ISBN</th>
                                <th scope="col">Título</th>
                                <th scope="col">Autores</th>
                                <th scope="col">Géneros</th>
                                <th scope="col">Fecha de Edición</th>
                                <th scope="col">Portada <th>
                            </tr>
                        </thead>
                        <tbody><tr>
                                <c:forEach var="libro" items="${listaLibros}">
                                <tr>
                                    <td><c:out value="${libro.isbn}" /></td>
                                    <td><c:out value="${libro.titulo}" /></td>
                                    <td>
                                        <c:forEach var="autor" items="${libro.autores}">
                                            <c:out value="${autor.nombre} ${autor.apellidos}" /><br/>
                                        </c:forEach>
                                    </td>
                                    <td>
                                        <c:forEach var="genero" items="${libro.generos}">
                                            <c:out value="${genero.nombre}" /><br/>
                                        </c:forEach>
                                    </td>
                                    <td><c:out value="${libro.fechaEdicion}" /></td>
                                    <td>
                                        <img src="<c:url value='img/${libro.imagen}' />" alt="<c:out value="${libro.titulo}" />" class="img-thumbnail custom-smaller-image">
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:if>

                <c:if test="${empty listaLibros}">
                    <p class="mt-3"><fmt:message key="menuPrincipal.noBooks" /></p>
                </c:if>
            </div>

            <footer class="fixed-bottom mt-5">
                <p class="text-center">&copy; 2024 <fmt:message key="menuPrincipal.libraryName" /></p>
            </footer>

            <style>.custom-smaller-image {
                    max-width: 75px; /* Ajusta el ancho máximo según tus necesidades */
                    max-height: 75px; /* Ajusta la altura máxima según tus necesidades */
                }</style>
            <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"></script>
            <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        </body>
    </html>
</fmt:bundle>