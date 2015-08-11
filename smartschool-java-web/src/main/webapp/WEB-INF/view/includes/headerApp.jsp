<title>Smartschool</title>
<nav class="navbar navbar-default">
    <div class="container-fluid">

        <a class="navbar-brand" href="${pageContext.request.contextPath}/dashboard">Smartschool</a>

        <ul class="nav navbar-nav navbar-right">
            <li class="dropdown">
                <a href="*" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
                    <i class="fa fa-user"></i> 
                    ${sessionScope.userSession.username}
                     <span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
						<li>
                         	<a href="${pageContext.request.contextPath}/profile" style="cursor:pointer;">
                         		<i class="fa fa-cog"></i> Perfil
                         	</a>
                         </li>
                         <li>
                         	<a href="${pageContext.request.contextPath}/auth/logout" style="cursor:pointer;">
                         		<i class="fa fa-sign-out"></i> Sair
                         	</a>
                         </li>
                         
                        

                    </ul>
                </li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
