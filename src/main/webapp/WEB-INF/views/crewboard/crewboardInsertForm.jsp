<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/common_top.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.js"></script>
<script type="text/javascript">
	function largesmall(f){
		var sel_ind = f.crewname.selectedIndex;
		//var crewbean = "<c:out value='${myCrew}'/>"; 문자열로 인식됨
		//var crewbean = '${myCrew}';
		//alert(crewbean);
		
		var largelist = new Array();
		var smalllist = new Array();
	
		<c:forEach items="${myCrew}" var="item">
			largelist.push("${item.large}");
			smalllist.push("${item.small}");
		</c:forEach>
		
		if(sel_ind == 0){ // 첫 옵션을 선택하면 들어왔던 값 다 지우기
			$('#large').removeAttr('value');
			$('#small').removeAttr('value');
			$('#large_view').removeAttr('value');
			$('#small_view').removeAttr('value');
		}else{
			//진짜 value
			$('#large').attr('value',largelist[sel_ind-1]);
			$('#small').attr('value',smalllist[sel_ind-1]);
			
			//보여지는 value
			if(largelist[sel_ind-1] == 1){ // 1:1일크루, 2:정기크루
				$('#large_view').attr('value','1일 크루');
			}else{
				$('#large_view').attr('value','정기 크루');
			}
			
			if(smalllist[sel_ind-1] == 'M'){ // M:등산, P:플로깅
				$('#small_view').attr('value','등산');
			}else{
				$('#small_view').attr('value','플로깅');
			}
		}
	}
	
	/* reset 확인*/
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


    <!-- Page Header Start -->
    <div class="container-fluid page-header py-5 mb-5 wow fadeIn" data-wow-delay="0.1s">
        <div class="container text-center py-5">
            <h1 class="display-3 text-white mb-4 animated slideInDown">크루 모집</h1>
            <nav aria-label="breadcrumb animated slideInDown">
                <ol class="breadcrumb justify-content-center mb-0">
                </ol>
            </nav>
        </div>
    </div>
    <!-- Page Header End -->
    
   <!-- SignUp Start -->
   <form:form commandName="cbb" name="crewboardform" action="/crewboard/user/insert.bdcr" method="post">
    	
    	<!-- 작성자 -->
       	<input type="hidden" name="writer" value="${loginId}">
        <!-- //작성자 -->
    	
    <div class="container-fluid py-5">
        <div class="container">
            <div class="text-center mx-auto wow fadeInUp" data-wow-delay="0.1s" style="max-width: 500px;">
                <h1 class="display-5 mb-5">크루 모집 게시글쓰기</h1>
            </div>
            <div class="row justify-content-center">
                <div class="col-lg-7">
                    <div class="bg-light rounded p-4 p-sm-5 wow fadeInUp" data-wow-delay="0.1s">
                        <div class="row g-3">
                        	<!-- 글제목 -->
                        	<div class="col-12">
                                <div class="form-floating">
                                    <input class="form-control border-0" name="subject" id="subject" value="${cbb.subject}">
                                    <label for="subject">글 제목</label>
                                </div>
                        		<form:errors cssClass="err" path="subject"/>
                            </div>
                            <!-- //글제목 -->
                            
                            <!-- 크루선택 -->
                        	<div class="col-sm-5">
                        		<div class="form-floating">
                                <select name="crewname" id="crewname" class="form-select border-0" onchange="largesmall(crewboardform)">
                                		<option value="">내가 만든 크루</option>
                                		<c:forEach var="myCrew" items="${myCrew}">
                                			<option value="${myCrew.crewname}" <c:if test="${myCrew.crewname == cbb.crewname}">selected</c:if>>${myCrew.crewname}</option>
                                		</c:forEach>
                                </select>
                                <label for="crewname">크루선택</label>
                                </div>
                                <form:errors cssClass="err" path="crewname"/>
                            </div>
                            
                            <div class="col-sm-4">
                                <div class="form-floating">
                                	<input type="hidden" name="large" id="large" value="${cbb.large}">
                                    <input type="text" readonly class="form-control border-0" id="large_view" placeholder="Gurdian Name"
                                    	<c:if test="${cbb.large == 1}">value="1일 크루"</c:if>
                                    	<c:if test="${cbb.large == 2}">value="정기 크루"</c:if>>
                                    <label for="large_view">구분1</label>
                                </div>
                            </div>
                            
                            <div class="col-sm-3">
                                <div class="form-floating">
                                	<input type="hidden" name="small" id="small" value="${cbb.small}">
                                    <input type="text" readonly class="form-control border-0" id="small_view" placeholder="Gurdian Name" 
                                    	<c:if test="${cbb.small == 'M'}">value="등산"</c:if>
                                    	<c:if test="${cbb.small == 'P'}">value="플로깅"</c:if>>
                                    <label for="small_view">구분2</label>
                                </div>
                            </div>
                            <!-- //크루선택 -->
                            
                            <!-- 글 내용 -->
                            <div class="col-12">
                                <div class="form-floating">
                                    <textarea class="form-control border-0" id="contents" name="contents" style="height: 200px; resize:none;">${cbb.contents}</textarea>
                                    <label for="contents">글 내용</label>
                                    <form:errors cssClass="err" path="contents"/>
                                </div>
                            </div>
                            <!-- //글 내용 -->
                            
                             <!-- reset & submit -->
                            <div class="col-12 text-center">
                               	<input type="submit" class="btn btn-success" value="등록하기">
                            	<input type="reset" class="btn btn-success" value="다시작성" onclick="return resetconfirm()">
                            	<input type="button" class="btn btn-success" value="목록" onclick="location.href='/crewboard/all/list.bdcr'">
                            </div>
                             <!-- //reset & submit -->
                        </div><!--//row  -->
                    </div><!-- //bg-light -->
                </div><!-- //col-lg-7 -->
            </div><!-- //row justify-content-center -->
        </div><!-- //container -->
    </div><!-- //container-fluid -->
    </form:form>
    <!-- SignUp End -->


<%@ include file="../common/common_bottom.jsp"%>