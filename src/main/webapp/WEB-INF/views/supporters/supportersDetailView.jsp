<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/common_top.jsp"%>


<!-- 오늘날짜 가져와서 toDay 변수에 넣음 -->
<%-- <c:set var="now" value="<%=new java.util.Date()%>" />
<fmt:formatDate pattern="yyyy-MM-dd" value="${now}" var="toDay" /> --%>


<!-- 조건으로 모집 상태와 글자색을 구분함 -->
<c:choose>
	<c:when test="${supporters.state == 1}">
    	<c:set var="statusLabel" value="[모집완료]" />
    	<c:set var="statusColor" value="red" />
	</c:when>
  
	<c:when test="${supporters.state == 2}">
    	<c:set var="statusLabel" value="[모집기한 마감]" /> 
    	<c:set var="statusColor" value="black" />
	</c:when>
	<c:otherwise>
		<c:set var="statusLabel" value="[모집중]" />
		<c:set var="statusColor" value="blue" />
	</c:otherwise>
</c:choose>
<!-- //조건으로 모집 상태와 글자색을 구분함 -->


<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.js"></script>
<script type="text/javascript">
	
	$(document).ready(function(){
		/* 현재 로그인한 사용자의 해당 게시글 좋아요 클릭여부 하트이미지 on/off */
		heartHandler();
	})//ready
	/* 신청하기 버튼 클릭 */
	function supportersApply(supportersname, userRole, state, target, num){
		//글번호, 서포터즈명, 사용자의 등급, 모집상태, 모집대상을 받음
		//userRole(사용자의 등급)은 SupportersDetailController에서 가져옴
		
		if(state == '1'){ //해당 서포터즈가 모집완료 일때
			alert('모집완료된 서포터즈입니다');
			return;
		} else if(state == '2'){ //모집기한 마감 일때
			alert('모집기한 마감된 서포터즈입니다');
			return;
		} else if (target == '1' && userRole == 'r01') { //모집대상이 우수회원인데 일반회원이 신청 버튼을 누른 경우
			alert('우수 회원만 신청 가능합니다.');
			return;
		} 
		
		//위의 3가지 경우에 다 해당되지 않는다면
		$.ajax({
			url : "/supportersapply/user/apply_check.su", //일단 내가 이 서포터즈에 신청을 했었는지 안했었는지를 체크해줘야함
			data : ({	
				num : num //글번호
			}),
			success : function(data){
				//alert(data);
				if(data === 'YES') { //이미 신청함
					alert('이미 신청한 서포터즈입니다.');
				} else { //신청 안함 -> 신청할 수 있음
					var result = confirm("["+supportersname+"] 서포터즈에 신청하시겠습니까?"); 
					if(result == true){ //신청을 한다고 하면
						$.ajax({
							url : "/supportersapply/user/insert.sua", //이제 apply에 insert를 해야함
							data : ({	
								supportersname : supportersname,
								num : num,
							}),
							type : 'POST',
							success : function(data){
								//alert(data.status);
								if(data.status === 'YES') {//신청(insert) 성공
									alert('신청이 완료되었습니다.'); 
								} else { //NO가 리턴 -> 신청(insert) 실패
									alert('신청도중 문제가 발생했습니다.'); 
								}
							} //success		
						});//ajax	
					}
				}
			} //success		
		}); //ajax	
	}//supportersApply

	
	/* 취소하기 버튼 클릭 */
	function supportersCancle(num){ //글번호를 받음
		$.ajax({
			url : "/supportersapply/user/apply_cancel.su", //get 방식
			data : ({	
				num : num
			}),
			success : function(data){
				//alert(data);
				if(data === 'YES') {
					alert('취소처리가 완료되었습니다.');
				} else { //NO가 넘어오면
					alert('신청이력이 존재하지 않습니다.');
				}
			} //success		
		}); //ajax	
	}
	
	
	/* 글의 삭제 버튼 클릭 */
	function deletesupporters(num, pageNumber){
		var isDel = confirm("정말 삭제하시겠습니까?"); 
		if(isDel == true){
			location.href="/supporters/admin/delete.su?num="+num+"&pageNumber="+pageNumber;
		}			
	}
	

	/* 현재 로그인한 사용자의 해당 게시글 좋아요 클릭여부 하트이미지 on/off */
	function heartHandler(){
		var getHeartval = $("#heartStatus").val();
		if(Number(getHeartval) > 0) {
			$('#heart').attr('src', '../../resources/images/icon/heart.png');
		} else {
		    $("#heart").prop("src", "/resources/images/icon/empty_heart.png");
		}
    }//heartHandler
	

	// 좋아요(empty -> fill) 
	function heartEvent(){
		//alert($('input[name=heartCId]').val() + " / " + $('input[name=num]').val() );
		var getHeartval = $("#heartStatus").val();
    	//좋아요
    	if(Number(getHeartval) == 0) {
    		$.ajax({
    			type: 'POST',
    		    url: '/heart/user/supporters/insertHeart.ht',
    			data : {
    				input_userId: $('input[name=heartCId]').val(),    // 좋아요 클릭한 아이디
    				input_num	: $('input[name=num]').val()        // 게시글 번호
    			},
    			success: function(data) {
                    if (data == 'success') {
                    	$('#heart').attr('src', '../../resources/images/icon/heart.png');
                        //alert("좋아요❤️");
                        $("#heartStatus").val("1");
                    } else if(data == 'fail') {
                        alert("게시글 좋아요 실패했습니다.");
                        $('#heart').attr('src', '../../resources/images/icon/empty_heart.png');
                    } else{
                    	alert("게시글 좋아요 실패했습니다.");
                    }
                }//success
    		});//ajax
    	} 
    	// 좋아요 취소
    	else {
    		$.ajax({
    			type: 'POST',
    		    url: '/heart/user/supporters/deleteHeart.ht',
    			data : {
    				input_userId: $('input[name=heartCId]').val(),    // 좋아요 클릭한 아이디
    				input_num	: $('input[name=num]').val()        // 게시글 번호
    			},
    			success: function(data) {
                    if (data == 'success') {
                        $('#heart').attr('src', '../../resources/images/icon/empty_heart.png');
                        //alert("게시글 좋아요를 취소하였습니다.");
                        $("#heartStatus").val("");
                    } else if(data == 'fail') {
                        alert("게시글 좋아요 취소 실패했습니다.");
                    } else{
                    	alert("게시글 좋아요 취소 실패했습니다.");
                    }
                }//success
    		});
    	}
    	//좋아요 수 카운트 증가를 위해 Post 데이터를 포함해 페이지를 새로 고침 
    	location.reload(); 
    }//heartEvent
	
	
