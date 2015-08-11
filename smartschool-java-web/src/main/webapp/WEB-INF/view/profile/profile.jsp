<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../includes/header.jsp"/>
<jsp:include page="../includes/headerApp.jsp"/>

<div class="container-fluid mainContent" ng-controller="ProfessorController">
	
	<ol class="breadcrumb">
        <li><a href="${pageContext.request.contextPath}/dashboard"><span class="fa fa-home" aria-hidden="true"></span> Dashboard</a></li>
        <li class="active"><span class="fa fa-book" aria-hidden="true"></span> Perfil</li>
    </ol>

    <h3>
      Perfil
    </h3>
    
    <jsp:include page="../includes/alert.jsp"/>
    
    <div class="row">
    <div class="col-sm-6">
    <form name="formModalDiscipline" action="${pageContext.request.contextPath}/profile/change-access" method="POST" >
	
		<input id="fieldName" type="hidden" name="code" class="form-control square" value="${professor.code}">
		<input id="fieldName" type="hidden" name="person.code" class="form-control square" value="${professor.person.code}">
		
		<div class="form-group">
		    <label for="fieldName">Nome</label>
		    <input id="fieldName" name="person.name" type="text" class="form-control square" placeholder="" required value="${professor.person.name}">
		</div>
		
		<div class="form-group">
		    <label for="fieldName">CPF</label>
		    <input id="fieldName" name="person.cpf" type="text" class="form-control square" placeholder="" required value="${professor.person.cpf}" data-mask="999.999.999-99">
		</div>
		
		<div class="form-group">
		    <label for="fieldName">Formação</label>
		    <input id="fieldName" name="formation" type="text" class="form-control square" placeholder="" required value="${professor.formation}">
		</div>
		
		<div class="form-group">
		    <label for="fieldName">Matrícula</label>
		    <input id="fieldName" name="registry"  type="text" class="form-control square" placeholder="" required value="${professor.registry}">
		</div>
		
		<fieldset>
		<legend>Dados de usuário</legend>
			<div class="form-group">
			    <label for="fieldName">Email</label>
			    <input id="fieldName" name="person.email" type="email" class="form-control square" placeholder="" required value="${professor.person.email}">
			</div>
			<div class="form-group">
			    <label for="fieldName">Senha Atual</label>
			    <input id="fieldName" name="password" type="password" class="form-control square" placeholder="" required value="">
			</div>
			
			<div class="form-group">
			    <label for="fieldName">Nova Senha</label>
			    <input id="fieldName" name="newPassword" type="password" class="form-control square" placeholder="" required value="">
			</div>
		</fieldset>
		
		<button type="submit" class="btn btn-primary btn-primary">Salvar</button>
	
	</form>
	</div>
	</div>


</div><!-- end container -->
<jsp:include page="../includes/footerApp.jsp"/>
