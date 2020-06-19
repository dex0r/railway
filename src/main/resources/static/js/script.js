$(document).ready(function(){
	$('.dropdown-station').select2({
		theme: 'bootstrap4',
		placeholder: ""
	});

	$('#discountType').select2({
		theme: 'bootstrap4',
		placeholder: ""
	});

	$('.dropdown-arrival-station').select2({
		theme: 'boostrap4',
		placeholder: ''
	});

	$('.buyTicket').hover(
		function(){
			$(this).addClass('btn-primary');
			},
		function(){
			$(this).removeClass('btn-primary');
		}
		)

	var userLanguage = $('html').attr('lang');
	var timeoutId = 0;

	$('#firstName').keydown(function(){
		clearTimeout(timeoutId);
		timeoutId = setTimeout(validateFirstName, 200);
	});

	function validateFirstName() {
		var firstName = $('#firstName').val();
		if ((/^([a-zA-Zа-яА-Я])+$/.test(firstName) == true) && firstName.length >= 1){
			if ($('#firstName').hasClass("is-invalid")) {
				$('#firstName').removeClass("is-invalid");
				$('#firstName').parent().remove('.invalid-feedback');
			}
			$('#firstName').addClass("is-valid");
			return true;
		}else{
			$('#firstName').removeClass("is-valid");
			$('#firstName').removeClass("is-invalid");
			$('#firstName').addClass("is-invalid");
			$('#firstName').parent().find('.invalid-feedback').remove('.invalid-feedback');
			if(firstName.length == 0){
				if(userLanguage == 'bg'){
					$('#firstName').parent().append("<div class='invalid-feedback'>Полето не може да бъде празно.</div>");
				}
				if(userLanguage == 'en'){
					$('#firstName').parent().append("<div class='invalid-feedback'>The field cannot be blank.</div>");
				}
			}else{
				if(userLanguage == 'bg'){
					$('#firstName').parent().append("<div class='invalid-feedback'>Името трябва да съдържа само букви.</div>");
				}
				if(userLanguage == 'en'){
					$('#firstName').parent().append("<div class='invalid-feedback'>Name must contain only characters.</div>");
				}
			}
			return false;
		}
	}

	$('#lastName').keydown(function(){
		clearTimeout(timeoutId);
		timeoutId = setTimeout(validateLastName, 200);
	});

	function validateLastName() {
		var lastName = $('#lastName').val();
		if ((/^([a-zA-Zа-яА-Я])+$/.test(lastName) == true) && lastName.length >= 1){
			if ($('#lastName').hasClass("is-invalid")) {
				$('#lastName').removeClass("is-invalid");
				$('#lastName').parent().remove('.invalid-feedback');
			}
			$('#lastName').addClass("is-valid");
			return true;
		}else{
			$('#lastName').removeClass("is-valid");
			$('#lastName').removeClass("is-invalid");
			$('#lastName').addClass("is-invalid");
			$('#lastName').parent().find('.invalid-feedback').remove('.invalid-feedback');
			if(lastName.length == 0){
				if(userLanguage == 'bg'){
					$('#lastName').parent().append("<div class='invalid-feedback'>Полето не може да бъде празно.</div>");
				}
				if(userLanguage == 'en'){
					$('#lastName').parent().append("<div class='invalid-feedback'>The field cannot be blank.</div>");
				}
			}else{
				if(userLanguage == 'bg'){
					$('#lastName').parent().append("<div class='invalid-feedback'>Фамилията трябва да съдържа само букви.</div>");
				}
				if(userLanguage == 'en'){
					$('#lastName').parent().append("<div class='invalid-feedback'>Surname must contain only characters.</div>");
				}
			}
			return false;
		}
	}

	$('#password').keydown(function(){
		clearTimeout(timeoutId);
		timeoutId = setTimeout(validatePassword, 300);
	});

	function validatePassword() {
		var password = $('#password').val();
		if (password.length > 8) {
			if ($('#password').hasClass("is-invalid")) {
				$('#password').removeClass("is-invalid");
				$('#password').parent().remove('.invalid-feedback');
			}
			if(window.location.pathname == "/register") {
				$('#password').addClass("is-valid");
			}
			return true;
		} else {
			if(window.location.pathname == "/register") {
				$('#password').removeClass("is-valid");
				$('#password').removeClass("is-invalid");
				$('#password').addClass("is-invalid");
				$('#password').parent().find('.invalid-feedback').remove('.invalid-feedback');
				if (password.length == 0) {
					if (userLanguage == 'bg') {
						$('#password').parent().append("<div class='invalid-feedback'>Полето не може да бъде празно.</div>");
					}
					if (userLanguage == 'en') {
						$('#password').parent().append("<div class='invalid-feedback'>The field cannot be blank.</div>");
					}
				} else {
					if (userLanguage == 'bg') {
						$('#password').parent().append("<div class='invalid-feedback'>Паролата трябва да бъде повече от 8 символа.</div>");
					}
					if (userLanguage == 'en') {
						$('#password').parent().append("<div class='invalid-feedback'>The password must be longer than 8 characters.</div>");
					}
				}
			}
			return false;
		}
	}

	$('#newPassword').keydown(function(){
		clearTimeout(timeoutId);
		timeoutId = setTimeout(validateNewPassword, 300);
	});

	function validateNewPassword() {
		var password = $('#newPassword').val();
		if (password.length > 8) {
			if ($('#newPassword').hasClass("is-invalid")) {
				$('#newPassword').removeClass("is-invalid");
				$('#newPassword').parent().remove('.invalid-feedback');
			}
			$('#newPassword').addClass("is-valid");
			return true;
		} else {
			$('#newPassword').removeClass("is-valid");
			$('#newPassword').removeClass("is-invalid");
			$('#newPassword').addClass("is-invalid");
			$('#newPassword').parent().find('.invalid-feedback').remove('.invalid-feedback');
			if(password.length == 0){
				if(userLanguage == 'bg'){
					$('#newPassword').parent().append("<div class='invalid-feedback'>Полето не може да бъде празно.</div>");
				}
				if(userLanguage == 'en'){
					$('#newPassword').parent().append("<div class='invalid-feedback'>The field cannot be blank.</div>");
				}
			}else{

				if(userLanguage == 'bg'){
					$('#newPassword').parent().append("<div class='invalid-feedback'>Паролата трябва да бъде повече от 8 символа.</div>");
				}
				if(userLanguage == 'en'){
					$('#newPassword').parent().append("<div class='invalid-feedback'>The password must be longer than 8 characters.</div>");
				}
			}
			return false;
		}

	}

	$('#confirmPassword').keydown(function(){
		clearTimeout(timeoutId);
		timeoutId = setTimeout(validateConfirmPassword, 300);
	});

	function validateConfirmPassword() {
		if(window.location.pathname == "/profile/information"){
			var password = $('#newPassword').val();
		}else{
			var password = $('#password').val();
		}

		var confirmPassword = $('#confirmPassword').val();
		console.log(password);
		console.log(confirmPassword);
		if (password == confirmPassword && confirmPassword >= 1) {
			if ($('#confirmPassword').hasClass("is-invalid")) {
				$('#confirmPassword').removeClass("is-invalid");
				$('#confirmPassword').parent().remove('.invalid-feedback');
			}
			$('#confirmPassword').addClass("is-valid");
			return true;
		} else {
			$('#confirmPassword').removeClass("is-valid");
			$('#confirmPassword').removeClass("is-invalid");
			$('#confirmPassword').addClass("is-invalid");
			$('#confirmPassword').parent().find('.invalid-feedback').remove('.invalid-feedback');
			if(confirmPassword.length == 0){
				if(userLanguage == 'bg'){
					$('#confirmPassword').parent().append("<div class='invalid-feedback'>Полето не може да бъде празно.</div>");
				}
				if(userLanguage == 'en'){
					$('#confirmPassword').parent().append("<div class='invalid-feedback'>The field cannot be blank.</div>");
				}
			}else{
				if(userLanguage == 'bg'){
					$('#confirmPassword').parent().append("<div class='invalid-feedback'>Паролите не съвпадат.</div>");
				}
				if(userLanguage == 'en'){
					$('#confirmPassword').parent().append("<div class='invalid-feedback'>The passwords don't match.</div>");
				}
			}
			return false;
		}
	}

	$('#emailAddress').keydown(function(){
		clearTimeout(timeoutId);
		timeoutId = setTimeout(validateEmailAddress, 300);
	});

	function validateEmailAddress() {
		var emailAddress = $('#emailAddress').val();
		if ((/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(emailAddress) == true) && emailAddress.length >= 1) {
			if ($('#emailAddress').hasClass("is-invalid")) {
				$('#emailAddress').removeClass("is-invalid");
				$('#emailAddress').parent().remove('.invalid-feedback');
			}
			$('#emailAddress').addClass("is-valid");
			return true;
		} else {
			$('#emailAddress').removeClass("is-valid");
			$('#emailAddress').removeClass("is-invalid");
			$('#emailAddress').addClass("is-invalid");
			$('#emailAddress').parent().find('.invalid-feedback').remove('.invalid-feedback');
			if(emailAddress.length == 0){
				if(userLanguage == 'bg'){
					$('#emailAddress').parent().append("<div class='invalid-feedback'>Полето не може да бъде празно.</div>");
				}
				if(userLanguage == 'en'){
					$('#emailAddress').parent().append("<div class='invalid-feedback'>The field cannot be blank.</div>");
				}
			}else{
				if(userLanguage == 'bg'){
					$('#emailAddress').parent().append("<div class='invalid-feedback'>Невалиден email адрес.</div>");
				}
				if(userLanguage == 'en'){
					$('#emailAddress').parent().append("<div class='invalid-feedback'>The email address is invalid.</div>");
				}
			}
			return false;
		}
	}

	$('#phoneNumber').keydown(function(){
		clearTimeout(timeoutId);
		timeoutId = setTimeout(validatePhoneNumber, 300);
	});

	function validatePhoneNumber() {
		var phoneNumber = $('#phoneNumber').val();
		if ((/^(?:\+)(359)[ ]?(87|88|89|98)[0-9]{7}$/.test(phoneNumber) == true) && phoneNumber.length >= 1) {
			if ($('#phoneNumber').hasClass("is-invalid")) {
				$('#phoneNumber').removeClass("is-invalid");
				$('#phoneNumber').parent().remove('.invalid-feedback');
			}
			$('#phoneNumber').addClass("is-valid");
			return true;
		} else {
			$('#phoneNumber').removeClass("is-valid");
			$('#phoneNumber').removeClass("is-invalid");
			$('#phoneNumber').addClass("is-invalid");
			$('#phoneNumber').parent().find('.invalid-feedback').remove('.invalid-feedback');
			if(phoneNumber.length == 0){
				if(userLanguage == 'bg'){
					$('#phoneNumber').parent().append("<div class='invalid-feedback'>Полето не може да бъде празно.</div>");
				}
				if(userLanguage == 'en'){
					$('#phoneNumber').parent().append("<div class='invalid-feedback'>The field cannot be blank.</div>");
				}
			}else{
				if(userLanguage == 'bg'){
					$('#phoneNumber').parent().append("<div class='invalid-feedback'>Невалиден телефонен номер.</div>");
				}
				if(userLanguage == 'en'){
					$('#phoneNumber').parent().append("<div class='invalid-feedback'>The phone number is invalid.</div>");
				}
			}
			return false;
		}
	}

	function checkStringForNumbers(input){
		var str = String(input);
		for( var i = 0; i < str.length; i++){
			if(!isNaN(str.charAt(i))){           //if the string is a number, do the following
				return true;
			}
		}
	}

	$('#inputCity').keydown(function(){
		clearTimeout(timeoutId);
		timeoutId = setTimeout(validateCity, 300);
	});

	function validateCity() {
		var city = $('#inputCity').val();
		if(!checkStringForNumbers(city) && city.length >= 1){
			if ($('#inputCity').hasClass("is-invalid")) {
				$('#inputCity').removeClass("is-invalid");
				$('#inputCity').parent().remove('.invalid-feedback');
			}
			$('#inputCity').addClass("is-valid");
			return true;
		}else{
			$('#inputCity').removeClass("is-valid");
			$('#inputCity').removeClass("is-invalid");
			$('#inputCity').addClass("is-invalid");
			$('#inputCity').parent().find('.invalid-feedback').remove('.invalid-feedback');
			if(userLanguage == 'bg'){
				$('#inputCity').parent().append("<div class='invalid-feedback'>Градът не може да съдържа цифри и полето не може да бъде празно.</div>");
			}
			if(userLanguage == 'en'){
				$('#inputCity').parent().append("<div class='invalid-feedback'>The city cannot contain numbers and cannot be blank.</div>");
			}
			return false;
		}
	}

	$('#inputAddress').keydown(function(){
		clearTimeout(timeoutId);
		timeoutId = setTimeout(validateAddress, 300);
	});

	function validateAddress() {
		var address = $('#inputAddress').val();
		if((/^[a-zA-Z0-9а-яА-Я- ,.]*$/.test(address) == true) && address.length >= 1){
			if ($('#inputAddress').hasClass("is-invalid")) {
				$('#inputAddress').removeClass("is-invalid");
				$('#inputAddress').parent().remove('.invalid-feedback');
			}
			$('#inputAddress').addClass("is-valid");
			return true;
		}else{
			$('#inputAddress').removeClass("is-valid");
			$('#inputAddress').removeClass("is-invalid");
			$('#inputAddress').addClass("is-invalid");
			$('#inputAddress').parent().find('.invalid-feedback').remove('.invalid-feedback');
			if(address.length == 0){
				if(userLanguage == 'bg'){
					$('#inputAddress').parent().append("<div class='invalid-feedback'>Полето не може да бъде празно.</div>");
				}
				if(userLanguage == 'en'){
					$('#inputAddress').parent().append("<div class='invalid-feedback'>The field cannot be blank.</div>");
				}
			}else{
				if(userLanguage == 'bg'){
					$('#inputAddress').parent().append("<div class='invalid-feedback'>Адресът трябва да съдържа само букви, цифри, точка или запетая.</div>");
				}
				if(userLanguage == 'en'){
					$('#inputAddress').parent().append("<div class='invalid-feedback'>The address must contain only numbers, characters, period or comma.</div>");
				}
			}
			return false;
		}
	}

	$('#inputAddress2').keydown(function(){
		clearTimeout(timeoutId);
		timeoutId = setTimeout(validateAdditionalAddress, 300);
	});

	function validateAdditionalAddress() {
		var address = $('#inputAddress2').val();
		if(/^[a-zA-Z0-9а-яА-Я- ,.]*$/.test(address) == true){
			if ($('#inputAddress2').hasClass("is-invalid")) {
				$('#inputAddress2').removeClass("is-invalid");
				$('#inputAddress2').parent().remove('.invalid-feedback');
			}
			$('#inputAddress2').addClass("is-valid");
			return true;
		}else{
			$('#inputAddress2').removeClass("is-valid");
			$('#inputAddress2').removeClass("is-invalid");
			$('#inputAddress2').addClass("is-invalid");
			$('#inputAddress2').parent().find('.invalid-feedback').remove('.invalid-feedback');
			if(userLanguage == 'bg'){
				$('#inputAddress2').parent().append("<div class='invalid-feedback'>Адресът трябва да съдържа само букви, цифри, точка или запетая.</div>");
			}
			if(userLanguage == 'en'){
				$('#inputAddress2').parent().append("<div class='invalid-feedback'>The address must contain only numbers, characters, period or comma.</div>");
			}
			return false;
		}
	}

	$('#inputZip').keydown(function(){
		clearTimeout(timeoutId);
		timeoutId = setTimeout(validateZIP, 300);
	});

	function validateZIP() {
		var zip = $('#inputZip').val();
		if((/^[1-9]{1}[0-9]{3}$/.test(zip) == true) && zip.length==4){
			if ($('#inputZip').hasClass("is-invalid")) {
				$('#inputZip').removeClass("is-invalid");
				$('#inputZip').parent().remove('.invalid-feedback');
			}
			$('#inputZip').addClass("is-valid");
			return true;
		}else{
			$('#inputZip').removeClass("is-valid");
			$('#inputZip').removeClass("is-invalid");
			$('#inputZip').addClass("is-invalid");
			$('#inputZip').parent().find('.invalid-feedback').remove('.invalid-feedback');
			if(/^[a-zA-Z0-9а-яА-Я- ,.]*$/.test(zip)){
				if(userLanguage == 'bg'){
					$('#inputZip').parent().append("<div class='invalid-feedback'>Пощенският код трябва да съдържа само цифри.</div>");
				}
				if(userLanguage == 'en'){
					$('#inputZip').parent().append("<div class='invalid-feedback'>ZIP must contain only digits.</div>");
				}
			}
			if(zip.length!=4){
				if(userLanguage == 'bg'){
					$('#inputZip').parent().append("<div class='invalid-feedback'>Пощенският код трябва да бъде 4 цифрен.</div>");
				}
				if(userLanguage == 'en'){
					$('#inputZip').parent().append("<div class='invalid-feedback'>ZIP must be 4 digits long.</div>");
				}
			}
			return false;
		}
	}

	$('#inputCreditCardName').keydown(function(){
		clearTimeout(timeoutId);
		timeoutId = setTimeout(validateCreditCardName, 300);
	});

	function validateCreditCardName() {
		var creditCardName = $('#inputCreditCardName').val();
		if(/^[a-zA-Zа-яА-Я]+( )[a-zA-Zа-яА-Я]+$/.test(creditCardName) == true){
			if ($('#inputCreditCardName').hasClass("is-invalid")) {
				$('#inputCreditCardName').removeClass("is-invalid");
				$('#inputCreditCardName').parent().remove('.invalid-feedback');
			}
			$('#inputCreditCardName').addClass("is-valid");
			return true;
		}else{
			$('#inputCreditCardName').removeClass("is-valid");
			$('#inputCreditCardName').removeClass("is-invalid");
			$('#inputCreditCardName').addClass("is-invalid");
			$('#inputCreditCardName').parent().find('.invalid-feedback').remove('.invalid-feedback');
			if(userLanguage == 'bg'){
				$('#inputCreditCardName').parent().append("<div class='invalid-feedback'>Името трябва да съдържа само букви, цифри или празно място.</div>");
			}
			if(userLanguage == 'en'){
				$('#inputCreditCardName').parent().append("<div class='invalid-feedback'>Name must contain only numbers, characters or white space.</div>");
			}
			return false;
		}
	}

	$('#inputCreditCard').keydown(function(){
		clearTimeout(timeoutId);
		timeoutId = setTimeout(validateCreditCardNumber, 300);
	});

	function validateCreditCardNumber() {
		var creditCardNumber = $('#inputCreditCard').val();
		if((/^(?:4[0-9]{12}(?:[0-9]{3})?)$/.test(creditCardNumber) == true) || (/^(?:5[1-5][0-9]{14})$/.test(creditCardNumber) == true)){
			if ($('#inputCreditCard').hasClass("is-invalid")) {
				$('#inputCreditCard').removeClass("is-invalid");
				$('#inputCreditCard').parent().remove('.invalid-feedback');
			}
			$('#inputCreditCard').addClass("is-valid");
			return true;
		}else{
			$('#inputCreditCard').removeClass("is-valid");
			$('#inputCreditCard').removeClass("is-invalid");
			$('#inputCreditCard').addClass("is-invalid");
			$('#inputCreditCard').parent().find('.invalid-feedback').remove('.invalid-feedback');
			if(userLanguage == 'bg'){
				$('#inputCreditCard').parent().append("<div class='invalid-feedback'>Въведете валиден Visa или MasterCard номер.</div>");
			}
			if(userLanguage == 'en'){
				$('#inputCreditCard').parent().append("<div class='invalid-feedback'>Enter valid Visa or MasterCard number..</div>");
			}
			return false;
		}
	}

	$('#inputExpirationMonth').keydown(function(){
		clearTimeout(timeoutId);
		timeoutId = setTimeout(validateCreditCardExpirationMonth, 300);
	});

	function validateCreditCardExpirationMonth() {
		var expirationMonth = $('#inputExpirationMonth').val();
		if((/^[1-9]{1}$/.test(expirationMonth) == true) || (/^[1]{1}[0-2]{1}$/.test(expirationMonth) == true)){
			if ($('#inputExpirationMonth').hasClass("is-invalid")) {
				$('#inputExpirationMonth').removeClass("is-invalid");
				$('#inputExpirationMonth').parent().remove('.invalid-feedback');
			}
			$('#inputExpirationMonth').addClass("is-valid");
			return true;
		}else{
			$('#inputExpirationMonth').removeClass("is-valid");
			$('#inputExpirationMonth').removeClass("is-invalid");
			$('#inputExpirationMonth').addClass("is-invalid");
			$('#inputExpirationMonth').parent().find('.invalid-feedback').remove('.invalid-feedback');
			if(userLanguage == 'bg'){
				$('#inputExpirationMonth').parent().append("<div class='invalid-feedback'>Месецът не е валиден.</div>");
			}
			if(userLanguage == 'en'){
				$('#inputExpirationMonth').parent().append("<div class='invalid-feedback'>Month is not valid.</div>");
			}
			return true;
		}
	}

	$('#inputExpirationYear').keydown(function(){
		clearTimeout(timeoutId);
		timeoutId = setTimeout(validateCreditCardExpirationYear, 300);
	});

	function validateCreditCardExpirationYear() {
		var expirationYear = $('#inputExpirationYear').val();
		if(/^[2-9]{1}[0-9]{1}$/.test(expirationYear) == true){
			if ($('#inputExpirationYear').hasClass("is-invalid")) {
				$('#inputExpirationYear').removeClass("is-invalid");
				$('#inputExpirationYear').parent().remove('.invalid-feedback');
			}
			$('#inputExpirationYear').addClass("is-valid");
			return true;
		}else{
			$('#inputExpirationYear').removeClass("is-valid");
			$('#inputExpirationYear').removeClass("is-invalid");
			$('#inputExpirationYear').addClass("is-invalid");
			$('#inputExpirationYear').parent().find('.invalid-feedback').remove('.invalid-feedback');
			if(userLanguage == 'bg'){
				$('#inputExpirationYear').parent().append("<div class='invalid-feedback'>Годината не е валидна.</div>");
			}
			if(userLanguage == 'en'){
				$('#inputExpirationYear').parent().append("<div class='invalid-feedback'>Year is not valid.</div>");
			}
			return false;
		}
	}

	var url = window.location.pathname;

	if(url == "/profile/payment" || url == "/booking/payment-data"){
		if(url == "/booking/payment-data"){
			if($('#inputCVV').val().length >= 3) {
				$("#maskedCVV").prop("hidden", false);
				$("#inputCVV").prop("hidden", true);
			}else{
				$("#maskedCVV").prop("hidden", true);
				$("#inputCVV").prop("hidden", false);
			}

			$('#maskedCVV').click(function(){
				$("#inputCVV").prop("hidden", false);
				$("#inputCVV").val("");
				$("#inputCVV").focus();
				$("#maskedCVV").prop("hidden", true);
			})
		}
		if($('#inputCVV').val().length >= 3){
			$("#inputCVV").prop("hidden", true);
			$("#maskedCVV").prop("hidden", false);
		}
	}

	$('#inputCVV').keydown(function(){
		clearTimeout(timeoutId);
		timeoutId = setTimeout(validateCVV, 300);
	});

	function validateCVV() {
		var CVV = $('#inputCVV').val();
		if(/^[0-9]{3}$/.test(CVV) == true){
			if ($('#inputCVV').hasClass("is-invalid")) {
				$('#inputCVV').removeClass("is-invalid");
				$('#inputCVV').parent().remove('.invalid-feedback');
			}
			$('#inputCVV').addClass("is-valid");
			return true;
		}else{
			if(window.location.pathname == "/booking/payment-data" && $("#maskedCVV").val() == '***'){
				return true;
			}
			$('#inputCVV').removeClass("is-valid");
			$('#inputCVV').removeClass("is-invalid");
			$('#inputCVV').addClass("is-invalid");
			$('#inputCVV').parent().find('.invalid-feedback').remove('.invalid-feedback');
			if(userLanguage == 'bg'){
				$('#inputCVV').parent().append("<div class='invalid-feedback'>CVV трябва да бъде 3 цифри.</div>");
			}
			if(userLanguage == 'en'){
				$('#inputCVV').parent().append("<div class='invalid-feedback'>CVV has to be 3 digits long.</div>");
			}
			return false;
		}
	}

	$('#inputDiscountDocumentNumber').keydown(function(){
		clearTimeout(timeoutId);
		timeoutId = setTimeout(validateDiscountDocumentNumber, 300);
	});

	function validateDiscountDocumentNumber() {
		var discountDocumentNumber = $('#inputDiscountDocumentNumber').val();
		if((/^[0-9]+$/.test(discountDocumentNumber) == true) && discountDocumentNumber.length >= 1){
			if ($('#inputDiscountDocumentNumber').hasClass("is-invalid")) {
				$('#inputDiscountDocumentNumber').removeClass("is-invalid");
				$('#inputDiscountDocumentNumber').parent().remove('.invalid-feedback');
			}
			$('#inputDiscountDocumentNumber').addClass("is-valid");
			return true;
		}else{
			$('#inputDiscountDocumentNumber').removeClass("is-valid");
			$('#inputDiscountDocumentNumber').removeClass("is-invalid");
			$('#inputDiscountDocumentNumber').addClass("is-invalid");
			$('#inputDiscountDocumentNumber').parent().find('.invalid-feedback').remove('.invalid-feedback');
			if(discountDocumentNumber.length == 0){
				if(userLanguage == 'bg'){
					$('#inputDiscountDocumentNumber').parent().append("<div class='invalid-feedback'>Полето не може да бъде празно.</div>");
				}
				if(userLanguage == 'en'){
					$('#inputDiscountDocumentNumber').parent().append("<div class='invalid-feedback'>The field cannot be blank.</div>");
				}
			}else{
				if(userLanguage == 'bg'){
					$('#inputDiscountDocumentNumber').parent().append("<div class='invalid-feedback'>Полето трябва да съдържа само цифри.</div>");
				}
				if(userLanguage == 'en'){
					$('#inputDiscountDocumentNumber').parent().append("<div class='invalid-feedback'>The field must contain only numbers.</div>");
				}
			}
			return false;
		}
	}

	$("#registerAddress").click(function () {
		if($("#addressInformation").hasClass("show")){
			$(this).find(".fas").removeClass("fa-angle-up").addClass("fa-angle-down");
			$("#addressInformation").collapse('hide');
		}else{
			$(this).find(".fas").removeClass("fa-angle-down").addClass("fa-angle-up");
			$("#addressInformation").collapse('show');
			$("#inputAddress").focus();

			var timeout = 0;
			clearTimeout(timeout);
			timeout = setTimeout(getResults, 330);
			function getResults() {
				$('html, body').animate({scrollTop: $("#addressInformation").offset().top}, 1600);
			}
		}
	});

	$("#registerPayment").click(function () {
		if($("#creditCardInformation").hasClass("show")){
			$(this).find(".fas").removeClass("fa-angle-up").addClass("fa-angle-down");
			$("#creditCardInformation").collapse('hide');
		}else{
			$(this).find(".fas").removeClass("fa-angle-down").addClass("fa-angle-up");
			$("#creditCardInformation").collapse('show');
			$("#inputCreditCardName").focus();

			var timeout = 0;
			clearTimeout(timeout);
			timeout = setTimeout(getResults, 330);
			function getResults() {
				$('html, body').animate({scrollTop: $("#creditCardInformation").offset().top}, 1600);
			}
		}
	});

	$("#registerDiscount").click(function () {
		if($("#discountInformation").hasClass("show")){
			$(this).find(".fas").removeClass("fa-angle-up").addClass("fa-angle-down");
			$("#discountInformation").collapse('hide');
		}else{
			$(this).find(".fas").removeClass("fa-angle-down").addClass("fa-angle-up");
			$("#discountInformation").collapse('show');
			$("#inputAddress").focus();

			var timeout = 0;
			clearTimeout(timeout);
			timeout = setTimeout(getResults, 330);
			function getResults() {
				$('html, body').animate({scrollTop: $("#discountInformation").offset().top}, 1600);
			}
		}
	});

	$("#discountType").change(function() {
		if($("#discountType :selected").val() != '1'){
			$("#documentInformation").collapse('show');

			$("#inputDiscountDocumentNumber").focus();
			var timeout = 0;
			clearTimeout(timeout);
			timeout = setTimeout(getResults, 330);
			function getResults() {
				$('html, body').animate({scrollTop: $("#inputDiscountDocumentNumber").offset().top}, 1400);
			}
		}else{
			if($("#inputDiscountDocumentNumber").val().length != 0){
				$("#inputDiscountDocumentNumber").val() == "";
			}
			$("#documentInformation").collapse('hide');

		}
	});

	$('#registerForm').submit(
		function(e){
			e.preventDefault(e);
			var firstName = $('#firstName').val();
			var lastName = $('#lastName').val();
			var email = $('#emailAddress').val();
			var password = $('#password').val();
			var confirmPassword = $('#confirmPassword').val();

			var address = $('#inputAddress').val();
			var addresstwo = $('#inputAddress2').val();
			var city = $('#inputCity').val();
			var zip = $('#inputZip').val();

			var creditCardName = $('#inputCreditCardName').val();
			var creditCardNumber = $('#inputCreditCard').val();
			var expirationMonth = $('#inputExpirationMonth').val();
			var expirationYear = $('#inputExpirationYear').val();
			var cvv = $('#inputCVV').val();

			var discountType = $('#discountType').val();
			var documentNumber = $('#inputDiscountDocumentNumber').val();
			//console.log(password);
			//console.log(confirmPassword);
			//console.log(password.length);

			console.log(discountType);

			if(validateFirstName()
				&& validateLastName()
				&& validateEmailAddress()
				&& validatePhoneNumber()
				&& validatePassword()
				&& validateConfirmPassword()){
				console.log(address.length);
				console.log(addresstwo.length);
				console.log(city.length);
				console.log(zip.length);
				if(address.length != 0
					|| addresstwo.length != 0
					|| city.length != 0
					|| zip.length !=0)
				{
					if(validateAddress()
					&& validateAdditionalAddress()
					&& validateCity()
					&& validateZIP()){
						if(creditCardName.length != 0
						|| creditCardNumber.length != 0
						|| expirationMonth.length != 0
						|| expirationYear.length != 0
						|| cvv.length != 0){
							if(validateCreditCardName()
							&& validateCreditCardNumber()
							&& validateCreditCardExpirationMonth()
							&& validateCreditCardExpirationYear()
							&& validateCVV()){
								if(discountType != 1){
									if(validateDiscountDocumentNumber()){
										e.currentTarget.submit();
									}
								}else{
									e.currentTarget.submit();
								}
							}
						}else{
							e.currentTarget.submit();
						}
					}
				}else
				{
					e.currentTarget.submit();
				}
			}else{
				console.log("catched");
			}
		});

	if(window.location.pathname == "register/fail?error=bademail"){
		setTimeout(function() {
			$('#badEmailMessage').fadeOut('fast');
		}, 5000); // <-- time in milliseconds
	}

	$('#passwordChange').hide();

	$('#changePasswordCheckbox').click(function(){
		if($(this).prop("checked") == true){
			$('#passwordChange').show( "slow" );
		}
		else if($(this).prop("checked") == false){
			$('#passwordChange').hide( "slow" );
		}
	});

	$('#updateAddress').submit(function(e){
		e.preventDefault();
		$('#saveChangesModal').modal('show');
		$('#saveChanges').click(function(evt){
			if(validateAddress()
				&& validateAdditionalAddress()
				&& validateCity()
				&& validateZIP()) {
				e.currentTarget.submit();
			}
		});
	});

	$('#updateInformation').submit(function(e){
		e.preventDefault();
		$('#saveChangesModal').modal('show');
		$('#saveChanges').click(function(evt){
			if(validatePassword()
				&& validateNewPassword()
				&& validateConfirmPassword()){
				e.currentTarget.submit();
			}
		});
		//validation to be done
	});

	$('#changeAddressFields').click(function(){
		$('#inputAddress').prop('readonly', false);
		$('#inputAddress2').prop('readonly', false);
		$('#inputCity').prop('readonly', false);
		$('#inputZip').prop('readonly', false);
		$('#saveChangesButton').prop('hidden', false);
	});

	$('#changePhoneFields').click(function(){
		$('#phoneNumber').prop('readonly', false);
		$('#savePhoneChangesButton').prop('hidden', false);
	});

	$('#updatePhone').submit(function(e){
		e.preventDefault();
		$('#saveChangesModal').modal('show');
		$('#saveChanges').click(function(evt){
			if(validatePhoneNumber()){
				e.currentTarget.submit();
			}
		});
	});

	$('#changeCreditCardFields').click(function(){
		$('#inputCreditCardName').prop('readonly', false);
		$('#inputCreditCard').prop('readonly', false);
		$('#inputExpirationMonth').prop('readonly', false);
		$('#inputExpirationYear').prop('readonly', false);
		$('#inputCVV').prop('readonly', false)
        $('#inputCVV').prop('hidden', false)
        $("#inputCVV").val("");
        $('#maskedCVV').prop('hidden', true)
		$('#saveChangesButton').prop('hidden', false);
	});

	$('#updateCreditCard').submit(function(e){
		e.preventDefault();
		$('#saveChangesModal').modal('show');
		$('#saveChanges').click(function(evt){
			if(validateCreditCardName()
				&& validateCreditCardNumber()
				&& validateCreditCardExpirationMonth()
				&& validateCreditCardExpirationYear()
				&& validateCVV()) {
				e.currentTarget.submit();
			}
		});

		//validation to be done
	});

	$('.cancelTicket').submit(function(e){
		e.preventDefault();
		$('#cancelTicketModal').modal('show');
		$('#confirmCancel').click(function(evt){
			e.currentTarget.submit();
		});
	});

	$('#postDelivery, #emailDelivery').change(function(){

		if ($("#emailDelivery").is(":checked")) {
			$('#addressInformation.collapse').collapse('hide');
		}
		else if ($("#postDelivery").is(":checked")) {
			$('#addressInformation.collapse').collapse('show');
		}
	});

	$('.tg-0pky').click(function(){
		$("#spaces td").each(function () {
			if ($(this).hasClass("green")) {
				$(this).removeClass("green");
			}
		});
		$(this).addClass("green");
	});

    $("#spaceTable").collapse('hide');

    $("#compartmentType").change(function(){
		$("#spaceTable").collapse('hide');
		$("#spaces td").each(function () {
			if($(this).hasClass("red")){
				$(this).removeClass("red");
				$(this).prop("disabled", false);
			}
		});
	});

    $("#showSpaceTable").click(function(){
		if(!$("#spaceTable").hasClass("show")) {
			var train = $('#train').html();
			var date = $('#purchaseDate').html();
			var arr = date.split(".");
			var formattedDate = new Date(arr[2] + " " + arr[1] + " " + arr[0]);
			var d = formattedDate.getDate();
			var m =  formattedDate.getMonth();
			m += 1;
			var y = formattedDate.getFullYear();

			var purchasedate = y + "-" + m + "-" + d;
			var compartmentType = $('#compartmentType :selected').val();

			$.ajax({
				url: "/booking/return/spaces",
				type: 'GET',
				dataType: "JSON",
				data: { train: train, purchasedate: purchasedate, compartment: compartmentType },
				error: function(){
					console.log("ajax failed");
				},
				success: function(data){
					$.each(data, function(i, post){
						$("#spaces td").each(function () {
							if($(this).html() == post.seatName){
								$(this).addClass("red");
								$(this).prop("disabled", true);
							}
						});
					})
				}
			});
			$("#spaceTable").collapse('show');
		}
    });

    $('#bookingDataForm').submit(function(e){
    	e.preventDefault();
		var discountType = $('#discountType').val();
    	if(validateFirstName()
		&& validateLastName()
		&& validateEmailAddress()
		&& validatePhoneNumber()){
			if(discountType != 1){
				if(validateDiscountDocumentNumber()){
					e.currentTarget.submit();
				}
			}else{
				e.currentTarget.submit();
			}
		}
	})

    $("#spaceForm").submit(function(e){
    	e.preventDefault(e);
    	var selectedSeat;
		$("#spaces td").each(function () {
			if($(this).hasClass("green")){
				selectedSeat = $(this).html();
			}
		});

		if(typeof selectedSeat == "undefined"){
			$('#selectSpace').collapse('show');
			var timeout = 0;
			clearTimeout(timeout);
			timeout = setTimeout(getResults, 5000);
			function getResults() {
				$('#selectSpace').collapse('hide');
			}
		}else{
			$('#selectSpace').collapse('hide');
			$.ajax({
				type: "GET",
				url: "/booking/setSpace",
				data: { seatName: selectedSeat }
			})
				.done(function(){
					e.currentTarget.submit();
				});
		}
		//console.log(selectedSeat);
	});

    $('#deliveryForm').submit(function(e){
    	e.preventDefault();

		if($("#deliveryForm input[type='radio']:checked").val() == 'postDelivery'){
			if(validateAddress()
			&& validateAdditionalAddress()
			&& validateCity()
			&& validateZIP()){
				e.currentTarget.submit();
				e.currentTarget.submit();
			}
		}else{
			e.currentTarget.submit();
		}
	});

    $('#paymentForm').submit(function(e){
    	e.preventDefault();

    	if(validateCreditCardName()
		&& validateCreditCardNumber()
		&& validateCreditCardExpirationMonth()
		&& validateCreditCardExpirationYear()
		&& validateCVV()){
    		e.currentTarget.submit();
		}
	});

    $("#changeDiscountData").click(function () {
    	$('#discountType').prop('disabled', false);
		$('#inputDiscountDocumentNumber').prop('readonly', false);
		$('#saveChangesButton').prop('hidden', false);
	});

	$('#updateDiscount').submit(function(e){
		var discountType = $('#discountType').val();
		e.preventDefault();
		console.log(discountType);
		$('#saveChangesModal').modal('show');
		$('#saveChanges').click(function(evt){
			if(discountType != 1){
				if(validateDiscountDocumentNumber()){
					e.currentTarget.submit();
				}
			}else{
				e.currentTarget.submit();
			}
		});
	});

	$("#contactsForm").submit(function(e){
		e.preventDefault();
		$('#contactForm').collapse('hide');
		$('#loadingMessage').collapse('show');
		var timeout = 0;
		clearTimeout(timeout);
		timeout = setTimeout(getResults, 3000);
		function getResults() {
			$('#loadingMessage').collapse('hide');
			$('#successMessage').collapse('show');
		}
	});

	if(window.location.pathname == "booking/delivery"){
		$('#emailDelivery').prop( "checked", true );
	}
});