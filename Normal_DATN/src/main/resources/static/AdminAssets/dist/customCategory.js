var hostname = window.location.hostname;
var port = window.location.port
var protocol = window.location.protocol
baseUrl = protocol + '//' + hostname + ':' + port
currentUrl = window.location.href;
var convertUrl = new URL(currentUrl)
var editor;
//format quantity, money
function formatToVND(n, currency) {
	return n.toString().replace(/\B(?=(\d{3})+(?!\d))/g, '.') + ' ' + currency;
}



//block Inspect
/*document.addEventListener('contextmenu', function(e) {
	e.preventDefault();
});*/
if (convertUrl.pathname = '/admin/category') {

	/*$(document).keydown(function(event) {
		if (event.keyCode == 123) { // Prevent F12
			return false;
		} else if (event.ctrlKey && event.shiftKey && event.keyCode == 73) { // Prevent Ctrl+Shift+I        
			return false;
		}
	});*/

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
				contentType: "application/json",
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
				//console.log(rowData)
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
			$('#deleteModeButton').click(function(e) {
				//console.log("delete on working")
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
						},
						409: function(error) {
							$("#message").html('<div class="alert alert-danger alert-dismissible">' + '<button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>' + '<h4><i class="icon fa fa-ban"></i> Thông Báo!</h4> Danh mục đang tồn tại sản phẩm </div>')
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
				if ($('#tableCategory tbody tr td').hasClass('dataTables_empty')) {
				} else {
					tableProduct.$('tr.selected').removeClass('selected');
					$(this).addClass('selected');
				}
			}
		});

		$('#button').click(function() {
			table.row('.selected').remove().draw(false);
		});




	});
}
//----------------------------------------------PRODUCT---------------------------------------
if (convertUrl.pathname = '/admin/product') {
	//block F12
	/*$(document).keydown(function(event) {
		if (event.keyCode == 123) { // Prevent F12
			return false;
		} else if (event.ctrlKey && event.shiftKey && event.keyCode == 73) { // Prevent Ctrl+Shift+I        
			return false;
		}
	});*/
	$(document).ready(function() {
		var getProductUrl = protocol + '//' + hostname + ':' + port + '/api/products'
		var getCategoryUrl = protocol + '//' + hostname + ':' + port + '/api/categories'

		//load and display data product
		var tableProduct = $('#tableProduct').DataTable({
			ajax: {
				"type": "GET",
				"url": getProductUrl,
				"dataSrc": function(resp) {
					console.log(resp)
					return resp;
				}
			},
			columns: [
				{ "data": "id" },
				{
					"data": "name"
				},
				{
					"data": "available",
					render: function(data, type, row) {
						return data ? "Còn hàng" : "Hết hàng"
					}
				},
				{
					"data": "price",
					render: function(data, type, row) {
						return formatToVND(data, '₫')
					}
				},
				{ "data": "category.nameCategory" },
				{
					"data": "quantity",
					render: function(data, type, row) {
						if (row.available == false) {
							console.log("ok")
							return "0";
						} else {
							return data
						}
					}
				}

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


		//Load category to select
		function loadCategory() {
			$.ajax({
				type: "GET",
				dataType: "json",
				url: getCategoryUrl,
				success: function(resp) {
					console.log(resp)
					for (i in resp) {
						$("#selectedCategory").append("<option value='" + resp[i].id + "'>" + resp[i].nameCategory + "</option>");
					}
					if (resp.length <= 0) {
						$("#selectedCategory").html("<option value=''> Dang mục trống</option>");
					}
				}
			})
		}

		loadCategory()

		function clearForm() {
			$('#nameProduct').val("")
			$('#priceProduct').val("")
			$('#quanlityProduct').val("")
			$('#descriptionProduct').val("")
			$("#selectedCategory").val($("#selectedCategory option:first").val());
			$('#fileProduct').val("")
		}
		//Created Product
		$('.productFooter').on('click', '#submitProductButton', function(e) {
			e.preventDefault();
			var productForm = {
				name: $('#nameProduct').val(),
				price: $('#priceProduct').val(),
				quantity: $('#quanlityProduct').val(),
				description: $('#descriptionProduct').val(),
				category: {
					id: $('#selectedCategory option:selected').val()
				},
				subcategory: {
					id: $('#selectedSubcategory option:selected').val()
				}
			}
			//Max upload 3MB
			maxSizeCanUpdate = 3 * 1024 * 1024
			var checkFileSize = $('#fileProduct').prop('files')[0]
			//console.log(checkFileSize)
			var formData = new FormData();
			formData.append("fileProduct", checkFileSize)
			formData.append("products", new Blob([JSON.stringify(productForm)], { type: "application/json" }))
			if (checkFileSize == undefined) {
				//console.log("truong hop khong co anh, cho post")
				//Call Ajax Create
				$.ajax({
					method: 'POST',
					enctype: 'multipart/form-data',
					processData: false,
					contentType: false,
					url: getProductUrl,
					data: formData,
					success: function(resp, xhr) {
						$("#tableProduct").DataTable().ajax.reload();
						$("#messageProduct").html('<div class="alert alert-success alert-dismissible">' + '<button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>' + '<h4><i class="icon fa fa-check"></i> Thông báo!</h4>' + 'Bạn đã thêm sản phẩm thành công' + '</div>')
						clearForm();
					},
					statusCode: {
						400: function(error) {
							//console.log(error.responseJSON.errors)
							$("#messageProduct").html('<div class="alert alert-danger alert-dismissible">' + '<button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>' + '<h4><i class="icon fa fa-ban"></i> Thông Báo!</h4>' + String(error.responseJSON.errors) + '</div>')
							if (String(error.responseJSON.errors).includes('tên sản phẩm')) {
								$('#nameProduct').focus();
							}
							if (String(error.responseJSON.errors).includes('giá sản phẩm')) {
								$('#priceProduct').focus();
							}
							if (String(error.responseJSON.errors).includes('trống số lượng')) {
								$('#quanlityProduct').focus();
							}
							if (String(error.responseJSON.errors).includes('mô tả sản phẩm')) {
								$('#descriptionProduct').focus();
							}
						},
						502: function(error) {
							$("#messageProduct").html('<div class="alert alert-danger alert-dismissible">' + '<button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>' + '<h4><i class="icon fa fa-ban"></i> Thông Báo!</h4>' + 'Tên danh mục đã tồn tại' + '</div>')
						},
						409: function(error) {
							$("#messageProduct").html('<div class="alert alert-danger alert-dismissible">' + '<button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>' + '<h4><i class="icon fa fa-ban"></i> Thông Báo!</h4>' + 'Vui lòng chọn hoặc tạo danh mục' + '</div>')
						},
						423: function(error) {
							$("#messageProduct").html('<div class="alert alert-danger alert-dismissible">' + '<button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>' + '<h4><i class="icon fa fa-ban"></i> Thông Báo!</h4>' + 'Trùng tên sản phẩm' + '</div>')
							$('#nameProduct').focus();
						}
					}
				})
			} else {
				if (checkFileSize.size > maxSizeCanUpdate) {
					$("#messageProduct").html('<div class="alert alert-danger alert-dismissible">' + '<button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>' + '<h4><i class="icon fa fa-ban"></i> Thông Báo!</h4>' + 'Hình phải nhỏ hơn 3MB' + '</div>')
					$('#fileProduct').focus()
					$('#fileProduct').val("")
				} else if (checkFileSize.type != 'image/png') {
					$("#messageProduct").html('<div class="alert alert-danger alert-dismissible">' + '<button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>' + '<h4><i class="icon fa fa-ban"></i> Thông Báo!</h4>' + 'Ảnh phải định dạng PNG' + '</div>')
					$('#fileProduct').focus()
					$('#fileProduct').val("")
				} else {
					//console.log("ok")
					//Call Ajax Create
					$.ajax({
						method: 'POST',
						enctype: 'multipart/form-data',
						processData: false,
						contentType: false,
						url: getProductUrl,
						data: formData,
						success: function(resp, xhr) {
							$("#tableProduct").DataTable().ajax.reload();
							$("#messageProduct").html('<div class="alert alert-success alert-dismissible">' + '<button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>' + '<h4><i class="icon fa fa-check"></i> Thông báo!</h4>' + 'Bạn đã thêm sản phẩm thành công' + '</div>')
							clearForm();
						},
						statusCode: {
							400: function(error) {
								//console.log(error.responseJSON.errors)
								$("#messageProduct").html('<div class="alert alert-danger alert-dismissible">' + '<button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>' + '<h4><i class="icon fa fa-ban"></i> Thông Báo!</h4>' + String(error.responseJSON.errors) + '</div>')
								if (String(error.responseJSON.errors).includes('tên sản phẩm')) {
									$('#nameProduct').focus();
								}
								if (String(error.responseJSON.errors).includes('giá sản phẩm')) {
									$('#priceProduct').focus();
								}
								if (String(error.responseJSON.errors).includes('trống số lượng')) {
									$('#quanlityProduct').focus();
								}
								if (String(error.responseJSON.errors).includes('mô tả sản phẩm')) {
									$('#descriptionProduct').focus();
								}
							},
							502: function(error) {
								$("#messageProduct").html('<div class="alert alert-danger alert-dismissible">' + '<button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>' + '<h4><i class="icon fa fa-ban"></i> Thông Báo!</h4>' + 'Tên danh mục đã tồn tại' + '</div>')
							},
							409: function(error) {
								$("#messageProduct").html('<div class="alert alert-danger alert-dismissible">' + '<button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>' + '<h4><i class="icon fa fa-ban"></i> Thông Báo!</h4>' + 'Vui lòng chọn hoặc tạo danh mục' + '</div>')
							}
						}
					})
				}
			}

			//type: "image/jpeg"
		});

		//Update and Delete
		tableProduct.on('select', function(e, dt, type, indexes) {
			var rowData = tableProduct.rows(indexes).data().toArray()[0];
			$('#updateModeButtonProduct').remove();
			$('#cancelModeButtonProduct').remove();
			$('#deleteModeButtonProduct').remove();
			$('#submitProductButton').remove();
			//Give row data to form
			//console.log(rowData)
			//console.log(rowData.category.nameCategory)
			$('#nameProduct').val(rowData.name)
			$('#priceProduct').val(rowData.price)
			if (rowData.available == false) {
				$('#quanlityProduct').val(0)
			} else {
				$('#quanlityProduct').val(rowData.quantity)
			}
			$('#descriptionProduct').val(rowData.description)
			$("#selectedCategory").val(rowData.category.id).prop('selected', true);
			$("#selectedSubcategory").val(rowData.subcategory.id).prop('selected', true);
			$('.help-block').text('Hình ảnh: ' + rowData.image + '.png')
			$('.box-footer').append("<button type='button' class='btn btn-primary' id='updateModeButtonProduct'>Update</button> ")
			$('.box-footer').append("<button type='button' class='btn btn-primary' id='deleteModeButtonProduct'>Delete</button> ")
			$('.box-footer').append("<button type='button' class='btn btn-primary' id='cancelModeButtonProduct'>Cancel</button>")

			//Do Update		
			$('#updateModeButtonProduct').click(function() {
				var newNameProduct = $('#nameProduct').val();
				var newPriceProduct = $('#priceProduct').val()
				var newQuantityProduct = $('#quanlityProduct').val()
				var newDescriptionProduct = $('#descriptionProduct').val()
				var newidCategory = $('#selectedCategory option:selected').val()
				var newSubCategory = $('#selectedSubcategory option:selected').val()
				var file = $('#fileProduct').prop('files')[0]
				//console.log(file)
				rowData.name = newNameProduct
				rowData.price = newPriceProduct
				rowData.quantity = newQuantityProduct
				rowData.description = newDescriptionProduct
				rowData.category.id = newidCategory
				rowData.subcategory.id = newSubCategory
				//console.log(rowData)
				var productForm = {
					id: rowData.id,
					name: rowData.name,
					image: rowData.image,
					price: rowData.price,
					quantity: rowData.quantity,
					description: rowData.description,
					category: {
						id: rowData.category.id
					},
					subcategory: {
						id: rowData.subcategory.id
					}
				}

				//Max upload 3MB
				maxSizeCanUpdate = 3 * 1024 * 1024
				var checkFileSize = $('#fileProduct').prop('files')[0]
				var formData = new FormData();
				formData.append("fileUpdate", file)
				formData.append("productsUpdate", new Blob([JSON.stringify(productForm)], { type: "application/json" }))
				if (checkFileSize == undefined) {
					//Call Ajax Update
					$.ajax({
						method: 'PUT',
						url: getProductUrl,
						enctype: 'multipart/form-data',
						processData: false,
						contentType: false,
						data: formData,
						success: function(resp, xhr) {
							clearForm();
							$("#tableProduct").DataTable().ajax.reload();
							$("#messageProduct").html('<div class="alert alert-success alert-dismissible">' + '<button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>' + '<h4><i class="icon fa fa-check"></i> Thông báo!</h4>' + 'Bạn đã cập nhật sản phẩm thành công' + '</div>')
							$('#updateModeButtonProduct').remove();
							$('#cancelModeButtonProduct').remove();
							$('#deleteModeButtonProduct').remove();
							$('.box-footer').append('<button type="button" class="btn btn-primary" id="submitProductButton">Submit</button>')
							$('.help-block').text('Chỉ thêm được 1 hình')
							clearForm();

						},
						statusCode: {
							400: function(error) {
								//console.log(error.responseJSON.errors)
								$("#messageProduct").html('<div class="alert alert-danger alert-dismissible">' + '<button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>' + '<h4><i class="icon fa fa-ban"></i> Thông Báo!</h4>' + String(error.responseJSON.errors) + '</div>')
								if (String(error.responseJSON.errors).includes('tên sản phẩm')) {
									$('#nameProduct').focus();
								}
								if (String(error.responseJSON.errors).includes('giá sản phẩm')) {
									$('#priceProduct').focus();
								}
								if (String(error.responseJSON.errors).includes('trống số lượng')) {
									$('#quanlityProduct').focus();
								}
								if (String(error.responseJSON.errors).includes('mô tả sản phẩm')) {
									$('#descriptionProduct').focus();
								}
							},
							502: function(error) {
								$("#messageProduct").html('<div class="alert alert-danger alert-dismissible">' + '<button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>' + '<h4><i class="icon fa fa-ban"></i> Thông Báo!</h4>' + 'Tên danh mục đã tồn tại' + '</div>')
							},
							409: function(error) {
								$("#messageProduct").html('<div class="alert alert-danger alert-dismissible">' + '<button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>' + '<h4><i class="icon fa fa-ban"></i> Thông Báo!</h4>' + 'Vui lòng chọn hoặc tạo danh mục' + '</div>')
							}
						}
					})
				} else {
					if (checkFileSize.size > maxSizeCanUpdate) {
						$("#messageProduct").html('<div class="alert alert-danger alert-dismissible">' + '<button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>' + '<h4><i class="icon fa fa-ban"></i> Thông Báo!</h4>' + 'Hình phải nhỏ hơn 3MB' + '</div>')
						$('#fileProduct').focus()
						$('#fileProduct').val("")
					} else if (checkFileSize.type != 'image/png') {
						$("#messageProduct").html('<div class="alert alert-danger alert-dismissible">' + '<button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>' + '<h4><i class="icon fa fa-ban"></i> Thông Báo!</h4>' + 'Ảnh phải định dạng PNG' + '</div>')
						$('#fileProduct').focus()
						$('#fileProduct').val("")
					} else {
						//console.log("ok")
						//Call Ajax Update
						$.ajax({
							method: 'PUT',
							url: getProductUrl,
							enctype: 'multipart/form-data',
							processData: false,
							contentType: false,
							data: formData,
							success: function(resp, xhr) {
								clearForm();
								$("#tableProduct").DataTable().ajax.reload();
								$("#messageProduct").html('<div class="alert alert-success alert-dismissible">' + '<button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>' + '<h4><i class="icon fa fa-check"></i> Thông báo!</h4>' + 'Bạn đã cập nhật sản phẩm thành công' + '</div>')
								$('#updateModeButtonProduct').remove();
								$('#cancelModeButtonProduct').remove();
								$('#deleteModeButtonProduct').remove();
								$('.box-footer').append('<button type="button" class="btn btn-primary" id="submitProductButton">Submit</button>')
								$('.help-block').text('Chỉ thêm được 1 hình')
								clearForm();

							},
							statusCode: {
								400: function(error) {
									//console.log(error.responseJSON.errors)
									$("#messageProduct").html('<div class="alert alert-danger alert-dismissible">' + '<button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>' + '<h4><i class="icon fa fa-ban"></i> Thông Báo!</h4>' + String(error.responseJSON.errors) + '</div>')
									if (String(error.responseJSON.errors).includes('tên sản phẩm')) {
										$('#nameProduct').focus();
									}
									if (String(error.responseJSON.errors).includes('giá sản phẩm')) {
										$('#priceProduct').focus();
									}
									if (String(error.responseJSON.errors).includes('trống số lượng')) {
										$('#quanlityProduct').focus();
									}
									if (String(error.responseJSON.errors).includes('mô tả sản phẩm')) {
										$('#descriptionProduct').focus();
									}
								},
								502: function(error) {
									$("#messageProduct").html('<div class="alert alert-danger alert-dismissible">' + '<button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>' + '<h4><i class="icon fa fa-ban"></i> Thông Báo!</h4>' + 'Tên danh mục đã tồn tại' + '</div>')
								},
								409: function(error) {
									$("#messageProduct").html('<div class="alert alert-danger alert-dismissible">' + '<button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>' + '<h4><i class="icon fa fa-ban"></i> Thông Báo!</h4>' + 'Vui lòng chọn hoặc tạo danh mục' + '</div>')
								}
							}
						})
					}
				}

			})
			//Do delete
			$('#deleteModeButtonProduct').click(function() {

				//console.log("delete on working product")
				$.ajax({
					method: 'DELETE',
					url: getProductUrl + '/' + rowData.id + '/' + rowData.image,
					success: function(resp, xhr) {
						$("#messageProduct").html('<div class="alert alert-success alert-dismissible">' + '<button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>' + '<h4><i class="icon fa fa-check"></i> Thông báo!</h4>' + 'Bạn đã xóa sản phẩm thành công' + '</div>')
						$('#updateModeButtonProduct').remove();
						$('#cancelModeButtonProduct').remove();
						$('#deleteModeButtonProduct').remove();
						$('.box-footer').append('<button type="button" class="btn btn-primary" id="submitProductButton">Submit</button>')
						$('.help-block').text('Chỉ thêm được 1 hình')
						$("#tableProduct").DataTable().ajax.reload();
						clearForm();
					},
					statusCode: {
						400: function(error) {
							$("#message").html('<div class="alert alert-danger alert-dismissible">' + '<button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>' + '<h4><i class="icon fa fa-ban"></i> Thông Báo!</h4> Danh mục đã tồn tại. Vui lòng nhập lại </div>')
							//console.log(error)
						}
					}
				})
			})

			//Do cancel
			$('#cancelModeButtonProduct').click(function() {
				$('#updateModeButtonProduct').remove();
				$('#cancelModeButtonProduct').remove();
				$('#deleteModeButtonProduct').remove();
				$('.box-footer').append('<button type="button" class="btn btn-primary" id="submitProductButton">Submit</button>');
				$('.help-block').text('Chỉ thêm được 1 hình')
				$('tr').removeClass('selected');
				clearForm();
			})

		})

		$('#tableProduct tbody').on('click', 'tr', function() {
			if ($(this).hasClass('selected')) {
				$(this).removeClass('selected');
				$('#updateModeButtonProduct').remove();
				$('#cancelModeButtonProduct').remove();
				$('#deleteModeButtonProduct').remove();
				$('.box-footer').append('<button type="button" class="btn btn-primary" id="submitProductButton">Submit</button>');
				$('.help-block').text('Chỉ thêm được 1 hình')
				clearForm();
			} else {
				if ($('#tableProduct tbody tr td').hasClass('dataTables_empty')) {
				} else {
					tableProduct.$('tr.selected').removeClass('selected');
					$(this).addClass('selected');
				}
			}

		});
		$('#button').click(function() {

			table.row('.selected').remove().draw(false);
		});


	})
}
//----------------------------------------------Report by Inventory---------------------------------------
if (convertUrl.pathname = 'admin/report/inventory') {
	$(document).ready(function() {
		var getInventoryUrl = protocol + '//' + hostname + ':' + port + '/api/report/inventory';
		var tableReportInventory = $('#tableReportInventory').DataTable({
			ajax: {
				"type": "GET",
				"url": getInventoryUrl,
				"dataSrc": function(resp) {
					console.log(resp)
					return resp;
				}
			},
			columns: [
				{ "data": "nameCategory" },
				{
					"data": "quantiyProduct",
					render: function(data, type, row) {
						return formatToVND(data, '')
					}
				},
				{
					"data": "subTotal",
					render: function(data, type, row) {
						if (row.quantiyProduct == 0) {
							return formatToVND(0, '₫');
						} else {
							return formatToVND(data, '₫')
						}
					}
				},
				{
					"data": "minProduct",
					render: function(data, type, row) {
						if (row.quantiyProduct == 0) {
							return formatToVND(0, '₫');
						} else {
							return formatToVND(data, '₫')
						}
					}
				},
				{
					"data": "maxProdouct",
					render: function(data, type, row) {
						if (row.quantiyProduct == 0) {
							return formatToVND(0, '₫');
						} else {
							return formatToVND(data, '₫')
						}
					}
				},
				{
					"data": "avgProduct",
					render: function(data, type, row) {
						if (row.quantiyProduct == 0) {
							return formatToVND(0, '₫');
						} else {
							return formatToVND(data.toFixed(), '₫')
						}
					}
				}
			],
			pageLength: 5,
			lengthMenu: [5, 10, 20, 25, 50],
			order: [],
			processing: true

		})
	})
}
//----------------------------------------------Report by Customer---------------------------------------
if (convertUrl.pathname = 'admin/report/revenueByCustomer') {
	$(document).ready(function() {
		var getCustomerUrl = protocol + '//' + hostname + ':' + port + '/api/report/revenueByCustomer';
		var tableReportCustomer = $('#tableReportCustomer').DataTable({
			ajax: {
				"type": "GET",
				"url": getCustomerUrl,
				"dataSrc": function(resp) {
					var filteredValue = resp.filter(function(item) {
						return item.minPriceProduct != null;
					});
					
					
					return filteredValue;
				}
			},
			columns: [
				{ "data": "userId" },
				{
					"data": "quantity",
					render: function(data, type, row) {
						if (data == 0) {
							return 'Hủy/Chưa được giao';
						}
						return formatToVND(data, '')
					}
				},
				{
					"data": "subTotal",
					render: function(data, type, row) {
						if (data == 0) {
							return 'Hủy/Chưa được giao';
						}
						return formatToVND(data, '₫')
					}
				},
				{
					"data": "minPriceProduct",
					render: function(data, type, row) {
						if (data == null) {
							return 'Hủy/Chưa được giao';
						}
						return formatToVND(data, '₫')
					}
				},
				{
					"data": "maxPriceProduct",
					render: function(data, type, row) {
						if (data == 0) {
							return 'Hủy/Chưa được giao';
						}
						return formatToVND(data, '₫')
					}
				},
				{
					data: "avgPriceProduct",
					render: function(data, type, row) {
						if (data == null) {
							return 'Hủy/Chưa được giao';
						}
						return formatToVND(data.toFixed(), '₫')

					}

				}
			],
			pageLength: 5,
			lengthMenu: [5, 10, 20, 25, 50],
			order: [],
			processing: true

		})
	})
}
//----------------------------------------------Report by Category---------------------------------------
if (convertUrl.pathname = 'admin/report/revenueByCategories') {
	$(document).ready(function() {
		var getCategoryUrl = protocol + '//' + hostname + ':' + port + '/api/report/revenueByCategory';
		var tableReportCategory = $('#tableReportCategory').DataTable({
			ajax: {
				"type": "GET",
				"url": getCategoryUrl,
				"dataSrc": function(resp) {
					var filteredValue = resp.filter(function(item) {
						return item.minProduct > 0;
					});
					return filteredValue;
				}
			},
			columns: [
				{ "data": "nameCategory" },
				{
					"data": "quantiyProduct",
					render: function(data, type, row) {

						return formatToVND(data, '')
					}
				},
				{
					"data": "subTotal",
					render: function(data, type, row) {
						return formatToVND(data, '₫')
					}
				},
				{
					"data": "minProduct",
					render: function(data, type, row) {
						if (data == null) {
							return 0 + " " + '₫';
						}
						return formatToVND(data, '₫')
					}
				},
				{
					"data": "maxProdouct",
					render: function(data, type, row) {
						return formatToVND(data, '₫')
					}
				},
				{
					"data": "avgProduct",
					render: function(data, type, row) {
						if (data == null) {
							return 0 + " " + '₫';
						}
						return formatToVND(data.toFixed(), '₫')
					}
				}
			],
			pageLength: 5,
			lengthMenu: [5, 10, 20, 25, 50],
			order: [],
			processing: true

		})

	})

}
//----------------------------------------------Report Popular Products---------------------------------------
if (convertUrl.pathname = 'admin/report/popularProduct') {
	$(document).ready(function() {
		var getPopularProductUrl = protocol + '//' + hostname + ':' + port + '/api/report/popularProduct';
		var tablePopullarProduct = $('#tableReportByPopularProduct').DataTable({
			ajax: {
				"type": "GET",
				"url": getPopularProductUrl,
				"dataSrc": function(resp) {
					var filteredResp = resp.filter(function(item) {
						return item.times > 0;
					});
					
					return filteredResp;
				}
			},
			columns: [
				{ "data": "nameCategory" },
				{
					"data": "nameProduct"
				},
				{
					"data": "times",
					render: function(data, type, row) {

						return formatToVND(data.toFixed(), '')
					}

				}

			],
			pageLength: 5,
			lengthMenu: [5, 10, 20, 25, 50],
			order: [],
			processing: true

		})
	})
}
//----------------------------------------------Report by Product---------------------------------------
if (convertUrl.pathname = 'admin/report/reportRevenueByProduct') {
	$(document).ready(function() {
		var getRevenueByProductProductUrl = protocol + '//' + hostname + ':' + port + '/api/report/reportRevenueByProduct';
		var tableRevenueByProductProduct = $('#tableReportRevenueByProduct').DataTable({
			ajax: {
				"type": "GET",
				"url": getRevenueByProductProductUrl,
				"dataSrc": function(resp) {	
					var filteredResp = resp.filter(function(item) {
						return item.totalSub > 0;
					});
					return filteredResp;
				}
			},
			columns: [
				{ "data": "nameProduct" },
				{
					"data": "totalQuantity",
					render: function(data, type, row) {
						return formatToVND(data.toFixed(), '')
					}
				},
				{
					"data": "order",
					render: function(data, type, row) {
						return formatToVND(data.toFixed(), '')
					}
				},
				{
					"data": "totalSub",
					render: function(data, type, row) {
						return formatToVND(data.toFixed(), '₫')
					}
				}

			],
			pageLength: 5,
			lengthMenu: [5, 10, 20, 25, 50],
			order: [],
			processing: true

		})
	})
}
//----------------------------------------------Report by Years---------------------------------------
if (convertUrl.pathname = 'admin/report/reportRevenueByYears') {
	$(document).ready(function() {
		var getRevenueByYearUrl = protocol + '//' + hostname + ':' + port + '/api/report/reportByRevenueByYears';
		var tableRevenueByYear = $('#tableReportYears').DataTable({
			ajax: {
				"type": "GET",
				"url": getRevenueByYearUrl,
				"dataSrc": function(resp) {
					var filteredValue = resp.filter(function(item) {
						return item.minPrice > 0;
					});
					return filteredValue;
				}
			},
			columns: [
				{ "data": "year" },
				{
					"data": "quantity",
					render: function(data, type, row) {
						return formatToVND(data.toFixed(), '')
					}
				},
				{
					"data": "totalSub",
					render: function(data, type, row) {
						return formatToVND(data.toFixed(), '₫')
					}
				},
				{
					"data": "minPrice",
					render: function(data, type, row) {
						if (data == null) {
							return 0 + " " + "₫"
						}
						return formatToVND(data.toFixed(), '₫')
					}
				},
				{
					"data": "maxPrice",
					render: function(data, type, row) {
						return formatToVND(data.toFixed(), '₫')
					}
				},
				{
					"data": "avgPrice",
					render: function(data, type, row) {
						if (data == null) {
							return 0 + " " + "₫"
						}
						return formatToVND(data.toFixed(), '₫')
					}
				}

			],
			pageLength: 5,
			lengthMenu: [5, 10, 20, 25, 50],
			order: [],
			processing: true

		})
	})
}
//----------------------------------------------Report by Quarter---------------------------------------
if (convertUrl.pathname = 'admin/report/reportRevenueByQuarter') {
	$(document).ready(function() {
		var getRevenueByQuarterUrl = protocol + '//' + hostname + ':' + port + '/api/report/reportByRevenueByQuarter';
		var tableReportQuarter = $('#tableReportQuarter').DataTable({
			ajax: {
				"type": "GET",
				"url": getRevenueByQuarterUrl,
				"dataSrc": function(resp) {
					var filteredValue = resp.filter(function(item) {
						return item.minPrice != null;
					});
					return filteredValue;
				}
			},
			columns: [
				{
					"data": "years",
					render: function(data, type, row) {
						return "Quý " + row.quarter + " - " + data;
					}
				},

				{
					"data": "quantity",
					render: function(data, type, row) {
						return formatToVND(data.toFixed(), '')
					}
				},
				{
					"data": "totalSub",
					render: function(data, type, row) {
						return formatToVND(data.toFixed(), '₫')
					}
				},
				{
					"data": "minPrice",
					render: function(data, type, row) {
						if (data == null) {
							return 0 + " " + "₫"
						}
						return formatToVND(data.toFixed(), '₫')
					}
				},
				{
					"data": "maxPrice",
					render: function(data, type, row) {
						return formatToVND(data.toFixed(), '₫')
					}
				},
				{
					"data": "avgPrice",
					render: function(data, type, row) {
						if (data == null) {
							return 0 + " " + "₫"
						}
						return formatToVND(data.toFixed(), '₫')
					}
				}

			],
			pageLength: 5,
			lengthMenu: [5, 10, 20, 25, 50],
			order: [],
			processing: true

		})
	})
}
//----------------------------------------------Report by Quarter---------------------------------------
if (convertUrl.pathname = 'admin/report/reportRevenueByMonth') {
	$(document).ready(function() {
		var getRevenueByMonthUrl = protocol + '//' + hostname + ':' + port + '/api/report/reportByRevenueByMonth';
		var tableReportMonth = $('#tableReportMonth').DataTable({
			ajax: {
				"type": "GET",
				"url": getRevenueByMonthUrl,
				"dataSrc": function(resp) {
					var filteredValue = resp.filter(function(item) {
						return item.quantity > 0;
					});
					return filteredValue;
				}
			},
			columns: [
				{
					"data": "years",
					render: function(data, type, row) {
						return row.month + " - " + data;
					}
				},

				{
					"data": "quantity",
					render: function(data, type, row) {
						return formatToVND(data.toFixed(), '')
					}
				},
				{
					"data": "totalSub",
					render: function(data, type, row) {
						return formatToVND(data.toFixed(), '₫')
					}
				},
				{
					"data": "minPrice",
					render: function(data, type, row) {
						if (data == null) {
							return 0 + " " + "₫"
						}
						return formatToVND(data.toFixed(), '₫')
					}
				},
				{
					"data": "maxPrice",
					render: function(data, type, row) {
						return formatToVND(data.toFixed(), '₫')
					}
				},
				{
					"data": "avgPrice",
					render: function(data, type, row) {
						if (data == null) {
							return 0 + " " + "₫"
						}
						return formatToVND(data.toFixed(), '₫')
					}
				}

			],
			pageLength: 5,
			lengthMenu: [5, 10, 20, 25, 50],
			order: [],
			processing: true

		})
	})
}