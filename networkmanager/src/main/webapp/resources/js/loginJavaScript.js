var rootUrl = "http://localhost:8080/NetworkManager";

$(document).ready(function() {
	checkAdmin();
});

$("#loginBtn").click(function() {
	loginUser($('#userName').val(), $('#password').val());

});

function checkAdmin() {
	$.ajax({
		type : 'GET',
		url : "http://localhost:8080/NetworkManager/rest/users",
		datatype : "json",
		success : function(data) {
			if (data.length == 0) {
				$('#registerModal').modal('show');
			} else {
				$('#loginModal').modal('show');
			}
		}
	});
}

var formToJSONLogin = function() {
	return JSON.stringify({
		"id" : $('#userId').val(),
		"name" : $('#userName').val(),
		"password" : $('#password').val(),
		"type" : $('#userType').val(),
	});
};

var formToJSONRegister = function() {
	return JSON.stringify({
		"id" : $('#userId').val(),
		"name" : $('#exampleInputEmail1').val(),
		"password" : $('#exampleInputPassword1').val(),
		"type" : $('#userType').val(),
	});
};

var renderDetails = function(user) {
	$('#userId').val(user.id);
	$("#userName").val(user.name);
	$("#password").val(user.password);
	$("#userType").val(user.type);

}

var renderList = function(data) {
	$.each(data, function(index, user) {
		$('#userList').append('<li><a href="#" id="' + user.id + '">' + user.name + '</a></li>');
	});
};

var loginUser = function(userName, password) {
	console.log("in login user");
	var newURL = "http://localhost:8080/NetworkManager/rest/users/userLogin/"
			+ $("#userName").val() + "+" + $("#password").val();
	$.ajax({
		type : 'GET',
		dataType : 'text',
		url : newURL,
		success : loginOK,
		error : function(jqXHR, textStatus, errorThrown) {
			alert("Error in loginUser()")
		}
	});
}

var loginOK = function(data) {
	console.log("in login ok");
	console.log(data);
	var userType = data;
	if (userType == '0') {
		alert("Login denied");
	}
	if (userType == 'ADMIN') {
		console.log("data type is 1");
		alert("You made it");
	}
}

var findAll = function() {
	$.ajax({
		type : 'GET',
		url : rootUrl,
		dataType : "json",
		success : renderList
	});
}

var userAvailability = function(){
	var username = $('#userName').val();
};

function isUserAvailable(){
	var userName = document.getElementById('userName').value;
	$.ajax({
		type: 'POST',
		contentType : 'application/json',
		url : rootUrl +"/availability",
		dataType : "text",
		data : formToJSONRegister(),
		success : function(data, textStatus, jqXHR) {
			console.log(data);
			
			if(data == true){
				addUser();
				}
			else if(data == false){
				alert('Username already exisits in database');
			}
		}, 
		error: function(jqXHR, textStatus, errorThrown){
			alert("error in checking user");
		}
});
};
	
var addUser = function() {
	console.log('addUser');
	$.ajax({
		type : 'POST',
		contentType : 'application/json',
		url : rootUrl + "/rest/users",
		dataType : "json",
		data : formToJSONRegister(),
		success : function(data, textStatus, jqXHR) {
			alert("User added to database");
			$('#userId').val(data.id);
			findAll();
		},
		error : function(jgXHR, textStatus, errorThrown) {
			alert('addUser error: ' + textStatus);
		}
	});
};

$(document).on("click", "#registerUser", function() {
			if ($('#exampleInputEmail1').val() == "") {
				alert('Invalid user name');
			} else if ($('#exampleInputPassword1').val() <= 1
					&& $('#exampleInputPassword1').val() >= 3) {
				alert('Invalid password must be 6 characters or more');
			} else if ($('#userType').val() == '') {
				alert('Invalid userType');
			} else {
				addUser();
			}
		});


