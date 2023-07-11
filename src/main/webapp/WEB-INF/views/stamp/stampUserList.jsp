<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 마이페이지-완등내역-완등내역 클릭했을 때 -->
<%@ include file="../common/common_top.jsp"%>
<%@ include file="../common/common_nav_myPage.jsp"%>
<!-- stampList.jsp -->
<style>
		.tab-wrapper {
		  position: relative;
		  height: 270px;
		  clear: both;
		  padding: 6px 15px 0;
		  overflow: hidden;
		}
		
		.tab-item {
		  float: left;
		  
		  [type="radio"] {
		    display: none;
		    
		    &:checked ~ .tab-label {
		      background: #fff;
		      border-bottom: 1px solid #fff;
		      color: #2a2b39;
		      z-index: 55;
		      
		      & ~ .tab-content {
		        z-index: 50;  
		      }
		    }
		  }
		}
		
		.tab-label {
		  display: flex;
		  align-items: center;
		  position: relative;
		  left: 1px;
		  bottom: 2px;
		  height: 36px;
		  padding-right: 24px;
		  padding-left: 24px;
		  background: #d9dbe6;
		  border: 1px solid #caccdb;
		  margin-left: 2px;
		  border-top-left-radius: 5px;
		  border-top-right-radius: 5px;
		  font-weight: bold;
		  cursor: pointer;
		  font-size: 15px;
		  color: #666;
		}
		
		.tab-content {
		  position: absolute;
		  top: 41px;
		  right: 0;
		  left: 0;
		  bottom: 0;
		  padding: 15px;
		  background: #fff;
		  border-top: 1px solid #caccdb;
		  overflow: auto;
		}
		.grid-container {
			display: grid;
			grid-template: repeat(5, 60px) / repeat(5, 30px);
			grid-auto-flow: row;
			grid-template-columns: repeat(3, 15rem);
			grid-auto-rows: 15rem;
			grid-gap: 10px;
		}
		.item {
			padding: 0.5rem 2rem;
			transition: all 0.3s ease;
		}
		
		/*div변경과 관련 코드*/
		.main input[type=radio] {
			display: none;
		}
		#tab-1:checked ~ .tab label:nth-child(1),
		#tab-2:checked ~ .tab label:nth-child(2),
		#tab-3:checked ~ .tab label:nth-child(3) {
			background-color: rgba(0, 0, 0, 0.2);
		  box-shadow: none;
		}
		.content > div {
			display: none;
		}
		#tab-1:checked ~ .content div:nth-child(1),
		#tab-2:checked ~ .content div:nth-child(2),
		#tab-3:checked ~ .content div:nth-child(3)  {
			display: block;
		}
		.main {
		  margin: 0 auto;
		  main-width:1380px;
		  max-width: 100%;
		}
		.tab {
		  overflow: hidden;
		}
		.tab label {
		    font-size: 18px;
		    cursor: pointer;
		    float: left;
		    width: 25%;
		    text-align: center;
		    padding: 15px 0;
		    text-transform: uppercase;
		    font-weight: bold;
		    letter-spacing: 2px;
		    user-select: none;
		    -webkit-user-select: none;
		}
		.content {
		  background-color: rgba(0, 0, 0, 0.2);
		  min-height: 250px;
		  overflow:scroll;
		}
		.content > div{
		   padding: 30px;
		   line-height: 1.5;
		   font-size: 17px;
		}
		#stamp-pan{
			border-radius: 12px;
			filter: drop-shadow(5px 5px 5px #A9A9A9);
		}
