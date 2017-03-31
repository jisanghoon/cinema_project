/**
 * 
 */

var drawTable = function(temp) {
	var data = temp;
	$('.seat_table').empty()

	for (var i = 0; i < data.length + 1; i++) {
		$('.seat_table').append('<tr></tr>');

		for (var j = 0; j < data[0].length + 1; j++) {

			if (i === 0 && j === 0) {
				$('.seat_table tr').last().append('<td class="leftTop"></td>')
			} else if (i === 0 || j === 0) {
				var header;
				if (i === 0) header = '<td class="col_header"><input type="checkbox"></td>'
				else header = '<td class="row_header"><input type="checkbox"></td>'
				$('.seat_table tr').last().append(header)
			}

			else {
				var text = data[i - 1][j - 1].name === 'empty' ? '' : data[i - 1][j - 1].name;
				var className = data[i - 1][j - 1].name === 'empty' ? 'empty' : '';
				var data_row
				$('.seat_table tr').last()
						.append('<td class="seat">' + text + '</td>')
				$('.seat_table tr').last().find('td').last()
						.addClass(className);
			}
		}
	}// end of method ==> for (var i = 0; i < data.length + 1; i++)

	$('.empty').css('background', '#221938');

	$(".col_header input").change(function() {

		makeCopyData(data);

		if ($(this).is(":checked")) {

			var colnum = $(this).parent().index();

			for (var i = 0; i < data.length; i++) {
				var json = new Object();
				json.name = 'empty';
				data[i].splice(colnum - 1, 0, json)
				for (var j = 0; j < data[0].length; j++) {
					data[i][j].row = i + 1;
					data[i][j].col = j + 1;
				}
			}

			drawTable(data);
		}
	})// end of method ==> $(".col_header input").change

	$(".row_header input").change(function() {
		makeCopyData(data);
		var rownum = $(this).parent().parent().index();
		var colCntPerRow = data[0].length;

		data.splice(rownum - 1, 0, new Array())

		for (var j = 0; j < colCntPerRow; j++) {
			var json = new Object();

			json.name = 'empty';

			data[rownum - 1][j] = json;
		}

		for (var i = 0; i < data.length; i++) {
			for (var k = 0; k < colCntPerRow; k++) {
				data[i][k].row = i + 1;
				data[i][k].col = k + 1;
			}
		}

		drawTable(data);

	})// end of method ==> $(".row_header input").change

	$('.seat').click(function() {
		var originText = $(this).text();
		makeCopyData(data)
		var c_idx = $(this).index();
		var r_idx = $(this).parent().index();

		var rowname = '';
		var colname = '';

		if ($(this).hasClass('empty')) {

			// 행 이름 찾기
			var tdCnt = $(this).parent().find('td').length;

			for (var i = 0; i < tdCnt; i++) {
				var seatRow = $(this).parent().find('td').eq(i).text();

				if (seatRow != '') {
					rowname = seatRow.slice(0, 1);
					break;
				}
			}

			// 열 이름 찾기
			var trCnt = $(this).parent().parent().find('tr').length;

			for (var j = 0; j < trCnt; j++) {
				var seatCol = $(this).parent().parent().find('tr').eq(i)
						.find('td').eq(c_idx).text();
				if (seatCol != '') {
					var temptext = seatCol.split('-')
					/* colname=temptext[temptext.length-1]; */
					colname = temptext[temptext.length - 1]
					break;
				}
			}
			data[r_idx - 1][c_idx - 1].name = rowname + "-" + colname;

		} else {
			data[r_idx - 1][c_idx - 1].name = 'empty';
		}
		drawTable(data);
	})// end of method ==> $('.seat').click

	// 마우스를 좌석 위에 올려놓으면 글자 표시
	$(".seat").hover(function() {
		var seatName = $(this).text();
		if (seatName != '') {
			var txt = seatName.split('-');
			$(".rowName").text(txt[0] + " 행 : ");
			$(".colName").text(txt[1] + " 열");
		}
	})// end of method ===> $(".seat").hover

}// end of method = > drawTable()
