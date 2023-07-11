<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<nav class="navbar navbar-expand-lg navbar-light bg-light rounded">
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample10" aria-controls="navbarsExample10" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse justify-content-md-center" id="navbarsExample10">
      <ul class="navbar-nav">
      	 <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="/crewboard/all/list.bdcr" id="dropdown10" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">전체</a>
          <div class="dropdown-menu" aria-labelledby="dropdown10">
            <a class="dropdown-item" href="/crewboard/all/list.bdcr?whatColumn=ING">모집중</a>
            <a class="dropdown-item" href="/crewboard/all/list.bdcr?whatColumn=Finish">모집완료</a>
          </div>
        </li>
      	 <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="dropdown10" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">정기 크루</a>
          <div class="dropdown-menu" aria-labelledby="dropdown10">
            <a class="dropdown-item" href="/crewboard/all/list.bdcr?whatColumn=2M">등산</a>
            <a class="dropdown-item" href="/crewboard/all/list.bdcr?whatColumn=2P">플로깅</a>
          </div>
        </li>
      	 <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="dropdown10" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">1일 크루</a>
          <div class="dropdown-menu" aria-labelledby="dropdown10">
            <a class="dropdown-item" href="/crewboard/all/list.bdcr?whatColumn=1M">등산</a>
            <a class="dropdown-item" href="/crewboard/all/list.bdcr?whatColumn=1P">플로깅</a>
          </div>
        </li>
        
        <!-- 로그인한 상태만 보여짐 -->
		<sec:authorize access="isAuthenticated()">
      	 <li class="nav-item">
          <a class="nav-link" href="/crew/user/myPage1.cr">내 크루 보기</a>
        </li>
      	 <li class="nav-item">
          <a class="nav-link" href="/crew/user/insert.cr">크루 만들기</a>
        </li>
        </sec:authorize>
        <!-- //로그인한 상태만 보여짐 -->
      </ul> 
    </div>
  </nav>