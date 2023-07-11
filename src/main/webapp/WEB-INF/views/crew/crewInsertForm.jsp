<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/common_top.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.js"></script>
<script type="text/javascript">
	//구분1 선택시 설명
	function largeInfo(f){
		var sel_ind = f.large.selectedIndex;
		//alert(sel_ind);
		
		myarea = document.getElementById("largeInfo"); //myarea = <div>영역
		if(sel_ind == 0){
			myarea.innerHTML = "";
		}
		else if(sel_ind == 1){
			myarea.innerHTML = "<font size='2px'>하루동안 운영되는 단발성 크루입니다.</font>";
		}else if(sel_ind == 2){
			myarea.innerHTML = "<font size='2px'>정기적으로 운영되는 크루입니다.</font>";
		}
	}
	
	//구분2 선택시 설명
	function smallInfo(f){
		var sel_ind = f.small.selectedIndex;
		myarea = document.getElementById("smallInfo");
		if(sel_ind == 2){
			myarea.innerHTML = "<font size='2px'>* 플로깅 : 조깅을 하면서 동시에 쓰레기를 줍는 운동</font>";
		}else{
			myarea.innerHTML = "";
		}
	}
	
	/* ---------------------------------------------- */
	
	/* 크루이름 중복체크 */
	$(document).ready(function(){
		var isCheck = false;
		var use = "";
		$('#crewname_chk').click(function(){ // 중복체크 버튼 눌렀을 때
			//alert(1);
			isCheck = true;
			$.ajax({
				url : 'crewname_check.cr',
				data : ({
					input_name : $('input[name=crewname]').val()
				}),
				success : function(data){ //가능하면 yes 불가능하면 no
					if($('input[name=crewname]').val()==""){
						$('#crname_chk').html("<font color='red' size='2px'>크루이름을 입력하세요.</font>");
						$('input[name=crewname]').focus();
					}else if(data == 'NO'){
						$('#crname_chk').html("<font color='red' size='2px'>이미 등록된 크루이름입니다.</font>");
						$('#crname_chk').show();
						use = "impossible";
					}else if(data == 'YES'){
						$('#crname_chk').html("<font color='blue' size='2px'>사용가능한 크루이름입니다.</font>");
						$('#crname_chk').show();
						use = "possible";
					}
				}
			});//ajax
		});
		
		$('#crewname').keydown(function(){
			$('#crname_chk').css('display', 'none');
		});//keydown
		
		$('#sub').click(function(){ //등록하기 클릭했을때
			if(!isCheck){
				alert('크루이름 중복여부를 확인해주세요.');
				return false;
			}else if(use == 'impossible'){
				alert('사용중인 크루이름입니다');
				$('input[name=crewname]').select();
				return false;
			}else if($('input[name=crewname]').val() == ""){
				alert('크루이름을 입력하세요');
				return false;
			}
		});//click
	})//ready
	
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
		color : red;
		font-weight : bold;
	}
