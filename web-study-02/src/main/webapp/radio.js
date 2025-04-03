function check() {
	if(document.frm.gender.value == "") {
		alert("성별을 선택해주세요.");
		return false;
	}else if(document.frm.chk_mail.value == "") {
		alert("메일 정보 수신 여부를 선택해주세요.");
		return false;
	}else if(document.frm.content.value == "") {
		alert("한 글자 이상 입력해주세요.");
		return false;
	}else {
		return true;
	}
}