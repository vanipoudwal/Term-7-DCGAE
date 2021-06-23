/**
 * 
 */

function init(){
	var rootpath="//"+window.location.host+"/_ah/api";
	gapi.client.load("addEndPointApi","v1",loadCallback,rootpath);
	//gapi.client.load("addEndPointApi","v1",loadCallback1,rootpath);
}
function loadCallback(){
	btn=document.getElementById("imageField");
	btn.onclick=function(){saveData();};
}
function loadCallback1(){
	container=document.getElementById("resultDiv");
	container.style.display="none";
	getAllData();
}
function saveData(){
	var name=document.getElementById("name_field").value;
	var email=document.getElementById("email_field").value;
	var profession=document.getElementById("profession_field").value;
	var cell=document.getElementById("cell_field").value;
	var about=document.getElementById("about_area").value;
	
	var request=gapi.client.addEndPointApi.saveData({'name':name,'email':email,
			'profession':profession,'cell':cell,'about':about});
			request.execute(saveCallback);
}

function saveCallback(response){
	alert("inserted");
	window.location="index.html";
}
function getAllData(){
	var request=gapi.client.addEndPointApi.getRecords();
	request.execute(getCallback);
}
function getCallback(){
	var table;
	if(response.items.length!=0){
			for(var i=0;i<response.items.length;i++){
			table+=response.items[i].email+" "+response.items[i].name;
		}
	}
	else
		{
		table='<h4>No records</h4>';
		}
	container.innerHTML=table;
	conatiner.style.display="block";
}