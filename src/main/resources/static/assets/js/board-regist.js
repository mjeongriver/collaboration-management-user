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
	
	let writerName = $('span[name="writer"]').text();

	});
});   
    
//file 담아줄 배열 선언
let filesTempArr = [];

$(document).ready(function() {
  
// 파일 추가
  function addFiles(files) {
    $.each(files, function(i, file) {
      let reader = new FileReader();
      reader.onload = function() {
		//파일 주소
        //console.log(reader.result);
        filesTempArr.push(reader.result);
      };
      reader.readAsDataURL(file);
    });
  }
  
// 파일 선택 시 이벤트 처리
  $('input[name="fileUpload"]').on('change', function() {
    let files = $(this).prop('files');
    addFiles(files);
  });
});

//작성 완료 버튼 누를 시 ajax로 데이터 넘김
$("#boardSuccess").click(function(event) {
	//기본으로 정의된 이벤트를 작동하지 못하게 함, 즉 submit을 막는다.
	event.preventDefault();
    
    let urlParams = new URLSearchParams(window.location.search);
    let selectedCategory = $("#categorySelect option:selected").val();
    let selectedProcess = $("#processSelect option:selected").val();
    let writer = $('span[name="writer"]').text();
    let writerId = $('span[name="writer"]').attr('id');
    let boardTitle = $("input[name='boardTitle']").val();
    let startDate = $("input[name='startDate']").val();
    let endDate = $("input[name='endDate']").val();
    let description = $("textarea[name='description']").val();
    
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
	
	if( description == "") {
		$('#contentWarning').text("세부 내용을 입력해주세요.")
		$('#contentWarning').show();
		return false;
	} else {
		$('#contentWarning').hide();
	}
    
	let formData = {
		selectedCategory: selectedCategory,
		selectedProcess: selectedProcess,
		writer: writer,
		writerId: writerId,
		boardTitle: boardTitle,
		startDate: startDate,
		endDate: endDate,
		description: description,
		pjNum: urlParams.get('pj_num'),
		fileUpload: filesTempArr
	}
	
	//ajax로 글 등록하기
	$("#boardSuccess").prop("disabled", true);
	
	$.ajax({
		type: "POST",
		url:"../reg-board",
		data: JSON.stringify(formData),
		processData: false,
		contentType: "application/json",
		cache: false,
		success: function (data) {
			alert("등록이 완료되었습니다.")
			location.href = "/userboards/board-list?pj_num=" + urlParams.get('pj_num');
		},
		error: function(e){
			console.log("ERROR: ", e);
			//버튼 사용 불가
			$("#boardSuccess").prop("disabled", false);
			alert("fail");
		}
	})
});

