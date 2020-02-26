<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">
    <div class="row m-3">
        <a href="${pageContext.request.contextPath}/agregarPersona.jsp" class="btn btn-primary">Agregar Persona</a>
    </div>

</div>
<div class="container">
    <div class="col-md-12">
        <table class="table table-hover">
            <thead class="thead-dark">
                <tr>
                    <th>Nombre</th>
                    <th>Apellido</th>
                    <th>Edad</th>
                    <th>E Mail</th>
                    <th></th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="persona" items="${personas}" varStatus="status">
                    <tr>
                        <td>${persona.nombre}</td>
                        <td>${persona.apellido}</td>
                        <td>${persona.edad}</td>
                        <td>${persona.email}</td>
                        <td class="text-right"> <a onclick="return confirm('¿Estas seguro de eliminar el registro?')" style="width: 100%" href="${pageContext.request.contextPath}/PersonasServlet?accion=eliminar&idPersona=${persona.idPersona}" class="btn btn-danger">
                                Eliminar</a>                            
                        </td>
                        <td class="text-right"> <a style="width: 100%" href="${pageContext.request.contextPath}/PersonasServlet?accion=modificar&idPersona=${persona.idPersona}" class="btn btn-secondary">
                                Modificar</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>

