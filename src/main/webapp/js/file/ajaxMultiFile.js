
var fileCnt = 0;

var fileMap =  new Map();

var type = '';

var br_uid = 0;

// set default variable 
$(document).ready(function(){ 
	//위의 녀석은 $(function(){
	//		과 같다.				});
	br_uid = $('#uid').val();
	type = $('#type').val();
	
	// not create
	// select file List such br_uid
	if (br_uid != 0) {
		$.ajax({
			url : '/Board/file/ajax/selectlist.do?uid=' + br_uid,
			// http://localhost:8080/Board/file/ajax/selectlist.do?uid=4 //json
			// view을 통해서, 뭐가 넘어오는지 알 수 있다.
			type : 'GET',
			dataType: 'json',
			cache : false,
			success : function(data, status) {
				if (status == 'success') {
					var size = data.size;
					var fileList = data.list;
					
					// test code
					// alert(fileList[0].f_uid);
					// set fileList Table
					
					for (var file of fileList){
						appendFileRow(file, type);
						if (isImage(file)) appendImagePreview(file); // 만일
																		// isImage가
																		// 있으면,
																		// appendimagepreview를
																		// 실행시켜라.
						fileMap.set(file.f_uid, file);
					}
					
				// for (var i = 0; i < size; i++) {
					// appendFileRow(fileList[i], type);
						// if(isImage[i]) appendImagePreview([i]);
						 // fileSizeMap.set(fileList[i].f_uid,
							// fileList[i].size);
					// set fileTotSize
					changeTotFileSize(calcTotSize());
					// set fileCnt
					fileCnt = size;
				};
			}
		});
	}
	// test code
	// alert(fileCnt + " : " + calcTotSize())
});

// ajax upload file
$(document).on('change', '#fileUpload', function(e){
	var formData = new FormData();
	formData.append('file', e.target.files[0]);
	
	$.ajax({
		url : '/Board/file/ajax/upload.do',
		data : formData,
		processData : false,
		contentType : false,
		type : 'POST',
		dataType : 'json',
		async: false,
		success : function(data, status) {
			// test code
			// alert(data.f_uid + " | " + data.originalName);
			// append file row
			if (status == 'success') {
				appendFileRow(data, type);
				
				if (isImage(data)) appendImagePreview(data);
				
				fileCnt++;
				fileMap.set(data.f_uid, data);
				changeTotFileSize(calcTotSize());
				
				$(e.target).val(''); 
				// 이게 뭐지 ==>function(e)의 target은
				// #fileUpload이니, 그것의 value를 말하는것인가.
			}
		}
	});
});

// ajax delete file
function deleteFileData(f_uid) {
	var result = confirm('파일을 삭제 하시겠습니까?')
	if(result){
	$.ajax({
		url : '/Board/file/ajax/delete.do',
		data : {
			uid : f_uid
		},
		type : 'POST',
		cache: false,
		// dataType : 'json' .... json type return 이 아님.
		success : function(data, status) {
			// test code
			// alert(data);
			// delete row
			if (status == 'success') {
				$('#fileList_' + f_uid).remove();
				$('#img_' + f_uid).remove();
				
				fileCnt--;
				fileMap.delete(f_uid);
				changeTotFileSize(calcTotSize());
			}
		}
	});	
}else{
	return;
}
}

// append file Row to HTML
function appendFileRow(file, type) {
	
	// test code
	// alert('appendFileRow' + file.f_uid);
	
	var html = '';
	
	if (type == 'view') {
		html += '<tr id="fileList_' + file.f_uid + '">';
		html += '<td><a href="/Board/file/download.do?br_uid='+ br_uid +'&f_uid=' + file.f_uid + '">' + file.originalName + ' (' + file.parseSize + ')' + '</a></td>';
		// html += '<td>' + '<button type="button" class="deleteBtn"
		// onclick="deleteFileData(' + file.f_uid + ')">Delete</button>'
		// +'</td>';
		html += '<td>' + '<input type="hidden" name="fileUids" value="' + file.f_uid + '">' + '</td>'
		html += '</tr>';
	} else {
		html += '<tr id="fileList_' + file.f_uid + '">';
		html += '<td>' + file.originalName + ' (' + file.parseSize + ')' + '</td>';
		html += '<td>' + '<button type="button" class="deleteBtn" onclick="deleteFileData(' + file.f_uid + ')">Delete</button>' + '</td>';
		html += '<td>' + '<input type="hidden" name="fileUids" value="' + file.f_uid + '">' + '</td>'
		html += '</tr>';
	}
	
 $('#fileTalbe').append(html);
}


function appendImagePreview(file){
	var html = '';
	html += '<div id="img_' + file.f_uid + '">';
	html += '<img src="/' + file.realPath + '"width="100" height="100">';
	html += '</div>';
	
	$('#imgPreview').append(html);
	
}

// chana totFileSize HTML
function changeTotFileSize(size) {
	$('#fileTotSize').html('Total File Size : ' + returnFileSize(size));
}

// calctotSize
function calcTotSize() {
	var tot = 0;
	for (let file of fileMap.values()) { // /key는 f_uid, value file object
		// fileMap은, arrary로 되어잇는 file
		// map.values() files 안에있는 사이즈를 얻기위해, file.size로 작성.
		tot += file.size;
	}
	return tot;
}

// byte to KB or MB
function returnFileSize(size) {
	kb = 1024;
	mb = 1048576;
	
	if (size < 1024) {
		return size + "bytes";
	} else if (size >= kb && size < mb) {
		return (size/kb).toFixed(1) + "KB";
	} else if (size >= mb) {
		return (size/mb).toFixed(1) + "MB";
	}
}
function isImage(file){ // 이미지인가? 이미지면 true 이미지가 아니면 false
	var name = file.originalName;
	var image = ['jpg', 'png', 'gif', 'bmp'];
	var ext = name.slice(name.indexOf('.') + 1).toLowerCase(); // 파일명에서 점부터 끝까지
																// 가져와서 ,그게
																// image 에 있냐
	for( var e of image){ // 있으면 return true 없으며 false
		if(ext == e) return true;
	}
	return false;
}
