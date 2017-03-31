$(function() {
	slide('#sel_date');

});

function slide(id) {
	var i = 0;
	var isMovable = true;

	$(id + ' .slidebar_item ol').width(
			$(id + ' .slidebar_item li').width()
					* $(id + ' .slidebar_item li').length);

	var view_area = parseInt($(id + ' .slidebar_item').width())
			- parseInt($(id + ' .slidebar_item ol').width());

	$(id + ' .slidebar_control .prev').on('click', function() {
		var item_left = $(id + ' .slidebar_item ol').css('left');
		var move_width = $(id + ' .slidebar_item li').width();
		if (parseInt(item_left) >= 0) {
			$(id + ' .slidebar_item ol').css('left', '0px');
		} else {
			if (isMovable) {
				isMovable = false;
				$(id + ' .slidebar_item ol').animate({
					left : '+=' + move_width + 'px'
				}, 'fast', function() {
					isMovable = true;
				});
			}
		}

	});

	$(id + ' .slidebar_control .next').click(function() {

		var item_left = $(id + ' .slidebar_item ol').css('left');
		var move_width = $(id + ' .slidebar_item li').width();

		if (parseInt(item_left) <= view_area) {
			$(id + ' .slidebar_item ol').css('left', view_area);
		} else {
			if (isMovable) {
				isMovable = false;
				$(id + ' .slidebar_item ol').animate({
					left : '-=' + move_width + 'px'
				}, 'fast', function() {
					isMovable = true;
				});
			}
		}
	});
}