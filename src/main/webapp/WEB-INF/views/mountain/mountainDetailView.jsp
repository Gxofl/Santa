<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- mountainDetailView.jsp -->
<%@ include file="../common/common_top.jsp" %>
<style>
	th, td {
	  text-align: center;
	}
</style>
    <!-- Page Header Start -->
    <div class="container-fluid page-header py-5 mb-5 wow fadeIn" data-wow-delay="0.1s">
        <div class="container text-center py-5">
            <h1 class="display-3 text-white mb-4 animated slideInDown">산 상세 정보</h1>
        </div>
    </div>
    <!-- Page Header End -->
	<!-- hidden으로 넘겨줌 -->
	<c:set var="mountainnum" value="${mountainBean.mountainnum}"/>
	
	<div class="container-xxl py-5">
        <div class="container">
            <div class="row g-5">
                <div class="col-lg-6">
                    <h1 class="display-5 mb-5" align="center">${mountainBean.mountainname}</h1>
                    <!-- 산 목록 Start -->
							<table class="table table-hover table-borded align-middle">
							    <tr>
							      <th scope="col">지역</th>
							      <td colspan="2" class="w-75">${mountainBean.mountainlocal}</td>
							    </tr>
							  	<tr>
							  	  <th scope="col">높이</th>
							      <td><fmt:formatNumber value="${mountainBean.mountainheight}"/>m</td>
							    </tr>
							    <tr>
							      <th scope="col">난이도</th>
							      <td>
							      <c:choose>
							      	<c:when test="${mountainBean.mountainheight <= avg*1}">
							      		초급
							      	</c:when>
							      	<c:when test="${mountainBean.mountainheight > avg*1 and mountainBean.mountainheight <= avg*2}">
							      		중급
							      	</c:when>
							      	<c:when test="${mountainBean.mountainheight < avg*2 and mountainBean.mountainheight <= avg*3}">
							      		상급
							      	</c:when>
							      </c:choose>
							      </td>
							    </tr>
							    <tr>
							      <th scope="col">국립공원</th>
							      <td>
								      <c:if test="${fn:contains(mountainBean.mountaincontent, '국립공원')}">
								      	O
								      </c:if>
								      <c:if test="${not fn:contains(mountainBean.mountaincontent, '국립공원')}">
								      	X
								      </c:if>
							      </td>
							      </tr>
							      <tr>
							        <th scope="col">100대 명산</th>
							        <td>
								      <c:if test="${mountainBean.mountaingreat != null}">
								      	O
								      </c:if>
								      <c:if test="${mountainBean.mountaingreat == null}">
								      	X
								      </c:if>
							        </td>
								  </tr>
							      <c:if test="${mountainBean.mountaingreat != null}">
								    <tr>
								      <th scope="col">100대 명산<br>선정 이유</th>
								      <td>${mountainBean.mountaingreat}</td>
								    </tr>
							      </c:if>
							      <tr>
							        <th scope="col">상세 정보</th>
							        <td>${mountainBean.mountaincontent}</td>
							      </tr>
							</table>
                            <!-- //산 목록 End -->
                </div>
				<!-- 산 이미지 -->
                <div class="col-lg-6" style="min-height: 450px;">
                <c:if test="${fn:contains(mountainBean.mountainimage,'http')}">
                	<img src="${mountainBean.mountainimage}" style="width:500px;"><br>
                </c:if>
                <c:if test="${!fn:contains(mountainBean.mountainimage,'http')}">
           			<img style="width:620px; margin-bottom:10px;" src="<%=request.getContextPath()%>/resources/images/mountain/${mountainBean.mountainimage}"><br>
                </c:if>
                <!-- //산이미지-->
                <!-- 정보 등록 버튼(관리자만 볼 수 있음) -->
                <c:if test="${principal.getName() == 'admin'}">
                <form action="/mountain/admin/update.mnt" method="get">
                	<div class="container" align="center">
                	<input type="hidden" name="mountainnum" value="${mountainBean.mountainnum}">
                    <input type="submit" class="btn btn-success" value="산 정보 수정" onClick="location.href='/mountain/admin/update.mnt'">
                    <input type="button" class="btn btn-success" onclick="deleteOne()" value="현재 정보 삭제">
                	</div>
                </form>
                </c:if>
               <!-- //정보 등록 버튼 end -->
                </div>
            </div>
        </div>
    </div>
    <!-- Contact End -->

<script src="../../../resources/js/jquery.js"></script>
<script  type="text/javascript">
//선택된 값 넘기기
function deleteOne(){

	var chkArray = "<c:out value='${mountainBean.mountainnum}'/>"
	
	if(!confirm('정말 삭제하시겠습니까?')){
		//취소를 눌렀을 때
		//돌아가기
		return;
	}else{
		//확인을 눌렀을 때
		//delete 컨트롤러로 이동한 후 새로고침

        //alert(chkArray);
        
        //배열을 넘기는 ajax
        $.ajax({
        	  type : 'post',
        	  url : '/mountain/admin/delete.mnt',
        	  data : {chkArray : chkArray},
        	  dataType : 'json',
        	  traditional: true,
        	  error: function(xhr, status, error){
        		// 실패했을 때 처리
        	  },
        	  success : function(data){
        		// 성공했을 때 처리
        	  }
        	});
        window.location.replace("/mountain/all/list.mnt");
    }//if~else
}
	
</script>
<%@ include file="../common/common_bottom.jsp" %>