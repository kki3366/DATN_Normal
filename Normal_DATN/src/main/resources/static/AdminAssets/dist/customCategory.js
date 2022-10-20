var hostname = window.location.hostname;
var port = window.location.port
var protocol = window.location.protocol
baseUrl = protocol + '//' + hostname + ':' + port
currentUrl = window.location.href;
var convertUrl = new URL(currentUrl)
if (convertUrl.pathname = '/admin/category') {
	$(document).ready(function() {
		var getCategoryUrl = protocol + '//' + hostname + ':' + port + '/api/categories'
		//get All
		//setInterval(function(){
			
		//})
		
		/*$.ajax({
			method: 'get',
			dataType: 'json',
			url: getCategoryUrl,
			success: function(datas) {
				var result = ""
				datas.forEach(item => {
					result += `
						<tr role="row" class="even">
                  			<td class="sorting_1">${item.id}</td>
                  			<td>${item.nameCategory}</td>
                		</tr>
					`;
				});
				$("#tableCategory").append(result)
			}
		})	*/
		
		// post
		/*var categoryForm = {
			nameCategory: document.getElementById('nameCategory').value 
		}
		
		$("#submitCategory").click(function(){
     	 console.log(categoryForm);
    });*/
		
	});
} else {
	console.log("not ok")
}