<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/common_top.jsp"%>

<c:set var="now" value="<%=new java.util.Date()%>" />
<c:set var="sysYear"><fmt:formatDate value="${now}" pattern="yyyy-MM-dd" /></c:set> 

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
	//alert(1);
	
	/* 입력폼의 날짜 관련 유효성검사 */
	let today = new Date();   

	let year = today.getFullYear(); //년도
	let month = today.getMonth() + 1;  //월
	//자바스크립트에서 getMonth() 메서드는 0부터 시작한다(0이 1월, 11이 12월). 그래서 +1을 해줘야함
	let date = today.getDate();  //날짜
	let day = today.getDay();  //요일	
	
	$(document).ready(function(){
		
		//숫자로 변형하여 계산하기 위해 1~9월과 1~9일 앞에는 0을 붙임
		month = (month < 10) ? month = '0' + month : month; 
		date = (date < 10) ? date = '0' + date : date; 
		let today = year + month + date; //오늘날짜인 20230620이 today 변수에 들어감
		
		//모집 마감일 : 오늘보다 과거날짜는 선택할 수 없음
		$("#deadline").change(function(){
			let deadline = $(this).val().replaceAll("-", ""); //2023-06-19를 20230619로 바꿔서 넣음
			
			if(Number(deadline) < Number(today)){
				alert('과거날짜는 선택이 불가능합니다.');
				$(this).val(""); //과거날짜를 선택하면 선택값 지워지도록
				return false;
			}
		})
		
		//활동시작일 : 1. 오늘보다 과거날짜는 선택할 수 없음
		//2. 모집마감일보다 이전 날짜를 선택할 수 없음
		$("#startdate").change(function(){ //today
			
			let deadline = $("#deadline").val().replaceAll("-", "");
			let startdate = $("#startdate").val().replaceAll("-", "");
					
			if(Number(startdate) < Number(today)){
				alert('과거날짜는 선택이 불가능합니다.');
				$(this).val("");
				return false;
			}
			else if(Number(startdate) <= Number(deadline)){
				alert('모집마감일 보다 이전 혹은 같은날짜는 선택이 불가능합니다.');
				$(this).val("");
				return false;
			}			
		})
		
		//활동종료일 : 1.오늘보다 과거날짜는 선택할 수 없음
		//2. 활동시작일보다 이전 날짜를 선택할 수 없음
		//3. 활동기간은 최소 30일 이상이어야 합니다.
		$("#enddate").change(function(){
			let endDate = $(this).val().replaceAll("-", "");
			let startDate = $("#startdate").val().replaceAll("-", "");
			
			if(Number(endDate) < Number(today)){
				alert('과거날짜는 선택이 불가능합니다.');
				$(this).val("");
				return false;
			}
			else if(Number(endDate) <= Number(startDate)){
				alert('활동시작일 보다 이전 혹은 같은날짜는 선택이 불가능합니다.');
				$(this).val("");
				return false;
			}
			else if(Number(endDate) - Number(startDate) < 30 ){
				alert('활동기간은 최소 30일 이상이어야 합니다.');
				$(this).val("");
				return false;
			}		
		})//활동종료일
	})
	
	
	/* 서포터즈명 중복체크 */
	use = "";
	isCheck = false;
	
	$('#supo_check').click(function(){
		//alert('supo_check');
		
		if($('input[name=supportersname]').val() == ''){
			$('#supo_message').html("<font color='red' size='2px' font-weight='bold'>서포터즈명을 입력하세요.</font>");
			use = "missing";
			$('#supo_message').show();			
			return false;
		} 
		
		isCheck = true;			
		$.ajax({
			url : "/supporters/admin/supportersname_check.su", //방식
			data : ({					
				input_name : $('input[name=supportersname]').val()
			}),
			success : function(data){
				//data 변수에 요청 응답의 결과(Yes 또는 No)가 들어온다.
				//alert('data: '+$.trim(data));
				
				if(data.code == "200"){
					$('#supo_message').html("<font color='blue' size='2px' font-weight='bold'>사용가능한 서포터즈명입니다.</font>");
					use = "possible";
					$('#supo_message').show();
				}
				else{
					$('#supo_message').html("<font color='red' size='2px' font-weight='bold'>이미 등록된 서포터즈명입니다.</font>");
					use = "impossible";
					$('#supo_message').show();
				}									
			} //success		
		}); //ajax	
	}); //id_click
	/* 서포터즈명 중복체크 */
	
	//가입하기 버튼을 누를때
	//1. 중복체크 버튼을 눌렀는지 확인
	//2. 입력 누락인지 확인
	//3. 이미 사용중인 아이디인지 확인
	//4. 날짜가 비어있다면 false를 리턴하도록
	$('#sub').click(function(){
		
		if(isCheck == false){ //!isCheck
			alert('서포터즈명 중복여부를 확인해주세요.');
			return false;
		}			
		else if(use == "missing"){
			alert("서포터즈명을 입력하세요.");
			$('input[name=supportersname]').focus();
			return false;
		}
		else if(use == "impossible"){
			alert('사용중인 서포터즈명입니다.');
			return false;
		}
		else if($('input[name=deadline]').val() == ''){
			alert("모집마감일을 입력하세요.");
			$('input[name=deadline]').focus();
			return false;		
		}
		else if($('input[name=startdate]').val() == ''){
			alert("활동시작일을 입력하세요.");
			$('input[name=startdate]').focus();
			return false;		
		}
		else if($('input[name=enddate]').val() == ''){
			alert("활동종료일을 입력하세요.");
			$('input[name=enddate]').focus();
			return false;		
		}
		
	});//sub_click	
	
	//키보드 눌리면(새로 입력되면)
	$('input[name=supportersname]').keydown(function(){
		
		//1.span 글자 지워지도록 2.use 초기화 3. 중복체크 초기화
		$('#supo_message').css('display','none');
		use = "";
		isCheck = false;		
	});//keydown		
	
});//document
	
