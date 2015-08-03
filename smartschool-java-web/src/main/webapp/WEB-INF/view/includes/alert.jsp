<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if  test="${responseData != null}">
   <div class="alert alert-${responseData.status} alert-dismissible" role="alert">
   		<button type="button" class="close" data-dismiss="alert" aria-label="Close">
   			<span aria-hidden="true">&times;</span>
   		</button>
      	<span>${responseData.message}</span>
   </div>
</c:if>