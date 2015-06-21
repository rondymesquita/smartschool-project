

<jsp:include page="../includes/header.jsp"/>
<style>
body{
    background:gray;
}
div.loginContainer{
    width: 100%;
    max-width: 480px;
    margin: 20px auto 0px auto;
    background: #eee;
    padding:20px;
    border-radius:4px;

}
#email{
    border-radius:6px 6px 0 0;
    border-bottom: none !important;

}
#email:focus{
    border-bottom: none !important;
}
#password{
    border-radius:0 0 6px 6px;

}
.form-control{
    box-shadow: none;
    /*height:68px;*/

}
.form-control:focus{
    border-color: none;
    outline: 0;
    -webkit-box-shadow: none;
    box-shadow: none;
    border-color: #ccc;
    color:#337ab7;
}
</style>

<div class="loginContainer">
    <form class="form-signin" role="form" action="${pageContext.request.contextPath}/auth/login" method="post">

      <h2 class="form-signin-heading">Smartschool</h2>
	

      <div id="loginAlert" class="alert alert-{{responseDataLogin.status}} alert-dismissible" role="alert"  ng-show="onResponse && !onTransaction ">
          <button type="button" class="close" data-dismiss="loginAlert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
          <span>{{responseDataLogin.message}}</span>
       </div>


      <label for="email" class="sr-only">Email</label>
      <input id="email" name="email" type="text" class="form-control input-lg" placeholder="Email address" required autofocus >

      <label for="password" class="sr-only">Senha</label>
      <input id="password" name="password" type="password" class="form-control input-lg" placeholder="Password" required >

      <br>
      <button class="btn btn-lg btn-primary btn-block" type="submit" >Login</button>
    </form>
</div>


