var hostname = window.location.hostname;
var port = window.location.port
var protocol = window.location.protocol
baseUrl = protocol + '//' + hostname + ':' + port
currentUrl = window.location.href;
var convertUrl = new URL(currentUrl)
var editor;
if (convertUrl.pathname = '/admin/category') {
	$(document).ready(function() {
		var getCategoryUrl = protocol + '//' + hostname + ':' + port + '/api/categories'
		// Call data from API

		/*$('#tableCategory').DataTable({

			ajax: {
				"type": "GET",
				"url": getCategoryUrl,
				"dataSrc": function(resp) {
					return resp;
				}
			},
			columns: [
				{ "data": "id" },
				{ "data": "nameCategory" }
			],
			pageLength: 5,
			lengthMenu: [5, 10, 20, 25, 50],
			order: [[0, 'asc']],
			select: {
				style: 'os',
				selector: 'td:first-child'
			},
			buttons: [
				{ extend: "create", editor: editor },
				{ extend: "edit", editor: editor },
				{ extend: "remove", editor: editor }
			]
		});*/

		//submit from form
		$('#submitCategory').click(function() {
			var categoryForm = {
				nameCategory: $('#nameCategory').val()
			}
			$.ajax({
				method: 'POST',
				url: getCategoryUrl,
				contentType: "application/json; charset=utf-8",
				data: JSON.stringify(categoryForm),
				success: function(resp, xhr) {
					$("#tableCategory").DataTable().ajax.reload();
					$("#message").html('<div class="alert alert-success alert-dismissible">' + '<button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>' + '<h4><i class="icon fa fa-check"></i> Thông báo!</h4>' + 'Bạn đã thêm danh mục thành công' + '</div>')
					$('#nameCategory').val("")
				},
				statusCode: {
					400: function(error) {
						$("#message").html('<div class="alert alert-danger alert-dismissible">' + '<button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>' + '<h4><i class="icon fa fa-ban"></i> Thông Báo!</h4>' + String(error.responseJSON.errors) + '</div>')
						$('#nameCategory').val("")
					},
					502: function(error) {
						$("#message").html('<div class="alert alert-danger alert-dismissible">' + '<button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>' + '<h4><i class="icon fa fa-ban"></i> Thông Báo!</h4>' + 'Tên danh mục đã tồn tại' + '</div>')
						$('#nameCategory').val("")
					}
				}
			})
		});
		//edit
		editor = new $.fn.dataTable.Editor({
			ajax: {
				"type": "GET",
				"url": getCategoryUrl,
				"dataSrc": function(resp) {
					return resp;
				}
			},
			table: "#tableCategory",
			idSrc: 'id',
			fields: [{
				label: "Name Category:",
				name: "nameCategory"
			}
			]
		});
		$('#tableCategory').on('click', 'tbody td:not(:first-child)', function(e) {
			editor.inline(this);
		});
$('#tableCategory').DataTable({
	ajax: {
				"type": "GET",
				"url": getCategoryUrl,
				"dataSrc": function(resp) {
					return resp;
				}
			},
			columns: [
				{ "data": "id" },
				{ "data": "nameCategory" }
			],
			pageLength: 5,
			lengthMenu: [5, 10, 20, 25, 50],
			order: [[0, 'asc']],
			select: {
				style: 'os',
				selector: 'td:first-child'
			},
			buttons: [
				{ extend: "create", editor: editor },
				{ extend: "edit", editor: editor },
				{ extend: "remove", editor: editor }
			]
})
	});
} else {
	console.log("not ok")
}