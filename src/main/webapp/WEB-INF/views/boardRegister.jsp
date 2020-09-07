<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator"
	uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	/**
	* @Class Name : egovSampleRegister.jsp
	* @Description : Sample Register 화면
	* @Modification Information
	*
	*   수정일         수정자                   수정내용
	*  -------    --------    ---------------------------
	*  2009.02.01            최초 생성
	*
	* author 실행환경 개발팀
	* since 2009.02.01
	*
	* Copyright (C) 2009 by MOPAS  All right reserved.
	*/
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><c:if test="${type == 'create'}">Board 작성</c:if><c:if
		test="${type =='modify'}">Board 수정 </c:if><c:if
		test="${type =='view'}">
		<c:out value="${boardVO.subject}" />
	</c:if></title>
<link type="text/css" rel="stylesheet"
	href="<c:url value='/css/sample.css'/>" />
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>

<script type="text/javaScript" language="javascript" defer="defer">
	function fn_egov_selectList() {
		document.detailForm.action = "<c:url value = '/board.do'/>";
		document.detailForm.submit();
	}
	function fn_egov_modify() {
		document.detailForm.action = "<c:url value ='/updateView.do'/>";
		document.detailForm.submit();
		
	}
	function fn_egov_delete(){
		document.detailForm.action = "<c:url value = '/deleteBoard.do'/>"
		document.detailForm.submit();
	}
	function fn_egov_save(){
		frm = document.detailForm;
		frm.action = "<c:url value ="${type == 'create' ? '/addBoard.do' : '/updateBoard.do'}"/>";
		frm.submit();
	}
</script>
<!-- <script type="text/javascript" src="<c:url value='/js/file/multiFile.js'/>" ></script> -->
<script type="text/javascript"
	src="<c:url value='/js/file/ajaxMultiFile.js'/>"></script>
<!-- 여러 파일 업로드 -->

</head>
<body
	style="text-align: center; margin: 0 auto; display: inline; padding-top: 100px;">
	<form id = "detailForm" name = "detailForm" method = "post">
		<input type = "hidden" id = "type" value 
	
	
	
	
	
	
	</form>
	
</body>
</html>