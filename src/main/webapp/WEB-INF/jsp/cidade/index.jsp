<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<body>
<h1>Listing Cidades</h1>

<table>
  <tr>
   <th>nome</th>
   <th></th>
   <th></th>
   <th></th>
  </tr>

<c:forEach items="${cidadeList}" var="cidade">
  <tr>
      <td>
      ${cidade.nome}
    </td>
      <td><a href="<c:url value="/cidades/${cidade.id}"/>">show</a></td>
    <td><a href="<c:url value="/cidades/${cidade.id}/edit"/>">edit</a></td>
    <td>
      <form action="<c:url value="/cidades/${cidade.id}"/>" method="post">
    	  <input type="hidden" name="_method" value="delete"/>
    	  <button type="submit" onclick="return confirm('Are you sure?')">destroy</button>
	    </form>
	  </td>
    </tr>
</c:forEach>
</table>

<br />
<a href="<c:url value="/cidades/new"/>">New Cidade</a> 
</body>
