<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 마이페이지-완등내역-스탬프 신청 클릭했을 때 -->
<%@ include file="../common/common_top.jsp"%>
<%@ include file="../common/common_nav_myPage.jsp"%>
<!-- stampCertInsertForm.jsp -->
<style type="text/css">
	.err {
		font-weight: bold;
		color: red;
		font-size:7px;
	}
</style>

    <!-- stampCert Insert Form Start -->
    <div class="container-fluid py-5">
        <div class="container">
        	<!-- 타이틀 -->
            <div class="text-center mx-auto">
                <h1 class="display-5 mb-5">스탬프 신청</h1>
            </div>
            <!-- //타이틀 -->
            
            <!-- 폼 -->
            <form:form commandName="stampBean" action="/stamp/user/insert.stp" method="post" enctype="multipart/form-data">
            <div class="row justify-content-center">
                <div class="col-lg-7">
                    <div class="bg-light rounded p-4 p-sm-5">
                        <div class="row g-3" style="margin-bottom:-10px;">
                        	
                        	<!-- 제목 입력 -->
                            <div class="col-6" style="float: left; ">
	                            <select class="form-select" name="mountainnum" id="mountainnum">
									<option value="0">선택</option>
	                                <c:forEach var="mn" items="${mountainNames}">
										<option value="${mn.mountainnum}" <c:if test="${mn.mountainnum == stampBean.mountainnum}">selected</c:if>>${mn.mountainname}</option>
	                                </c:forEach>
								</select>
	                                <form:errors cssClass="err" path="mountainnum"/>
	                        </div>
	                        <!-- //제목 입력 -->
                        	
                        	<!-- 인증 사진 허가 기준 -->
							<div>
								<button class="btn btn-secondary btn-sm" onclick ="return apply_info()">
									인증허가 기준
								</button>
							</div>
                        	<!-- //인증 사진 허가 기준 -->
                        	
                            <!-- 사진 첨부 -->
                            <div class="mb-1" style="clear:both;">
                                <div class="mb-2 mx-1"><b>완등 사진 : </b></div>
  								<input class="form-control" type="file" name="upload" value="${stampBean.stampimage}">
  								<input type="hidden" name="stampimage">
							</div>
							<form:errors cssClass="err" path="stampimage"/>
                            <!-- //사진 첨부 -->
                            
                            <!-- 등록하기 버튼 -->
                            <div class="col-12 text-center">
                                <button class="btn btn-success" type="submit">등록하기</button>
                                <button class="btn btn-success" type="reset" onclick="return resetCheck()">다시작성</button>
                                <input type="button" class="btn btn-success" onClick="location.href='/stamp/user/list.stp'" value="목록">
                            </div>
                            <!-- //등록하기 버튼 -->
                        </div>
                    </div>
                </div>
            </div>
            </form:form>
            <!-- //폼 end -->
        </div>
    </div>
    <!-- stampCert Insert Form End -->
<script type="text/javascript">

//reset 확인
function resetCheck(){
	var check = confirm("정말 초기화 하시겠습니까?");
	 if(!check){
		return false;
	}
}

	function apply_info(){
		window.open('/stamp/all/applyinfo.stp', '_blank', 'width=800, height=600');
		return false;		
	}
</script>
<%@ include file="../common/common_bottom.jsp" %>