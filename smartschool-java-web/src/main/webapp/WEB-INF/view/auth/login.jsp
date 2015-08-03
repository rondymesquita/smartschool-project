<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
	 
	    <c:if  test="${responseData != null}">
	       <div id="loginAlert" class="alert alert-danger alert-dismissible" role="alert">
	          <span>${responseData.message}</span>
	       </div>
	    </c:if>


      <label for="email" class="sr-only">Email</label>
      <input id="email" name="username" type="text" class="form-control input-lg" placeholder="Email address" required autofocus value="admin">

      <label for="password" class="sr-only">Senha</label>
      <input id="password" name="password" type="password" class="form-control input-lg" placeholder="Password" required value="admin">

      <br>
      <button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>
    </form>
</div>


