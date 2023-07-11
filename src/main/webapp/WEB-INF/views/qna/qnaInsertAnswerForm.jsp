<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- qnaInsertAnswerForm.jsp -->
<%@ include file="../common/common_top.jsp" %>
<style type="text/css">
	.err {
		font-weight: bold;
		color: red;
		font-size:7px;
	}
</style>
    <!-- Page Header Start -->
    <div class="container-fluid page-header py-5 mb-5 wow fadeIn" data-wow-delay="0.1s">
        <div class="container text-center py-5">
            <h1 class="display-3 text-white mb-4 animated slideInDown">Q&A</h1>
        </div>
    </div>
    <!-- Page Header End -->


    <!-- Answer Insert Form Start -->
    <div class="container-fluid py-5">
        <div class="container">
        	<!-- 타이틀 -->
            <div class="text-center mx-auto" tyle="max-width: 500px;">
                <h1 class="display-5 mb-5">답변등록</h1>
            </div>
            <!-- //타이틀 -->
            
            <!-- 폼 -->
            <div class="row justify-content-center">
                <div class="col-lg-7">
                    <div class="bg-light rounded p-4 p-sm-5">
                    	<div class="mb-2 mx-1"><b>질문내용 : </b></div>
	                    <div class="card mb-3">
	                		<div class="card-body">
								<h6 class="card-title"><b>${qnaBean.qnasubject}</b></h6>
								<p class="card-text">${qnaBean.qnaquestion}</p>
							</div>
	                    </div>
	                    
            			<form:form action="/qna/admin/insertAnswer.qna" method="post">
                        
                        <!-- hidden으로 num넘기기 -->
                        <input type="hidden" name="qnanum" value="${qnaBean.qnanum}">
                        <!-- //hidden으로 num넘기기 -->
                            
                        <!-- 답변 내용 입력 -->
                        <div class="col-12 mb-5">
                             	<div class="form-floating">
                            	<textarea name="qnaanswer" style="resize:none; height:200px;" class="form-control border-0" placeholder="답변 내용" id="message">${qnaBean.qnaanswer}</textarea>
								<label for="qnaquestion" style="font-size:13px;opacity: 60%;">답변 내용</label>
								</div>
                        </div>
                        <form:errors cssClass="err" path="qnaanswer"/>
                        <!-- //답변 내용 입력 -->
                        
                        <!-- 답변하기 버튼 -->
                            <div class="col-12 text-center">
                                <button class="btn btn-success" type="submit">답변하기</button>
                                <button class="btn btn-success" type="reset" onclick="return resetCheck()">다시작성</button>
                                <input type="button" class="btn btn-success" onClick="location.href='/qna/all/list.qna'" value="목록">
                            </div>
                        <!-- //답변하기 버튼 -->
			            </form:form>
                        </div>
                    </div>
                </div>
            </div>
            <!-- //폼 end -->
        </div>
    	<!-- //Answer Insert Form End-->
<script>
//reset 확인
function resetCheck(){
	var check = confirm("정말 초기화 하시겠습니까?");
	 if(!check){
		return false;
	}
}
</script>
<%@ include file="../common/common_bottom.jsp" %>