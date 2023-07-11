<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<nav class="navbar navbar-expand-lg navbar-light bg-light rounded">
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample10" aria-controls="navbarsExample10" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse justify-content-md-center" id="navbarsExample10">
      <ul class="navbar-nav">
        <li class="nav-item active">
          <a class="nav-link" href="/mountain/all/list.mnt">전체 조회<span class="sr-only">(current)</span></a>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="dropdown10" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">난이도별</a>
          <div class="dropdown-menu" aria-labelledby="dropdown10">
            <a class="dropdown-item" href="/mountain/all/list.mnt?category=difficulty_easy">초급</a>
            <a class="dropdown-item" href="/mountain/all/list.mnt?category=difficulty_normal">중급</a>
            <a class="dropdown-item" href="/mountain/all/list.mnt?category=difficulty_hard">상급</a>
          </div>
        <li class="nav-item">
          <a class="nav-link" href="/mountain/all/list.mnt?category=height">높이순 조회</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/mountain/all/list.mnt?category=great">100대 명산 조회</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/mountain/all/list.mnt?category=national">국립공원 조회</a>
        </li>
      </ul>
    </div>
  </nav>