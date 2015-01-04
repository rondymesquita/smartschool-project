angular.module('SmartschoolApp', [])

//post init
.run(function(){
  console.log($location.path());
});
