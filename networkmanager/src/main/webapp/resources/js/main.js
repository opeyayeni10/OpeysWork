var rootUrl = "http://localhost:8080/NetworkManager/rest/invalidevents";

var findAll = function() {
	$.ajax({
		type : 'GET',
		url : rootUrl,
		dataType : "json",
		success : buildTable
	});
};

$.formattedDate = function(dateToFormat) {
	var dateObject = new Date(dateToFormat);
	var second = dateObject.getSeconds();
	var minute = dateObject.getMinutes();
	var hour = dateObject.getHours();
	var day = dateObject.getDate();
	var month = dateObject.getMonth() + 1;
	var year = dateObject.getFullYear();
	day = day < 10 ? "0" + day : day;
	month = month < 10 ? "0" + month : month;
	var formattedDate = day + "/" + month + "/" + year + " " + hour + ":"
			+ minute + ":" + second;
	return formattedDate;
};

var buildTable = function(data) {
	$.each(data, function(index, base) {
		$('#errorRecordsTable').append(
				'<tr><td>' + $.formattedDate(base.date_time) + '</td>' + '<td>'
						+ base.event_id + '</td>' + '<td>' + base.failure_class
						+ '</td>' + '<td>' + base.ue_type + '</td>' + '<td>'
						+ base.market + '</td>' + '<td>' + base.operator
						+ '</td>' + '<td>' + base.cell_id + '</td>' + '<td>'
						+ base.duration + '</td>' + '<td>' + base.cause_code
						+ '</td>' + '<td>' + base.ne_version + '</td>' + '<td>'
						+ base.imsi + '</td>' + '<td>' + base.hier3_id
						+ '</td>' + '<td>' + base.hier32_id + '</td>' + '<td>'
						+ base.hier321_id + '</td></tr>');
	});
}

$(document).ready(function() {
	findAll();
});