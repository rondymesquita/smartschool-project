angular
.module('SmartschoolApp', ['ngRoute','ngTable'])
.run(function(){
    console.log("loaded");
});

function ResponseData(message, status){
    this.message = message;
    this.status = status;
}
