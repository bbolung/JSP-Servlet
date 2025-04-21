function productCheck(){
	if(document.frm.name.value.length == 0){
		alert("상품명을 입력해주세요.");
		frm.name.focus();
		return false;
	}
	
	if(document.frm.price.value.length == 0){
		alert("가격을 입력해주세요.");
		frm.price.focus();
		return false;
	}
	
	//isNaN : Not a Number 숫자가 아닌 값
	if(isNaN(document.frm.price.value)){
		alert("숫자를 입력해야 합니다.");
		frm.price.focus();
		return false;
	}
	return true;
}

function chkDelete(){
	if(confirm("해당 상품을 삭제하시겠습니까?")){
		return true;	
	}
	else{
		return false;
	}
}