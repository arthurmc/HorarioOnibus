<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<body>

<p>
	<b>Linha:</b> ${linha.origem} - ${linha.destino}
</p>
<c:forEach items="${horariosDaLinha }" var="horario">
	<p>${horario.hora }</p>
</c:forEach>


<a href="<c:url value="/linhas/${linha.id}/edit"/>">Edit</a>
<a href="<c:url value="/linhas"/>">Back</a>

</body>
