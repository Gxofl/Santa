<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<nav class="navbar navbar-expand-lg navbar-light bg-light rounded">
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample10" aria-controls="navbarsExample10" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse justify-content-md-center" id="navbarsExample10">
      <ul class="navbar-nav">
        <!-- <li class="nav-item active">
          <a class="nav-link" href="#">Centered nav only<span class="sr-only">(current)</span></a>
        </li> -->
        <li class="nav-item">
          <a class="nav-link" href="/products/all/list.prd">전체</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/products/all/list.prd?whatColumn=sell">판매</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/products/all/list.prd?whatColumn=give">나눔</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/products/all/list.prd?whatColumn=change">교환</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/products/all/list.prd?whatColumn=state0">거래중</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/products/all/list.prd?whatColumn=state1">거래완료</a>
        </li>
         
         <!-- 로그인한 상태만 보여짐 -->
		<sec:authorize access="isAuthenticated()">
        <li class="nav-item">
          <a class="nav-link" href="/products/all/list.prd?whatColumn=loginId">내 판매글 보기</a> 
        </li>
        </sec:authorize>
         <!-- //로그인한 상태만 보여짐 -->
      </ul>
    </div>
  </nav>