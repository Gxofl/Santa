<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file ="../../common/common_top.jsp" %>
<%@ include file ="../../common/common_nav_admin.jsp" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.js"></script>
<script type="text/javascript">
	/* 승인 버튼 클릭시 */
	function approval(id, prdname, paynum){
		var msg = id + "님의 '" + prdname + "' 결제내역을 승인하시겠습니까?";
		
		var result = confirm(msg);
		if(result == true){
			location.href = "/pay/admin/approval.pay?paynum="+paynum;
		}
	}
	
	/* 취소 버튼 클릭시 */
	function cancel(id, paynum){
		var msg = id + "님의 결제를 취소하시겠습니까?";
		
		var result = confirm(msg);
		if(result == true){
			location.href = "/pay/admin/cancel.pay?paynum="+paynum;
		}
	}
</script>
<div class="container-fluid py-5">
        <div class="container">
            <div class="text-center mx-auto wow fadeInUp" data-wow-delay="0.1s" style="max-width: 500px;">
                <h1 class="display-5 mb-5">결제 대기 내역 </h1>
        	</div>
                
            <div class="row justify-content-center">
                <div class="col-lg-9 mx-auto">
                    <div class="bg-light rounded p-4 p-sm-5 wow fadeInUp" data-wow-delay="0.1s">
                        <div class="row g-3 mx-auto" >
                         	<table class="table table-hover table-borded align-middle">
                         		<thead>
                         			<tr align="center">
                         				<th>결제일</th>
                         				<th>상품명</th>
                         				<th>가격</th>
                         				<th>아이디</th>
                         				<th>결제승인/취소</th>
                         			</tr>
                         		</thead>
                         			<c:if test="${paylist.size() == 0}">
                         				<td colspan="5" align="center">
                         					결과가 없습니다.
                         				</td>
                         			</c:if>
                         			<c:if test="${paylist.size() != 0}">
                         				<c:forEach var="paylist" items="${paylist}">
                         					<tr align="center">
	                         					<td>${paylist.regdate}</td>
	                         					<td>${paylist.prdname}</td>
	                         					<td><fmt:formatNumber value="${paylist.price}" pattern="###,###"/>원</td>
	                         					<td>${paylist.buyer}</td>
	                         					<td>
	                         						<input type="button" class="btn btn-secondary btn-sm" value="승인" 
	                         							onclick="javascript:approval('${paylist.buyer}', '${paylist.prdname}', '${paylist.paynum}')">
	                         						<input type="button" class="btn btn-secondary btn-sm" value="취소" onclick="javascript:cancel('${paylist.buyer}', '${paylist.paynum}')">
	                         					</td>
                         					</tr>
                         				</c:forEach>
                         			</c:if>
                         		</table>
                            </div><!--//row g-3  -->
                        </div><!-- //bg-light -->
                    </div><!-- //col-lg-7 -->
                </div><!-- //row justify-content-center -->
            </div><!-- //container -->
        </div>

<%@ include file ="../../common/common_bottom.jsp" %>