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
		var putCategoryUrl = protocol + '//' + hostname + ':' + port + '/api/categories/'
		// Call data from API

		var table = $('#tableCategory').DataTable({
			ajax: {
				"type": "GET",
				"url": getCategoryUrl,
				"dataSrc": function(resp) {
					return resp;
				}
			},
			columns: [
				{ "data": "id" },
				{ "data": "nameCategory" },


			],
			pageLength: 5,
			lengthMenu: [5, 10, 20, 25, 50],
			order: [],
			processing: true,
			select: {
				info: false,
				style: 'single'

			}

		})

		//submit from form
		$('.box-footer').on('click', '#submitCategoryButton', function() {
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
		})


		//edit
		// get value edit
		table.on('select', function(e, dt, type, indexes) {
			var rowData = table.rows(indexes).data().toArray()[0];
			$('#updateModeButton').remove();
			$('#cancelModeButton').remove();
			$('#submitCategoryButton').remove();
			$('.box-footer').append("<button type='button' class='btn btn-primary' id='updateModeButton'>Update</button> ")
			$('.box-footer').append("<button type='button' class='btn btn-primary' id='cancelModeButton'>Cancel</button>")
			//doing edit
			$('#updateModeButton').click(function() {
				console.log("will do update")
			})
			$('#cancelModeButton').click(function(e) {
				$('#updateModeButton').remove();
				$('#cancelModeButton').remove();
				$('.box-footer').append('<button type="button" class="btn btn-primary" id="submitCategoryButton">Submit</button>')
				$('tr').removeClass('selected');
				$('#nameCategory').val("")
			})
			console.log(rowData)
		})
		//get style edit
		$('#tableCategory tbody').on('click', 'tr', function() {
			if ($(this).hasClass('selected')) {
				$(this).removeClass('selected');
				$('#updateModeButton').remove();
				$('#cancelModeButton').remove();
				$('.box-footer').append('<button type="button" class="btn btn-primary" id="submitCategoryButton">Submit</button>')
				$('#nameCategory').val("")
			} else {
				table.$('tr.selected').removeClass('selected');
				$(this).addClass('selected');
			}
		});

		$('#button').click(function() {
			table.row('.selected').remove().draw(false);
		});




	});
} else {
	console.log("not ok")
}