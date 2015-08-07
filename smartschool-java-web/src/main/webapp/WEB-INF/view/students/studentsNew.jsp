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
      Novo Registro
    </h3>

	
	<form name="formModalDiscipline" action="${pageContext.request.contextPath}/students" method="POST" th:object="${student}">
	
		<input id="fieldName" type="hidden" class="form-control square" placeholder="">
		
		<div class="form-group">
		    <label for="fieldName">Nome</label>
		    <input id="fieldName" name="person.name" th:field="*{name}" type="text" class="form-control square" placeholder="" required value="Peter Parker">
		</div>
		
		<div class="form-group">
		    <label for="fieldName">CPF</label>
		    <input id="fieldName" name="person.cpf" th:field="*{cpf}" type="text" class="form-control square" placeholder="" required value="123.456.789-00" data-mask="999.999.999-99">
		</div>
		
		<div class="form-group">
		    <label for="fieldName">Matrícula</label>
		    <input id="fieldName" name="registry"  type="text" class="form-control square" placeholder="" required value="123">
		</div>
		
		<fieldset>
		<legend>Dados de usuário</legend>
			<div class="form-group">
			    <label for="fieldName">Email</label>
			    <input id="fieldName" name="person.email" th:field="*{email}" type="email" class="form-control square" placeholder="" required value="email@email.com">
			</div>
		</fieldset>
	
		<button type="submit" class="btn btn-primary btn-primary">Salvar</button>
	
	</form>
		

</div><!-- end container -->
<%-- <jsp:include page="../includes/footerApp.jsp"/> --%>
