//기본 프로필 사진일 경우 다운로드 못하게 하기
	$(document).ready(function() {
		var myProfileImg = document.getElementById("myProfileImg").src;
		var downloadBtn = document.getElementById("downloadBtn");
		
		if(myProfileImg === "https://formmesoftimg.s3.ap-northeast-2.amazonaws.com/defaultImg.jpeg"){ //기본 이미지
			downloadBtn.disabled = true;
			
		}
		
	});


	//프로필 사진 다운로드하기
	function downloadMyProfileImg() {
		var myProfileImg = document.getElementById("myProfileImg").src;
		location.href="../aws/profile-download?myProfileImg=" + myProfileImg;
		/* $.ajax({
			url : "../AWS/profileDownload",
			type : "get",
			async : false,
			data : {
				"myProfileImg" : myProfileImg
			},
			success : function(result) {
				console.log(result);
				
			},
			error : function(err) {
				alert("사진 다운로드에 실패했습니다. 담당자에게 문의하세요.");

			}
			
		}); */
		
	}

	//휴대폰번호에 자동으로 하이픈 붙이기
	function autoHyphen() {
		var phoneNumber = document.getElementById("user_cell");
		phoneNumber.value = phoneNumber.value
							.replace(/[^0-9]/g, '') //숫자가 아닌 글자는 공백으로 바꿔버림
							.replace(/^(\d{2,3})(\d{3,4})(\d{4})$/, `$1-$2-$3`);
	};

	function changeInfo() {
		var birthdayCheck = document.getElementById("user_birth");
		var phoneNumberCheck = document.getElementById("user_cell");

		var birthday = RegExp(/^(19[0-9][0-9]|20\d{2})(0[0-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])$/);
		var phoneNumber = RegExp(/^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$/);

		var changeInfoForm = document.getElementById("changeInfoForm");

		//휴대폰번호 공백 확인
		if (phoneNumberCheck.value == "") {
			alert("핸드폰 번호를 입력해주세요.");
			phoneNumberCheck.focus();
			return false;
		}

		//휴대폰번호 길이 확인
		if (phoneNumberCheck.value.length < 10) {
			alert("핸드폰 번호를 다시 확인해주세요.");
			phoneNumberCheck.focus();
			return false;
		}

		//휴대폰번호 유효성검사
		if (!phoneNumber.test(phoneNumberCheck.value)) {
			alert("핸드폰 번호는 숫자로만 입력해주세요.");
			phoneNumberCheck.value = "";
			phoneNumberCheck.focus();
			return false;
		}
		
		//생년월일 8자인지 확인
		if (birthdayCheck.value.length < 8) {
			alert("생년월일 8자리를 입력하세요.");
			birthdayCheck.value = "";
			birthdayCheck.focus();
			return false;
		}

		//생년월일 유효성 검사
		if (!birthday.test(birthdayCheck.value)) {
			alert("생년월일이 올바르지 않습니다.");
			birthdayCheck.value = "";
			birthdayCheck.focus();
			return false;
		}
		
		// form 전송하기
		changeInfoForm.submit();
		
	}
	
	function changePw(){
		var passCheck = document.getElementById("user_pw");
		var passCheckch = document.getElementById("renewPassword");
		
		var password = RegExp(/^.*(?=^.{8,16}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$/);

		var changePwForm = document.getElementById("changePwForm");
		
		
		//비밀번호 공백 확인
		if (passCheck.value == "") {
			alert("비밀번호를 입력해주세요.");
			passCheck.focus();
			return false;
		}

		//비밀번호 길이 확인
		if (passCheck.value.length < 8) {
			alert("비밀번호가 너무 짧습니다.");
			passCheck.focus();
			return false;
		}

		//비밀번호 유효성검사
		if (!password.test(passCheck.value)) {
			alert("비밀번호는 영문 소문자, 숫자, 특수문자를 각각 1개 이상 조합해주세요.");
			passCheck.value = "";
			passCheck.focus();
			return false;
		}

		//비밀번호 확인란 공백 확인
		if (passCheckch.value == "") {
			alert("비밀번호 확인을 입력해주세요.");
			passCheckch.focus();
			return false;
		}

		//비밀번호 서로확인
		if (passCheck.value != passCheckch.value) {
			alert("비밀번호와 비밀번호 확인이 다릅니다.");
			passCheckch.value = "";
			passCheck.focus();
			return false;
		}
		
		changePwForm.submit();

	}