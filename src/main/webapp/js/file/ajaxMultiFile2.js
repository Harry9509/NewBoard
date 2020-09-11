var fileCnt =0;
var fileMap = new Map();
var type = '';
var br_uid = 0;

$(document).ready(function(){ 

	br_uid = $('#uid').val();
	type = $('#type').val();
	
	if (br_uid != 0) {
		$.ajax({
			url : '/Board/file/ajax/selectlist.do?uid=' + br_uid,
			type : 'GET',
			dataType: 'json',
			cache : false,
			success : function(data, status) {
				if (status == 'success') {
					var size = data.size;
					var fileList = data.list;
					
					for (var file of fileList){
						appendFileRow(file, type);
						if (isImage(file)) appendImagePreview(file); // 만일

						fileMap.set(file.f_uid, file);
					}
					
					changeTotFileSize(calcTotSize());
					fileCnt = size;
				};
			}
		});
	}
});

$(document).on('change', '#fileUpload', function(e){
	var formData = new FormData();
	formData.append('file', e.target.files[0]);
	
	$.ajax({
		url : '/Board/file/ajax/upload.do',
		data : formData,
		processData : false,
		contentType : false,
		type : 'POST',
		dataType : 'JSON',
		async : false,
		success : function(data, status){
			if(status == 'success'){
				appendFileRow(data, type);
				if (isImage(data)) appendImagePreview(data);
				
				fileCnt++;
				fileMap.set(data.f_uid, data);
				changeTotFileSize(calcTotSize());
				$(e.target).val('');
			}
		}
		
	});
});

function deleteFileData(f_uid){
	var result = confirm ('파일을 삭제하시겠습니까?')
	if(result){
		$.ajax({
			url : '/Board/file/ajax/delete.do',
			data : {
				uid :f_uid
			},
			type : 'POST',
			cache: false,
			success : function(data, status){
				if(status =='success'){
					$('#fileList_'+f_uid).remove();
					$('#img_'+f_uid).remove();
					
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

function appendFileRow(file, type){
	
	var html = '';
	
		if(type == 'view'){
			html += '<tr id = "file_'+file.f_uid+'">';
			html += '<td><a href = "/Board/file/download.do?br_uid='+br_uid+'&f_uid='+file.f_uid+'">'
					+file.originalName+'('+file.parseSize+')'+'</a></td>';
			html += '<td>'+'<input type = "hidden" name ="fileUids" value = "'+file.f_uid+'">'+'</td>'
			html += '</tr>';
			
		}else{
			html += '<tr id ="fileList_'+file.f_uid+'">';
			html += '<td>'+file.originalName+'('+file.parseSize+')'+'</td>'
			html += '<td>'+'<button type = "button" class = "deleteBtn" onclick= "deleteFileData('+file.f_uid+')">Delete</button>'+'</td>';
			html += '<td>'+'<input type = "hidden" name = "fileUids" value = "'+file.f_uid+'">'+'</td>'
			html += '</tr>';
		}
	$('#fileTable').append(html);
	
}

function appendImagePreview(file){
	var html = '';
	html += <div id = img_file.f_uid>;
	html += <img src = "/file.realPath "width="100" height="100">;
	html += '</div>';
	
	$('#imgPreview').append(html);
}






























