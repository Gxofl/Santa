<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/common_top.jsp" %>  
<style type="text/css">
	.err{
		color:red;
		font-weight: bold;
		font-size: 7px;
	}
</style>

<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.js"></script>
<script type="text/javascript">
	
/* 파일 업로드 */
function boardfilechange() {
	
	/* 파일 업로드 개수제한 */
	/*
	var $fileUpload = $("input[type='file']");
	if (parseInt($fileUpload.get(0).files.length) > 10) {
		alert("이미지는 10개 이하만 올릴 수 있습니다");
		$("input[type='file']").val('');
	}*/
	
	/* 업로드한 이미지 미리보기*/
	var fileTag = document.querySelector('input[name=upload]');
	var divTag = document.querySelector('#img_preview');
	
	if(fileTag.files.length>0){ // 파일을 올렸을 때
		for(var i=0; i<fileTag.files.length; i++){
			var reader = new FileReader();
			reader.onload = function(data){
				//1. 이미지 태그 만들기
				var imgTag = document.createElement('img');
				
				//2. 이미지 태그에 속성 넣기
				imgTag.setAttribute('src', data.target.result);
				imgTag.setAttribute('width', '250');
				imgTag.setAttribute('height', '150');
				
				//3. 이미지 태그 div에 추가하기
				divTag.appendChild(imgTag);
			}
			reader.readAsDataURL(fileTag.files[i]);
		}//for
	}//if
	else{ //취소버튼을 눌렀을 때
		//div안에 내용 비우기
		divTag.innerHTML = "";
	}
	
	//reset 확인
	function resetCheck(){
		var check = confirm("정말 초기화 하시겠습니까?");
		 if(!check){
			return false;
		}
	}
	
}

</script>


    <!-- boardInsertForm.jsp<br> -->
    
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
    
<%
	String[] cateArr = {"정보","후기","추천","잡담"};
%>


<!-- Quote Start -->
<form:form commandName="board" action="/board/user/insert.br" method="post" enctype="multipart/form-data">
	<div class="container-fluid py-5">
		<div class="container">
			<div class="text-center mx-auto wow fadeInUp" data-wow-delay="0.1s" style="max-width: 600px;">
				<p class="fs-5 fw-bold text-primary"></p>
				<h1 class="display-5 mb-5">자유 게시글 작성</h1>
			</div>
			<div class="row justify-content-center">
				<div class="col-lg-9">
					<div class="bg-light rounded p-4 p-sm-5 wow fadeInUp" data-wow-delay="0.1s">
						<div class="row g-3">
								                       
                            <!-- 작성자 -->
                            <!-- <div class="col-11" style="float: left;"> -->
							<div class="col-10">                     		
                                <div class="form-floating">
                                    <input type="text" class="form-control border-0" name="userid" id="userid" value="${userId}" readonly>
                                	<label for="userid">작성자</label>
                                </div>
                            </div>
                            <!-- //작성자 -->
														
							<!-- 카테고리 -->
							<div class="col-10">              		                  		
                                <div class="form-floating">  
                                	<select name="category" id="category" class="form-select">
										<option value="">선택하세요</option>
										<c:forEach var="cate" items="<%=cateArr%>">
											<option value="${cate}" <c:if test="${board.category == cate}">selected</c:if>>${cate}
										</c:forEach>
									</select>
								<label for="category">카테고리</label>
								<form:errors cssClass="err" path="category" />
                                </div>
                            </div>       
                            <!-- //카테고리 -->
                            
                            <!-- 글제목 -->
                            <div class="col-10">
                                <div class="form-floating">
                                    <input type="text" class="form-control border-0" name="subject" id="subject" value="${board.subject}"> 
									<form:errors cssClass="err" path="subject" />
									<label for="subject">글 제목</label>
                                </div>
                            </div>
                            <!-- //글제목 -->
                            
                            <!-- 글내용 -->
                            <div class="col-10">
                               	<div class="form-floating"> <!--  -->
                                    <textarea name="content" class="form-control border-0" id="content" style="height:280px; resize:none;">${board.content}</textarea>
                                    <label for="content">글 내용</label>
                                    <form:errors cssClass="err" path="content" />
                                </div>
                            </div>
                            <!-- //글내용 -->
                            
                            <!-- 이미지 -->
                            <div class="col-10">
                            	<h6>이미지 첨부</h6>
                            	<input type="file" class="form-control" name="upload" id="upload" value="${board.image}" onchange="boardfilechange()">                           	
		                    	<input type="hidden" name="image">
                            	<form:errors cssClass="err" path="image"/> 
                            	<!-- 이미지 미리보기 -->
		                    	<div id="img_preview" style="height:300">
		                    	
		                    	</div>
		                    	<!-- //이미지 미리보기 -->                        	
                            </div><br>
                            <!-- //이미지 -->

							<div class="col-10 text-center">                          	
                            	<input type="submit" value="글쓰기" class="btn btn-success">
                            	<input type="reset" value="다시작성" class="btn btn-success" onclick="return resetCheck()"> 
                            	<input type="button" value="목록" class="btn btn-success" onclick="location.href='/board/all/list.br?pageNumber=${pageNumber}'">
                            </div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</form:form>
<!-- //Quote Start -->

<%@ include file="../common/common_bottom.jsp"%> 
