angular.module('SmartschoolApp').constant('config',(function(){
	
	var url = 'http://localhost:9090/smartschool-java-web/'; 
	
    return{
    	url: url,
	    dashboardUrl: 'http://localhost:9090/smartschool-java-web/dashboard',
	    
	    professorshipsUri: 'professorships/',
	    professorshipsUrl: url + 'professorships/',
	    
	    professorsUri: 'professors/',
	    professorsUrl: url + 'professors/',
	    
	    disciplinesUri: 'disciplines/',
	    disciplinesUrl: url + 'disciplines',
    }
})());
