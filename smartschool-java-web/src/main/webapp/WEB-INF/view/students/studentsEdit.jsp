<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../includes/header.jsp"/>
<%-- <jsp:include page="../includes/headerApp.jsp"/> --%>

<div class="container-fluid mainContent" ng-controller="StudentController">

 	<ol class="breadcrumb">
        <li><a href="${pageContext.request.contextPath}/dashboard"><span class="fa fa-home" aria-hidden="true"></span> Dashboard</a></li>
        <li><a href="${pageContext.request.contextPath}/students"><span class="fa fa-book" aria-hidden="true"></span> Alunos</a></li>
        <li class="active"><span class="fa fa-book" aria-hidden="true"></span> Novo</li>
    </ol>

    <h3>
      Editar Registro
    </h3>

	
	<form name="formModalDiscipline" action="${pageContext.request.contextPath}/students/update" method="POST" th:object="${student}">
	
		<input id="fieldName" type="hidden" name="code" class="form-control square" value="${student.code}">
		<input id="fieldName" type="hidden" name="person.code" class="form-control square" value="${student.person.code}">
		
		<div class="form-group">
		    <label for="fieldName">Nome</label>
		    <input id="fieldName" name="person.name" type="text" class="form-control square" placeholder="" required value="${student.person.name}">
		</div>
		
		<div class="form-group">
		    <label for="fieldName">CPF</label>
		    <input id="fieldName" name="person.cpf" type="text" class="form-control square" placeholder="" required value="${student.person.cpf }" data-mask="999.999.999-99">
		</div>
		
		<div class="form-group">
		    <label for="fieldName">Matrícula</label>
		    <input id="fieldName" name="registry"  type="text" class="form-control square" placeholder="" required value="${student.registry }">
		</div>
		
		<fieldset>
		<legend>Dados de usuário</legend>
			<div class="form-group">
			    <label for="fieldName">Email</label>
			    <input id="fieldName" name="person.email" type="email" class="form-control square" placeholder="" required value="${student.person.email }" >
			</div>
		</fieldset>
		
		<button type="submit" class="btn btn-primary btn-primary">Salvar</button>
	
	</form>
		

</div><!-- end container -->
<%-- <jsp:include page="../includes/footerApp.jsp"/> --%>
