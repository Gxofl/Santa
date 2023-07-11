<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/common_top.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.js"></script>
<script type="text/javascript">
	/* 구분선택시 가격창 비활성화 */
	function kind_change(f){
		var sel_ind = f.kind.selectedIndex;
		//alert(sel_ind);
		if(sel_ind == 0){
			$('#price').attr('placeholder','price')
		}else if(sel_ind == 1){ // 판매일때
			$('#price').removeAttr('readonly');
			$('#price').attr('value','');
			$('#price').attr('placeholder','price')
		}else if(sel_ind == 2){ // 나눔일때
			$('#price').attr('readonly','readonly');
			$('#price').attr('value', '0');
		}else if(sel_ind == 3){ // 교환일때
			$('#price').attr('readonly','readonly');
			$('#price').attr('value', '0');
		}
	}
	
	/* 파일 업로드 */
	function filechange() {
		/* 파일 업로드 개수제한 */
		var $fileUpload = $("input[type='file']");
		if (parseInt($fileUpload.get(0).files.length) > 4) {
			alert("이미지는 4개 이하만 올릴 수 있습니다");
			$("input[type='file']").val('');
			
		}else{ // 업로드 개수 제한과 상관없을 때
			
			/* 업로드한 이미지 미리보기*/
			var fileTag = document.querySelector('input[name=upload]');
			var divTag = document.querySelector('#img_preview');
			
			if(fileTag.files.length>0){ // 파일을 올렸을 때
				
				divTag.innerHTML = ""; // 원래 이미지 미리보기 삭제하기
			
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
		}
	}
	
	/* reset 확인 */
	function resetconfirm(){
		var check = confirm("정말 초기화 하시겠습니까?");
		 if(!check){
			return false;
		}
	}
</script>
<style>
	.err{
		font-size : 7px;
		color: red;
		font-weight : bold;
	}
</style>

<!-- SignUp Start -->
    <form:form name="prdform" commandName="pb" action="update.prd" method="post" enctype="multipart/form-data">
    
    	<!-- prdnum & 페이지 hidden -->
    	<input type="hidden" name="productsnum" value="${pb.productsnum}">
    	<input type="hidden" name="pageNumber" value="${pageNumber}">
    	<!-- //prdnum & 페이지 hidden -->
    	
    <div class="container-fluid py-5">
        <div class="container">
            <div class="text-center mx-auto wow fadeInUp" data-wow-delay="0.1s" style="max-width: 500px;">
                <h1 class="display-5 mb-5">거래 게시글 수정</h1>
            </div>
            <div class="row justify-content-center">
                <div class="col-lg-7">
                    <div class="bg-light rounded p-4 p-sm-5 wow fadeInUp" data-wow-delay="0.1s">
                        <div class="row g-3">
                        	
                        	<!-- 구분 -->
                        		<div class="col-9">
                        			<div class="form-floating">
			                        	<select name="kind" id="kind" class="form-select border-0" onchange="kind_change(prdform)">
			                                <option value="">판매/나눔/교환</option>
			                                <option value="a" <c:if test="${pb.kind == 'a'}">selected</c:if>>판매</option>
			                                <option value="b" <c:if test="${pb.kind == 'b'}">selected</c:if>>나눔</option>
			                                <option value="c" <c:if test="${pb.kind == 'c'}">selected</c:if>>교환</option>
			                            </select>
			                            <label for="kind">판매/나눔/교환</label>
			                        </div>
		                           	<form:errors cssClass="err" path="kind"/>
		                        </div>
                        	<!-- //구분 -->
                        	
                        	<!-- 상품명 -->
                        	<div class="col-9">
                        		<div class="form-floating">
	                                <input type="text" class="form-control border-0" name="name" id="name" placeholder="name" value="${pb.name}">
	                                <label for="name">상품명</label>
	                            </div>
	                            <form:errors cssClass="err" path="name"/>
                        	</div>
                        	<!-- 상품명 -->
                        	
                        	<!-- 가격 -->
                        	<div class="col-9">
                        		<div class="form-floating">
	                                <input type="text" class="form-control border-0" name="price" id="price" value="${pb.price}"
	                                	<c:if test="${pb.kind == 'b' || pb.kind == 'c' }">disabled</c:if>>
	                                <label for="price">가격</label>
	                            </div>
	                            <form:errors cssClass="err" path="price"/>
	                        </div>
                        	<!-- //가격 -->
                        	
                        	<!-- 상품설명 -->
                        	<div class="col-12">
	                            <div class="form-floating">
	                                  <textarea class="form-control border-0" placeholder="Leave a message here" name="info" id="info" style="height: 150px; resize:none;">${pb.info}</textarea>
	                                  <label for="info">상품 설명</label>
	                            </div>
	                           	<form:errors cssClass="err" path="info"/>
	                        </div>
                        	<!-- //상품설명 -->
                        	
                        	<!-- 이미지업로드 -->
                        	<div class="col-12">
		                    		<div class="mb-2 mx-1"><b>상품 사진 </b></div>
		                    		<input type="file" class="form-control" multiple="multiple" name="upload" value="${pb.upload}" onchange="filechange()">
		                    		<span style="font-size:10px; color: gray;">※첨부파일은 최대 10개까지 등록이 가능합니다.</span><br>
		                    		<input type="hidden" name="upload2" value="${pb.upload2}"/> <!-- 기존 이미지 -->
		                    		<form:errors cssClass="err" path="image"/>
	                    		
		                    		<input type="hidden" value="${pb.image}">
		                    		<!-- 이미지 미리보기 -->
		                    		<div id="img_preview" style="height:300">
		                    			<!-- 원래 업로드 이미지 보여지기 -->
		                    			<c:set var="array" value="${fn:split(pb.upload2, ',')}" />
		                    			<c:forEach var="org_img" items="${array}">
		                    				<img src="<%=request.getContextPath()%>/resources/images/products/${org_img}" width="250" height="150">
		                    			</c:forEach>
		                    		</div>
		                    		<!-- //이미지 미리보기 -->
	                		</div>
                        	<!-- //이미지업로드 -->
                        	
                        	<!-- 버튼 -->
                        	<div class="col-12 text-center">
                        		<input type="submit" class="btn btn-success" value="수정하기">
                            	<input type="reset" class="btn btn-success" value="다시작성" onclick="return resetconfirm()">
                            	<input type="button" class="btn btn-success" value="목록" onclick="location.href='/products/all/list.prd'">
	                        </div>
	                        <!-- //버튼 -->
	                        
                        </div><!--//row  -->
                    </div><!-- //bg-light -->
                </div><!-- //col-lg-7 -->
            </div><!-- //row justify-content-center -->
        </div><!-- //container -->
    </div><!-- //container-fluid -->
    </form:form>
    <!-- SignUp End -->


<%@ include file="../common/common_bottom.jsp"%>