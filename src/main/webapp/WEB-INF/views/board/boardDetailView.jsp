<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/common_top.jsp" %> 

    <!-- boardDetailView.jsp<br> -->

<style type="text/css">
	.err{
		color:red;
		font-weight: bold;
		font-size: 7px;
	}
</style>

<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.js"></script> 
<script type="text/javascript"> 
	
	$(document).ready(function(){ 
		
		//댓글 목록 가져오는 메서드
		getAllBoardComments();
		
		//댓글창의 비밀 체크 박스를 클릭하면 이곳으로 와서 isSecret을 'N'에서 'Y'로 바꿈
		$("#reply_secret").change(function(){
			if($(this).prop("checked")){
				$("input[name=isSecret]").val('Y');
			}else{
				$("input[name=isSecret]").val('N');
			}
		});
		
		/* 현재 로그인한 사용자의 해당 게시글 좋아요 클릭여부 하트이미지 on/off */
		heartHandler();
	});//ready
	
	
	//댓글 목록 가져오기
	function getAllBoardComments(){
		var userid = $('input[name=userid]').val();
		var pageNumber2 = $('input[name=pageNumber]').val(); //현재 페이지
		//console.log(userid) //아이디 출력해보기
		
		$.ajax({ 
			url : "/boardcomments/user/list.bcmt",
			type : 'post',
			data: { 
	        	idx : $('input[name=bnum]').val(), 
	        	pageNumber : $('input[name=pageNumber]').val() 
	        },
			success : function(data){
				var result = "<h6 class='border-bottom pb-2 mb-0'>댓글 목록</h6>";
				//data로 넘어온 값이 없으면 "등록된 댓글이 없습니다."
				if (data.length === 0 ) {
					result += "<tr><td colspan='5' align='center'>등록된 댓글이 없습니다.</td></tr>"
				} else { //댓글이 있으면
					$.each(data, function(bnum, value) { // 값이 여러개 일 때는 반복문 사용
						result += "<div class='d-flex text-body-secondary pt-3'>";
						//console.log("value" + value.userid)
						if(value.relevel > 0){ //대댓글일 경우 들어가도록 설정
		                	var wid = value.relevel*15;
		                	result += "<img src='../../../resources/images/comments/level.gif' width='"+wid+"' height='32'>";
							result += "<img src='../../../resources/images/comments/re.png' width='32' height='32'>";
		                }
						result += "<img class='bd-placeholder-img flex-shrink-0 me-2 rounded' src='../../../resources/images/comments/member.png' width='32' height='32'>";
	                	result += "<p class='pb-3 mb-0 small lh-sm border-bottom'>";
	                	
	                	//isdeleted가 'Y'이면(삭제되면)
						if (value.isdeleted === 'Y') { 
							result += "삭제된 댓글입니다";
		                	result += "</p>";
						} 
						//isSecret가 'Y'이면(비밀댓글 체크하면)
						else if(value.isSecret === 'Y'){
							result += "<strong class='d-block text-gray-dark'>@"+value.userid;
		                	
		                	if(value.userid == userid ){ // 댓글 작성자라면 수정, 삭제가 보임
		                		result += "&nbsp; [<a href='javascript:updateBoardComments("+value.num+","+pageNumber2+","+value.bnum+")'>수정</a>/";
		                		result += "<a href='javascript:deleteBoardComments("+value.num+","+pageNumber2+","+value.bnum+")'>삭제</a>]";
		                	}
		                	result += "</strong>";
		                	
		                	<%-- result += "<span id='bcmt_update"+value.num+"'><img src='<%=request.getContextPath()%>/resources/images/icon/comments_lock.png'>비밀 댓글입니다.<br>"; --%>
		                	//1.댓글 작성자 본인일때 2. 해당 게시글의 작성자일때 3. 관리자일때 => 댓글 비밀설정해도 볼 수 있음
		                	if(value.userid == userid || "${board.userid}" == userid || "admin" == userid || value.orgwriter == userid){
		                		result += "<span id='bcmt_update" + value.num + "'><img src='<%=request.getContextPath()%>/resources/images/icon/comments_lock.png' width='15' height='15'>" + value.content + "<br>";

		                	}else{ //위 3가지 경우에 해당되지 않는다면, 내용 대신 "비밀 댓글입니다."가 보임
		                		result += "<span id='bcmt_update" + value.num + "'><img src='<%=request.getContextPath()%>/resources/images/icon/comments_lock.png' width='15' height='15'> 비밀 댓글입니다.<br>";
		                	}      			                	
		                	result += "<font color='gray' size='1px'>("+value.regdate+")</font><br>";
		                	result += "<input type='button' class='btn btn-light' value='답글' ";
		                	result += "onclick='replybcmt("+value.num+","+value.bnum+","+value.ref+","+value.restep+","+value.relevel+","+value.pageNumber+")'>";
		                	result += "<span id='replybcmt_area"+value.num+"'></span>";
		                	result += "</span>";
		                	result += "</p>";
						} else{ // 비밀댓글 체크 안함
		                	result += "<strong class='d-block text-gray-dark'>@"+value.userid;
		                	
		                	if(value.userid == userid ){ // 댓글 작성자라면 수정, 삭제 보임
		                		result += "&nbsp; [<a href='javascript:updateBoardComments("+value.num+","+pageNumber2+","+value.bnum+")'>수정</a>/";
		                		result += "<a href='javascript:deleteBoardComments("+value.num+","+pageNumber2+","+value.bnum+")'>삭제</a>]";
		                	}
		                	result += "</strong>";
		                	
		                	result += "<span id='bcmt_update"+value.num+"'>"+value.content+"<br>";
		                	result += "<font color='gray' size='1px'>("+value.regdate+")</font><br>";
		                	result += "<input type='button' class='btn btn-light' value='답글' ";
		                	result += "onclick='replybcmt("+value.num+","+value.bnum+","+value.ref+","+value.restep+","+value.relevel+","+value.pageNumber+")'>";
		                	result += "<span id='replybcmt_area"+value.num+"'></span>";
		                	result += "</span>";
		                	result += "</p>";
		               	} //else
						 result += "</div>";
	                })//each								
				}//else
				$('#comments_area').html(result);
			},//success
			error : function(request, error) {
				alert("error");
				alert("code : "+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			}
		});//ajax				
	}//getAllBoardComments
	
	
	//댓글의 유효성 검사
	function board_comments_check(f){
		if(f.content.value == ""){
			alert('내용을 입력하세요');
			return false;
		}
	}
	
	//'글'의 삭제버튼을 클릭
	function deleteboard(num, pageNumber){
		
		var isDel = confirm("정말 삭제하시겠습니까?"); //확인:true, 취소:false
		if(isDel == true){
			location.href="/board/user/delete.br?num="+num+"&pageNumber="+pageNumber;
		}			
	}	
	
	//'댓글'의 삭제 버튼 클릭
	function deleteBoardComments(num, pageNumber, bnum){
		//alert("삭제할 댓글의 번호: "+num);
		var bcmt_result = confirm('정말 삭제하시겠습니까?');
		if(bcmt_result == true){
			location.href = '/boardcomments/user/delete.bcmt?num='+num+'&pageNumber='+pageNumber+'&bnum='+bnum;
		}
	}
	
	//답글달기 버튼 클릭
	function replybcmt(num, bnum, ref, re_step, re_level, pageNumber){
		//댓글 입력창 보여지기
		var replybcmt_area = "<form class='form-control' id='reply_form' action='/boardcomments/user/reply.bcmt' method='post'>";
		replybcmt_area += "<input type='hidden' name='bnum' value='"+bnum+"'>";
		replybcmt_area += "<input type='hidden' name='ref' value='"+ref+"'>";
		replybcmt_area += "<input type='hidden' name='restep' value='"+re_step+"'>";
		replybcmt_area += "<input type='hidden' name='relevel' value='"+re_level+"'>";
		replybcmt_area += "<input type='hidden' name='pageNumber' value='"+pageNumber+"'>";
		replybcmt_area += "<input type='hidden' id='isSecret2' name='isSecret' value='N'>"; // 비밀 댓글
		replybcmt_area += "<input type='text' name='content'>";
		replybcmt_area += "<input type='submit' class='btn btn-light' value='등록' onclick='javascript:return board_comments_check(reply_form)'>";
		
		//비밀 댓글 설정
		replybcmt_area += "<div>";
		replybcmt_area += "<input type='checkbox' id='reply_secret2' name='reply_secret2' style='margin-bottom: 10px; margin-top: 10px; margin-right: 5px;' ";
		replybcmt_area += "onchange='javascript:board_reply_secret()'/>";
		replybcmt_area += "<label for='reply_secret2' style='cusor:pointer;'>비밀댓글로 작성</label></div>";
		
		$('#replybcmt_area'+num).html(replybcmt_area);
	}
	
	/* 댓글 답글달기 비밀글 설정 클릭 */
	function board_reply_secret(){
		//alert('1');
		if($('#reply_secret2').prop("checked")){
			$('#isSecret2').attr('value','Y');
		}else{
			$('#isSecret2').attr('value', 'N');
		} 
	}
	
	//댓글의 수정 버튼 클릭
	function updateBoardComments(bcmt_num, pageNumber, bnum){
		//alert("수정할 댓글의 번호: "+bcmt_num);
		var bcmt_updateform = "<form class='form-control' action='/boardcomments/user/update.bcmt' method='post'>";
		bcmt_updateform += "<input type='text' name='content'>";
		bcmt_updateform += "<input type='hidden' name='num' value='"+bcmt_num+"'>";
		bcmt_updateform += "<input type='hidden' name='bnum' value='"+bnum+"'>";
		bcmt_updateform += "<input type='hidden' name='pageNumber' value='"+pageNumber+"'>";
		bcmt_updateform += "<input type='submit' value='확인'>";
		bcmt_updateform += "<input type='reset' value='취소' onclick='javascript:getAllBoardComments()'>";
		bcmt_updateform += "</form>";
		
		$('#bcmt_update'+bcmt_num).html(bcmt_updateform);
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
		
		var getHeartval = $("#heartStatus").val();
    	//좋아요
    	if(Number(getHeartval) == 0) {
    		$.ajax({
    			type: 'POST',
    		    url: '/heart/user/board/insertHeart.ht',
    			data : {
    				input_userId: $('input[name=userid]').val(),    // 좋아요 클릭한 아이디
    				input_num	: $('input[name=bnum]').val()       // 게시글 번호
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
    		    url: '/heart/user/board/deleteHeart.ht',
    			data : {
    				input_userId: $('input[name=userid]').val(),    // 좋아요 클릭한 아이디
    				input_num	: $('input[name=bnum]').val()       // 게시글 번호
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
            <h1 class="display-3 text-white mb-4 animated slideInDown">자유 게시판</h1>
            <nav aria-label="breadcrumb animated slideInDown">
                <ol class="breadcrumb justify-content-center mb-0">
                </ol>
            </nav>
        </div>
    </div>
<!-- Page Header End -->

<div class="container-xxl py-5">
	<div class="container">
        <div class="text-center mx-auto wow fadeInUp" data-wow-delay="0.1s" style="max-width: 500px;">
        	<h2 class="display-5 mb-5">글내용 보기</h2>
        </div> 
<table class="table">
	<tr>
		<th width="20%" align="center">글번호</th>
		<td>${board.num }</td>		
	</tr>
	<tr>
		<th align="center">작성자</th>
		<td>${board.userid}</td>
	</tr>
	<tr>
		<th align="center">작성일</th>
		<td>
			<fmt:parseDate var="newDay" value="${board.regdate}" pattern="yyyy-MM-dd"/>				
			<fmt:formatDate var="fNewDay" value="${newDay}" pattern="yyyy-MM-dd"/>
			${fNewDay }
		</td>
	</tr>
	<tr>
		<th align="center">조회수</th>
		<td>${board.readcount }</td>
	</tr>
	<tr>
		<th align="center">글제목</th>
		<td>${board.subject }</td>
	</tr>
	<tr>
		<th align="center">글내용</th>
		<td>${board.content }</td>
	</tr>
	<tr>
		<th align="center">이미지</th>
		<td>
			<img src="<%=request.getContextPath()%>/resources/images/board/${board.image}" width="330" height="360">		
		</td>
	</tr>
	<tr>
		<td colspan="2" align="center">			
			<!-- 수정, 삭제 버튼은 작성자와 관리자 에게만 보이도록 -->
			<c:if test="${board.userid == userid || 'admin' == userid}">
				<input type="button" value="수정" class="btn btn-success" onClick="location.href='/board/user/update.br?num=${board.num}&pageNumber=${pageNumber}'">
				<input type="button" value="삭제" class="btn btn-success" onClick="deleteboard('${board.num}','${pageNumber}')">
			</c:if>	
			<!-- //수정, 삭제 버튼은 글 작성자에게만 보이도록 -->
				<input type="button" value="목록" class="btn btn-success" onclick="location.href='/board/all/list.br?pageNumber=${pageNumber}'">		
		</td>
	</tr>
</table>
</div>
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
<form action="/boardcomments/user/insert.bcmt" method="post">
	<input type="hidden" name="bnum" value="${board.num}"> 
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
					<textarea class="form-control" name="content" rows="3" style="resize: none;"></textarea>
					<form:errors cssClass="err" path="content"/> <!-- 유효성검사 -->
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
	</div>
</form>
<!-- //댓글 입력창 -->
		
	
	<!-- 댓글 목록 출력 -->
	<div class="my-3 p-3 bg-body rounded shadow-sm" id="comments_area">
			
	</div>
	<!-- //댓글 목록 출력 -->
	   	

<%@ include file="../common/common_bottom.jsp"%>
