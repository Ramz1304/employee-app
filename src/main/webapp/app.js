function sayHello(){
var name = document.getElementById("name").value;
alert("hello" + name);
calculateSalary(printSalary);
}

function calculateSalary(xyzFunction){
var age = document.getElementById("age").value;
var salary = 2*age;
xyzFunction(salary);
}

function printSalary(salary){
 console.info(" the salary is " + salary);
 }
 
 function getEmployee(){
      var xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = processReq;
	  xhttp.open("GET", "http://localhost:9000/employee/api", true);
	  xhttp.send();
	  }
	  
	  function processReq(){
	  if(this.status == 200){
	  console.log(this.responseText);
	  }
	  else{
	  console.log("a network error has happened");
	  }
	  }