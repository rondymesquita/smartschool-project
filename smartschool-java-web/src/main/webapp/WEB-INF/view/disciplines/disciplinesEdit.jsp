<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../includes/header.jsp"/>
<%-- <jsp:include page="../includes/headerApp.jsp"/> --%>

<div class="container-fluid mainContent" ng-controller="ProfessorshipController">


 	<ol class="breadcrumb">
        <li><a href="${pageContext.request.contextPath}/dashboard"><span class="fa fa-home" aria-hidden="true"></span> Dashboard</a></li>
        <li><a href="${pageContext.request.contextPath}/disciplines"><span class="fa fa-book" aria-hidden="true"></span> Disciplinas</a></li>
        <li class="active"><span class="fa fa-book" aria-hidden="true"></span> Novo</li>
    </ol>

    <h3>
      Editar Registro
    </h3>

	
	<form name="formModalDiscipline" action="${pageContext.request.contextPath}/disciplines/update" method="POST" th:object="${discipline}">
	
		<input id="fieldName" type="hidden" name="code" th:field="*{code}" class="form-control square" placeholder="Ex: Programação" value="${discipline.code}">
		
		<div class="form-group" ng-class="{'has-error': !formModalDiscipline.name.$valid && formModalDiscipline.name.$dirty}">
		    <label for="fieldName">Nome da Disciplina</label>
		    <input id="fieldName" name="name" th:field="*{name}" type="text" class="form-control square" placeholder="Ex: Programação" value="${discipline.name}" required>
		</div>
		
		<div class="form-group" ng-class="{'has-error': !formModalDiscipline.workload.$valid && formModalDiscipline.workload.$dirty}">
		    <label for="fieldName">Carga Horária <small class="text-danger">&nbsp(Somente números)</small></label>
		    <input id="fieldName" name="workload" th:field="*{workload}" type="number" class="form-control square" placeholder="Ex: 40" value="${discipline.workload}" required>
		</div>
	
		<button type="submit" class="btn btn-primary btn-primary">Atualizar</button>
	
	</form>
		

</div><!-- end container -->
<%-- <jsp:include page="../includes/footerApp.jsp"/> --%>
