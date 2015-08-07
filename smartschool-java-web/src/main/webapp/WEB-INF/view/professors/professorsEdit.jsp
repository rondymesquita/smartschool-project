<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../includes/header.jsp"/>
<%-- <jsp:include page="../includes/headerApp.jsp"/> --%>

<div class="container-fluid mainContent" ng-controller="ProfessorController">

 	<ol class="breadcrumb">
        <li><a href="${pageContext.request.contextPath}/dashboard"><span class="fa fa-home" aria-hidden="true"></span> Dashboard</a></li>
        <li><a href="${pageContext.request.contextPath}/professors"><span class="fa fa-book" aria-hidden="true"></span> Professores</a></li>
        <li class="active"><span class="fa fa-book" aria-hidden="true"></span> Novo</li>
    </ol>

    <h3>
      Editar Registro
    </h3>

	
	<form name="formModalDiscipline" action="${pageContext.request.contextPath}/professors/update" method="POST" th:object="${professor}">
	
		<input id="fieldName" type="hidden" name="code" class="form-control square" value="${professor.code}">
		<input id="fieldName" type="hidden" name="person.code" class="form-control square" value="${professor.person.code}">
		
		<div class="form-group">
		    <label for="fieldName">Nome</label>
		    <input id="fieldName" name="person.name" type="text" class="form-control square" placeholder="" required value="${professor.person.name}">
		</div>
		
		<div class="form-group">
		    <label for="fieldName">CPF</label>
		    <input id="fieldName" name="person.cpf" type="text" class="form-control square" placeholder="" required value="${professor.person.cpf }" data-mask="999.999.999-99">
		</div>
		
		<div class="form-group">
		    <label for="fieldName">Formação</label>
		    <input id="fieldName" name="formation" type="text" class="form-control square" placeholder="" required value="${professor.formation }">
		</div>
		
		<div class="form-group">
		    <label for="fieldName">Matrícula</label>
		    <input id="fieldName" name="registry"  type="text" class="form-control square" placeholder="" required value="${professor.registry }">
		</div>
		
		<fieldset>
		<legend>Dados de usuário</legend>
			<div class="form-group">
			    <label for="fieldName">Email</label>
			    <input id="fieldName" name="person.email" type="email" class="form-control square" placeholder="" required value="${professor.person.email }">
			</div>
		</fieldset>
		
		
		<c:if test="${professor.person.role == 'ROLE_MANAGER'}">
			<c:set var="managerChecked" value="checked"/>
		</c:if>
		<c:if test="${professor.person.role == 'ROLE_PROFESSOR'}">
			<c:set var="professorChecked" value="checked"/>
		</c:if>
		
		
		<div class="radio">
		  <label>
		    <input type="radio" name="person.role" th:field="*{person.person.role}" id="ROLE_MANAGER" value="ROLE_MANAGER" ${managerChecked}>
		    Coordenador
		  </label>
		</div>
		
		<div class="radio">
		  <label>
		    <input type="radio" name="person.role" th:field="*{person.person.role}" id="ROLE_PROFESSOR" value="ROLE_PROFESSOR" ${professorChecked}> 
		    Professor
		  </label>
		</div>
		
	
		<button type="submit" class="btn btn-primary btn-primary">Salvar</button>
	
	</form>
		

</div><!-- end container -->
<%-- <jsp:include page="../includes/footerApp.jsp"/> --%>
