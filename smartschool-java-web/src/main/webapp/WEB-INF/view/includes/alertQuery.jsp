<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if  test="${responseDataQuery != null}">
   <div class="">
        <div class="alertQuery ${responseDataQuery.status} text-${responseDataQuery.status}">
          <i class="fa fa-${responseDataQuery.status}"></i>
          ${responseDataQuery.message}
        </div>
	</div>
</c:if>