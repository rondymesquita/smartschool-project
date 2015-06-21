<title>{{appName}} - {{title}}</title>
<nav class="navbar navbar-default">
    <div class="container-fluid">

        <a class="navbar-brand" href="#/dashboard">{{appName}}</a>

        <ul class="nav navbar-nav navbar-right">
            <li class="dropdown">
                <a href="*" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
                    <i class="fa fa-user"></i> {{username}} <span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">

                        <form id="formlogout" ng-submit="logout()" >
                        </form>
                        <li><a onclick="logoutForm();" style="cursor:pointer;"><i class="fa fa-sign-out"></i> Sign out</a></li>

                        <script>
                            function logoutForm(){
                                $("#formlogout").submit();
                            }
                        </script>

                    </ul>
                </li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
