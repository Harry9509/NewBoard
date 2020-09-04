
$(document).ready(function(){
	$('#submitBtn').on('click', function(e) {
		login();
		
		alert('AJAX 되는지 확인')
		var data = $('#LoginForm').serializeArray();
		
		console.log(data);
		
		var dataObj = {};
		
		console.log(dataObj);
		
		for (var item of data) {
			dataObj[item.name] = item.value;
		}
		// test
		// for (var item in dataObj) {
		// }
		$.ajax({
			url : '/Board/loginCK.do',
			data : dataObj,
			type : 'POST',
			cache: false,	
			success : function(data, status) {
				if (data == 'success') {
					alert('로그인에 성공하였습니다.')
					location.href = '/Board/board.do';
				} else {
					alert("아이디가 없거나, 비밀번호가 일치 하지 않습니다.");
				}
			}
		});
	});

	
});

function login(){
    var id = $("#userId");
    var pw = $("#password");
    
    if(id.val() == ""){
        alert("아이디를 입력 해주세요.");
        id.focus();
    }
    
    if(pw.val() == ""){
        alert("비밀번호를 입력 해주세요.");
        pw.focus();
    }
    
    // rsa 암호화
    var rsa = new RSAKey();
    rsa.setPublic($('#RSAModulus').val(),$('#RSAExponent').val());
    
    $("#USER_ID").val(rsa.encrypt(id.val()));
    $("#USER_PW").val(rsa.encrypt(pw.val()));
    
    id.val("");
    pw.val("");

}


