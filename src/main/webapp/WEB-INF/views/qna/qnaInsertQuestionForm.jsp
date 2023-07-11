<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- qnaInsertQuestionForm.jsp -->
<%@ include file="../common/common_top.jsp" %>
<style type="text/css">
	.err {
		font-weight: bold;
		color: red;
		font-sizr:7px;
	}
</style>
    <!-- Page Header Start -->
    <div class="container-fluid page-header py-5 mb-5 wow fadeIn" data-wow-delay="0.1s">
        <div class="container text-center py-5">
            <h1 class="display-3 text-white mb-4 animated slideInDown">Q&A</h1>
        </div>
    </div>
    <!-- Page Header End -->


    <!-- qna Insert Form Start -->
    <div class="container-fluid py-5">
        <div class="container">
        	<!-- 타이틀 -->
            <div class="text-center mx-auto" style="max-width: 500px;">
                <h1 class="display-5 mb-5">질문등록</h1>
            </div>
            <!-- //타이틀 -->
            
            <!-- 폼 -->
            <form:form commandName="qnaBean" action="/qna/user/insertQuestion.qna" method="post" enctype="multipart/form-data">
            <div class="row justify-content-center">
                <div class="col-lg-7">
                    <div class="bg-light rounded p-4 p-sm-5">
                        <div class="row g-3" style="margin-bottom:-10px;">
                        	
                        	<!-- 제목 입력 -->
                        	<div class="col-6" style="float: left; ">
	                                <div class="form-floating">
	                                    <input type="text" class="form-control border-0" id="qnasubject" name="qnasubject" value="${qnaBean.qnasubject}">
	                                    <label for="qnasubject">질문 제목</label>
	                                </div>
	                                <form:errors cssClass="err" path="qnasubject"/>
	                        </div>
                        	<!-- //제목 입력 -->
                        	
                        	<!-- 카테고리 입력 -->
                            <div class="col-sm-3" style="margin-bottom:-10px;">
                                	<div class="form-floating">
                                	<select name="qnacategory" class="form-select border-0">
                                		<option value="">선택</option>
	    								<option value="계정" <c:if test="${qnaBean.qnacategory=='계정'}">selected</c:if>>계정</option>
					    				<option value="크루" <c:if test="${qnaBean.qnacategory=='크루'}">selected</c:if>>크루</option>
					    				<option value="게시판" <c:if test="${qnaBean.qnacategory=='게시판'}">selected</c:if>>게시판</option>
					    				<option value="거래/나눔" <c:if test="${qnaBean.qnacategory=='거래/나눔'}">selected</c:if>>거래/나눔</option>
					    				<option value="기타" <c:if test="${qnaBean.qnacategory=='기타'}">selected</c:if>>기타</option>
                                	</select>
                                	<label for="qnasubject">카테고리</label>
                                	</div>
                            </div>
                            <form:errors cssClass="err" path="qnacategory"/>
                        	<!-- //카테고리 입력 -->
                            
                            <!-- 사진 첨부 -->
                            <div class="mb-1">
                                <div class="mb-2 mx-1"><b>참고 사진 : </b></div>
  								<input class="form-control" type="file" id="formFileMultiple" name="upload" onChange="fileMax()" multiple>
								<span style="font-size:10px; color: gray;">※첨부파일은 최대 10개까지 등록이 가능합니다.</span>
							</div>
                            <!-- //사진 첨부 -->
                            
                        	<!-- 질문 내용 입력 -->
                            <div class="col-12" style="float: left; ">
                             	<div class="form-floating">
                            	<textarea name="qnaquestion" style="resize:none; height:200px;" class="form-control border-0" placeholder="질문 내용" id="message">${qnaBean.qnaquestion}</textarea>
								<label for="qnaquestion" style="font-size:13px;opacity: 60%;">질문 내용</label>
								</div>
                            </div>
                            <form:errors cssClass="err" path="qnaquestion"/>
                        	<!-- //질문 내용 입력 -->
                            
                            <!-- 질문하기 버튼 -->
                            <div class="col-12 text-center">
                                <button class="btn btn-success" type="submit">질문하기</button>
                                <button class="btn btn-success" type="reset" onclick="return resetCheck()">다시작성</button>
                                <input type="button" class="btn btn-success" onClick="location.href='/mountain/all/list.qna'" value="목록">
                            </div>
                            <!-- //질문하기 버튼 -->
                        </div>
                    </div>
                </div>
            </div>
            </form:form>
            <!-- //폼 end -->
        </div>
    </div>
    <!-- qna Insert Form End -->
<script type="text/javascript">

	function fileMax(){
		var x = document.getElementById("formFileMultiple");
		var txt = "";
		if ('files' in x) {
		    if (x.files.length > 10) {
		        alert("첨부파일은 최대 10개까지 등록이 가능합니다.");
		        document.getElementById("formFileMultiple").value = "";
		        return;
		    }
		}
	}
	
	//reset 확인
	function resetCheck(){
		var check = confirm("정말 초기화 하시겠습니까?");
		 if(!check){
			return false;
		}
	}
	
</script>
<%@ include file="../common/common_bottom.jsp" %>