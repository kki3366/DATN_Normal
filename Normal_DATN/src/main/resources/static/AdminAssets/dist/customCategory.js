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
		var result = ""
		function getDataCategory() {
			$.ajax({
				method: 'get',
				dataType: 'json',
				url: getCategoryUrl,
				success: function(datas) {
					datas.forEach(item => {
						result += `
						<tr role="row" class="even">
								<td class="sorting_1">${item.id}</td>
								<td>${item.nameCategory}</td>
						</tr>
					`;
						$("#tableCategory").append(result)
					});
				}
			})
		}
		getDataCategory()


		$('#submitCategory').click(function() {
			var categoryForm = {
				nameCategory: $('#nameCategory').val()
			}
			$.ajax({
				method: 'POST',
				dataType: 'json',
				url: getCategoryUrl,
				contentType: "application/json; charset=utf-8",
				data: JSON.stringify(categoryForm),
				success: function(datas) {
					
				}
			})
			
		});

	});
} else {
	console.log("not ok")
}