var hostname = window.location.hostname;
var port = window.location.port
var protocol = window.location.protocol
baseUrl = protocol + '//' + hostname + ':' + port
currentUrl = window.location.href;
var convertUrl = new URL(currentUrl)

if (convertUrl.pathname = '/admin/category') {
	$(document).ready(function() {
		var getCategoryUrl = protocol + '//' + hostname + ':' + port + '/api/categories'
		// Call data from API
		$.ajax({
			method: 'GET',
			dataType: 'json',
			async: false,
			url: getCategoryUrl,
			success: function(data) {
				$('#tableCategory').DataTable({
					data: data,
					pageLength: 5,
					lengthMenu: [5, 10, 20,25,50],
					columns: [
						{ 'data': 'id' },
						{ 'data': 'nameCategory' }
					]
				});
			}
		});

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
					
					$("#tableCategory").DataTable().ajax.reload();
				},
				error: function(resp) {
					console.log(resp.responseText)
				}
			})
		});
		
		
	});
} else {
	console.log("not ok")
}