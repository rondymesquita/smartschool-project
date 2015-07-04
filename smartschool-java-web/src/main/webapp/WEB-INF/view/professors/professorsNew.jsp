<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../includes/header.jsp"/>
<%-- <jsp:include page="../includes/headerApp.jsp"/> --%>

<div class="container-fluid mainContent" ng-controller="ProfessorshipController">


 	<ol class="breadcrumb">
        <li><a href="${pageContext.request.contextPath}/dashboard"><span class="fa fa-home" aria-hidden="true"></span> Dashboard</a></li>
        <li><a href="${pageContext.request.contextPath}/professors"><span class="fa fa-book" aria-hidden="true"></span> Disciplinas</a></li>
        <li class="active"><span class="fa fa-book" aria-hidden="true"></span> Novo</li>
    </ol>

    <h3>
      Novo Registro
    </h3>

	
	<form name="formModalDiscipline" action="${pageContext.request.contextPath}/professors" method="POST" th:object="${professor}">
	
		<input id="fieldName" type="hidden" class="form-control square" placeholder="">
		
		<div class="form-group">
		    <label for="fieldName">Nome</label>
		    <input id="fieldName" name="person.name" th:field="*{name}" type="text" class="form-control square" placeholder="" required value="peter parker">
		</div>
		
		<div class="form-group">
		    <label for="fieldName">CPF</label>
		    <input id="fieldName" name="person.cpf" th:field="*{cpf}" type="number" class="form-control square" placeholder="" required value="12345678900">
		</div>
		
		<div class="form-group">
		    <label for="fieldName">Formação</label>
		    <input id="fieldName" name="formation" type="text" class="form-control square" placeholder="" required value="msc">
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
			<div class="form-group">
			    <label for="fieldName">Senha</label>
			    <input id="fieldName" name="password" th:field="*{email}" type="email" class="form-control square" placeholder="" required value="123">
			</div>
		</fieldset>
		
		
		<div class="radio">
		  <label>
		    <input type="radio" name="person.role" th:field="*{person.person.role}" id="ROLE_MANAGER" value="ROLE_MANAGER" checked="checked">
		    Coordenador
		  </label>
		</div>
		
		<div class="radio">
		  <label>
		    <input type="radio" name="person.role" th:field="*{person.person.role}" id="ROLE_PROFESSOR" value="ROLE_PROFESSOR">
		    Professor
		  </label>
		</div>
		
	
		<button type="submit" class="btn btn-primary btn-primary">Salvar</button>
	
	</form>
		

</div><!-- end container -->
<%-- <jsp:include page="../includes/footerApp.jsp"/> --%>
