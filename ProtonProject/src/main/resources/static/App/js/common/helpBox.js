var helpBox = {
	getHelp: function (url) {
		$.ajax({
			method: "GET",
			url: "/help/getHelp",
			data: {
				requestUrl: url
			}
		}).done(function (data) {

			if (data != null) {
				bootbox.dialog({
					message: data.helpText,
					title: data.helpName
				})
			} else {
				bootbox.dialog({
					message: "Sorry, but help not find",
					title: "Not find"
				})
			}
		})
	},

	getHelpId: function (id) {
		$.ajax({
			method: "GET",
			url: "/help/getHelpId",
			data: {
				helpId: id
			}
		}).done(function (data) {

			if (data != null) {
				bootbox.dialog({
					message: data.helpText,
					title: data.helpName
				})
			} else {
				bootbox.dialog({
					message: "Sorry, but help not find",
					title: "Not find"
				})
			}
		})
	},

	getHelpReport: function (reportId) {
		$.ajax({
			url: "/help/getHelpReport",
			method: "GET",
			data: {
				reportId: reportId
			}
		}).done(function (data) {

			if (data != null) {
				bootbox.dialog({
					message: data.helpText,
					title: data.helpName
				})
			} else {
				bootbox.dialog({
					message: "Sorry, but help not find",
					title: "Not find"
				})
			}
		})
	},

	getHelpConstructor: function (constrId) {
		$.ajax({
			url: "/help/getHelpCustRep",
			method: "GET",
			data: {
				constrId: constrId
			}
		}).done(function (data) {

			if (data != null) {
				bootbox.dialog({
					message: data.helpText,
					title: data.helpName
				})
			} else {
				bootbox.dialog({
					message: "Sorry, but help not find",
					title: "Not find"
				})
			}
		})
	},

	drawHelp: function (data) {
		if (data != null) {
			bootbox.dialog({
				message: data.helpText,
				title: data.helpName,
				size: 'large'
			})
		} else {
			bootbox.dialog({
				message: "Sorry, but help not find",
				title: "Not find"
			})
		}
	},

	showDraft: function (title, message) {
		if (title != null && message != null) {
			bootbox.dialog({
				message: message,
				title: title,
				size: 'large'
			})
		} else {
			$.growl({ title: "Hey!", message: "You did not write anything!" })
		}
	},

	get: function () {
	}
}