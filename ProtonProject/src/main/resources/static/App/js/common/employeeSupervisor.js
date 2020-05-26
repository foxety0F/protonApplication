var EmployeeSupervisor = {
	addNewUser: function () {
		var alphaUsersSelector = '<select id = "alphaUsersSelector" data-live-search="true" onchange="EmployeeSupervisor.searchInfo()">'
		for (var i in alphaUsers) {
			alphaUsersSelector += '<option value = "' + alphaUsers[i].userId + '">' + alphaUsers[i].login + '</option>';
		}
		alphaUsersSelector += '</select>';

		var groupSelector = "<select id = 'groupSelector' data-live-search='true'>";

		for (var i in employeesGroups) {
			groupSelector += '<option value = "' + employeesGroups[i].idGroup + '"> ' + employeesGroups[i].nameGroup + '</option>'
		}

		groupSelector += "</select>";

		var titleSelector = "<select id = 'titleSelector' data-live-search = 'true'>";

		for (var i in employeeTitles) {
			titleSelector += '<option value = "' + employeeTitles[i].titleId + '">' + employeeTitles[i].titleName + '</option>'
		}

		titleSelector += "</select>";

		var message = `<table style = "border : 0px">
						<tr>
							<td> Select user : </td>
							<td> ` + alphaUsersSelector + `</td>
						</tr>
						<tr>
							<td> First Name : </td>
							<td> <input id = "firstName" value = "` + alphaUsers[0].firstName + `" disabled class = "form-control"/></td>
						</tr>
						<tr>
							<td> Surname : </td>
							<td> <input id="surname" value = "` + alphaUsers[0].surname + `" disabled class = "form-control"/></td>
						</tr>
						<tr>
							<td> Login : </td>
							<td> <input id = "login" value = "` + alphaUsers[0].login + `" disabled class = "form-control"/></td>
						</tr>
						<tr>
							<td> Group : </td>
							<td> ` + groupSelector + ` </td>
						</tr>
						<tr>
							<td> Title : </td>
							<td> ` + titleSelector + `</td>
						</tr>
						<tr>
							<td> Hired date : </td>
							<td> <input id = "hiredDate" type = "date" value = "" class = "form-control" /></td>
						</tr>
						<tr>
							<td> PC Number : </td>
							<td> <input id = "pcNumber" value = "" class = "form-control" /></td>
						</tr>
						<tr>
							<td> Place : </td>
							<td> <input id = "placeNumber" value = "" class = "form-control" /></td>
						</tr>
						<tr>
							<td> IP Address </td>
							<td> <input id = "ipAddress" value = "" class = "form-control" /></td>
						</tr>
					</table>`;

		bootbox.confirm({
			title: "Add new user",
			message: message,
			size: 'large',

			buttons: {
				confirm: {
					label: "Add",
					className: "btn-success"
				},
				cancel: {
					label: "Cancel",
					className: "btn-danger"
				}
			},

			callback: function (result) {
				if (result) {
					if ($('#selectUser').val() == "") {
						$.growl.error({ title: "Oops!", message: "User can not be empty" });
					} else {
						$.ajax({
							url: "/employees/setNewEmployee",
							data: {
								idUser: parseInt($('#alphaUsersSelector').val()),
								login: $('#login').val(),
								idGroup: $('#groupSelector').val(),
								titleId: $('#titleSelector').val(),
								pcNumber: $('#pcNumber').val(),
								placeNumber: $('#placeNumber').val(),
								ipAddress: $('#ipAddress').val(),
								startDate: new Date($('#hiredDate').val())
							}
						}).done(function (data) {
							$.growl({ title: "Add new user", message: data })
						})
					}
				}
			}
		})

		document.getElementById('hiredDate').valueAsDate = new Date();

		$('#alphaUsersSelector').selectpicker();
		$('#groupSelector').selectpicker();
		$('#titleSelector').selectpicker();

	},

	addNewRow: function (node) {
		console.log(node)
		var employeeId = node.dataset.userid;
		var lastPcName, lastIpAddress, lastDate, lastGroupName, lastTitleName, login, firstName, surname, lastPlace;

		for (var i in employee) {
			if (employee[i].idEmployee == employeeId && employee[i].isActualRow == true) {
				lastPcName = employee[i].pcNumber;
				lastIpAddress = employee[i].ipAddress;
				lastDate = employee[i].startDate;
				lastGroupName = employee[i].groupName;
				lastTitleName = employee[i].titleName;
				login = employee[i].login;
				firstName = employee[i].firstName;
				surname = employee[i].surname;
				lastPlace = employee[i].placeNumber;
			}
		}

		var groupSelector = "<select id = 'groupSelector' data-live-search='true'>";

		for (var i in employeesGroups) {
			var ch = "";

			if (employeesGroups[i].nameGroup == lastGroupName) {
				ch = "checked";
			}

			groupSelector += '<option value = "' + employeesGroups[i].idGroup + '" ' + ch + '> ' + employeesGroups[i].nameGroup + '</option>'
		}

		groupSelector += "</select>";

		var titleSelector = "<select id = 'titleSelector' data-live-search = 'true'>";

		for (var i in employeeTitles) {
			var ch = "";

			if (employeeTitles[i].titleName == lastTitleName) {
				ch = "checked";
			}

			titleSelector += '<option value = "' + employeeTitles[i].titleId + '" ' + ch + '>' + employeeTitles[i].titleName + '</option>'
		}

		titleSelector += "</select>";

		var message = `<table>
							<tr>
								<td> Login : </td>
								<td> <input value = ` + login + ` class = "form-control" disabled /> </td>
							</tr>
							<tr>
								<td> First Name : </td>
								<td> <input id = "firstName" class = "form-control" value = "` + firstName + `" disabled /> </td>
							</tr>
							<tr>
								<td> Surname : </td>
								<td> <input id = "surname" class = "form-control" value = "` + surname + `" disabled /> </td>
							</tr>
							<tr>
								<td> Group Name : </td>
								<td> ` + groupSelector + `</td>
							</tr>
							<tr>
								<td> Title Name : </td>
								<td> ` + titleSelector + `</td>
							</tr>
							<tr>
								<td> PC Number : </td>
								<td> <input id = "pcNumber" class = "form-control" value = "` + lastPcName + `" /> </td>
							</tr>
							<tr>
								<td> Place Number : </td>
								<td> <input id = "placeNumber" class = "form-control" value = "` + lastPlace + `" /> </td>
							</tr>
							<tr>
								<td> IP address : </td>
								<td> <input id = "ipAddress" class = "form-control" value = "` + lastIpAddress + `" /> </td>
							</tr>
							<tr>
								<td> Start Date : </td>
								<td> <input type="date" id = "startDate" data-minval="` + lastDate + `" onchange="EmployeeSupervisor.checkDate(this)" /> </td>
							</tr>`

		bootbox.confirm({
			message: message,
			title: "Add new row for " + login,
			size: 'large',

			buttons: {
				confirm: {
					label: "Add new row",
					className: "btn-success"
				},
				cancel: {
					label: "Cancel",
					className: "btn-danger"
				}
			},

			callback: function (result) {
				if (result) {
					$.ajax({
						url : "/employee/updateEmployee",
						data : {
							employeeId : employeeId,
							idGroup : $('#groupSelector').val(),
							titleId : $('#titleSelector').val(),
							pcNumber : $('#pcNumber').val(),
							placeNumber : $('#placeNumber').val(),
							ipNumber : $('#ipAddress').val(),
							startDate : new Date($('#startDate').val())
						}
					}).done(function(result){
						$.growl({
							message : result,
							title : "User updated"
						});
					}).fail(function(result){
						$.growl({
							title : "Error",
							message : result.responseText
						})
					})
				}
			}
		})

		document.getElementById('startDate').valueAsDate = new Date();
	},

	searchInfo: function () {
		var userId = $('#alphaUsersSelector').val();
		for (var i in alphaUsers) {
			if (alphaUsers[i].userId == userId) {
				$('#firstName')[0].value = alphaUsers[i].firstName;
				$('#surname')[0].value = alphaUsers[i].surname;
				$('#login')[0].value = alphaUsers[i].login;
			}
		}
	},

	addNewGroup: function () {
		var msg = `<div>
					<span onclick = "EmployeeSupervisor.visibleGroups()" id = "showGropus" data-show = "false">Show all groups</span>`
		var tbl = `<table id = "groups" style = "display : none">
					<thead>
						<tr>
							<th>Group ID</th>
							<th>Group Name </th>
							<th>Group Description</th>
						</tr>
					</thead>
					<tbody>`;

		var ex = false;
		for (var i in employeesGroups) {
			ex = true;
			tbl += `<tr><td>` + employeesGroups[i].idGroup
				+ `</td><td>` + employeesGroups[i].nameGroup
				+ `</td><td>` + employeesGroups[i].description
				+ `</td></tr>`;
		}

		tbl += `</tbody></table>`;

		if (ex) {
			msg += tbl;
		} else {
			msg += `<div style = "display : none" id = "groups">Current group size is 0</div>`;
		}

		msg += `<table style = "border : 0px">
					<tr>
						<td> Group Name : </td>
						<td> <input id = "groupName" /> </td>
					</tr>
					<tr>
						<td> Group description : </td>
						<td> <input id = "groupDescription"/></td>
					</tr>
				</table>`

		msg += `</div>`;

		bootbox.confirm({
			title: 'Add new Group',
			message: msg,

			buttons: {
				confirm: {
					label: "Add",
					className: "btn-success"
				},
				cancel: {
					label: "Cancel",
					className: "btn-danger"
				}
			},

			callback: function (result) {
				if (result) {
					var groupName = $('#groupName').val();
					if ($('#groupName').val() == "") {
						$.growl.error({ title: "Oops!", message: "Name group can not be empty!" })
					} else {

						$.ajax({
							url: "/employees/setNewGroup",
							data: {
								name: $('#groupName').val(),
								description: $('#groupDescription').val()
							}
						}).done(function (data) {
							$.growl({ title: "Group was be add", message: "You add new group - <b>" + groupName + "</b>" });
							$.ajax({
								url: "/employees/getGroups"
							}).done(function (data) {
								employeesGroups = data;
							})
						})
					}

				}
			}
		})
	},

	addNewTitle: function () {

		var msg = `<div>
					<span onclick = "EmployeeSupervisor.visibleTitles()" id = "showTitle" data-show = "false">Show all titles</span>`
		var tbl = `<table id = "titles" style = "display : none">
					<thead>
						<tr>
							<th>Title ID</th>
							<th>Title Name </th>
							<th>Title Description</th>
						</tr>
					</thead>
					<tbody>`;

		var ex = false;
		for (var i in employeeTitles) {
			ex = true;
			tbl += `<tr><td>` + employeeTitles[i].titleId
				+ `</td><td>` + employeeTitles[i].nameTitle
				+ `</td><td>` + employeeTitles[i].description
				+ `</td></tr>`;
		}

		tbl += `</tbody></table>`;

		if (ex) {
			msg += tbl;
		} else {
			msg += `<div style = "display : none" id = "titles">Current group size is 0</div>`;
		}

		msg += `<table style = "border : 0px">
					<tr>
						<td> Title Name : </td>
						<td> <input id = "titleName" /> </td>
					</tr>
					<tr>
						<td> Title description : </td>
						<td> <input id = "titleDescription"/></td>
					</tr>
				</table>`

		msg += `</div>`;

		bootbox.confirm({
			title: 'Add new Title',
			message: msg,

			buttons: {
				confirm: {
					label: "Add",
					className: "btn-success"
				},
				cancel: {
					label: "Cancel",
					className: "btn-danger"
				}
			},

			callback: function (result) {
				if (result) {
					var titleName = $('#titleName').val();
					if ($('#titleName').val() == "") {
						$.growl.error({ title: "Oops!", message: "Title name can not be empty!" })
					} else {

						$.ajax({
							url: "/employees/setNewTitle",
							data: {
								name: $('#titleName').val(),
								description: $('#titleDescription').val()
							}
						}).done(function (data) {
							$.growl({ title: "Title was be add", message: "You add new title - <b>" + titleName + "</b>" });
							$.ajax({
								url: "/employees/getTitle"
							}).done(function (data) {
								employeeTitles = data;
							})
						})
					}

				}
			}
		})

	},

	visibleGroups: function () {
		var isShow = $('#showGropus')[0].dataset.show;

		if (isShow) {
			$('#showGropus')[0].dataset.show = "true";
			$('#groups').show();
		} else {
			$('#showGropus')[0].dataset.show = "false";
			$('#groups').hide();
		}
	},

	visibleTitles: function () {
		var isShow = $('#showTitle')[0].dataset.show;

		if (isShow) {
			$('#showTitle')[0].dataset.show = "true";
			$('#titles').show();
		} else {
			$('#showTitle')[0].dataset.show = "false";
			$('#titles').hide();
		}
	},

	checkDate: function (node) {
		console.log(node)
		var currVal = node.value;
		var minVal = node.dataset.minval;

		var dt1 = new Date(currVal);
		var dt2 = new Date(minVal);

		if (dt1 < dt2) {
			document.getElementById('startDate').valueAsDate = new Date(minVal);
			$.growl.error({
				message: "Sorry! But this interval is incorrect",
				title: "Error interval"
			})
		}

	}
}