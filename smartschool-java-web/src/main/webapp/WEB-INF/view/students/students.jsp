<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../includes/header.jsp"/>
<jsp:include page="../includes/headerApp.jsp"/>

<div class="container-fluid mainContent" ng-controller="StudentController">

 	<ol class="breadcrumb">
        <li><a href="${pageContext.request.contextPath}/dashboard"><span class="fa fa-home" aria-hidden="true"></span> Dashboard</a></li>
        <li class="active"><span class="fa fa-book" aria-hidden="true"></span> Alunos</li>
    </ol>

    <h3>
      Alunos
    </h3>
    
    <jsp:include page="../includes/alert.jsp"/>

      <nav class="navbar navbar-default">
        <div class="container-fluid">

          <div class="navbar-left toolbar">
            
            <a href="${pageContext.request.contextPath}/students/new"  data-target="" type="button" class="btn btn-primary navbar-btn" data-toggle="modal">
              <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
              Novo
            </a>

          </div>

          <div class="navbar-form navbar-left" role="search">
            <div class="form-group">
              <input type="text" class="form-control" placeholder="Nome ou código do aluno" style="min-width:300px;" ng-model="search" ng-redirect-on-enter="${pageContext.request.contextPath}/students/{{search}}">
            </div>
            <a href="${pageContext.request.contextPath}/students/{{search}}" type="submit" class="btn btn-primary" ng-disabled="onTransaction">
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
        
        <c:forEach var="student" items="${students}">
          <tr ng-mouseover="showButtons_${student.code} = true" ng-mouseleave="showButtons_${student.code} = false">

            <td width="20%">${student.code}</td>
            <td width="20%">${student.registry}</td>
            <td width="20%">${student.person.name}</td>
            <td width="20%">${student.person.email}</td>
            <td width="20%">

                <div class="registryOptions" ng-show="showButtons_${student.code}">

                    <!-- DELETE REGISTRY -->
                    <modal handler="studentDeleteModal-${student.code}" object="student" code="${student.code}" on-primary-button-click-action="${pageContext.request.contextPath}/students/delete" primary-button-text="Apagar" primary-button-context="danger" secondary-button-text="Cancelar" modal-title="Apagar Registro" modal-body-html="Deseja apagar o registro?" modal-dismissible="true"></modal>
                    
                    <a data-target="#studentDeleteModal-${student.code}" type="button" class="btn btn-danger btn-sm" data-toggle="modal">
                        <i class="fa fa-times-circle"></i>
                        Apagar
                    </a>
                    
                   
                    <form action="${pageContext.request.contextPath}/students/edit" method="POST" th:object="${student}" class="inline">
                    	
                    	<input type="hidden" class="form-control" name="code" th:field="*{code}" value="${student.code}" >
                    
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
