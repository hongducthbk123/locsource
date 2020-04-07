function getUrlArg(){
	var data = {};
	var argStr = window.location.search;
	var re = new RegExp("([^&?]*)=([^&?]*)", "g");
	var temp = null;
	while(temp = re.exec(argStr)) {
		data[temp[1]] = temp[2];
	}
	return data;
}

function createJS(lang){
	if(!lang){
		alert("lang is null");
		return
	}
	var head= document.getElementsByTagName('head')[0]; 
	var script= document.createElement('script'); 
	script.type= 'text/javascript'; 
	script.onload = script.onreadystatechange = function() { 
	if (!this.readyState || this.readyState === "loaded" || this.readyState === "complete" ) { 
	// Handle memory leak in IE 
	script.onload = script.onreadystatechange = null; 
	} }; 
	script.src= './config/properties/strings_'+lang+'.js'; 
	head.appendChild(script); 
}
