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

	<form name="formNewDiscipline" action="${pageContext.request.contextPath}/professorships" method="POST" th:object="${professorships}">
		
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
		
		<div class="form-group">
		    <label for="fieldName">Selecione curso</label>
		    <select class="combobox form-control square" name="courseCode">
		    	<c:forEach var="course" items="${courses}">
		    		<option value="${course.code}">${course.name}</option>
		    	</c:forEach>
		    </select>
		</div>
		
		<div class="form-group">
		    <label for="fieldName">Selecione semestre</label>
		    <select class="combobox form-control square" name="semesterCode">
		    	<c:forEach var="semester" items="${semesters}">
		    		<option value="${semester.code}">${semester.name}</option>
		    	</c:forEach>
		    </select>
		</div>
		
		
		<div class="row">
			<div class="col-xs-12">
				<legend>Selecionar Alunos</legend>
				<div class="row">
					<div class="col-xs-6">
						<nav class="navbar navbar-default">
					
					          <div class="navbar-form">
					            <div class="form-group"> 
					            <!-- ng-submit-on-enter="${pageContext.request.contextPath}/api/students/{{search}}" -->
					              <input type="text" class="form-control" placeholder="Nome ou código do aluno" style="min-width:300px;" ng-model="search" ng-enter="searchStudentsByCodeOrName(search)">
					            </div>
					            <!--  -->
					            <a class="btn btn-primary"  ng-disabled="onTransaction" ng-click="searchStudentsByCodeOrName(search)">
					              <span class="glyphicon glyphicon-search" aria-hidden="true"></span>
					              Buscar {{search}}</a>
					          </div>
					
					        
					      </nav>
					      <div class="table-scroll table-list-student-left">
						      <table class="table table-striped " ng-table="tableParams">
						      	<thead>
						      	<tr>
						      		<th>Código</th>
						      		<th>Matrícula</th>
						      		<th>Nome</th>
						      		<th></th>
						      		</tr>
						      	</thead>
						        <tbody class="">
						        	<tr ng-repeat="student in students" ng-click="addStudent(student)" ng-mouseover="showButtons = true" ng-mouseleave="showButtons = false" class="mouse-click">
						        		<td width="10%">{{student.code}}</td>
							            <td width="20%">{{student.registry}}</td>
							            <td width="50%">{{student.person.name}}</td>
						                <td width="10%">
							                <div class="registryOptions" ng-show="showButtons">
							                	 <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
							                </div>
						                </td>
						            </tr>
						            
						            <tr ng-show="onWarning || onTransaction">
							            <td colspan="3" class="{{responseData.status}} text-{{responseData.status}}">
							              <i class="fa fa-{{responseData.status}}"></i>
							              {{responseData.message}}
							            </td>
							        </tr>  
							        
						        </tbody>
						      </table>
					      </div>
									
					</div><!-- end col -->
					<div class="col-xs-6">
						<div class="table-scroll table-list-student-right">
							<table class="table table-striped" ng-table="tableParams">
							<thead>
					      	<tr>
					      		<th>Código</th>
					      		<th>Matrícula</th>
					      		<th>Nome</th>
					      		<th></th>
					      		</tr>
					      	</thead>
						        
						        <tbody class="">
					        	<tr ng-repeat="student in studentsToSave" ng-click="removeStudent(student)" ng-mouseover="showButtons = true" ng-mouseleave="showButtons = false" class="mouse-click">
					        		<td width="10%">
					        		<input type="checkbox" value="{{student.code}}" id="studentsCodes" name="studentsCodes" checked="checked">
					        		{{student.code}}
					        		</td>
						            <td width="20%">{{student.registry}}</td>
						            <td width="50%">{{student.person.name}}</td>
					                <td width="10%">
						                <div class="registryOptions" ng-show="showButtons">
						                	 <span class="glyphicon glyphicon-minus" aria-hidden="true"></span>
						                </div>
					                </td>
					            </tr>
					            
					            <!-- <div class="checkbox" ng-repeat="student in studentsToSave" ng-click="removeStudent(student)">
-                                                 <label>
-                                                   <input type="checkbox" value="{{student}}" id="students" name="students" class="hide" checked="checked">
-                                                   {{student.person.name}}
-                                                 </label>
-                                               </div> -->
					            
					        </tbody>
						      </table>
					      </div>
					   
					    
					</div><!-- end col -->
				</div>
			</div>
		</div>
		
		<div class="row" style="margin-top:12px;">
			<div class="col-xs-12">
				<button type="submit" class="btn btn-primary btn-primary" >Salvar</button>
			</div>
		</div>
	
	</form>

      
</div><!-- end container -->
<%-- <jsp:include page="../includes/footerApp.jsp"/> --%>
