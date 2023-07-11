<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- mountainList.jsp -->
<%@ include file="../common/common_top.jsp" %>
<style>
	th, td {
	  text-align: center;
	}
</style>
    <!-- Page Header Start -->
    <div class="container-fluid page-header py-5 mb-5 wow fadeIn" data-wow-delay="0.1s">
        <div class="container text-center py-5">
            <h1 class="display-3 text-white mb-4 animated slideInDown">산별정보</h1>
        </div>
    </div>
    <!-- Page Header End -->

    <!-- navbar -->
    <%@ include file="../common/common_nav_mountain.jsp" %>
    
    <!-- 산 목록 Start -->
    <div class="container-fluid py-5">
        <div class="container">
            <div class="text-center mx-auto" style="max-width: 500px;">
                <h1 class="display-5 mb-5">산 목록</h1>
            </div>
            
                            <div class="container">
                            <!-- 검색창 -->
				            <nav class="navbar navbar-light bg-light">
				  				<div class="container" style="display:table-cell; vertical-align:middle;">
				   				 	<form class="d-flex" action="/mountain/all/list.mnt" method="get">
				   				 		<div class="col-sm-2">
					   				 		<select name="whatColumn" class="form-select">
					   				 			<option value="">전체</option>
					   				 			<option value="이름">이름</option>
					   				 			<option value="내용">내용</option>
					   				 			<option value="지역">지역</option>
					   				 		</select>
				   				 		</div>
				   				 		<div class="col-sm-8">
				     						<input class="form-control me-2" type="text" name="keyword">
				      					</div>
				      					<div class="col-sm-2" align="center">
				      						<button class="btn btn-outline-success" type="submit">Search</button>
				      					</div>
				    				</form>
				 			 	</div>
							</nav>
							<br>
				 			<!-- //검색창 -->
                            <div align="left" style="margin-top:-25px;">
                            <font size="1px" color="grey">※ <img alt="인기산" src="<%=request.getContextPath()%>/resources/images/icon/fire.png" class="m-1">인기 인증 산 표시</font>
                            </div>
                            <!-- 산 목록 Start -->
							<table class="table table-hover table-borded" border="1">
							  <thead>
							    <tr align="center">
			                      <c:if test="${principal.getName() == 'admin'}">
			                      <th><input type="checkbox" class="form-check-input" id="success-outlined" name="selected_all"></th>
			                      </c:if>
							      <th scope="col">번호</th>
							      <th scope="col" class="w-25">이름</th>
							      <th scope="col" class="w-25">지역</th>
							      <th scope="col">높이</th>
							      <th scope="col">난이도</th>
							      <th scope="col">국립공원</th>
							      <th scope="col">상세보기</th>
							    </tr>
							  </thead>
							  <tbody>
							  	<c:choose>
							  	<c:when test="${principal.getName() == 'admin' and mountainList == null}">
							  		<td colspan="8">등록된 데이터가 없습니다.</td>
							  	</c:when>
							  	<c:when test="${mountainList == null}">
							  		<td colspan="7">등록된 데이터가 없습니다.</td>
							  	</c:when>
							  	</c:choose>
							  	<c:forEach var="ml" items="${mountainList}" varStatus="status">
							    <tr>
							  	  <c:if test="${principal.getName() == 'admin'}">
			                      <td><input type="checkbox" class="form-check-input" name="selected_one" value="${ml.mountainnum}"></td>
			                      </c:if>
							      <th scope="row">
							      ${(pageInfo.pageNumber-1)*pageInfo.limit+status.count} 
							      </th>
							      <td>
							      <c:forEach var="cl" items="${certList}">
							      <!-- 만약 현재 행의 산번호가 인증리스트의 산번호와 같고 -->
							      <!-- 인증리스트의 갯수가 10이상이면 인기산 표시 -->
							      <c:if test="${ml.mountainnum == cl.mountainnum && cl.certcount >= 10 }">
							      	<img alt="인기산" src="<%=request.getContextPath()%>/resources/images/icon/fire.png">
							      </c:if>
							      </c:forEach>
							      <a href="<c:url value='/mountain/all/detail.mnt?mountainnum=${ml.mountainnum}'/>">${ml.mountainname}</a></td>
							      <td>${ml.mountainlocal}</td>
							      <td>${ml.mountainheight}</td>
							      <td>
							      <c:choose>
							      	<c:when test="${ml.mountainheight <= avg*1}">
							      		초급
							      	</c:when>
							      	<c:when test="${ml.mountainheight > avg*1 and ml.mountainheight <= avg*2}">
							      		중급
							      	</c:when>
							      	<c:when test="${ml.mountainheight < avg*2 and ml.mountainheight <= avg*3}">
							      		상급
							      	</c:when>
							      </c:choose>
							      </td>
							      <td>
								      <c:if test="${fn:contains(ml.mountaincontent, '국립공원')}">
								      	O
								      </c:if>
								      <c:if test="${not fn:contains(ml.mountaincontent, '국립공원')}">
								      	X
								      </c:if>
							      </td>
							      <td>
							      <a href="<c:url value='/mountain/all/detail.mnt?mountainnum=${ml.mountainnum}'/>"><img alt="돋보기" src="<%=request.getContextPath()%>/resources/images/icon/detailview.png"></a>
							      </td>
							    </tr>
							  	</c:forEach>
							  </tbody>
							</table>
							</div>
                            <!-- 페이지 표시 -->
							<div align="center">
							 	${pageInfo.pagingHtml}
							</div>
							<input type="hidden" name="pageNumber" value="${pageInfo.pageNumber}">
							<!-- //페이지 표시 -->
                        </div>
                    <!-- 체크박스의 값을 넘기는 hidden input -->
                    <input type="hidden" name="hiddenValue" id="hiddenValue" value=""/>
                    
                    <!-- 정보 등록 버튼(관리자만 볼 수 있음) -->
                    <c:if test="${principal.getName() == 'admin'}">
                    <div class="container">
    				<div align="right">
                    	<!-- <button class="btn btn-success" onClick="location.href='/mountain/admin/api.mnt'">산 api 등록</button> -->
                    	<button class="btn btn-success" onClick="location.href='/mountain/admin/insert.mnt'">산 정보 등록</button>
                    	<button class="btn btn-success" onclick="fnGetdata()">선택 정보 삭제</button>
                    </div>
                    </div>
                    </c:if>
                    <!-- //정보 등록 버튼 end -->
                    </div>
                
    <!-- //산 목록 End -->
<script src="../../../resources/js/jquery.js"></script>
<script  type="text/javascript">
	//전체 선택 및 해제
	$('input[name=selected_all]').change(function(){
		$('input[name=selected_one]').prop('checked', this.checked);
	});
	
	function fnGetdata(){
        var obj = $("[name=selected_one]");
        var chkArray = new Array(); // 배열 선언
 
        $('input:checkbox[name=selected_one]:checked').each(function() { // 체크된 체크박스의 value 값을 가지고 온다.
            chkArray.push(this.value);
        });
        $('#hiddenValue').val(chkArray);
        
        //alert($('#hiddenValue').val()); // 아래 체크박스가 모두 체크되어 있다면 1,2,3,4 가 출력 된다.
       
        if(!confirm('정말 삭제하시겠습니까?')){
    		//취소를 눌렀을 때
    		//돌아가기
    		return;
    	}else{
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