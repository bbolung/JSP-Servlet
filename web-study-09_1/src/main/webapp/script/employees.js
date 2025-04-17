function loginCheck(){
	if(document.frm.userid.value.length == 0){
		alert("아이디를 입력해주세요.");
		frm.userid.focus();
		return false;
	}
	
	if(document.frm.pwd.value == ""){
			alert("암호를 입력 해주세요");
			frm.pwd.focus();
			return false;
	}
	
	return true;
}

function updateCheck(){
	if(document.frm.pwd.value == ""){
		alert("암호는 반드시 입력해야 합니다.");
		frm.pwd.value.focus();
		return false;
	}
	
	if(document.frm.name.value.length == 0){
		alert("이름을 작성해주세요.");
		frm.name.value.focus();
		return false;
	}
}

function idCheck(){
	if(document.frm.userid.value == ""){
			alert("아이디를 입력해주세요.");
			document.frm.userid.focus();
			return false;
		}
		
	let url = "idCheck.do?userid=" + document.frm.userid.value;
	
	window.open(url, "_blank_1", "toolbar=no, menubar=no, scrollbars=yes, resizable=no, width=450, height=200");
}

function idok(){
	opener.frm.userid.value = document.frm.userid.value;
	opener.frm.reid.value = document.frm.userid.value;
	self.close();
}

function joinCheck(){
	if(document.frm.userid.value.length == 0){
			alert("아이디를 입력해주세요.");
			frm.userid.focus();
			return false;
		}
		
	
	if(document.frm.pwd.value == ""){
		alert("암호는 반드시 입력해야 합니다.");
		frm.pwd.focus();
		return false;
	}
	
	if(document.frm.name.value.length == 0){
		alert("이름을 입력해주세요.");
		frm.name.focus();
		return false;
	}
}