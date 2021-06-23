/**
 * 
 */
function init()
{
	var rootpath = "//"+window.location.host+"/_ah/api";
	gapi.client.load('testapi','v1',loadCallback,rootpath);
}

function loadCallback()
{
	enableButtons();
}

function enableButtons()
{
	btn = document.getElementById("by_name");
	btn.onclick = function(){casualGreeting();}
	btn.value = "casualGreeting";
	btn = document.getElementById("by_period");
	btn.onclick = function(){formalGreeting();}
	btn.value = "formalGreeting";
}

function casualGreeting()
{
	var name = document.getElementById("name_field1").value;
	console.log(name);
	var request = gapi.client.testapi.method1({'name':name});
	request.execute(sayHelloCallback);
}

function sayHelloCallback(response)
{
	console.log(response.msg); 
	alert(response.msg); 
}

function formalGreeting()
{
	var name = document.getElementById("name_field1").value;
	var time_period = document.getElementById("period_select").value;
	var request = gapi.client.testapi.showTime({'name':name, 'time':time_period});
	request.execute(sayHelloCallback);	
}
