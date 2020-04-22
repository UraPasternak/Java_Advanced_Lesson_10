$('.message a').click(function() {
	$('form').animate({
		height : "toggle",
		opacity : "toggle"
	}, "slow");
});


$("button.register").click(function() {
			var firstName = $("form.register-form input.firstName").val();
			var lastName = $("form.register-form input.lastName").val();
			var email = $("form.register-form input.email").val();
			var password = $("form.register-form input.password").val();
			var cpassword = $("form.register-form input.cpassword").val();

			if (firstName == '' || lastName == '' || email == ''	|| password == '' || cpassword == '') {
				alert("Please fill all fields...!!!!!!");
				} else if ((password.length) < 8) {
				alert("Password should atleast 8 character in length...!!!!!!");
				} else if (!(password).match(cpassword)) {
				alert("Your passwords don't match. Try again?");
				} else {
				$.post("registration", {
					firstName : firstName,
					lastName : lastName,
					email : email,
					password : password
					}, function(data) {
							if (data == 'Success!') {
								$("form")[0].reset();
								$("form")[1].reset();
								$('form').animate({
									height : "toggle",
									opacity : "toggle"
													}, "slow");
								alert("Success!");
												}
							
											});
										}
									});

$("button.login").click(function() {
			var email = $("form.login-form input.email").val();
			var password = $("form.login-form input.password").val();

			if (email == '' || password == '') {
			alert("Please fill all fields...!!!!!!");
			} else {
					$.post("login",{
						email : email,
						password : password
						},
						function(data) {

							if (data == 'Success!') {
								$("form")[0].reset();
								alert("Success!");
								document.location.href = "cabinet.jsp";
								
							}else{
								alert("Err");
								$("form")[0].reset();
								$("form")[1].reset();
							}
							
											});
										}
									
					
				});
