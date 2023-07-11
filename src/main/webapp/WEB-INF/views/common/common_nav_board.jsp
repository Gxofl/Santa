<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <!-- common_nav_board.jsp<br> -->
    
<nav class="navbar navbar-expand-lg navbar-light bg-light rounded">
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample10" aria-controls="navbarsExample10" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse justify-content-md-center" id="navbarsExample10">
      <ul class="navbar-nav">
      	<li class="nav-item">
          <a class="nav-link" href="/board/all/list.br">전체</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/board/all/list.br?whatColumn=information">정보</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/board/all/list.br?whatColumn=review">후기</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/board/all/list.br?whatColumn=recommend">추천</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/board/all/list.br?whatColumn=free">잡담</a>
        </li>
      </ul>
    </div>
  </nav>
    