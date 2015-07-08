<jsp:include page="../includes/header.jsp"/>
<jsp:include page="../includes/headerApp.jsp"/>

<div class="container-fluid mainContent">

    <div class="row">
        <div class="col-sm-4">
            <div class="list-group">
            
                <a href="${pageContext.request.contextPath}/disciplines" class="list-group-item">
                    <span class="fa fa-book" aria-hidden="true"></span>
                    Disciplinas
                </a>
                
                <a href="${pageContext.request.contextPath}/professors" class="list-group-item">
                    <span class="fa fa-university" aria-hidden="true"></span>
                    Professores
                </a>
                
                <a href="${pageContext.request.contextPath}/students" class="list-group-item">
                    <span class="fa fa-graduation-cap" aria-hidden="true"></span>
                    Alunos
                </a>
                
                <a href="${pageContext.request.contextPath}/professorships" class="list-group-item">
                    <span class="fa fa-book" aria-hidden="true"></span>
                    Cadeiras
                </a>
                
                <a href="${pageContext.request.contextPath}/semesters" class="list-group-item">
                    <span class="fa fa-book" aria-hidden="true"></span>
                    Semestres
                </a>
                
            </div>
        </div>
    </div>


</div>
<jsp:include page="../includes/footerApp.jsp"/>
