var hostname= window.location.hostname;
var port= window.location.port
var protocol = window.location.protocol
baseUrl = protocol + '//' + hostname +  ':' + port
currentUrl = window.location.href;
var convertUrl = new URL(currentUrl)
console.log(convertUrl.pathname)
if(convertUrl = '/admin/category'){
	 $(document).ready(function(){
    $(".btn-primary").click(function(){
      alert("HELLO WORLD!!");
    });
  });
}else{
	console.log("not ok")
}