<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<body>
<h1>Listing Linhas</h1>

<table>
  <tr>
   <th>origem</th>
   <th>destino</th>
   <th></th>
   <th></th>
   <th></th>
  </tr>

<c:forEach items="${linhaList}" var="linha">
  <tr>
    <td>
      ${linha.origem}
    </td>
    <td>
      ${linha.destino}
    </td>
    <td><a href="<c:url value="/linhas/${linha.id}"/>">show</a></td>
    <td><a href="<c:url value="/linhas/${linha.id}/edit"/>">edit</a></td>
    <td>
      <form action="<c:url value="/linhas/${linha.id}"/>" method="post">
    	  <input type="hidden" name="_method" value="delete"/>
    	  <button type="submit" onclick="return confirm('Are you sure?')">destroy</button>
	    </form>
	</td>
  </tr>
</c:forEach>
</table>

<br />
<a href="<c:url value="/linhas/new"/>">New Linha</a> 
</body>