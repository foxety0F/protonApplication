var Education = {

	drawGroupedTable: function (groupField) {
		$('#fltTable tbody').empty();

		var distinctFilteredColumn = [];

		for (var i = 0; i < tempTestData.length; i++) {
			var exist = false;
			for (var j in distinctFilteredColumn) {
				if (j == tempTestData[i][groupField]) {
					exist = true;
					distinctFilteredColumn[j] = distinctFilteredColumn[j] + 1;
				}
			}

			if (!exist) {
				distinctFilteredColumn[tempTestData[i][groupField]] = 1;
			}
		}
		
		for(var i in distinctFilteredColumn){
			$('#fltTable thead').children()[0].innerText = groupField;
			$('#fltTable tbody').append('<tr><td>' + i + '</td><td>' + distinctFilteredColumn[i] + '</td></tr>')
		}
	}
}