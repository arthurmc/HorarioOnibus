<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${not empty errors}">
	<c:forEach items="${errors}" var="error">
		 ${error.category} - ${error.message}<br />
	</c:forEach>
</c:if>

<form action="<c:url value="/linhas"/>" method="post">
  
  <c:if test="${not empty linha.id}">
    <input type="hidden" name="linha.id" value="${linha.id}"/>
    <input type="hidden" name="_method" value="put"/>
  </c:if>
  
  <div class="field">
    Origem:<br />
    <select name="origem.id">
	    <c:forEach items="${cidadeList}" var="origem">
	    	<option value="${origem.id}">${origem.nome}</option>
	    </c:forEach>
    </select>
  </div>
  
  <div class="field">
    Destino:<br />
    <select name="destino.id">
	    <c:forEach items="${cidadeList}" var="destino">
	    	<option value="${destino.id}">${destino.nome}</option>
	    </c:forEach>
    </select>
  </div>
  
  <div class="actions">
    <button type="submit">send</button>
  </div>
</form>

<a href="<c:url value="/linhas"/>">Back</a>

