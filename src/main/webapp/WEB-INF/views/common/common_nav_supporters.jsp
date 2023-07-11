<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<nav class="navbar navbar-expand-lg navbar-light bg-light rounded">
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample10" aria-controls="navbarsExample10" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse justify-content-md-center" id="navbarsExample10">
      <ul class="navbar-nav">
        
        <li class="nav-item">
          <a class="nav-link" href="/supporters/all/list.su">전체</a>
        </li>
        
      	<li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="dropdown10" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">모집 상태</a>
          <div class="dropdown-menu" aria-labelledby="dropdown10">
            <a class="dropdown-item" href="/supporters/all/list.su?whatColumn=ing">모집중</a>
            <a class="dropdown-item" href="/supporters/all/list.su?whatColumn=complete">모집 완료</a>
            <a class="dropdown-item" href="/supporters/all/list.su?whatColumn=closed">모집기한 마감</a>
          </div>
        </li>
        
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="dropdown10" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">모집 대상</a>
          <div class="dropdown-menu" aria-labelledby="dropdown10">
            <a class="dropdown-item" href="/supporters/all/list.su?whatColumn=allMember">전체 회원</a>
            <a class="dropdown-item" href="/supporters/all/list.su?whatColumn=bestMember">우수 회원</a>
          </div>
        </li>
  
      </ul>
    </div>
  </nav>