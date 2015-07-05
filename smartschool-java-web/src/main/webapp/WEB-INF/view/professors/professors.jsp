<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../includes/header.jsp"/>
<jsp:include page="../includes/headerApp.jsp"/>

<div class="container-fluid mainContent" ng-controller="ProfessorController">

 	<ol class="breadcrumb">
        <li><a href="${pageContext.request.contextPath}/dashboard"><span class="fa fa-home" aria-hidden="true"></span> Dashboard</a></li>
        <li class="active"><span class="fa fa-book" aria-hidden="true"></span> Professores</li>
    </ol>

    <h3>
      Professores
    </h3>
    
    <jsp:include page="../includes/alert.jsp"/>

      <nav class="navbar navbar-default">
        <div class="container-fluid">

          <div class="navbar-left toolbar">
            
            <a href="${pageContext.request.contextPath}/professors/new"  data-target="" type="button" class="btn btn-primary navbar-btn" data-toggle="modal">
              <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
              Novo
            </a>

          </div>

          <div class="navbar-form navbar-left" role="search">
            <div class="form-group">
              <input type="text" class="form-control" placeholder="Nome ou código da disciplina" style="min-width:300px;" ng-model="search" ng-redirect-on-enter="${pageContext.request.contextPath}/professors/{{search}}">
            </div>
            <a href="${pageContext.request.contextPath}/professors/{{search}}" type="submit" class="btn btn-primary" ng-disabled="onTransaction">
              <span class="glyphicon glyphicon-search" aria-hidden="true"></span>
              Buscar {{search}} </a
          </div>

        </div>
      </nav>
	

    <div>
      <table class="table table-striped" ng-table="tableParams">
      	<thead>
      		<th>Código</th>
      		<th>Matrícula</th>
      		<th>Nome</th>
      		<th>Email</th>
      		<th></th>
      	</thead>
        <tbody>
        
        <c:forEach var="professor" items="${professors}">
          <tr ng-mouseover="showButtons_${professor.code} = true" ng-mouseleave="showButtons_${professor.code} = false">

            <td width="20%">${professor.code}</td>
            <td width="20%">${professor.registry}</td>
            <td width="20%">${professor.person.name}</td>
            <td width="20%">${professor.person.email}</td>
            <td width="20%">

                <div class="registryOptions" ng-show="showButtons_${professor.code}">

                    <!-- DELETE REGISTRY -->
                    <modal handler="professorDeleteModal-${professor.code}" on-primary-button-click-event="deleteProfessor(${professor.code})" primary-button-text="Apagar" primary-button-context="danger" secondary-button-text="Cancelar" modal-title="Apagar Registro" modal-body-html="Deseja apagar o registro?" modal-dismissible="true"></modal>
                    <a data-target="#professorDeleteModal-${professor.code}" type="button" class="btn btn-danger btn-sm" data-toggle="modal">
                        <i class="fa fa-times-circle"></i>
                        Apagar
                    </a>
                    
                   
                    <form action="${pageContext.request.contextPath}/professors/edit" method="POST" th:object="${professor}" class="inline">
                    	
                    	<input type="hidden" class="form-control" name="code" th:field="*{code}" value="${professor.code}" >
                    
	                    <button type="submit" type="button" class="btn btn-primary btn-sm">
	                        <i class="fa fa-pencil"></i>
	                        Editar
	                    </button>
					</form>
                </div>

            </td>
          </tr>
          </c:forEach>

        </tbody>
      </table>
      
      <jsp:include page="../includes/alertQuery.jsp"/>


    </div>


</div><!-- end container -->
<jsp:include page="../includes/footerApp.jsp"/>
