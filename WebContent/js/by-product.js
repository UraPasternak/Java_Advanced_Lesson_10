$(".product-buy").click(function() {
	var productId = jQuery(this).attr("product_id");
	
	
	$.post("ByProduct", {'productId':productId},
			function(data) {
				if (data == 'Success') {
					alert('Success');
				}else{alert('Err');}
			});
});