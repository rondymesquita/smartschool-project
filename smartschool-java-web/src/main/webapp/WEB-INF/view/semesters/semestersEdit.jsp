<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../includes/header.jsp"/>
<%-- <jsp:include page="../includes/headerApp.jsp"/> --%>

<div class="container-fluid mainContent" ng-controller="SemesterController">


 	<ol class="breadcrumb">
        <li><a href="${pageContext.request.contextPath}/dashboard"><span class="fa fa-home" aria-hidden="true"></span> Dashboard</a></li>
        <li><a href="${pageContext.request.contextPath}/semesters"><span class="fa fa-book" aria-hidden="true"></span> Cursos</a></li>
        <li class="active"><span class="fa fa-book" aria-hidden="true"></span> Novo</li>
    </ol>

    <h3>
      Editar Registro
    </h3>

	
	<form name="formModalDiscipline" action="${pageContext.request.contextPath}/semesters/update" method="POST" th:object="${semester}">
	
		<input id="fieldName" type="hidden" name="code" th:field="*{code}" class="form-control square" placeholder="Ex: Programação" value="${semester.code}">
		
		<div class="form-group" ng-class="{'has-error': !formModalDiscipline.name.$valid && formModalDiscipline.name.$dirty}">
		    <label for="fieldName">Nome do Semestre</label>
		    <input id="fieldName" name="name" th:field="*{name}" type="text" class="form-control square" placeholder="Ex: 2010.1" value="${semester.name}" required>
		</div>
	
		<button type="submit" class="btn btn-primary btn-primary">Atualizar</button>
	
	</form>
		

</div><!-- end container -->
<%-- <jsp:include page="../includes/footerApp.jsp"/> --%>
