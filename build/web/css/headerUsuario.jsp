<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="MenuPrincipal">Tu Biblioteca</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
        aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <a class="nav-link" href="MenuPrincipal">Inicio</a>
            </li>
            <li class="nav-item">
             <a href="${pageContext.request.contextPath}/LogoutServlet" class="btn btn-danger">Cerrar Sesión</a>
            </li> 
        </ul>
    </div>
</nav>
