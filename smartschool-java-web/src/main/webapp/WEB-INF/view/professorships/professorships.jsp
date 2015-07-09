<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../includes/header.jsp"/>
<jsp:include page="../includes/headerApp.jsp"/>

<div class="container-fluid mainContent">


 <ol class="breadcrumb">
        <li><a href="${pageContext.request.contextPath}/dashboard"><span class="fa fa-home" aria-hidden="true"></span> Dashboard</a></li>
        <li class="active"><span class="fa fa-book" aria-hidden="true"></span> Cadeiras</li>
    </ol>

    <h3>
      ${title}
    </h3>

      <nav class="navbar navbar-default">
        <div class="container-fluid">

          <div class="navbar-left toolbar">
           
            <a href="${pageContext.request.contextPath}/professorships/new" type="button" class="btn btn-primary navbar-btn">
              <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
              Novo
            </a>

          </div>

          <form class="navbar-form navbar-left" role="search" ng-submit="searchDisciplines()">
            <div class="form-group">
              <input type="text" class="form-control" placeholder="Nome ou código da cadeira" style="min-width:300px;">
            </div>
            <button type="submit" class="btn btn-primary" ng-disabled="onTransaction">
              <span class="glyphicon glyphicon-search" aria-hidden="true"></span>
              Buscar</button>
          </form>

        </div>
      </nav>


    <div>
      <table class="table table-striped" ng-table="tableParams">
        <tbody>
        
        <c:forEach var="professor" items="${professors}">
        	<tr>
                <td data-title="'Cód'" sortable="'code'" width="10%">${professor.person.code}</td>
                <td data-title="'Matrícula'" sortable="'registry'" width="10%">${professor.registry}</td>
                <td data-title="'Nome'" sortable="'name'" width="20%">${professor.person.name}</td>
                <td data-title="'CPF'" sortable="'cpf'" width="20%">${professor.person.cpf}</td>
                <td data-title="'Email'" sortable="'email'" width="20%">${professor.person.email}</td>

                <td width="20%">


                    <div class="registryOptions" ng-show="showButtons">

                        <!-- DELETE REGISTRY -->
                        <modal id="professorDeleteModal" on-primary-button-click-event="deleteProfessor(professor)" primary-button-text="Apagar" primary-button-context="danger" secondary-button-text="Cancelar" modal-title="Apagar Registro" modal-body-html="Deseja apagar o registro?" modal-dismissible="true"></modal>
                        <a data-target="" type="button" class="btn btn-danger btn-sm" data-toggle="modal">
                            <i class="fa fa-times-circle"></i>
                            Apagar
                        </a>

                        <!-- UPDATE REGISTRY -->
                        <modal id="professorUpdateModal" on-primary-button-click-event="updateProfessor(professor)" primary-button-text="Atualizar" primary-button-context="primary" secondary-button-text="Cancelar" modal-title="Atualizar Registro" modal-body="../professor/professorCreateModal.html" modal-dismissible="true"></modal>
                        <a data-target="" type="button" class="btn btn-primary btn-sm" data-toggle="modal">
                            <i class="fa fa-pencil"></i>
                            Editar
                        </a>

                    </div>

                </td>
                </tr>  
        </c:forEach>
          

          <tr ng-show="onResponse || onTransaction" ng-hide="onResponse">
            <td colspan="3" class="{{responseData.status}} text-{{responseData.status}}">
              <i class="fa fa-{{responseData.status}}"></i>
              {{responseData.message}}
            </td>
          </tr>

        </tbody>
      </table>

    </div>


</div><!-- end container -->
<jsp:include page="../includes/footerApp.jsp"/>
