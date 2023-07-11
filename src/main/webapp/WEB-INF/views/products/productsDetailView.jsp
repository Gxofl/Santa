<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file ="../common/common_top.jsp" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		//alert('1');
		getAllComments();
		
		/* 현재 로그인한 사용자의 해당 게시글 좋아요 클릭여부 하트이미지 on/off */
		heartHandler();
		
		/* 구매하기 버튼 클릭 */
		$('#apibtn').click(function(){
			$.ajax({
				url : '/pay/user/kakaopay.pay',
				data : ({
					num : $('input[name=idx]').val(),
					pageNumber : $('input[name=pageNumber]').val()
				}),
				dataType : 'json' ,
				success : function(data){
					//alert(data.tid);
					var url = data.next_redirect_pc_url;
					
					//창 크기 지정
					var width = 500;
					var height = 500;
					
					//pc화면기준 가운데 정렬
					var left = (window.screen.width / 2) - (width/2);
					var top = (window.screen.height / 4);
					
				    //윈도우 속성 지정
					var windowStatus = 'width='+width+', height='+height+', left='+left+', top='+top+', scrollbars=yes, status=yes, resizable=yes, titlebar=yes';
					
					//등록된 url 및 window 속성 기준으로 팝업창을 연다.
					window.open(url, "hello popup", windowStatus);
					
					//window.open(url);
										
				},
				error : function(error){
					alert(error);
				}
			}); //ajax
		});//click
		
		//댓글창의 비밀 체크 박스를 클릭하면 이곳으로 와서 isSecret을 'N'에서 'Y'로 바꿈
		$("#reply_secret").change(function(){
			if($(this).prop("checked")){
				$("input[name=isSecret]").val('Y');
			}else{
				$("input[name=isSecret]").val('N');
			}
		});
		
		
	});//ready
	
	/* 댓글 목록 가져오기 */
	function getAllComments(){
		//alert('getAllComments');
		var loginId = $('input[name=writer]').val(); // 현재 로그인 아이디
		var pageNumber2 = $('input[name=pageNumber]').val(); // 현재 페이지
		
		$.ajax({
			url : '/productscomments/user/list.pcmt',
			type : 'post',
			data : ({
				idx : $('input[name=idx]').val(),
				pageNumber : $('input[name=pageNumber]').val()
			}),
			success : function(data){
				//alert('성공');
				var result = "<h6 class='border-bottom pb-2 mb-0'>댓글 목록</h6>";
				$.each(data,function(index, value) { // 값이 여러개 일 때는 반복문 사용
	                result += "<div class='d-flex text-body-secondary pt-3'>";
					if(value.relevel>0){ // 대댓글일 경우
	                	var wid = value.relevel*15;
	                	result += "<img src='../../../resources/images/comments/level.gif' width='"+wid+"' height='32'>";
						result += "<img src='../../../resources/images/comments/re.png' width='32' height='32'>";
	                }
	                
	               		result += "<img class='bd-placeholder-img flex-shrink-0 me-2 rounded' src='../../../resources/images/comments/member.png' width='32' height='32'>";
	                	result += "<p class='pb-3 mb-0 small lh-sm border-bottom'>";
	               
	                if(value.deleteyn == 'Y'){
	                	result += "삭제된 댓글입니다";
	                	result += "</p>";
	               	}
	                
	                else if(value.isSecret == 'Y'){ // 비밀댓글
						result += "<strong class='d-block text-gray-dark'>@"+value.writer;
	                	
	                	if(value.writer == loginId ){ // 댓글 작성자라면 수정, 삭제가 보임
	                		result += "&nbsp; [<a href='javascript:updateComments("+value.num+","+pageNumber2+","+value.idx+")'>수정</a>/";
	                		result += "<a href='javascript:deleteComments("+value.num+","+pageNumber2+","+value.idx+")'>삭제</a>]";
	                	}
	                	result += "</strong>";
	                	
	                	//1.댓글 작성자 본인일때 2. 해당 게시글의 작성자일때 3. 관리자일때 4.원댓글 작성자=> 댓글 비밀설정해도 볼 수 있음
	                	if(value.writer == loginId || "${pb.seller}" == loginId || "admin" == loginId || value.orgwriter == loginId){
	                		result += "<span id='pcmt_update" + value.num + "'><img src='../../../resources/images/icon/comments_lock.png' width='15' height='15'>" + value.content + "<br>";

	                	}else{ //위 3가지 경우에 해당되지 않는다면, 내용 대신 "비밀 댓글입니다."가 보임
	                		result += "<span id='pcmt_update" + value.num + "'><img src='../../../resources/images/icon/comments_lock.png' width='15' height='15'> 비밀 댓글입니다.<br>";
	                	}      			                	
	                	result += "<font color='gray' size='1px'>("+value.regdate+")</font><br>";
	                	result += "<input type='button' class='btn btn-light' value='답글' ";
	                	result += "onclick='replypcmt("+value.num+","+value.idx+","+value.ref+","+value.restep+","+value.relevel+","+value.pageNumber+")'>";
	                	result += "<span id='replypcmt_area"+value.num+"'></span>";
	                	result += "</span>";
	                	result += "</p>";
					}
	                
	                
	                else{
	                	result += "<strong class='d-block text-gray-dark'>@"+value.writer;
	                	
	                	if(value.writer == loginId ){ // 댓글 작성자라면 수정, 삭제 보임
	                		result += "&nbsp; [<a href='javascript:updateComments("+value.num+","+pageNumber2+","+value.idx+")'>수정</a>/";
	                		result += "<a href='javascript:deleteComments("+value.num+","+pageNumber2+","+value.idx+")'>삭제</a>]";
	                	}
	                	result += "</strong>";
	                	
	                	result += "<span id='pcmt_update"+value.num+"'>"+value.content+"<br>";
	                	result += "<font color='gray' size='1px'>("+value.regdate+")</font><br>";
	                	result += "<input type='button' class='btn btn-light' value='답글' ";
	                	result += "onclick='replypcmt("+value.num+","+value.idx+","+value.ref+","+value.restep+","+value.relevel+","+value.pageNumber+")'>";
	                	result += "<span id='replypcmt_area"+value.num+"'></span>";
	                	result += "</span>";
	                	result += "</p>";
	               	}
	                result += "</div>";
                })//each
                $('#comments_area').html(result);
			},
			error : function(request, error) {
				alert("error");
				//error 발생이유
				alert("code : "+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			}
		}); //ajax
	}
	
	/* 댓글 유효성 검사 */
	function comments_check(f){
		if(f.content.value == ""){
			alert('내용을 입력하세요');
			return false;
		}
	}
	
	/* 댓글 답글달기 버튼 클릭 */
	function replypcmt(num, idx, ref, re_step, re_level, pageNumber){
		//1. 댓글 입력창 보여지기
		var replypcmt_area = "<form class='form-control' id='reply_form' action='/productscomments/user/reply.pcmt' method='post'>";
		replypcmt_area += "<input type='hidden' name='idx' value='"+idx+"'>";
		replypcmt_area += "<input type='hidden' name='ref' value='"+ref+"'>";
		replypcmt_area += "<input type='hidden' name='restep' value='"+re_step+"'>";
		replypcmt_area += "<input type='hidden' name='relevel' value='"+re_level+"'>";
		replypcmt_area += "<input type='hidden' name='pageNumber' value='"+pageNumber+"'>";
		replypcmt_area += "<input type='hidden' id='isSecret2' name='isSecret' value='N'>"; // 비밀글
		replypcmt_area += "<input type='text' name='content'>";
		replypcmt_area += "<input type='submit' class='btn btn-light value='등록' onclick='javascript:return comments_check(reply_form)'>";
		//비밀 댓글 설정
		replypcmt_area += "<div>";
		replypcmt_area += "<input type='checkbox' id='reply_secret2' name='reply_secret2' style='margin-bottom: 10px; margin-top: 10px; margin-right: 5px;' ";
		replypcmt_area += "onchange='javascript:reply_secret()'/>";
		replypcmt_area += "<label for='reply_secret2' style='cusor:pointer;'>비밀댓글로 작성</label></div>";
		
		$('#replypcmt_area'+num).html(replypcmt_area);
	}
	
	/* 댓글 답글달기 비밀글 설정 클릭 */
	function reply_secret(){
		//alert('1');
		if($('#reply_secret2').prop("checked")){
			$('#isSecret2').attr('value','Y');
		}else{
			$('#isSecret2').attr('value', 'N');
		} 
	}
	
	/* 거래완료 버튼 클릭 */
	function changeState(prd_num, pageNumber){
		//alert(prd_num);
		var result = confirm('거래 완료 상태로 바꾸시겠습니까?');
		if(result == true){ // 판매완료된 상품
			location.href = '/products/user/update.prd?num='+prd_num+'&state=change&pageNumber='+pageNumber;
		}
	}
	
	/* 게시글 삭제 버튼 클릭 */
	function deleteProducts(prd_num, pageNumber){
		var result = confirm('정말 삭제하시겠습니까?');
		if(result == true){ // 삭제 요청
			location.href = '/products/user/delete.prd?num='+prd_num+'&pageNumber='+pageNumber;
		}
	}
	
	/* 댓글 삭제 버튼 클릭 */
	function deleteComments(pcmt_num, pageNumber, idx){
		//alert(ccmt_num);
		//alert(pageNumber);
		var pcmt_result = confirm('정말 삭제하시겠습니까?');
		if(pcmt_result == true){
			location.href = '/productscomments/user/delete.pcmt?num='+pcmt_num+'&pageNumber='+pageNumber+'&idx='+idx;
		}
	}
	
	/* 댓글 수정 버튼 클릭 */
	function updateComments(pcmt_num, pageNumber, idx){
		//alert(pcmt_num);
		var pcmt_updateform = "<form class='form-control' id='comments_updateform' action='/productscomments/user/update.pcmt' method='post'>";
		pcmt_updateform += "<input type='text' name='content'>";
		pcmt_updateform += "<input type='hidden' name='num' value='"+pcmt_num+"'>";
		pcmt_updateform += "<input type='hidden' name='idx' value='"+idx+"'>";
		pcmt_updateform += "<input type='hidden' name='pageNumber' value='"+pageNumber+"'>";
		pcmt_updateform += "<input type='submit' value='확인' onclick='javascript:return comments_check(comments_updateform)'>";
		pcmt_updateform += "<input type='reset' value='취소' onclick='javascript:getAllComments()'>";
		pcmt_updateform += "</form>";
		
		$('#pcmt_update'+pcmt_num).html(pcmt_updateform);
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
		//alert($('input[name=writer]').val() + " / " + $('input[name=num]').val() );
		var getHeartval = $("#heartStatus").val();
    	//좋아요
    	if(Number(getHeartval) == 0) {
    		$.ajax({
    			type: 'POST',
    		    url: '/heart/user/products/insertHeart.ht',
    			data : {
    				input_userId: $('input[name=writer]').val(),    // 좋아요 클릭한 아이디
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
    		    url: '/heart/user/products/deleteHeart.ht',
    			data : {
    				input_userId: $('input[name=writer]').val(),    // 좋아요 클릭한 아이디
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

<div class="container-xxl py-5">
	<div class="container">
    	<div class="row g-5">
        	<div class="col-lg-6" data-wow-delay="0.1s">
            	<h1 class="display-5 mb-5" style="margin-left:250px;">${pb.name}</h1>
                    <!-- 이미지제외 설명 Start -->
                    <input type="hidden" name="num" id="num" value="${pb.productsnum}"/>
					<table class="table table-hover table-borded align-middle">
						<tr>
							<th scope="col">상품명</th>
							<td colspan="2" class="w-75">${pb.name}</td>
						</tr>
						<tr>
							<th scope="col">가격</th>
							<td><fmt:formatNumber value="${pb.price}" pattern="###,###" />원</td>
						</tr>
						<tr>
							<th scope="col">판매자<br>
							<td>${pb.seller}</td>
						</tr>
						<tr>
							<th scope="col">작성일</th>
							<td><fmt:formatDate value="${pb.inputdate}" type="date" dateStyle="medium" timeStyle="medium" pattern="yyyy-MM-dd hh:mm:ss"/></td>
						</tr>
							    
						<tr>
							<th scope="col">조회수</th>
							<td>${pb.readcount}</td>
						</tr>
						<tr>
							<th scope="col">상품 설명</th>
							<td height="300">${pb.info}</td>
						 </tr>
					</table>
                    <!-- //이미지제외 설명 Start -->
                    
                    <!-- 해당 글 작성자와 관리자만 보이게 설정 -->
                    <div align="right">
           			<c:if test="${pb.seller == loginId || loginId == 'admin'}">
           				<c:if test="${pb.state == 0}"> <input type="button" class="btn btn-success" value="거래완료" onclick="changeState(${pb.productsnum},${pageNumber})"> </c:if>
           				<input type="button" class="btn btn-success" value="수정" onclick="location.href='/products/user/update.prd?num=${pb.productsnum}&pageNumber=${pageNumber}'">
           				<input type="button" class="btn btn-success" value="삭제" onclick="deleteProducts(${pb.productsnum}, ${pageNumber})">
           			</c:if>
           			<!-- //해당 글 해당 글 작성자와 관리자 보이게 설정 -->
           			
           			<input type="button" class="btn btn-success" value="목록" onclick="location.href='/products/all/list.prd?pageNumber=${pageNumber}'">
           			<!-- 관리자 페이지 목록으로 돌아갈때 -->
           			<c:if test="${loginId == 'admin'}">
           				<br><br>
           				<input type="button" class="btn btn-success" value="관리자 페이지 목록" onclick="location.href='/products/admin/list.prd?pageNumber=${pageNumber}'">
           			</c:if>
           			<!-- // 관리자 페이지 목록으로 돌아갈때 -->
                    </div>
                </div>
                
                <div class="col-lg-6" data-wow-delay="0.5s" style="min-height: 450px;">
                	<!-- 상품 이미지 -->
                    <div class="position-relative rounded overflow-hidden h-100" align="center">
                    	<c:forEach var="prd_img" items="${fn:split(pb.image, ',')}">
           					<img src="<%=request.getContextPath()%>/resources/images/products/${prd_img}" width="80%"><br>
           				</c:forEach> 
                    </div>
                    <!-- //상품이미지-->
                    
	               <!-- 구매하기 버튼 (1.판매글 2.거래상태가 판매중 3.판매자가 아니면 보임) -->
	               <c:if test="${pb.kind == 'a' && pb.state == 0 && pb.seller != loginId}">
	               <div align="center">
	               		<input type="button" class="btn btn-success" id="apibtn" value="구매하기">
	               </div>
	               </c:if>
	               <!-- //구매하기 버튼 -->
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
            </div>
        </div>
        
        <!-- 댓글 입력창 -->
	    <form id="comments_form" action="/productscomments/user/insert.pcmt" method="post">
	    	<input type="hidden" name="idx" value="${pb.productsnum}"> <!-- 원글 번호 -->
		    <input type="hidden" name="writer" value="${loginId}"> <!-- 작성자 아이디 -->
		    <input type="hidden" name="pageNumber" value="${pageNumber}"> <!-- 페이지 -->
		    <input type="hidden" name="isSecret" value="N"> <!-- 비밀 댓글 여부 -->
		    
		    <div class="card mb-2">
			<div class="card-header bg-light">
			        <i class="fa fa-comment fa"></i> Comments
			</div>
			<div class="card-body">
				<ul class="list-group list-group-flush">
				    <li class="list-group-item">
					<div class="form-inline mb-2">
						<!-- <label for="replyId"><i class="fa fa-user-circle-o fa-2x"></i></label>
						<input type="text" class="form-control ml-2" placeholder="Enter yourId" id="replyId">
						<label for="replyPassword" class="ml-4"><i class="fa fa-unlock-alt fa-2x"></i></label>
						<input type="password" class="form-control ml-2" placeholder="Enter password" id="replyPassword"> -->
					</div>
					<textarea class="form-control" name="content" rows="3"></textarea>
					
					<!-- 비밀댓글 -->
					<div>
						<input type="checkbox" id="reply_secret" name="reply_secret" style="margin-bottom: 10px; margin-top: 10px; margin-right: 5px;" />
						<label for="reply_secret" style="cusor:pointer;">비밀댓글로 작성</label>
					</div>
					<!-- //비밀댓글 -->
					
					<input type="submit" class='btn btn-light' value="댓글달기" onclick="javascript:return comments_check(comments_form)">
				    </li>
				</ul>
			</div>
			</div>
		</form>
		<!-- //댓글 입력창-->
		
	   	<!-- 댓글 목록 -->
		<div class="my-3 p-3 bg-body rounded shadow-sm" id="comments_area">
			
		</div>
	   	<!-- //댓글 목록 -->
    </div>
    <!-- Contact End -->

<%@ include file ="../common/common_bottom.jsp" %>