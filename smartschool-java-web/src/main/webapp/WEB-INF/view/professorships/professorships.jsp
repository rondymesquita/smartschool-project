<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../includes/header.jsp"/>
<jsp:include page="../includes/headerApp.jsp"/>

<div class="container-fluid mainContent">


 <ol class="breadcrumb">
        <li><a href="${pageContext.request.contextPath}/dashboard"><span class="fa fa-home" aria-hidden="true"></span> Dashboard</a></li>
        <li class="active"><span class="fa fa-book" aria-hidden="true"></span> Cadeiras</li>
    </ol>

    <h3>
      ${title}
    </h3>

      <nav class="navbar navbar-default">
        <div class="container-fluid">

          <div class="navbar-left toolbar">
           
            <a href="${pageContext.request.contextPath}/professorships/new" type="button" class="btn btn-primary navbar-btn">
              <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
              Novo
            </a>

          </div>

          <form class="navbar-form navbar-left" role="search" ng-submit="searchDisciplines()">
            <div class="form-group">
              <input type="text" class="form-control" placeholder="Nome ou código da cadeira" style="min-width:300px;">
            </div>
            <button type="submit" class="btn btn-primary" ng-disabled="onTransaction">
              <span class="glyphicon glyphicon-search" aria-hidden="true"></span>
              Buscar</button>
          </form>

        </div>
      </nav>


    <div>
      <table class="table table-striped" ng-table="tableParams">
      <thead>
      		<th>Código</th>
      		<th>Professor</th>
      		<th>Disciplina</th>
      		<th></th>
      	</thead>
        <tbody>
        
        <c:forEach var="professorship" items="${professorships}">
        	<tr ng-mouseover="showButtons_${professorship.code} = true" ng-mouseleave="showButtons_${professorship.code} = false">
                <td width="10%">${professorship.code}</td>
                <td width="10%">${professorship.professor.person.name}</td>
                <td width="20%">${professorship.discipline.name}</td>

                <td width="20%">

                    <div class="registryOptions" ng-show="showButtons_${professorship.code}">
						<!-- DELETE REGISTRY -->
	                    <modal handler="studentDeleteModal-${professorship.code}" object="student" code="${professorship.code}" on-primary-button-click-action="${pageContext.request.contextPath}/professorships/delete" primary-button-text="Apagar" primary-button-context="danger" secondary-button-text="Cancelar" modal-title="Apagar Registro" modal-body-html="Deseja apagar o registro?" modal-dismissible="true"></modal>
	                    
	                    <a data-target="#studentDeleteModal-${professorship.code}" type="button" class="btn btn-danger btn-sm" data-toggle="modal" data-placement="top" title="Apagar">
	                        <i class="fa fa-trash"></i>
	                    </a>
	                    
	                   <!-- UPDATE REGISTRY -->
	                    <form action="${pageContext.request.contextPath}/professorships/edit" method="POST" th:object="${professorship}" class="inline">
	                    	<input type="hidden" class="form-control" name="code" th:field="*{code}" value="${professorship.code}" >
	                    
		                    <button type="submit" type="button" class="btn btn-primary btn-sm" data-placement="top" title="Editar">
		                        <i class="fa fa-pencil"></i>
		                    </button>
						</form>
                    </div>

                </td>
                </tr>  
        </c:forEach>
          

          <tr ng-show="onResponse || onTransaction" ng-hide="onResponse">
            <td colspan="3" class="{{responseData.status}} text-{{responseData.status}}">
              <i class="fa fa-{{responseData.status}}"></i>
              {{responseData.message}}
            </td>
          </tr>

        </tbody>
      </table>

    </div>


</div><!-- end container -->
<jsp:include page="../includes/footerApp.jsp"/>