</style>
		<div class="container">
			<div class="text-center mx-auto mt-5" style="max-width: 500px;">
				<h1 class="display-5 mb-5">완등 내역</h1>
			</div>
			
    <!-- Stamp Start -->
	            <div class="d-flex justify-content-center">
			    	<!-- stamp판 -->
			    	<!-- 누적 스탬프 계산 -->
			    	<c:set var="applyCount" value="0"/>
						<c:forEach var="sl" items="${stampList}">
							<c:if test="${sl.stampapply == 1}">
							   <c:set var="applyCount" value="${applyCount + 1}"/>
							</c:if>
						</c:forEach>
			    	<div class="container d-flex justify-content-center mb-5">
			    		<img id="stamp-pan" alt="스탬프판" width="550px" src="<%=request.getContextPath()%>/resources/images/stamp/stamp_${applyCount}.png">
			    	</div>
			    	<!-- //stamp판 -->
					
				    <div class="row" style="text-align:center;">
				    	<!-- stamp 현황 -->
				    		<div class="card m-2 align-middle" style="width: 18rem; height:7rem;">
							  <div class="card-body">
							    <h5 class="card-title">보유한 스탬프</h5>
							    <h3 class="card-text">
							    	${applyCount}
							    	/10
							    </h3>
							  </div>
							</div>
				    	<!-- //stamp 현황 -->
				    	<!-- 현재 등급 -->
				    		<div class="card m-2" style="width: 18rem; height:7rem;">
							  <div class="card-body">
							    <h5 class="card-title">${principal.getName()}님의 등급</h5>
							    <h3 class="card-text">
							    	<!-- <img alt="등급아이콘" src=""> -->
							    	<c:if test="${userRole == 'r01'}">
							    		: 일반
							    	</c:if>
							    	<c:if test="${userRole == 'r02'}">
							    		: 우수
							    	</c:if>
							    	<c:if test="${userRole == 'r99'}">
							    		: 관리자
							    	</c:if>
							    	등급
							    </h3>
							  </div>
							</div>
				    	</div>
				    	<!-- //현재 등급 -->
				 </div>
				    	
    	<!-- 완등/신청 목록 -->
    	<div class="container col-10">
			<div class="tab-wrapper">
			  <div class="tab-item">
			    <input type="radio" id="tab1" name="tabGroup" checked>
			    <label for="tab1" class="tab-label">완등 완료</label>
			    <div class="tab-content">
			    	<c:forEach var="cl" items="${certList}" varStatus="st">
			    		<c:choose>
						<c:when test="${cl.stampapply == 1 }">
							<div class="item">
							- 
							<a href="/stamp/user/usercertimage.stp?stampimage=${cl.stampimage}&mountainname=${cl.mountainname}&userid=${principal.getName()}&stampreject=${cl.stampreject}" target="_blank" target="_blank" onClick="window.open(this.href, '_blank', 'width=800, height=600'); return false;">
							${cl.mountainname}
							</a>
							</div>
						</c:when>
			    		</c:choose>
					</c:forEach>
			    </div>
			  </div>
			  
			  <div class="tab-item">
			    <input type="radio" id="tab2" name="tabGroup">
			    <label for="tab2" class="tab-label">완등신청 현황</label>
			    <div class="tab-content">
			    	<c:forEach var="cl" items="${certList}" varStatus="st">
			    		<c:choose>
						<c:when test="${cl.stampapply != 1 }">
							<div class="item">
							- 
							<a href="/stamp/user/usercertimage.stp?stampimage=${cl.stampimage}&mountainname=${cl.mountainname}&userid=${principal.getName()}&stampreject=${cl.stampreject}" target="_blank" target="_blank" onClick="window.open(this.href, '_blank', 'width=800, height=600'); return false;">
							${cl.mountainname}
							<c:if test="${cl.stampapply == 0 }"><font color="orange">(승인대기)</font></c:if>
							<c:if test="${cl.stampapply == 2 }"><font color="red">(승인거절)</font></c:if>
							</a></div>
						</c:when>
			    		</c:choose>
					</c:forEach>
			    </div>
			  </div>
			</div>
    	</div>
    	</div>
    	<!-- //완등/신청 목록 -->
    	<!-- Stamp End -->
<%@ include file="../common/common_bottom.jsp" %>