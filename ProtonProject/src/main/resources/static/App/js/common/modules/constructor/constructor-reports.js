var reportConstructor = {
	runQuery : function(){
		var init;
		var initColumns = [];

		for(var i = 0; i < columnList.length; i++){
			initColumns.push({
				id : columnList[i].columnId,
				customName : columnList[i].customName,
				columnAvailParam : parseInt(columnList[i].columnParameterSelect)
			});
		}

		init = {
			guid : reportConstructor.guid(),
			thread : parseInt(thread),
			columns : initColumns
		};

		$.ajax({
			url : 'makeConstructor/createReport',
			data : {
				columns : JSON.stringify(init)
			}
		}).done(function(data){
			console.log(data)
		})
	},
	makeReportColumnStepInit: function () {
		thread = $('#threadSelector option:selected').val();

		$.ajax({
			url: 'makeConstructor/getRelationsAndTables',
			data: {
				threadId: thread
			},
			method: 'GET'
		}).done(function (data) {
			tables = data;
			$('#step-one').hide();
			$('#step-two').show();
			$('#step-head-two').addClass('active-step');
			$('#step-head-three').addClass('available-step');


			var columns = "";
			for (var i = 0; i < data.tables.length; i++) {
				columns += '<div data-tableid="' + data.tables[i].id + '">' + '<i class="fas fa-angle-down" data-hidetable="' + data.tables[i].id + '" onclick = "reportConstructor.hideShowTableColumns(this)"></i>'
					+ '<span class = "steps-head-table-name" data-hidetable="' + data.tables[i].id + '">'
					+ (data.tables[i].businessName == null ? data.tables[i].tableName : data.tables[i].businessName)
					+ '</span>';
				for (var ii = 0; ii < data.tables[i].columns.length; ii++) {
					columns += '<p onclick = "reportConstructor.modifyColumn(this)" class = "steps-head-column" data-targettable = "' + data.tables[i].id + '" data-columnid = "' + data.tables[i].columns[ii].id + '">'
						+ (data.tables[i].columns[ii].businessName == null ? data.tables[i].columns[ii].columnName : data.tables[i].columns[ii].businessName)
						+ '</p>'
				}

				columns += '</div>';
			}
			$('#step-two-columns').append(columns)

			var conditions = "";
			for (var i = 0; i < data.tables.length; i++) {
				conditions += '<div data-tableid="' + data.tables[i].id + '">' + '<i class="fas fa-angle-down" data-hidetable="' + data.tables[i].id + '" onclick = "reportConstructor.hideShowTableColumns(this)"></i>'
					+ '<span class = "steps-head-table-name" data-hidetable="' + data.tables[i].id + '">'
					+ (data.tables[i].businessName == null ? data.tables[i].tableName : data.tables[i].businessName)
					+ '</span>';
				for (var ii = 0; ii < data.tables[i].columns.length; ii++) {
					conditions += '<p onclick = "reportConstructor.modifyColumn(this)" class = "steps-head-column" data-targettable = "' + data.tables[i].id + '" data-columnid = "' + data.tables[i].columns[ii].id + '">'
						+ (data.tables[i].columns[ii].businessName == null ? data.tables[i].columns[ii].columnName : data.tables[i].columns[ii].businessName)
						+ '</p>'
				}

				conditions += '</div>';
			}
			$('#step-three-columns').append(conditions)
			$('#step-head-two').css('cursor', 'pointer');
			$('#step-head-three').css('cursor', 'pointer');
			$('#step-head-three').on('click', function(){
				$('#step-two').hide();
				$('#step-four').hide();
				$('#step-three').show();
				$('#step-head-three').removeClass('available-step').addClass('active-step');
				$('#step-head-two').removeClass('active-step').addClass('available-step');
			});

			$('#step-head-two').on('click', function(){
				$('#step-three').hide();
				$('#step-four').hide();
				$('#step-two').show();
				$('#step-head-two').removeClass('available-step').addClass('active-step');
				$('#step-head-three').removeClass('active-step').addClass('available-step');
			});

			$('#step-head-four').addClass('result-step').css('cursor', 'pointer');
			$('#step-head-four').on('click', function(){
				$('#step-three').hide();
				$('#step-two').hide();
				$('#step-four').show();
			})
		}).fail(function (e) {
			$.growl.error({ title: 'Error', message: e.responseText })
		});
	},

	hideShowTableColumns: function (node) {
		if ($(node).hasClass('fa-angle-down')) {
			$('[data-targettable=' + node.dataset.hidetable + ']').hide('fast');
			$(node).removeClass('fa-angle-down').addClass('fa-angle-right')
		} else {
			$('[data-targettable=' + node.dataset.hidetable + ']').show('fast');
			$(node).removeClass('fa-angle-right').addClass('fa-angle-down')
		}

	},

	guid: function () {
		function s4() {
			return Math.floor((1 + Math.random()) * 0x10000)
				.toString(16)
				.substring(1);
		}
		return s4() + s4() + '-' + s4() + '-' + s4() + '-' +
			s4() + '-' + s4() + s4() + s4();
	},

	modifyColumn: function (node) {
		var canEdit = false;

		if (columnConfigurationEdit) {
			bootbox.confirm("You don't save changes. Press OK for continue", function (result) {
				console.log(result)
				if (result) {
					canEdit = true;
					reportConstructor.drawColumn(node);
				}
			})
		} else {
			canEdit = true;
		}
		columnConfigurationEdit = true;


		if (canEdit) {
			reportConstructor.drawColumn(node);
		}
	},

	drawColumn: function (node) {

		var table = node.dataset.targettable;
		var column = node.dataset.columnid;
		var tableName;
		var guid = reportConstructor.guid();
		var currCol = null;

		for (var i = 0; i < tables.tables.length; i++) {
			if (table == tables.tables[i].id) {
				tableName = (tables.tables[i].businessName == null ? tables.tables[i].tableName : tables.tables[i].businessName);
				for (var j = 0; j < tables.tables[i].columns.length; j++) {
					if (column == tables.tables[i].columns[j].id) {
						currCol = tables.tables[i].columns[j];
					}
				}
			}
		}

		console.log(currCol);
		$('#step-two-working-area-add-column').empty();
		var text = "";
		text += `<div class = "text-input-group">
					<div class = "column-information-head">Column information</div>
					<hr>
					<div> <span class = "column-info">Column name : </span> <span id = "column-info-column-name">` + (currCol.businessName == null ? currCol.columnName : currCol.businessName) + `</span></div>
					<div> <span class = "column-info">Table name : </span> ` + tableName + `</div>
				</div>`;
		text += `<div class = "text-input-group">
					<div class = "column-information-head">
						Parameters
					</div>
					<hr/>
					<div class="input-group">
						<span class="input-group-addon">Custom Name</span>
						<input id = "customName" type="text" class="form-control" value = "" placeholder="Write your custom name">
					</div>
				</div>`;
		
		text += '<br/><select id = "availColumnParametersSelector">'
		
		for(var i = 0; i < currCol.availColumnParameters.length; i++){
			text += '<option value = "' + currCol.availColumnParameters[i].id + '">' + currCol.availColumnParameters[i].title + '</option>';
		}

		text += '</select><br><br/>';
		text += '<button onclick="reportConstructor.columnAddToList(this)" data-guid = "' + guid + '" data-columnid = ' + currCol.id +' class = "btn btn-primary pull-right"> Add column </button>'

		$('#step-two-working-area-add-column').append(text);
		$('#availColumnParametersSelector').selectpicker();
		
	},

	columnAddToList : function(node){
		console.log(node);
		columnList.push({
			columnId : node.dataset.columnid,
			guid : node.dataset.guid,
			columnParameterSelect : $('#availColumnParametersSelector option:selected').val(),
			customName : $('#customName').val()
		});

		$('#step-two-working-area-list').append('<li class = "steps-head-column-list" data-guid = "' + node.dataset.guid + '">' + ($('#customName').val() == "" ? $('#column-info-column-name').text() : $('#customName').val()) + '</li>');
		$('#step-two-working-area-add-column').empty();
		columnConfigurationEdit = false;
	}
}