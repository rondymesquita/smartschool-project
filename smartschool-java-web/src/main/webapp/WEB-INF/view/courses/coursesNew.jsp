<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../includes/header.jsp"/>
<%-- <jsp:include page="../includes/headerApp.jsp"/> --%>

<div class="container-fluid mainContent" ng-controller="CourseController">


 	<ol class="breadcrumb">
        <li><a href="${pageContext.request.contextPath}/dashboard"><span class="fa fa-home" aria-hidden="true"></span> Dashboard</a></li>
        <li><a href="${pageContext.request.contextPath}/courses"><span class="fa fa-book" aria-hidden="true"></span> Cursos</a></li>
        <li class="active"><span class="fa fa-book" aria-hidden="true"></span> Novo</li>
    </ol>

    <h3>
      Novo Registro
    </h3>

	
	<form name="formModalCourse" action="${pageContext.request.contextPath}/courses" method="POST" th:object="${course}">
	
		<input id="fieldName" ng-model="parentScope.course.code" type="hidden" class="form-control square" placeholder="Ex: Programação">
		
		<div class="form-group" ng-class="{'has-error': !formModalDiscipline.name.$valid && formModalDiscipline.name.$dirty}">
		    <label for="fieldName">Nome do Curso</label>
		    <input id="fieldName" name="name" th:field="*{name}" type="text" class="form-control square" placeholder="Ex: Sistemas de Informação" required>
		</div>
		
	
		<button type="submit" class="btn btn-primary btn-primary">Salvar</button>
	
	</form>
		

</div><!-- end container -->
<%-- <jsp:include page="../includes/footerApp.jsp"/> --%>
