var rootUrl = "http://localhost:8080/NetworkManager/rest/users";

var findAll = function() {
	$.ajax({
		type : 'GET',
		url : rootUrl,
		dataType : "json",
		success : renderList
	});
};

/*var findById = function(id) {
 $.ajax({
 type : 'GET',
 url : rootUrl + "/" + id,
 dataType : "json",
 success : function(data) {
 $('#btnDelete').show();
 console.log('findById success: ' + data.name);
 currentUser = data;
 renderDetails(currentUser)
 }
 });
 };*/

var formToJson = function() {
	return JSON.stringify({
		"id" : $('#userId').val(),
		"name" : $('#userName').val(),
		"password" : $('#password').val(),
		"type" : $('#userType').val(),
	});
};

var renderDetails = function(user) {
	$("#userId").val(user.id);
	$("#userName").val(user.name);
	$("#password").val(user.password);
	$("#userType").val(user.type);

}

// data contains the json data for all the users as an array of objects
// .each is a jQuery method that iterates through the array list of objects
// each object is placed in user and its attributes can be accessed.
// userList is an id in the HTML page
var renderList = function(data) {
	$.each(data, function(index, user) {
		$('#userList').append(
				'<li><a href="#" id="' + user.id + '">' + user.name
						+ '</a></li>');
	});
};

var role = $('#userType').find(":selected").val(); 


function loginUser() {
	$.ajax({
		type : "POST",
		contentType : 'application/json',
		url : "http://localhost:8080/NetworkManager/rest/users/login",
		datatype : "json",
		data : formToLogJSON(),
		success : function(data, textStatus, jqXHR) {
			alert('login successful ');
			/*
			if (data.length == 0) {
				$('#error').css('color', 'red');
				$("#error").show();
			} else {
				if (data[0].type === "ADMIN") {
					$("#adminControlPannel").show();
				}
				if(data[0].privilege === "SUPPORTENGINEER"){
					$("#supportEngineerControlPannel").show();
				}
				if(data[0].privilege === "CSENGINEER"){
					$("#customerServiceControlPannel").show();
				}
				console.log(data[0].privilege);
				$('#loginModal').modal('hide');
				console.log(data);
			}
			 */},
		error : function(jqXHR, textStatus, errorThrown) {
			alert('Error creating user: ' + textStatus);
		}
	});
};

var addUser = function() {
	console.log('addUser');
	$.ajax({
		type : 'POST',
		contentType : 'application/json',
		url : "http://localhost:8080/NetworkManager/rest/users",
		dataType : "json",
		data : formToJson(),
		success : function(data, textStatus, jqXHR) {

			alert('User added successfully');
			$('#userId').val(data.id);
			findAll();
		},
		error : function(jgXHR, textStatus, errorThrown) {
			alert('addUser error: ' + textStauts);
		}
	});
};

// When the DOM (Document Object Model is loaded, call the findAll function
$(document).ready(function() {
	
});

$(document).on("click", "#createUser", function() {
	if ($('#userName').val() == "") {
		alert('Invalid User name');
	} else if ($('#password').val() <= 5) {
		alert('Invalid Password must be atleast 6 Characters');
	} else if ($('#userType').val() == "") {
		alert('Please select user type');
	} else {
		addUser();
	}
});

