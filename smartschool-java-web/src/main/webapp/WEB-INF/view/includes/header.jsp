<!DOCTYPE HTML>
<html>
  <head>
    <title>Smartschool Web</title>
    <!-- Styles -->
  <link  href="${pageContext.request.contextPath}/resources/bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css">
  <link  href="${pageContext.request.contextPath}/resources/bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
  <link  href="${pageContext.request.contextPath}/resources/bower_components/toastr/toastr.css" rel="stylesheet" type="text/css">
  <link  href="${pageContext.request.contextPath}/resources/css/custom.css" rel="stylesheet" type="text/css">
  <link  href="${pageContext.request.contextPath}/resources/bootstrap-combobox/bootstrap-combobox.css" rel="stylesheet" type="text/css">
  
  
  <script src="${pageContext.request.contextPath}/resources/bower_components/jquery/dist/jquery.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/bower_components/toastr/toastr.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/bootstrap-combobox/bootstrap-combobox.js"></script>
  <script src="${pageContext.request.contextPath}/resources/bower_components/angular/angular.js"></script>
  <script src="${pageContext.request.contextPath}/resources/bower_components/angular-resource/angular-resource.js"></script>
  <script src="${pageContext.request.contextPath}/resources/jasny/inputmask.js"></script>
  
  <!-- angular -->
  <script src="${pageContext.request.contextPath}/resources/js/app.module.js"></script>
  <script src="${pageContext.request.contextPath}/resources/js/shared/config.js"></script>
  <script src="${pageContext.request.contextPath}/resources/js/shared/constants.js"></script>
  <script src="${pageContext.request.contextPath}/resources/js/shared/toastFactory.js"></script>
  <script src="${pageContext.request.contextPath}/resources/js/shared/modal/modalDirective.js"></script>
  
  <!-- services -->
  <script src="${pageContext.request.contextPath}/resources/js/components/professorship/professorshipService.js"></script>
  <script src="${pageContext.request.contextPath}/resources/js/components/professor/professorService.js"></script>
  <script src="${pageContext.request.contextPath}/resources/js/components/discipline/disciplineService.js"></script>
  <script src="${pageContext.request.contextPath}/resources/js/components/student/studentService.js"></script>
  <script src="${pageContext.request.contextPath}/resources/js/components/course/courseService.js"></script>
  <script src="${pageContext.request.contextPath}/resources/js/components/semester/semesterService.js"></script>
  
  <!-- controllers -->
  <%-- <script src="${pageContext.request.contextPath}/resources/js/components/base/baseController.js"></script> --%>
  <script src="${pageContext.request.contextPath}/resources/js/components/professorship/professorshipController.js"></script>
  <script src="${pageContext.request.contextPath}/resources/js/components/professor/professorController.js"></script>
  <script src="${pageContext.request.contextPath}/resources/js/components/discipline/disciplineController.js"></script>
  <script src="${pageContext.request.contextPath}/resources/js/components/student/studentController.js"></script>
  <script src="${pageContext.request.contextPath}/resources/js/components/course/courseController.js"></script>
  <script src="${pageContext.request.contextPath}/resources/js/components/semester/semesterController.js"></script>
  
  <script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
  
  </head>
  <body ng-app="SmartschoolApp">