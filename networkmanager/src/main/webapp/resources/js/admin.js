var rootUrl = "http://localhost:8080/NetworkManager/admin.html";

$(document).ready(function() {
	
});

$(document).on("click", "#createUser", function() {
	
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

var findAll = function() {
	$.ajax({
		type : 'GET',
		url : rootUrl,
		dataType : "json",
		success : renderList
	});
}

$("#importBtn").click(function() {
	importData();
});

var renderList = function(data) {
	$.each(data, function(index, user) {
		$('#userList').append('<li><a href="#" id="' + user.name + '">'+user.name +'</a></li>');
	});
};

var formToJSONRegister = function() {
	return JSON.stringify({
		"name" : $('#exampleInputEmail1').val(),
		"password" : $('#exampleInputPassword1').val(),
		"type" : $('#userType').val(),
	});
};


var addUser = function() {
	console.log(formToJSONRegister());
	$.ajax({
		type : 'POST',
		contentType : 'application/json',
		url : "http://localhost:8080/NetworkManager/rest/users",
		dataType : "json",
		data : formToJSONRegister(),
		success : function(data, textStatus, jqXHR) {
			alert("User added to database");
			findAll();
		},
		error : function(jgXHR, textStatus, errorThrown) {
			alert('addUser error: ' + textStatus);
		}
	});
}

function importData(){
	$.ajax({
		type : 'GET',
		url : "http://localhost:8080/NetworkManager/rest/import",
		datatype : "json",
		success : function() {
			alert("Import successful");
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert("error in importing data");
		}
	});	
}

var userAvailability = function(){
	var username = $('#exampleInputEmail1').val();
};

function isUserAvailable(){
	var userName = document.getElementById('exampleInputEmail1').value;
	$.ajax({
		type: 'POST',
		contentType : 'application/json',
		url : "http://localhost:8080/NetworkManager/rest/users/availability",
		dataType : "text",
		data : formToJSONRegister(),
		success : function(data, textStatus, jqXHR) {
			console.log(data);
			
	/*		
			if(data == "available"){
				addUser();
				}
			else if(data == "unavailable"){
				alert('Username already exisits in database');*/
			
		}, 
		error: function(jqXHR, textStatus, errorThrown){
			alert("error in checking user");
		}
});
};
