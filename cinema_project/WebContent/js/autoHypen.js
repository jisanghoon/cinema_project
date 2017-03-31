function chk_tel(str, field) {
	var str;
	str = checkDigit(str);
	len = str.length;
	if (len < 8) {
		return;
	}
	if (len == 8) {
		if (str.substring(0, 2) == 02) {
			error_numbr(str, field);
		} else {
			field.value = phone_format(1, str);
		}
	} else if (len == 9) {
		if (str.substring(0, 2) == 02) {
			field.value = phone_format(2, str);
		} else {
			error_numbr(str, field);
		}
	} else if (len == 10) {
		if (str.substring(0, 2) == 02) {
			field.value = phone_format(2, str);
		} else {
			field.value = phone_format(3, str);
		}
	} else if (len == 11) {
		if (str.substring(0, 2) == 02) {
			error_numbr(str, field);
		} else {
			field.value = phone_format(3, str);
		}
	} else {
		error_numbr(str, field);
	}
}
function checkDigit(num) {
	var Digit = "1234567890";
	var string = num;
	var len = string.length
	var retVal = "";
	for (i = 0; i < len; i++) {
		if (Digit.indexOf(string.substring(i, i + 1)) >= 0) {
			retVal = retVal + string.substring(i, i + 1);
		}
	}
	return retVal;
}
function phone_format(type, num) {
	if (type == 1) {
		return num.replace(/([0-9]{4})([0-9]{4})/, "$1-$2");
	} else if (type == 2) {
		return num.replace(/([0-9]{2})([0-9]+)([0-9]{4})/, "$1-$2-$3");
	} else {
		return num.replace(/(^01.{1}|[0-9]{3})([0-9]+)([0-9]{4})/, "$1-$2-$3");
	}
}
function error_numbr(str, field) {
	alert("정상적인 번호가 아닙니다.");
	field.value = "";
	field.focus();
	return;
}