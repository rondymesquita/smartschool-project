<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../includes/header.jsp"/>
<%-- <jsp:include page="../includes/headerApp.jsp"/> --%>

<div class="container-fluid mainContent" ng-controller="ProfessorshipController">


 	<ol class="breadcrumb">
        <li><a href="${pageContext.request.contextPath}/dashboard"><span class="fa fa-home" aria-hidden="true"></span> Dashboard</a></li>
        <li><a href="${pageContext.request.contextPath}/professorships"><span class="fa fa-book" aria-hidden="true"></span> Cadeiras</a></li>
        <li class="active"><span class="fa fa-book" aria-hidden="true"></span> Novo</li>
    </ol>

    <h3>
      Novo Registro
    </h3>

	<form name="formNewDiscipline" action="${pageContext.request.contextPath}/professorships" method="POST">
		
		<div class="form-group">
		    <label for="fieldName">Selecione Disciplina
		    </label>
		    <select class="combobox form-control square" name="disciplineCode">
		    	
		    	<c:forEach var="discipline" items="${disciplines}">
		    		<option value="${discipline.code}">${discipline.name}</option>
		    	</c:forEach>
		    </select>
		</div>
		
		<div class="form-group">
		    <label for="fieldName">Selecione professor</label>
		    <select class="combobox form-control square" name="professorCode">
		    	<c:forEach var="professor" items="${professors}">
		    		<option value="${professor.code}">${professor.person.name}</option>
		    	</c:forEach>
		    </select>
		</div>
		
		
		<div class="row">
			<div class="col-xs-12">
			
				<div class="row">
					<div class="col-xs-6">
						
						<nav class="navbar navbar-default">
					
					          <div class="navbar-form">
					            <div class="form-group">
					              <input type="text" ng-model="studentName" class="form-control" placeholder="Nome ou código do aluno" style="min-width:300px;">
					            </div>
					            <a class="btn btn-primary" ng-click="searchStudents(studentName)" ng-disabled="onTransaction">
					              <span class="glyphicon glyphicon-search" aria-hidden="true"></span>
					              Buscar</a>
					          </div>
					
					        
					      </nav>
					      
					      <table class="table table-striped table-list-student-left" ng-table="tableParams">
					        <tbody class="table-scroll">
					        	<tr ng-repeat="student in students">
					                <td  width="10%" ng-click="addStudent(student)">{{student.person.name}}</td>
					            </tr>
					            
					            <tr ng-show="onTransaction || onWarning" ng-hide="onSuccess">
						            <td colspan="3" class="{{responseData.status}} text-{{responseData.status}}">
						              <i class="fa fa-{{responseData.status}}"></i>
						              {{responseData.message}}
						            </td>
						        </tr>  
						        
					        </tbody>
					      </table>
									
					</div><!-- end col -->
					<div class="col-xs-6">
					
						<table class="table table-striped table-list-student-right" ng-table="tableParams">
					        <tbody class="table-scroll">
					        	<tr ng-repeat="student in studentsToSave">
					               <td data-title="'Cód'" sortable="'code'" width="10%" ng-click="removeStudent(student)">{{student.person.name}}</td>
					            </tr>  
					        </tbody>
					      </table>
					   
					    
					</div><!-- end col -->
				</div>
			</div>
		</div>
	
		<button type="submit" class="btn btn-primary btn-primary" >Salvar</button>
	
	</form>

      
</div><!-- end container -->
<%-- <jsp:include page="../includes/footerApp.jsp"/> --%>
