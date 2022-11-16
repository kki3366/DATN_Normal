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
			$('#deleteModeButton').remove();
			$('#submitCategoryButton').remove();
			$('#nameCategory').val(rowData.nameCategory)
			$('.box-footer').append("<button type='button' class='btn btn-primary' id='updateModeButton'>Update</button> ")
			$('.box-footer').append("<button type='button' class='btn btn-primary' id='deleteModeButton'>Delete</button> ")
			$('.box-footer').append("<button type='button' class='btn btn-primary' id='cancelModeButton'>Cancel</button>")
			//doing edit
			$('#updateModeButton').click(function() {
				var newValue = $('#nameCategory').val()
				rowData.nameCategory = newValue
				console.log(rowData)
				$.ajax({
					method: 'PUT',
					url: getCategoryUrl,
					contentType: "application/json; charset=utf-8",
					data: JSON.stringify(rowData),
					success: function(resp, xhr) {
						$("#tableCategory").DataTable().ajax.reload();
						$("#message").html('<div class="alert alert-success alert-dismissible">' + '<button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>' + '<h4><i class="icon fa fa-check"></i> Thông báo!</h4>' + 'Bạn đã cập nhật danh mục thành công' + '</div>')
						$('#nameCategory').val("")
						$('#updateModeButton').remove();
						$('#cancelModeButton').remove();
						$('#deleteModeButton').remove();
						$('.box-footer').append('<button type="button" class="btn btn-primary" id="submitCategoryButton">Submit</button>')
						
					},
					statusCode: {
						500: function(error) {
							$("#message").html('<div class="alert alert-danger alert-dismissible">' + '<button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>' + '<h4><i class="icon fa fa-ban"></i> Thông Báo!</h4> Không được để trống tên danh mục trong khi cập nhật</div>')
							$('#nameCategory').val("")
						},
						406: function(error) {
							$("#message").html('<div class="alert alert-danger alert-dismissible">' + '<button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>' + '<h4><i class="icon fa fa-ban"></i> Thông Báo!</h4> Danh mục đã tồn tại. Vui lòng nhập lại </div>')
							$('#nameCategory').val("")
							$('#nameCategory').focus()
						}
					}
				})
			})
			$('#deleteModeButton').click(function(e){
				console.log("delete on working")
				$.ajax({
					method: 'DELETE',
					url: getCategoryUrl + '/' + rowData.id,
					success: function(resp, xhr) {
						$("#message").html('<div class="alert alert-success alert-dismissible">' + '<button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>' + '<h4><i class="icon fa fa-check"></i> Thông báo!</h4>' + 'Bạn đã xóa danh mục thành công' + '</div>')
						$('#nameCategory').val("")
						$('#updateModeButton').remove();
						$('#cancelModeButton').remove();
						$('#deleteModeButton').remove();
						$('.box-footer').append('<button type="button" class="btn btn-primary" id="submitCategoryButton">Submit</button>')
						$("#tableCategory").DataTable().ajax.reload();
					},
					statusCode: {
						500: function(error) {
							$("#message").html('<div class="alert alert-danger alert-dismissible">' + '<button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>' + '<h4><i class="icon fa fa-ban"></i> Thông Báo!</h4> Không được để trống tên danh mục trong khi cập nhật</div>')
							$('#nameCategory').val("")
						},
						406: function(error) {
							$("#message").html('<div class="alert alert-danger alert-dismissible">' + '<button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>' + '<h4><i class="icon fa fa-ban"></i> Thông Báo!</h4> Danh mục đã tồn tại. Vui lòng nhập lại </div>')
							$('#nameCategory').val("")
							$('#nameCategory').focus()
						}
					}
				})
			})
			$('#cancelModeButton').click(function(e) {
				$('#updateModeButton').remove();
				$('#cancelModeButton').remove();
				$('#deleteModeButton').remove();
				$('.box-footer').append('<button type="button" class="btn btn-primary" id="submitCategoryButton">Submit</button>')
				$('tr').removeClass('selected');
				$('#nameCategory').val("")
			})

		})
		//get style edit
		$('#tableCategory tbody').on('click', 'tr', function() {
			if ($(this).hasClass('selected')) {
				$(this).removeClass('selected');
				$('#updateModeButton').remove();
				$('#cancelModeButton').remove();
				$('#deleteModeButton').remove();
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