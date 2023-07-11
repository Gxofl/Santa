<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../common/common_top.jsp" %>
<%@ include file="../../common/common_nav_admin.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		//전체 선택 및 해제
		$('input[name=allCheck]').change(function(){
			$('input[name=oneCheck]').prop('checked', this.checked);
		});
		
		
	})
	
	/* 삭제 버튼 클릭 */
	function delete_check(){
		var chkArray = new Array(); // 배열 선언
			 
	    $('input[name=oneCheck]:checked').each(function() { // 체크된 체크박스의 value 값을 가지고 온다.
	        chkArray.push(this.value);
	    });
			
		//alert(chkArray.length);
		
		if(chkArray.length == 0){ // 선택하지 않았을 때
			alert('삭제할 글을 선택하세요');
		}else{
			var msg = chkArray.length +"개 글을 삭제하시겠습니까?";
			
			var cb_num = "";
			var result = confirm(msg);
			if(result == true){
				if(chkArray.length == 1){ // 1개만 선택했을때
					cb_num = chkArray[0];
					location.href = "/products/admin/delete.prd?num="+cb_num;
				}else{ // 다중 선택
					for(i=0; i<chkArray.length; i++){ // 확인
						if(i == 0){
							cb_num = chkArray[i];
						}else{
							cb_num += ","+chkArray[i];
						}
					}//for
					location.href = "/products/admin/delete.prd?num="+cb_num;
				}//else
			}//if
		}//else
	}//delete_check()
	
</script>
<style>
	.subject{
		background-color:white;
	}
</style>

    <!-- Feature Start -->
    <!-- 검색창 -->
    <div class="container-fluid py-5">
        <div class="container" style="display:table">
        	<div class="text-center mx-auto" style="max-width: 500px;">
				<p class="fs-5 fw-bold text-primary"></p>
            	<h3 class="display-5 mb-5">거래 게시판 
            		<c:if test="${kind == 'sell'}">
                		(판매)
                	</c:if>
                	<c:if test="${kind == 'give'}">
                		(나눔)
                	</c:if>
                	<c:if test="${kind == 'change'}">
                		(교환)
                	</c:if>
            	<br>관리자 관리페이지
            	</h3>
            	 <ol class="breadcrumb justify-content-center mb-0">
                    <li class="breadcrumb-item"><a href="/products/admin/list.prd?kind=">전체</a></li>
                    <li class="breadcrumb-item"><a href="/products/admin/list.prd?kind=sell">판매</a></li>
                    <li class="breadcrumb-item"><a href="/products/admin/list.prd?kind=give">나눔</a></li>
                    <li class="breadcrumb-item"><a href="/products/admin/list.prd?kind=change">교환</a></li>
                </ol>
        	</div>    
        
        	<br>
            <nav class="navbar navbar-light bg-light">
  				<div class="container" style="display:table-cell; vertical-align:middle;">
   				 	<form class="d-flex" action="/products/admin/list.prd" method="post">
   				 		<div class="col-sm-2">
	   				 		<select name="whatColumn" class="form-select">
	   				 			<option value="all">전체</option>
	   				 			<option value="name">상품명</option>
	   				 			<option value="seller">판매자</option>
	   				 		</select>
   				 		</div>
   				 		<div class="col-sm-8">
   				 			<input type="hidden" name="kind" value="${kind}">
     						<input class="form-control me-2" type="text" name="keyword">
      					</div>
      					<div class="col-sm-2" align="center">
      						<button class="btn btn-outline-success" type="submit">Search</button>
      					</div>
    				</form>
 			 	</div>
			</nav>
        </div>
    </div>
    <!-- //검색창 -->
    
    <!-- 거래 게시판 목록  -->
    <div class="container">
    	<!-- 정렬 -->
    	<div align="right">
    		<a href="/products/admin/list.prd?kind=${kind}&whatColumn=state0">거래중</a> / 
    		<a href="/products/admin/list.prd?kind=${kind}&whatColumn=state1">거래완료</a>
    		<br>
    	</div>
    	<!-- //정렬 -->
    	
	    <table class="table table-hover table-borded align-middle">
	    	<thead>
		    	<tr align="center">
		    		<th><input type="checkbox" name="allCheck"></th>
		    		<th>번호</th>
		    		<th>구분</th>
		    		<th>판매</th>
		    		<th>상품명</th>
		    		<th>가격</th>
		    		<th>판매자</th>
		    		<th>작성일</th>
				</tr>
			</thead>
	    	<c:if test="${plist.size() == 0 }">
	    		<tr>
	    			<td colspan="8" align="center">등록된 게시물이 없습니다.</td>
	    		</tr>
	    	</c:if>
	    	
	    	<c:if test="${plist.size() != 0 }">
	    		<c:forEach var="lists" items="${plist}" varStatus="status">
	    			<tr align="center">
	    				<td>
	    					<input type="checkbox" name="oneCheck" value="${lists.productsnum}">
	    				</td>
	    				<td>
	    					${(pageInfo.pageNumber-1)*pageInfo.limit+status.count}
	    				</td>
	    				<td>
	    					<c:if test="${lists.kind == 'a'}">
		                		판매
		                	</c:if>
		                	<c:if test="${lists.kind == 'b'}">
		                		나눔
		                	</c:if>
		                	<c:if test="${lists.kind == 'c'}">
		                		교환
		                	</c:if>
	    				</td>
	    				<td>
	    					<c:if test="${lists.state == 0}"><font color="blue">[거래중]</font></c:if>
	    					<c:if test="${lists.state == 1}"><font color="red">[거래완료]</font></c:if>
	    				</td>
	    				<td width="40%" align="left">
	    					<a href="/products/user/detail.prd?num=${lists.productsnum}&pageNumber=${pageInfo.pageNumber}">${lists.name}</a>
	    				</td>
	    				<td>
	    					<fmt:formatNumber value="${lists.price}" pattern="###,###"/>원
	    				</td>
	    				<td>
	    					${lists.seller}
	    				</td>
	    				<td>
	    					${lists.inputdate}
	    				</td>
	    			</tr>
	    		</c:forEach>
	    	</c:if>
	    </table>
    </div>
    <!-- //크루 게시판 목록  -->
    
    <!-- 삭제 -->
    <div class="container">
    	<div align="right">
    		<input type="button" class="btn btn-success" value="삭제" onclick="javascript : delete_check()">
    	</div>
    </div>
    <!-- //삭제 -->
    
     <!-- 페이지 표시 -->
	 <div align="center">
	 	${pageInfo.pagingHtml}
	 </div>
	 <!-- //페이지 표시 -->
    <!-- Feature End -->
<%@ include file="../../common/common_bottom.jsp" %>
