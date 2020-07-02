var rootUrl = "http://localhost:8080/NetworkManager";

$(document).ready(function() {
	checkAdmin();

});

$("#addUser").click(function() {
	loginUser();
});

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
})


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
		"name" : $('#userName').val(),
		"password" : $('#password').val(),	
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



var findAll = function() {
	$.ajax({
		type : 'GET',
		url : rootUrl,
		dataType : "json",
		success : renderList
	});
}

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
}

function loginUser() {
	$.ajax({
		type : "POST",
		contentType : 'application/json',
		url : "http://localhost:8080/NetworkManager/rest/users/login",
		datatype : "json",
		data : formToJSONLogin(),
		success : function(data, textStatus, jqXHR) {
			location.href= "http://localhost:8080/NetworkManager/admin.html";
			
		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert('Error creating user: ' + textStatus);
		}
	});
}
