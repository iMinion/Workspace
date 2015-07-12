/**
 * 
 */function validateForm() {
	var fname = document.formname.fname;
	var lname = document.formname.lname;
	var uname = document.formname.uname;
	var pwd = document.formname.password;
	var rpwd = document.formname.repassword;
	var day = document.formname.day;
	var month = document.formname.month;
	var year = document.formname.year;
	var address = document.formname.address;
	var gender = document.formname.gender;
	var email = document.formname.email;
	var	secret = document.formname.secret;
	var answer = document.formname.answer;

	if(validfname(fname) && validuname(uname) && validpwd(pwd) && validrpwd(rpwd) && validemail(email) && validsecret(secret) && validanswer(answer) ) {
		return true;
	}
	return false;
}

function validfname(fname) {
	var letters = /^[A-Za-z]+$/
	var fname_len = fname.value.length;
	if(fname_len == 0) {
		alert("First name should not be empty");
		fname.focus();
		return false;
	}
	else if (!fname.value.match(letters)) {
		alert("First name contains only letters");
		fname.focus();
		return false;
	}
	return true;
}

function validuname(uname) {
	var uname_len = uname.value.length;
	var letters=/^[A-Za-z0-9]+$/
	if(uname_len==0) {
		alert("Username should not be empty and must be in between 6 and 12 characters");
		uname.focus();
		return false;
	}
	else if(!(uname_len>=6 && uname_len<=12)) {
		alert("Username must be in between 6 and 12 characters");
		uname.focus();
		return false;
	}
	return true;
}

function validpwd(pwd) {
	var letters = /^[A-Za-z0-9]+$/
	if(pwd.value.length==0) {
		alert("password should not be empty and must be a minimum of 8 characters");
		pwd.focus();
		return false;
	}
	else if(pwd.value.length < 8) {
		alert("password must be of 8 characters atleast");
		pwd.focus();
		return false;
	}
	else if(!pwd.value.match(letters)) {
		alert("password must have atleast 1 Upper case letter, 1 lower case letter and 1 digit");
		pwd.focus();
		return false;
	}
	return true;
}

function validrpwd(rpwd) {
	if(rpwd.value.length==0) {
		alert("please re-enter password");
		rpwd.focus();
		return false;
	}
	else if(!rpwd.value.match(pwd)) {
		alert("passwords did not match");
		rpwd.focus();
		return false;
	}
	return true;
}


function validemail(email) {
	if(email.value.length == 0) {
		alert("email could not be empty");
		email.focus();
		return false;
	}
	var emf=/^[A-Za-z0-9._]*\@[A-Za-z]*\.[a-z]{2,5}+$/;
	if(!email.value.match(emf)) {
		alert("Please enter a valid email id");
		email.focus();
		return false;
	}
	return true;
}

function validsecret(secret) {
	if(secret.value == 0) {
		alert("Please select a question");
		secret.focus();
		return false;
	}
	return true;
}

function validanswer(answer) {
	if(answer.value.length == 0) {
		alert("Please enter the question");
		answer.focus();
		return false;
	}
	return true;
}