//reset 확인
function resetCheck(){
	var check = confirm("정말 초기화 하시겠습니까?");
	 if(!check){
		return false;
	}
}

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
    <!-- Page Header End -->
   

<!-- Quote Start -->
<form:form commandName="supporters" action="/supporters/admin/update.su" method="post">

	<div class="container-fluid py-5">
		<div class="container">
			<div class="text-center mx-auto wow fadeInUp" data-wow-delay="0.1s" style="max-width: 500px;">
				<p class="fs-5 fw-bold text-primary"></p>
				<h1 class="display-5 mb-5">서포터즈 게시글 수정</h1>
			</div>
			<div class="row justify-content-center">
				<div class="col-lg-9">
					<div class="bg-light rounded p-4 p-sm-5 wow fadeInUp" data-wow-delay="0.1s">
						<div class="row g-3">
							
							<input type="hidden" name="num" value="${supporters.num}">
							<input type="hidden" name="userid" value="${userId}">
							<input type="hidden" name="pageNumber" value="${param.pageNumber }">

							<!-- 서포터즈명 전체 -->
							<div class="col-12">
								<!-- 서포터즈명 -->
								<div class="col-9" style="float: left;">
									<div class="form-floating">
										<input type="text" class="form-control border-0" id="supportersname" name="supportersname" value="${supporters.supportersname}">
										<label for="supportersname">서포터즈</label>
									</div>
								</div>
								<!-- //서포터즈명 -->
								<!-- 서포터즈명 중복체크 -->
								<div style="float: left; margin-left: 10px; display: inline-block;">
									<input type="button" class="btn btn-secondary btn-sm" value="중복체크" id="supo_check"><br>
								</div>
								<div style="float: left; display: inline-block;">
									<span id="supo_message" style="text-align: left;"> 
									</span>
								</div>
								<!-- //서포터즈명 중복체크 -->
							</div>
							<!-- //서포터즈명 전체 -->
							
							<!-- 모집마감일-->
							<div class="col-9">
								<div class="form-floating">
									<fmt:parseDate var="newDay3" value="${supporters.deadline}" pattern="yyyy-MM-dd" /> 
									<fmt:formatDate var="fNewDay3" value="${newDay3}" pattern="yyyy-MM-dd" />			
                                    <input type="date" name="deadline" id="deadline" class="form-control border-0" value="${fNewDay3 }">
                                    <label for="deadline">모집 마감일</label>
                                </div>
							</div>
							<!-- //모집마감일 -->
							
							<!-- 모집 대상 -->
							<div class="col-9">
								<h6>모집 대상</h6>
								<div class="form-floating">
									<input type="radio" class=" form-check-input border-0" name="target" value="0" <c:if test="${supporters.target eq '0'}">checked</c:if>>전체회원 &nbsp;&nbsp;&nbsp;
									<input type="radio" class=" form-check-input border-0" name="target" value="1" <c:if test="${supporters.target eq '1'}">checked</c:if>>우수회원									
								</div>
								<form:errors cssClass="err" path="target" />
							</div>
							<!-- //모집 대상 -->
							
							<!-- 모집 인원-->
							<div class="col-sm-9">
								<div class="form-floating">
									<input type="number" class="form-control border-0" name="limit" id="limit" value="${supporters.limit}">
									<label for="limit">모집 인원</label>
									<form:errors cssClass="err" path="limit" />
								</div>
							</div>
							<!-- //모집 인원 -->
							
							<!-- 활동시작일 -->
							<div class="col-9">
								<div class="form-floating">
									<fmt:parseDate var="newDay1" value="${supporters.startdate}" pattern="yyyy-MM-dd" /> 
									<fmt:formatDate var="fNewDay1" value="${newDay1}" pattern="yyyy-MM-dd" />			
                                    <input type="date" name="startdate" id="startdate" class="form-control border-0" value="${fNewDay1 }">
									<label for="startdate">활동 시작일</label>
								</div>
							</div>
							<!-- //활동시작일 -->
							
							<!-- 활동종료일 -->
							<div class="col-9">
								<div class="form-floating">
									<fmt:parseDate var="newDay2" value="${supporters.enddate}" pattern="yyyy-MM-dd" /> 
									<fmt:formatDate var="fNewDay2" value="${newDay2}" pattern="yyyy-MM-dd" />			
                                    <input type="date" name="enddate" id="enddate" class="form-control border-0" value="${fNewDay2 }">
									<label for="enddate">활동 종료일</label>
								</div>
							</div>
							<!-- //활동종료일 -->
							
							<!-- 활동 내용 -->
							<div class="col-9">
								<div class="form-floating">
									<textarea name="content" id="content" class="form-control border-0" style="height: 400px; resize:none;">${supporters.content}</textarea>
									<label for="content">활동 내용</label>
									<form:errors cssClass="err" path="content" />
								</div>
							</div>
							<!-- //활동 내용 -->
							
							<div class="col-12 text-center">
								<input type="submit" value="수정하기" class="btn btn-success" id="sub"> 
								<input type="reset" value="다시작성" class="btn btn-success" onclick="return resetCheck()"> 
								<input type="button" value="목록" class="btn btn-success" onclick="location.href='/supporters/all/list.su'">
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