</script>


	<!-- Page Header Start -->
    <div class="container-fluid page-header py-5 mb-5 wow fadeIn" data-wow-delay="0.1s">
        <div class="container text-center py-5">
            <h1 class="display-3 text-white mb-4 animated slideInDown">서포터즈 게시판</h1>
            <nav aria-label="breadcrumb animated slideInDown">
                <ol class="breadcrumb justify-content-center mb-0">
                </ol>
            </nav>
        </div>
    </div>
	<!-- //Page Header End -->
	<div class="container-xxl py-5">
		<div class="container">
			<div class="text-center mx-auto wow fadeInUp" data-wow-delay="0.1s" style="max-width: 500px;">
				<h2 class="display-5 mb-5">서포터즈 등록글</h2>
			</div>
			<input type="hidden" name="heartCId" value="${loginId}">
			<input type="hidden" name="num" value="${supporters.num}">
			<!-- 게시글 상세보기 -->
			<table class="table">
			<%-- 
				<tr>
				 	<td>글번호</td>
					<td>${supporters.num }</td>		
				</tr> --%>
				<tr>
					<th>서포터즈</th>
					<td colspan="3" align="center">${supporters.supportersname }</td>
				</tr>		
				<tr>
					<th>모집상태</th>
					<td colspan="3" align="center">
						<%-- <c:if test="${supporters.state == 0}"><font color="blue">[모집중]</font></c:if>
			    		<c:if test="${supporters.state == 1}"><font color="red">[모집완료]</font></c:if>
			    		<c:if test="${supporters.state == 2}"><font color="black">[모집기한 마감]</font></c:if>	 --%>	
					 	<font color="${statusColor}">${statusLabel}</font>
					</td>
				</tr>
				<tr>
					<th>모집기한</th>
					<td colspan="3" align="center">
						<fmt:parseDate var="newDay2" value="${supporters.deadline}" pattern="yyyy-MM-dd" /> 
						<fmt:formatDate var="fNewDay2" value="${newDay2}" pattern="yyyy-MM-dd" />
						${fNewDay2 }
					</td>
				</tr>
				<tr>
					<th>모집대상</th>
					<td colspan="3" align="center">
						<c:if test="${supporters.target == 0}">전체회원</c:if> 
						<c:if test="${supporters.target == 1}">우수회원</c:if>
					</td>
				</tr>
				<tr>
					<th>모집인원</th>
					<td>${supporters.limit }</td>
					<th>신청인원</th>
					<td>${supporters.applicant }</td>
				</tr>
				<tr>
					<th>활동 시작일</th>
					<td>
						<fmt:parseDate var="newDay3" value="${supporters.startdate}" pattern="yyyy-MM-dd" /> 
						<fmt:formatDate var="fNewDay3" value="${newDay3}" pattern="yyyy-MM-dd" />
						${fNewDay3 }
					</td>
					<th>활동 종료일</th>
					<td>
						<fmt:parseDate var="newDay4" value="${supporters.enddate}" pattern="yyyy-MM-dd" /> 
						<fmt:formatDate var="fNewDay4" value="${newDay4}" pattern="yyyy-MM-dd" /> 
						${fNewDay4}
					</td>
				</tr>
				<tr>
					<th width="120px">등록일</th>
					<td>
						<fmt:formatDate value="${supporters.regdate}" type="date" dateStyle="medium" timeStyle="medium" pattern="yyyy-MM-dd hh:mm:ss"/>	
					</td>
					<th width="120px">조회수</th>
					<td>${supporters.readcount }</td>
				</tr>
				<tr>
					<th>활동내용</th>
					<td colspan="3" height="200">${supporters.content }</td>
				</tr>
				<tr>
					<td colspan="4" align="center">
			    		<!-- 신청할때 넘겨야할것 5가지 : 글번호, 서포터즈명, 사용자의 등급, 모집상태, 모집대상 
			    			 userRole(사용자의 등급)은 SupportersDetailController에서 가져옴 -->
						<input type="submit" value="신청하기" class="btn btn-success" onClick="supportersApply('${supporters.supportersname }','${userRole}','${supporters.state}','${supporters.target}', ${supporters.num})"> 
						<!-- 취소할때는 글번호 1개만 넘김 -->
						<input type="button" value="취소하기" class="btn btn-success" onClick="supportersCancle(${supporters.num})"> 
						<input type="button" value="목록" class="btn btn-success" onclick="location.href='/supporters/all/list.su?pageNumber=${pageNumber}'">
					</td>
				</tr>
				<!-- 수정, 삭제 버튼은 관리자(admin) 에게만 보이도록 --> 
				<sec:authorize access="hasRole('ROLE_ADMIN')">
					<tr>
						<td colspan="4" align="center">	
							<input type="button" value="수정" class="btn btn-success" onClick="location.href='/supporters/admin/update.su?num=${supporters.num}&pageNumber=${pageNumber}'">
							<input type="button" value="삭제" class="btn btn-success" onClick="deletesupporters('${supporters.num}','${pageNumber}')">				
						</td>
					</tr>
				</sec:authorize> 
				<!-- //수정, 삭제 버튼은 관리자에게만 보이도록 -->
			</table>
		</div>
	
		<!-- 좋아요 아이콘 -->
	   	<div class="container" align="right">
			<div align="center" style="width: 100px; border-radius: 20px; border: 1px solid #dee2e6; margin-bottom: 25px; padding:10px;" >
				<!-- 좋아요 -->
				<div style="display: inline-block;">
					<input type="hidden" id="heartStatus" name="heartStatus" value='<c:out value="${getHeartCnt}"/>'>
		   			<span>
		   				<img src="<%=request.getContextPath()%>/resources/images/icon/empty_heart.png" id="heart" width="30" height="30" onclick="heartEvent()">
		   			</span>
		   			<span style="padding-left: 10px;">${getHeartTotal}</span>
	   			</div>
	   			<!-- //좋아요 -->
	   		</div>
		</div>
   		<!-- // 좋아요 아이콘 -->

		<!-- 댓글 입력창 -->
		<%-- 
		<form action="/supporters/admin/insert.scmt" method="post">
			<input type="hidden" name="bnum" value="${supporters.num}"> 
			<input type="hidden" name="userid" value="${userid}"> 
			<input type="hidden" name="pageNumber" value="${pageNumber }">
			<input type="hidden" name="isSecret" value="N">
			
			<div class="card mb-2">
				<div class="card-header bg-light">
					<i class="fa fa-comment fa"></i> COMMENTS
				</div>
				<div class="card-body">
					<ul class="list-group list-group-flush">
						<li class="list-group-item">
							<div class="form-inline mb-2"></div>					
							<textarea class="form-control" name="content" rows="3"></textarea>
							<div>
								<input type="checkbox" id="reply_secret" name="reply_secret" style="margin-bottom: 10px; margin-top: 10px; margin-right: 5px;" />
								<label for="reply_secret" style="cusor:pointer;">비밀댓글로 작성</label>
							</div>
							<div>
								<input type="submit" class="btn btn-success" value="댓글달기">
							</div>
						</li>
					</ul>
				</div>
			</div><!-- //card -->
		</form> --%>
		<!-- //댓글 입력창 -->	  
	</div>
	<!-- //container-xxl -->
	    

<%@ include file="../common/common_bottom.jsp"%>     