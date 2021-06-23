/**
 * 
 */
var container;
function init(){
	var rootpath="//"+window.location.host+"/_ah/api";
	gapi.client.load('addEndPointApi','v1',loadCallback,rootpath);
}
function loadCallback(){
	container=document.getElementById("resultDiv");
	container.style.display="none";
	getAllData();
}

function getAllData(){
	var request=gapi.client.addEndPointApi.getRecords();
	request.execute(getCallback);
}
function getCallback(response){
	var table;
		if(response.items.length!=0){
		
		table='<table id="t01" border="1"><tr><th>Email Id</th><th>Name</th><th>Profession</th><th>About Us</th><th>Cell</th></tr>';
			for(var i=0;i<response.items.length;i++){
			table+='<tr><td>'+response.items[i].email+'</td><td>'+response.items[i].name+'</td>';
			table+='<td>'+response.items[i].profession+'</td><td>'+response.items[i].about+'</td>';
			table+='<td>'+response.items[i].cell+'</td></tr>';
		}
			table+='</table>';
	}
	else
		{
		table='<h4>No records</h4>';
		}
	container.innerHTML=table;
	
	container.style.display="block";
}
