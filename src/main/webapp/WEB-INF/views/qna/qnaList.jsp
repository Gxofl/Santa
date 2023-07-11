<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 권한에 따른 페이지 열람을 위한 라이브러리 추가 -->
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!-- qnaList.jsp -->
<%@ include file="../common/common_top.jsp" %>
	<style type="text/css">
		html,body {
		  font-family: Helvetica, Arial, sans-serif;
		  margin: 0;
		}
		.panel-faq-container {
		  margin-bottom: -16px;
		}
		.panel-faq-title {
		  cursor: pointer;
		}
		.panel-faq-answer {
		  height: 0;
		  overflow: hidden;
		  /* 변화가 시작되는 쪽에다가 transition 적용해준다 0 -> 300px 
		  왜? 닫기 버튼을 누를 때 변화가 티남 */
		  transition: all 1s;
		}
		.activeList {
		  display: block;
		  /* 높이를 정해줘야지만 transition이 적용됨 */
		  height: 300px;
		}
		#uploadimage{
			width:20px;
		}
	</style>
	
	<!-- Page Header Start -->
    <div class="container-fluid page-header py-5 mb-5 wow fadeIn" data-wow-delay="0.1s">
        <div class="container text-center py-5">
            <h1 class="display-3 text-white mb-4 animated slideInDown">QnA</h1>
        </div>
    </div>
    <!-- Page Header End -->
    	
    	<!-- qna 목록 -->
	    <div class="container-fluid py-5">
	        <div class="container">
	            <div class="text-center mx-auto" style="max-width: 500px;">
	                <h1 class="display-5 mb-5">Q&A</h1>
	            </div>
		 				<!-- 검색창 -->
				            <nav class="navbar navbar-light bg-light">
				  				<div class="container" style="display:table-cell; vertical-align:middle;">
				   				 	<form class="d-flex" action="/qna/all/list.qna" method="get">
				   				 		<div class="col-sm-2">
					   				 		<select name="whatColumn" class="form-select">
							    				<option value="">전체</option>
							    				<option value="제목">제목</option>
							    				<option value="카테고리">카테고리</option>
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
		
		<table id="qna-table" class="table table-hover table-borded" border="1">
			<thead>
        		<tr align="center">
        			<th>번호</th>
        			<th>카테고리</th>
        			<th>질문 제목</th>
        			<th>작성자</th>
        			<th>날짜</th>
        		</tr>
			</thead>
			<tbody>
			<c:forEach var="i" begin="0" end="${fn:length(qnaLists)-1}" varStatus="status">
			<tr align="center">
				<td>${(pageInfo.pageNumber-1)*pageInfo.limit+status.count} </td>
				<td>${qnaLists[i].qnacategory}</td>
				<td align="left" width="50%" >
				<div class="panel-faq-container">
					<p class="panel-faq-title">${qnaLists[i].qnasubject}</p>
					<div class="panel-faq-answer">
						<p>Q : ${qnaLists[i].qnaquestion}</p>
						<!-- 상품 이미지 -->
						<div>
						<c:if test="${qnaLists[i].qnaimage!=null}">
                    	<c:forEach var="qna_img" items="${fn:split(qnaLists[i].qnaimage, ',')}">
           					<img src="<%=request.getContextPath()%>/resources/images/qna/${qna_img}" width="100px" height="100px">
           				</c:forEach> 
						</c:if>
						</div>
                    	<!-- //상품이미지-->
						<br>
						<!-- 답변이 null이 아닐 때만 출력 -->
						<c:if test="${qnaLists[i].qnaanswer != null}">
						<p>A : ${qnaLists[i].qnaanswer}</p>
						</c:if>
						
						<!-- 로그인한 id가 admin일 때 현재 글 삭제, 답변이 없을 때 답변 등록 가능-->
						<!-- 로그인한 id가 작성자와 같을 때 현재 글 수정 가능-->
						<c:choose>
						<c:when test="${principal.getName() == 'admin'}">
							<c:set var="qnanum" value="${qnaLists[i].qnanum}"/>
							<button class="btn btn-success" onClick="return deleteQna('${qnaLists[i].qnanum}')">질문삭제</button>
    						<button class="btn btn-success" onClick="location.href='/qna/admin/insertAnswer.qna?qnanum=${qnaLists[i].qnanum}&pageNumber=${pageNumber}'">답변등록/수정</button>
						</c:when>
						<c:when test="${principal.getName() == qnaLists[i].usersid}">
							<c:if test="${qnaLists[i].qnaanswer == null}">
							<form action="/qna/user/update.qna" method="get">
								<input type="hidden" name="qnanum" value="${qnaLists[i].qnanum}">
    							<input type="submit" value="질문수정" class="btn btn-success" style="float:left; margin-right:5px;">
							</form>
    							<button class="btn btn-success" onClick="return deleteQna('${qnaLists[i].qnanum}')">질문삭제</button>
							</c:if>
						</c:when>
						</c:choose>
						
					</div>
				</div>
				</td>
          		<td>${qnaLists[i].usersid}</td>
          		<td>
          			<fmt:formatDate var="fdate" value="${qnaLists[i].qnadate}" pattern="yyyy-MM-dd"/>
          			${fdate }
          		</td>
        	</tr>
			</c:forEach>
			</tbody>
		</table>
		<!-- 페이지 표시 -->
		<div align="center">
		 	${pageInfo.pagingHtml}
		</div>
		<!-- //페이지 표시 -->
    	<!-- 모두 닫기/질문 등록 버튼 -->
    	<div class="container">
    	<div align="right">
        <button id="btn-all-close" class="btn btn-success">Q&A 모두 닫기</button>
        <button class="btn btn-success" onClick="location.href='/qna/user/insertQuestion.qna'">질문등록</button>
        </div>
        </div>
    	<!-- //모두 닫기/질문 등록 버튼 -->
		</div>
    	<!-- //qna 목록 -->
    	</div>

<script type="text/javascript">
			window.onload = () => {
		  // panel-faq-container
		  const panelFaqContainer = document.querySelectorAll(".panel-faq-container"); // NodeList 객체
		  
		  // panel-faq-answer
		  let panelFaqAnswer = document.querySelectorAll(".panel-faq-answer");

		  // btn-all-close
		  const btnAllClose = document.querySelector("#btn-all-close");
		  
		  // 반복문 순회하면서 해당 Qna제목 클릭시 콜백 처리
		  for( let i=0; i < panelFaqContainer.length; i++ ) {
		    panelFaqContainer[i].addEventListener('click', function() { // 클릭시 처리할 일
		    	// FAQ 제목 클릭시 -> 본문이 보이게끔 -> active 클래스 추가
		      panelFaqAnswer[i].classList.toggle('activeList');
		    });
		  };
		  
		  btnAllClose.addEventListener('click', function() {
		    // 버튼 클릭시 처리할 일  
		    for(let i=0; i < panelFaqAnswer.length; i++) {
		        panelFaqAnswer[i].classList.remove('activeList');
		    };
		  });
		}
			//현재 글 삭제를 눌렀을 때=>deleteqna
			function deleteQna(qnanum){
				
				if(!confirm('정말 삭제하시겠습니까?')){
					//취소를 눌렀을 때
					//돌아가기
					return;
				}else{
					//확인을 눌렀을 때
					//delete 컨트롤러로 이동
					location.href="/qna/user/deleteQna.qna?qnanum="+qnanum+"&pageNumber=${pageNumber}";
				}//if~else
			}//deleteqna end

</script>
<%@ include file="../common/common_bottom.jsp" %>