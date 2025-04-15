function loginCheck(){
	//userid 공백인지 확인
	if(document.frm.userid.value.length == 0){
		alert("아이디 입력 해주세요");
		frm.userid.focus();
		return false;
	}
	//pwd 공백인지 확인
	if(document.frm.pwd.value == ""){
		alert("암호를 입력 해주세요");
		frm.pwd.focus();
		return false;
	}
	
	//userid와 pwd가 공백이 아닐 경우 true 반환
	return true;
}

//userid 중복 체크
function idCheck(){
	if(document.frm.userid.value == ""){
		alert("아이디를 입력하여 주세요.");
		document.frm.userid.focus();
		return false;
	}
	
	//idCheck.do 사이트 찾아가면서 쿼리스트링 형태로 userid 값 가져감(주소창에 나옴) =>  get방식
	let url = "idCheck.do?userid=" + document.frm.userid.value;
	
	//url을 새 창으로 띄움(scrollbar만 허용)
	//toolbar: 단축도구창(toolbar), menubar: 메뉴창, scrollbars : 스크롤바 사용, resizable: 창 크기 변형 여부
	window.open(url, "_blank_1", "toolbar=no, menubar=no, scrollbars=yes, resizable=no, width=450, height=200");
}

//아이디 중복 체크 완료 처리
function idok(){
	opener.frm.userid.value = document.frm.userid.value;
	opener.frm.reid.value = document.frm.userid.value;
	self.close();
}

//회원가입 유효성 체크
function joinCheck(){
	if(document.frm.name.value.length == 0){
		alert("이름을 작성해주세요.");
		frm.name.focus();
		return false;
	}
	
	if(document.frm.userid.value.length == 0){
		alert("아이디를 작성해주세요.");
		frm.userid.focus();
		return false;
	}
	
	if(document.frm.userid.value.length<4){
		alert("아이디는 4글자 이상이어야 합니다.");
		frm.userid.focus();
		return false;
	}
	
	if(document.frm.pwd.value == ""){
		alert("암호는 반드시 입력해야 합니다.");
		frm.pwd.focus();
		return false;
	}
	
	if(document.frm.pwd.value != document.frm.pwd_check.value){
		alert("암호가 일치하지 않습니다.");
		frm.pwd.focus();
		return false;
	}
	
	if(document.frm.reid.value.length == 0){
		alert("중복 체크를 하지 않았습니다.");
		frm.userid.focus();
		return false;
	}
}