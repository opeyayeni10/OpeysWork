var rootURL = "http://localhost:8080/WebTech/rest";

$(document).ready(function() {
	
	$("#workArea").hide();
	$("#loginmodal").modal("show");
	
	
	$('[data-toggle="tooltip"]').tooltip();
	findAllUsers();
	findAllBooks();

	$(document).on("click", "#booksTable tr[role='row']", function() {
		initialiseBookForm();
		$('#myModal').modal('toggle');
		getBookById($(this).children('td:first-child').text());
	});

	$(document).on("click", "#usersTableBody tr[role='row']", function() {
		initialiseUserForm();
		replaceUserTypeFunctionBack();
		replacePasswordFunctionBack();
		$('#myModal').modal('toggle');
		getUserById($(this).children('td:first-child').text());
	});

	$(document).on("click", "#updateBook", function() {
		console.log("click update book");
		updateBook(this.id);

	});

	$(document).on("click", "#newBook", function() {
		$('#bookId').val(null);
		$('#isbn').val("");
		$('#bookName').val("");
		$('#author').val("");
		$('#category').val("");
		$('#year').val("");
		$('#publisher').val("");

		$('#addBook').show();
		$('#editBook').hide();
		$('#deleteBook').hide();
		$('#newBook').hide();
		$('#updateBook').hide();
		$('#myModal h4').html('Add Book');
		$('#bookForm input').prop('disabled', false);
		$('#bookId').prop('disabled', true);
	});

	$(document).on("click", "#editBook", function() {

		$('#addBook').hide();
		$('#editBook').hide();
		$('#deleteBook').hide();
		$('#newBook').hide();
		$('#updateBook').show();
		$('#myModal h4').html('Book Editor');
		$('#bookForm input').prop('disabled', false);
		$('#bookId').prop('disabled', true);
	});

	$(document).on("click", "#addBook", function() {
		console.log("addbook here")
		createBook();

	});

	$(document).on("click", "#deleteBook", function() {
		if (confirm('Are you sure you want to delete this Book?')) {
			removeBook($("#bookId").val())
		}
	});
});

$(document).on("click", "#loginBtn", function() {
	loginUser();
	console.log("clicked");
});

function loginUser() {
	$.ajax({
		type : "POST",
		contentType : 'application/json',
		url : rootURL + "/users/login",
		datatype : "json",
		data : formToJSONLogin(),
		success : function(data, textStatus, jqXHR) {
			console.log(data);
			$("#loginmodal").modal("hide");
			$("#workArea").show();
			
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log(formToJSONLogin());
			alert("Incorrect Login details");
		}
	});
};

var formToJSONLogin = function() {
	return JSON.stringify({
		"userName" : $('#usernameEG').val(),
		"password" : $('#passwordEG').val(),
	});
};

$(document).on("click", "#newUser", function() {
	
	$('#userId').val(null);
	$('#firstName').val("");
	$('#lastName').val("");
	$('#userName').val("");
	$('#userType').val("");
	$('#password').val("");
	$('#picture').attr('src', 'resources/images/pictureR.jpg');
	replaceUserTypeFunction();
	replacePasswordFunction();

	$('#addUser').show();
	$('#editUser').hide();
	$('#deleteUser').hide();
	$('#newUser').hide();
	$('#updateUser').hide();
	$('#myModal h4').html('Add User');
	$('#userForm input').prop('disabled', false);
	$('#userId').prop('disabled', true);
});

$(document).on("click", "#addUser", function() {
	console.log("addUser here")
	createUser();

});

$(document).on("click", "#editUser", function() {

	$('#addUser').hide();
	$('#editUser').hide();
	$('#deleteUser').hide();
	$('#newUser').hide();
	$('#updateUser').show();
	$('#myModal h4').html('Edit User');
	$('#userForm input').prop('disabled', false);
	$('#userId').prop('disabled', true);
});

$(document).on("click", "#updateUser", function(){
	updateUser(this.id);
})

$(document).on("click", "#deleteUser", function() {
		if (confirm('Are you sure you want to delete this User?')) {
			removeUser($("#userId").val())
		}
});


var findAllUsers = function() {
	$.ajax({
		type : 'GET',
		url : rootURL + '/users',
		dataType : "json",
		success : function(data) {
			usersTable(data);
			usersCount(data);
		}
	});
};
var findAllBooks = function() {
	$.ajax({
		type : 'GET',
		url : rootURL + '/books',
		dataType : "json",
		success : function(data) {
			booksTable(data);
			booksCount(data);
		}
	});
};

function getBookById(id) {
	$.ajax({
		type : 'GET',
		url : rootURL + '/books/' + id,
		dataType : 'json',
		success : function(data, textStatus, jqXHR) {
			$('#bookId').val(data.bookId);
			$('#isbn').val(data.isbn);
			$('#bookName').val(data.bookName);
			$('#author').val(data.author);
			$('#category').val(data.bookCategory);
			$('#year').val(data.yearPublished);
			$('#publisher').val(data.publisher);
		}
	})
}

function getUserById(id) {
	$.ajax({
		type : 'GET',
		url : rootURL + '/users/' + id,
		dataType : 'json',
		success : function(data, textStatus, jqXHR) {
			$('#picture').attr('src', 'resources/images/' + data.picture);
			$('#userId').val(data.userId);
			$('#firstName').val(data.firstName);
			$('#lastName').val(data.lastName);
			$('#userName').val(data.userName);
			$('#userType').val(data.userType);
			$('#password').val(data.password);

		}
	})
}

