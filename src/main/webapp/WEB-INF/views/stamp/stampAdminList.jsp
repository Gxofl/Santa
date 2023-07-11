<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/common_top.jsp"%>
<%@ include file="../common/common_nav_admin.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.js"></script>
<script type="text/javascript">
</script>
<style>
	th, td {
	  text-align: center;
	}
</style>
    
    <!-- 산 목록 Start -->
        <div class="container">
            <div class="text-center mx-auto" style="max-width: 500px;">
                <h1 class="display-5 mt-5 mb-5">완등내역 관리</h1>
            </div>
            
                 <!-- 검색창 -->
				            <nav class="navbar navbar-light bg-light">
				  				<div class="container" style="display:table-cell; vertical-align:middle;">
				   				 	<form class="d-flex" action="/stamp/admin/adminlist.stp" method="get">
				   				 		<div class="col-sm-2">
					   				 		<select name="whatColumn" class="form-select">
					   				 			<option value="">전체</option>
					   				 			<option value="아이디">아이디</option>
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
			    
							
                            <!-- 인증신청 목록 Start -->
							<table border="1" class="table table-hover table-borded" border="1">
							  <thead>
							    <tr align="center">
							      <th scope="col">번호</th>
							      <th scope="col">아이디</th>
							      <th scope="col">보유 스탬프</th>
							      <th scope="col">인증신청 산 번호</th>
							      <th scope="col">인증사진 확인</th>
							      <th scope="col">인증상태</th>
							      <th scope="col">거절사유</th>
							    </tr>
							  </thead>
							  <tbody>
							  	<c:if test="${stampList == null}">
							  		<td colspan="7">등록된 데이터가 없습니다.</td>
							  	</c:if>
							  	<c:forEach var="sl" items="${stampList}" varStatus="status">
							    <tr>
							      <th scope="row">${(pageInfo.pageNumber-1)*pageInfo.limit+status.count} </th>
							      <td>${sl.usersid}</td>
							      <td>
							      	<!-- applylist의 id를 현재 행의 id와 반복해서 비교, 해당 id의 누적 스탬프 출력 -->
							      	<c:forEach var="al" items="${applyCount}">
							      		<c:if test="${sl.usersid == al.usersid}">
							    			${al.applycount}
							      		</c:if>
							      	</c:forEach>
							      	<c:if test="${zero == 0}">
							      		${zero}
							      	</c:if>
							      </td>
							      <td>${sl.mountainnum}</td>
							      <td>
							      <a href="/stamp/admin/admincertimage.stp?stampnum=${sl.stampnum}" target="_blank" onClick="window.open(this.href, '_blank', 'width=800, height=600'); return false;">
							      <img alt="돋보기" src="<%=request.getContextPath()%>/resources/images/icon/detailview.png">
							      </a>
							      </td>
							      <td>
									<select id="stampapply${sl.stampnum}" class="form-select" onChange="updateApply('${sl.stampnum}','${sl.usersid}')">
										<option value="0" <c:if test="${sl.stampapply == 0}">selected</c:if>>대기</option>
										<option value="1" <c:if test="${sl.stampapply == 1}">selected</c:if>>승인</option>
										<option value="2" <c:if test="${sl.stampapply == 2}">selected</c:if>>거절</option>
									</select>
								  </td>
								  <td>
								  	<select id="stampreject${sl.stampnum}" class="form-select" onChange="updateReject('${sl.stampnum}')" <c:if test="${sl.stampapply != 2}">disabled</c:if>>
										<option value="0" <c:if test="${sl.stampreject == 0}">selected</c:if>>거절사유 선택</option>
										<option value="1" <c:if test="${sl.stampreject == 1}">selected</c:if>>사진 부적합</option>
										<option value="2" <c:if test="${sl.stampreject == 2}">selected</c:if>>중복 인증 신청</option>
										<option value="3" <c:if test="${sl.stampreject == 3}">selected</c:if>>기타</option>
									</select>
								  </td>
							    </tr>
							  	</c:forEach>
							  </tbody>
							</table>
                            <!-- //인증신청 목록 End -->
						<!-- 페이지 표시 -->
						<div align="center">
						 	${pageInfo.pagingHtml}
						</div>
						<!-- //페이지 표시 -->
                        </div>
    <!-- //완등내역 End -->
    <script>
    	function updateApply(stampnum, userid){

    		var selector = document.getElementById('stampapply'+stampnum);
    		var selectItem = selector.options[selector.selectedIndex].value;

    		console.log(selectItem+","+stampnum);
    		
    		if(confirm('인증 상태를 정말 바꾸시겠습니까?')){
	    		location.href='/stamp/admin/updateapply.stp?stampapply='+selectItem+'&stampnum='+stampnum+'&userid='+userid;	
    		}else{
    			return;
    		}
    		
    	}

    	function updateReject(stampnum){
    		
    		var selector = document.getElementById('stampreject'+stampnum);
    		var selectItem = selector.options[selector.selectedIndex].value;

    		console.log(selectItem+","+stampnum);
    		
    		if(selectItem == 0){
    			alert('거절사유를 선택하세요.');
    			return;
    		}
    		
    		if(confirm('거절 사유를 정말 바꾸시겠습니까?')){
    		location.href='/stamp/admin/updatereject.stp?stampreject='+selectItem+'&stampnum='+stampnu;
    		}else{
    			return;
    		}
    	}
    </script>
<%@ include file="../common/common_bottom.jsp" %>