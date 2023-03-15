//카카오 api
$(".kakaoBtn").click(function(){
	var apiKey = "f8a80263c7064b6a299e0d1d596e890a";
	var address = "http://127.0.0.1:8686/user/kakao";
		alert("이메일 수집에 꼭 동의해주세요!");
		
	location.href="https://kauth.kakao.com/oauth/authorize?client_id="+ apiKey +"&redirect_uri="+ address +"&response_type=code"
})


//회원가입 유효성 검사
function loginCheck() {

	var idCheck = document.getElementById("user_id");
	var passCheck = document.getElementById("user_pw");

	var id = RegExp(/^[a-zA-Z0-9]{4,12}$/);
	var password = RegExp(/^.*(?=^.{8,16}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$/);

	var idWarning = document.getElementById("idWarning");
	var pwWarning = document.getElementById("pwWarning");

	var loginForm = document.getElementById("Login");

	
	idWarning.innerHTML = "";
	pwWarning.innerHTML = "";

	//아이디 공백 확인
	if (idCheck.value == "") {
		idWarning.innerHTML = "아이디를 입력해주세요.";
		idCheck.focus();
		return false;
	}

	//아이디 최소 길이 확인
	if (idCheck.value.length < 4) {
		idWarning.innerHTML = "아이디는 4자 이상입니다.";
		idCheck.value = "";
		idCheck.focus();
		return false;
	}
	
	//아이디 최대 길이 확인
	if (idCheck.value.length > 12) {
		idWarning.innerHTML = "아이디는 12자 이하입니다.";
		idCheck.value = "";
		idCheck.focus();
		return false;
	}
	
	//아이디 유효성검사
	if (!id.test(idCheck.value)) {
		idWarning.innerHTML = "형식에 맞게 입력해주세요";
		idCheck.value = "";
		idCheck.focus();
		return false;
	}
	
	//비밀번호 공백 확인
	if (passCheck.value == "") {
		pwWarning.innerHTML = "비밀번호를 입력해주세요";
		passCheck.focus();
		return false;
	}

	//비밀번호 길이 확인
	if (passCheck.value.length < 8) {
		pwWarning.innerHTML = "비밀번호가 너무 짧습니다.";
		passCheck.focus();
		return false;
	}

	
	// form 전송하기
	loginForm.submit();
}