<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<body>

<p>
  <b>Nome:</b>
   ${cidade.nome}
</p>

<a href="<c:url value="/cidades/${cidade.id}/edit"/>">Edit</a>
<a href="<c:url value="/cidades"/>">Back</a>

</body>
