<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- mountainUpdateForm.jsp -->
<%@ include file="../common/common_top.jsp" %>
<style type="text/css">
	.err {
		font-weight: bold;
		color: red;
		font-size: 7px;
	}
</style>
    <!-- Page Header Start -->
    <div class="container-fluid page-header py-5 mb-5 wow fadeIn" data-wow-delay="0.1s">
        <div class="container text-center py-5">
            <h1 class="display-3 text-white mb-4 animated slideInDown">산 정보 수정</h1>
        </div>
    </div>
    <!-- Page Header End -->


    <!-- mountain Insert Form Start -->
    <div class="container-fluid py-5">
        <div class="container">
        	<!-- 타이틀 -->
            <div class="text-center mx-auto" style="max-width: 500px;">
                <h1 class="display-5 mb-5">산 정보 수정</h1>
            </div>
            <!-- //타이틀 -->
            
            <!-- 폼 -->
            <form:form commandName="mountainBean" action="/mountain/admin/update.mnt" method="post" enctype="multipart/form-data">
            <div class="row justify-content-center">
                <div class="col-lg-7">
                    <div class="bg-light rounded p-4 p-sm-5 wow fadeInUp" data-wow-delay="0.1s">
                        <div class="row g-3" style="margin-bottom:-10px;">
                        	<!-- hidden으로 num 넘겨주기 -->
                            <input type="hidden" value="${mountainBean.mountainnum}" class="form-control border-0" name="mountainnum"><br>
                        	<!-- 제목 입력 --><!-- 제목 입력 -->
                        	<div class="col-6" style="float: left; ">
	                                <div class="form-floating">
	                                    <input type="text" class="form-control border-0" id="mountainname" name="mountainname" value="${mountainBean.mountainname}">
	                                    <label for="mountainname">산 이름</label>
	                                </div>
	                                <form:errors cssClass="err" path="mountainname"/>
	                        </div>
                        	<!-- //제목 입력 -->
                        	
                            <!-- 사진 첨부 -->
                            <div class="mb-1">
                                <div class="mb-2 mx-1"><b>참고 사진 : </b></div>
  								<input class="form-control" type="file" name="upload">
							</div>
                            <!-- //사진 첨부 -->
                            
                            <!-- 높이 입력 -->
                            <div class="col-5" style="float: left; ">
	                                <div class="form-floating">
	                                    <input type="text" class="form-control border-0" id="mountainheight" name="mountainheight" value="${mountainBean.mountainheight}">
	                                    <label for="mountainheight">산 높이</label>
	                                </div>
	                                <form:errors cssClass="err" path="mountainheight"/>
	                        </div>
                        	<!-- //높이 입력 -->

                            <!-- 지역 입력 -->
                            <div class="col-8" style="float: left; ">
	                                <div class="form-floating">
	                                    <input type="text" class="form-control border-0" id="mountainlocal" name="mountainlocal" value="${mountainBean.mountainlocal}">
	                                    <label for="mountainlocal">산 지역</label>
	                                </div>
	                                <form:errors cssClass="err" path="mountainlocal"/>
	                        </div>
							<!-- //지역 입력 -->
                        	
                        	<!-- 상세 정보 입력 -->
                             <div class="col-12" style="float: left; ">
                             	<div class="form-floating">
                            	<textarea name="mountaincontent" style="resize:none; height:200px;" class="form-control border-0" placeholder="산 상세 정보" id="message">${mountainBean.mountaincontent}</textarea>
								<label for="mountaincontent" style="font-size:13px;opacity: 60%;">산 상세정보</label>
								</div>
                            </div>
                            <form:errors cssClass="err" path="mountaincontent"/>
                        	<!-- //상세 정보 입력 -->

                        	<!-- 100대 명산 입력 -->
                            <div class="col-12">
                            	<div class="mb-2 mx-1"><b>특이사항 : </b></div>
                            	
                            	<div class="form-check form-check-inline mb-3">
								  <input name="mountaincheck" value="0" class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault1" <c:if test="${mountainBean.mountaingreat == null}">checked</c:if>>
								  <label class="form-check-label" for="flexRadioDefault1">
								    없음
								  </label>
								</div>
								<div class="form-check form-check-inline">
								  <input name="mountaincheck" value="1" class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault2" <c:if test="${mountainBean.mountaingreat != null}">checked</c:if>>
								  <label class="form-check-label" for="flexRadioDefault2">
								    100대 명산
								  </label>
								</div>
							<div class="col-12" style="float: left; ">
                             	<div class="form-floating">
                            	<textarea name="mountaingreat" id="mountaingreat" style="resize:none;" class="form-control border-0" placeholder="100대 명산 선정 이유" id="message" style="height: 130px" <c:if test="${mountainBean.mountaingreat == null}">disabled</c:if>>${mountainBean.mountaingreat}</textarea>
								<label for="mountaingreat" style="font-size:13px;opacity: 60%;">100대 명산 선정 이유</label>
								</div>
                            </div>
                            <span style="font-size:7px;color:grey;">※선정 이유를 입력해주셔야 100대명산으로 등록됩니다.</span>
                            </div>
                        	<!-- //100대 명산 입력 -->
                            
                            <!-- 등록하기 버튼 -->
                            <div class="col-12 text-center">
                        		<input type="submit" value="수정하기" class="btn btn-success">
                            	<input type="reset" value="다시작성" class="btn btn-success" onclick="return resetCheck()"> 
                                <input type="button" class="btn btn-success" onClick="location.href='/mountain/all/list.mnt'" value="목록">
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
    <!-- mountain Insert Form End -->
<script src="../../../resources/js/jquery.js"></script>
<script>
	$('input[name="mountaincheck"]').change(function(){
	    var value = $(this).val();
	    var checked = $(this).prop('checked');
	    if(value=='1'){
	            document.getElementById('mountaingreat').disabled=false;
	            document.getElementById('mountaingreatdanger').disabled=false;
	    }else{
	            document.getElementById('mountaingreat').disabled=true;
	            document.getElementById('mountaingreatdanger').disabled=true;
	    }
       	document.getElementById('mountaingreat').value='';
	});
	

	//reset 확인
	function resetCheck(){
		var check = confirm("정말 초기화 하시겠습니까?");
		 if(!check){
			return false;
		}
	}
</script>
<%@ include file="../common/common_bottom.jsp" %>