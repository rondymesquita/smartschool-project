angular.module('SmartschoolApp').constant('config',(function(){
	
	var url = 'http://localhost:9090/smartschool-java-web/'; 
	
    return{
    	url: url,
	    dashboardUrl: url +'dashboard',
	    
	    professorshipsUri: 'professorships/',
	    professorshipsUrl: url + 'professorships/',
	    
	    professorsUri: 'professors/',
	    professorsUrl: url + 'professors/',
	    
	    disciplinesUri: 'disciplines/',
	    disciplinesUrl: url + 'disciplines/',
	    
	    studentsUri: 'students/',
	    studentsUrl: url + 'students/',
	    studentsApiUrl: url + 'api/students/',
	    
	    coursesUri: 'courses/',
	    coursesUrl: url + 'courses/',
	    coursesApiUrl: url + 'api/courses/',
	    
	    semestersUri: 'semesters/',
	    semestersUrl: url + 'semesters/',
	    semestersApiUrl: url + 'api/semesters/',
	    
    }
})());
