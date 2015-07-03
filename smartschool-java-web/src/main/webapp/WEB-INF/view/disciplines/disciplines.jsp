<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../includes/header.jsp"/>
<jsp:include page="../includes/headerApp.jsp"/>

<div class="container-fluid mainContent" ng-controller="DisciplineController">


 <ol class="breadcrumb">
        <li><a href="${pageContext.request.contextPath}/dashboard"><span class="fa fa-home" aria-hidden="true"></span> Dashboard</a></li>
        <li class="active"><span class="fa fa-book" aria-hidden="true"></span> Disciplinas</li>
    </ol>

    <h3>
      Disciplinas
    </h3>
    
    ${search}
    
    <jsp:include page="../includes/alert.jsp"/>

      <nav class="navbar navbar-default">
        <div class="container-fluid">

          <div class="navbar-left toolbar">
            
            <a href="${pageContext.request.contextPath}/disciplines/new"  data-target="" type="button" class="btn btn-primary navbar-btn" data-toggle="modal">
              <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
              Novo
            </a>

          </div>

		
          <form class="navbar-form navbar-left" role="search">
            <div class="form-group">
              <input type="text" class="form-control" placeholder="Nome ou c�digo da disciplina" style="min-width:300px;" ng-model="search" ng-redirect-on-enter="${pageContext.request.contextPath}/disciplines/{{search}}">
            </div>
            <a href="${pageContext.request.contextPath}/disciplines/{{search}}" type="submit" class="btn btn-primary" ng-disabled="onTransaction">
              <span class="glyphicon glyphicon-search" aria-hidden="true"></span>
              Buscar {{search}} </a
          </form>

        </div>
      </nav>
	

    <div>
      <table class="table table-striped" ng-table="tableParams">
      	<thead>
      		<th>C�digo</th>
      		<th>Nome</th>
      		<th>Carga Hor�ria</th>
      		<th></th>
      	</thead>
        <tbody>
        
        <c:forEach var="discipline" items="${disciplines}">
          <tr ng-mouseover="showButtons = true" ng-mouseleave="showButtons = false">

            <td width="20%">${discipline.code}</td>
            <td width="40%">${discipline.name}</td>
            <td width="20%">${discipline.workload}</td>
            <td width="20%">

                <div class="registryOptions"> <!-- ng-show="showButtons" -->

                    <!-- DELETE REGISTRY -->
                    <modal handler="disciplineDeleteModal-${discipline.code}" on-primary-button-click-event="deleteDiscipline(${discipline.code})" primary-button-text="Apagar" primary-button-context="danger" secondary-button-text="Cancelar" modal-title="Apagar Registro" modal-body-html="Deseja apagar o registro?" modal-dismissible="true"></modal>
                    <a data-target="#disciplineDeleteModal-${discipline.code}" type="button" class="btn btn-danger btn-sm" data-toggle="modal">
                        <i class="fa fa-times-circle"></i>
                        Apagar
                    </a>
                   
                    <form action="${pageContext.request.contextPath}/disciplines/edit" method="POST" th:object="${discipline}" class="inline">
                    	
                    	<input type="hidden" class="form-control" name="code" th:field="*{code}" value="${discipline.code}" >
                    
	                    <button type="submit" data-target="" type="button" class="btn btn-primary btn-sm">
	                        <i class="fa fa-pencil"></i>
	                        Editar
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

      <p><strong>Page:</strong> {{tableParams.page()}}
      <p><strong>Count per page:</strong> {{tableParams.count()}}

    </div>


</div><!-- end container -->
<jsp:include page="../includes/footerApp.jsp"/>
