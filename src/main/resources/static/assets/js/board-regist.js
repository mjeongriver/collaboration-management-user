//summernote editor
$(document).ready(function() {
	$('#summernote').summernote({
		height: 270,
		minHeight: null,
		maxHeight: null,
		focus: true
	});

});

//파일 이름 목록에 추가 
$(document).ready(function() {
	$('#formFile').on('change', function() {
		let files = $(this).prop('files');
		let fileNames = "";
		for (var i = 0; i < files.length; i++) {
			fileNames += "<li>" + files[i].name + "</li>";
		}
		$('#fileNames').html("<ul style='margin-top: 10px;'>" + fileNames + "</ul>");
	});
});   
    
//작성 완료시 유효성 검사, ajax로 데이터 보내기
function boardSuccess() {
	let selectedCategory = $("#categorySelect option:selected").val();
    let selectedProcess = $("#processSelect option:selected").val();
    let writerId = $('span[name="writer"]').attr('id');
    let boardTitle = $("input[name='boardTitle']").val();
    //let fileUpload = $("input[name='fileUpload']").val();
    let startDate = $("input[name='startDate']").val();
    let endDate = $("input[name='endDate']").val();
    //let description = $("textarea[name='description']").val();
    
    //제목-유효성 검사
    if(boardTitle.trim() === ""){
		$('#titleWarning').text("제목을 입력해주세요").show();
		//selectedCategory.focus();
		return false;
	} else {
		$('#titleWarning').hide();
	}
	
	//시작일-유효성 검사
	if(startDate == ""){
	$('#startWarning').text("시작일을 입력해주세요");
		//startDate.focus();
		return false;
	} else {
		$('#startWarning').hide();
	}
	
	//종료일-유효성 검사
	if(endDate == ""){
		$('#endWarning').text("종료일을 입력해주세요");
		//endDate.focus();
		return false;
	} else {
		$('#endWarning').hide();
	}
	
	//시작일 > 종료일-유효성 검사
	if (startDate > endDate) {
		$('#endWarning').text("종료일은 시작일보다 작을 수 없습니다.");
		$('#endWarning').show();
		startDate.focus();
		endDate.focus();
		return false;
	}
	
	let objParams = {
		"selectedCategory": selectedCategory,
		"selectedProcess": selectedProcess,
		"writerId": writerId,
		"boardTitle": boardTitle,
		//"fileUpload": fileUpload,
		"startDate": startDate,
		"endDate": endDate,
		//"description": description
	}
	
	$.ajax({
		url: "../reg-board",
		type: "post",
		async: false,
		data: JSON.stringify(objParams),
		dataType: "json",
		contentType: "application/json",
		success: function(result) {
			alert(result.msg);
			location.href = "/";
		},
		error: function(xhr, status, error) {
    	console.error(xhr);
    	console.error(status);
    	console.error(error);
    	alert("에러가 발생했습니다.");
}
	});
	
}