function usersTable(data) {
	$("#usersTableBody").DataTable({
		"data" : data,
		"columns" : [ {
			"data" : "userId"
		}, {
			"data" : "firstName"
		}, {
			"data" : "lastName"
		}, {
			"data" : "userName"
		}, {
			"data" : "userType"
		}, ],
		"bDestroy" : true
	});
}

function booksTable(data) {
	$("#booksTable").DataTable({
		"data" : data,
		"columns" : [ {
			"data" : "bookId"
		}, {
			"data" : "bookName"
		}, {
			"data" : "author"
		}, {
			"data" : "yearPublished"
		}, {
			"defaultContent" : "<button>Info</button>"
		}, ],
		"bDestroy" : true
	});
}

function removeBook(id) {
	console.log(id)
	$.ajax({
		type : 'DELETE',
		url : rootURL + '/books/' + id,
		dataType : "json",
		success : function(data, textStatus, jqXHR) {
			$('#myModal').modal('toggle');

			findAllBooks();
		}
	});
}

function createBook() {
	console.log("here createbook")
	$.ajax({
		type : 'POST',
		url : rootURL + "/books",
		dataType : "json",
		contentType : 'application/json',
		data : createBookJSON2(),
		success : function(data, textStatus, jqXHR) {
			$('#myModal').modal('toggle');
			findAllBooks();
		}
	});
}

function createUser() {
	console.log("here createUser")
	$.ajax({
		type : 'POST',
		url : rootURL + "/users",
		dataType : "json",
		contentType : 'application/json',
		data : createUserJSON(),
		success : function(data, textStatus, jqXHR) {
			$('#myModal').modal('toggle');
			findAllUsers();
		}
	});
}

function initialiseBookForm() {
	$('#addBook').hide();
	$('#editBook').show();
	$('#deleteBook').show();
	$('#newBook').show();
	$('#updateBook').hide();

	$('#bookFormFooter').show();
	$('#bookForm').show();
	$('#userFormFooter').hide();
	$('#userForm').hide();

	$('#myModal h4').html('Book Details');
	$('#bookForm input').prop('disabled', true);
}

function initialiseUserForm() {
	$('#addUser').hide();
	$('#editUser').show();
	$('#deleteUser').show();
	$('#newUser').show();
	$('#updateUser').hide();

	$('#userFormFooter').show();
	$('#userForm').show();
	$('#bookFormFooter').hide();
	$('#bookForm').hide();

	$('#myModal h4').html('User Details');
	$('#userForm input').prop('disabled', true);
}

function booksCount(data) {
	$("#totalBooks").text(data.length);
}

function usersCount(data) {
	$("#totalUsers").text(data.length);
}

var createBookJSON = function() {
	return JSON.stringify({
		"bookId" : $('#bookId').val(),
		"isbn" : $('#isbn').val(),
		"bookName" : $('#bookName').val(),
		"author" : $('#author').val(),
		"bookCategory" : $('#category').val(),
		"yearPublished" : $('#year').val(),
		"publisher" : $('#publisher').val()
	});
	console.log(data);
};

var createBookJSON2 = function() {
	return JSON.stringify({

		"bookId" : $('#bookId').val(),
		"isbn" : $('#isbn').val(),
		"bookName" : $('#bookName').val(),
		"author" : $('#author').val(),
		"bookCategory" : $('#category').val(),
		"yearPublished" : $('#year').val(),
		"publisher" : $('#publisher').val()
	});
	console.log("here");
};

var createUserJSON = function() {
	return JSON.stringify({

		"userId" : $('#userId').val(),
		"firstName" : $('#firstName').val(),
		"lastName" : $('#lastName').val(),
		"userName" : $('#userName').val(),
		"userType" : $('#userType').val(),
		"password" : $('#Password').val(),
		"picture" : $('#picture').val()
	});
	console.log("here");
};

function updateBook(data) {
	console.log(data);
	console.log("here");
	$.ajax({
		type : 'PUT',
		url : rootURL + '/books/' + $('#bookId').val(),
		contentType : 'application/json',
		dataType : "json",
		data : createBookJSON(),
		success : function(data, textStatus, jqXHR) {
			console.log(data);
			$('#myModal').modal('toggle');

			findAllBooks();
		}
	});
}

function updateUser(data) {
	console.log(data);
	console.log("here");
	$.ajax({
		type : 'PUT',
		url : rootURL + '/users/' + $('#userId').val(),
		contentType : 'application/json',
		dataType : "json",
		data : createUserJSON(),
		success : function(data, textStatus, jqXHR) {
			console.log(data);
			$('#myModal').modal('toggle');

			findAllUsers();
		}
	});
}

function removeUser(id) {
	console.log(id)
	$.ajax({
		type : 'DELETE',
		url : rootURL + '/users/' + id,
		dataType : "json",
		success : function(data, textStatus, jqXHR) {
			$('#myModal').modal('toggle');

			findAllUsers();
		}
	});
}

function replaceUserTypeFunction() {
	$('#userType')
			.replaceWith(
					'<select name= "selectUserType" id= "userType" class="form-control" style= "height: 43px;"> <option value= "Admin" >Admin</option> <option value= "User">User</option> </select>');
}

function replaceUserTypeFunctionBack() {
	$('#userType')
			.replaceWith(
					'<input id="userType" type="text" required class="form-control" placeholder="User Type" disabled>');
}

function replacePasswordFunction() {
	$('#Password')
			.replaceWith('<input id="Password" type="text" required class="form-control" placeholder="Password" disabled>');
}

function replacePasswordFunctionBack() {
	$('#Password')
			.replaceWith('<input id="Password" type="password" required class="form-control" placeholder="Password" disabled>');
}