$('.add-product').click(function() {
	$('.form-add-product').show();
});

$("button.add-product").click(function() {
	var nameProduct = $(".add-name").val();
	var descriptionProduct = $(".add-description").val();
	var price = $(".add-price").val();
	

	if (nameProduct == '' || descriptionProduct == '' || price == '') {
		alert("Please fill all fields...!!!!!!");
		} else {
		$.post("AddProduct", {
			nameProduct : nameProduct,
			descriptionProduct : descriptionProduct,
			price : price,
			}, function(data) {
					if (data == 'Success!') {
						$("form.form-add-product")[0].reset();
												
						alert("Success!");
										}
					
									});
								}
							});