</style>

    <!-- Page Header Start -->
    <div class="container-fluid page-header py-5 mb-5 wow fadeIn" data-wow-delay="0.1s">
        <div class="container text-center py-5">
            <h1 class="display-3 text-white mb-4 animated slideInDown">크루 등록</h1>
            <nav aria-label="breadcrumb animated slideInDown">
                <ol class="breadcrumb justify-content-center mb-0">
                </ol>
            </nav>
        </div>
    </div>
    <!-- Page Header End -->

	<!-- SignUp Start -->
    <form:form name="crewform" commandName="cb" action="/crew/user/insert.cr" method="post">
    	
    	<!-- 아이디를 hidden으로 넘김 -->
    	<input type="hidden" name="crewmanager" value="${loginId}">
    	<input type="hidden" name="crewmember" value="${loginId}">
    	<!--  //아이디 hidden -->
    	
    <div class="container-fluid py-5">
        <div class="container">
            <div class="text-center mx-auto wow fadeInUp" data-wow-delay="0.1s" style="max-width: 500px;">
                <h1 class="display-5 mb-5">크루 등록하기</h1>
            </div>
            <div class="row justify-content-center">
                <div class="col-lg-7">
                    <div class="bg-light rounded p-4 p-sm-5 wow fadeInUp" data-wow-delay="0.1s">
                        <div class="row g-3">
                        	
                            <div class="col-sm-6" style="float: left; ">
                            	<div class="form-floating">
                                	<select name="large" id="large" class="form-select border-0" onchange="largeInfo(crewform)">
                                		<option value="0"></option>
                                		<option value="1" <c:if test="${cb.large == 1}">selected</c:if>>1일 크루</option>
                                		<option value="2" <c:if test="${cb.large == 2}">selected</c:if>>정기 크루</option>
                                	</select>
                                	<label for="large">일일/정기</label>
                               	</div>
                            </div>
                            <div class="col-sm-6" style="display:table">
                            		<div id="largeInfo" style="display:table-cell; vertical-align:middle">
                            			<form:errors cssClass="err" path="large"/>
                            		</div>
                            </div>
                            
                            <div class="col-sm-6">
                            	<div class="form-floating">
                                	<select name="small" id="small" class="form-select border-0" onchange="smallInfo(crewform)">
                                		<option value=""></option>
                                		<option value="M" <c:if test="${cb.small == 'M'}">selected</c:if>>등산</option>
                                		<option value="P" <c:if test="${cb.small == 'P'}">selected</c:if>>플로깅</option>
                                	</select>
	                                <label for="small">등산/플로깅</label>
                                </div>
                            </div>
                            <div class="col-sm-6" style="display:table">
                                	<div id="smallInfo" style="display:table-cell; vertical-align:middle">
                                		<form:errors cssClass="err" path="small"/>
                                	</div>
                            </div>
                            
                            <!-- 크루이름 & 중복체크 -->
                            <div class="col-12">
                            	<!-- 크루이름 -->
	                            <div class="col-9" style="float: left; ">
	                                <div class="form-floating">
                                    <input type="text" class="form-control border-0" id="crewname" name="crewname" value="${cb.crewname}">
                                    <label for="crewname">크루 이름</label>
                                	</div>
	                            </div>
	                            <!-- //크루이름 -->
                            	
                            	<!-- 크루이름 중복체크 -->
	                            <div style="float: left; margin-left:10px; display:inline-block;"  >
		                            <input type="button" class="btn btn-secondary btn-sm" value="중복체크" id="crewname_chk"><br>
	                            	
	                            </div>
	                            <div style="float: left; display:inline-block;"  >
	                           		<span id="crname_chk" style="text-align: left;">
	                           	    	
	                                </span>
	                            </div>
	                            <!-- //크루이름 중복체크 -->
                            </div>
                            <!-- //크루이름 & 중복체크 -->
                            
                            <!-- 가입 인원 -->
                            <div class="col-9">
                                <div class="form-floating">
                                    <input type="number" class="form-control border-0" id="crewlimit" name="crewlimit" value="${cb.crewlimit}">
                                    <label for="crewlimit">가입 제한 인원</label>
                                </div>
                                <form:errors cssClass="err" path="crewlimit"/>
                            </div>
                            <!-- //가입 인원 설정 -->
                            
                            <!-- 크루설명 -->
                            <div class="col-12">
                                <div class="form-floating">
                                    <textarea class="form-control border-0" name="crewcontents" id="crewcontents" style="height: 100px; resize:none;">${cb.crewcontents}</textarea>
                                    <label for="crewcontents">크루 설명</label>
                                     <form:errors cssClass="err" path="crewcontents"/>
                                </div>
                            </div>
                            <!-- // 크루설명 -->
                            
                            <!-- reset & submit -->
                            <div class="col-12 text-center">
                                <input type="submit" id="sub" value="등록하기" class="btn btn-success">
                                <input type="reset" value="다시작성" class="btn btn-success" onclick="return resetconfirm()">
                            	<input type="button" class="btn btn-success" value="목록" onclick="location.href='/crewboard/all/list.bdcr'">
                            </div>
                            <!-- reset & submit -->
                        </div><!--//row  -->
                    </div><!-- //bg-light -->
                </div><!-- //col-lg-7 -->
            </div><!-- //row justify-content-center -->
        </div><!-- //container -->
    </div><!-- //container-fluid -->
    </form:form>
    <!-- SignUp End -->
    
    
<%@ include file="../common/common_bottom.jsp"%>