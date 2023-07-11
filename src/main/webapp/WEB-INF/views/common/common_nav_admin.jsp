<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<nav class="navbar navbar-expand-lg navbar-light bg-light rounded">
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarsExample10" aria-controls="navbarsExample10"
		aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div class="collapse navbar-collapse justify-content-md-center" id="navbarsExample10">
		<ul class="navbar-nav">
			<!-- users -->
			<li class="nav-item dropdown">
				<a class="nav-link dropdown-toggle" href="#" id="dropdown10"
					data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">회원</a>
				<div class="dropdown-menu" aria-labelledby="dropdown10">
					<a class="dropdown-item" href="/users/admin/userStatistics.us">회원 통계</a>
					<a class="dropdown-item" href="/users/admin/usersList.us">회원 관리</a>
					<a class="dropdown-item" href="/users/admin/usersWithdrawalList.us">탈퇴 회원 관리</a>
				</div>
			</li>
			<!-- //users -->

			<!-- crew -->
			<li class="nav-item dropdown">
				<a class="nav-link dropdown-toggle" href="#" id="dropdown10"
					data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					크루
				</a>
				<div class="dropdown-menu" aria-labelledby="dropdown10">
					<a class="dropdown-item" href="/crew/admin/list.cr">크루통계</a> 
					<a class="dropdown-item" href="/crew/admin/list1.cr">일일크루</a> 
					<a class="dropdown-item" href="/crew/admin/list2.cr">정기크루</a>
					<a class="dropdown-item" href="/crewboard/admin/list.bdcr">크루 모집글 관리</a>
				</div>
			</li>
			<!-- //crew -->

			<!-- stamp -->
			<li class="nav-item">
				<a class="nav-link" href="/stamp/admin/adminlist.stp">
					완등 내역 관리
				</a>
			</li>
			<!-- //stamp -->

			<!-- 커뮤니티 관련 -->
			<li class="nav-item dropdown">
				<a class="nav-link dropdown-toggle" href="#" id="dropdown10"
					data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					커뮤니티 게시판 관리
				</a>
				<div class="dropdown-menu" aria-labelledby="dropdown10">
					<a class="dropdown-item" href="/notice/admin/list.no">공지사항 관리</a>
					<a class="dropdown-item" href="/board/admin/list.br">자유 게시판 관리</a> 
					<a class="dropdown-item" href="/products/admin/list.prd">거래 게시판 관리</a> 
				</div>
			</li>
			<!-- //커뮤니티 관련 -->

			<!-- 결제 관련 -->
			<li class="nav-item dropdown">
				<a class="nav-link dropdown-toggle" href="#" id="dropdown10"
					data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					결제 관리
				</a>
				<div class="dropdown-menu" aria-labelledby="dropdown10">
					<a class="dropdown-item" href="/pay/admin/waitlist.pay">결제 승인 대기내역</a>
					<a class="dropdown-item" href="/pay/admin/finishlist.pay">결제 승인 완료내역</a>
					<a class="dropdown-item" href="/pay/admin/cancellist.pay">결제 취소 내역</a>
				</div>
			</li>
			<!-- //게시글 관련 -->
			<!-- 서포터즈 관련 -->	
			<li class="nav-item dropdown">
				<a class="nav-link dropdown-toggle" href="#" id="dropdown10"
					data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					서포터즈 관리
				</a>
				<div class="dropdown-menu" aria-labelledby="dropdown10">
					<a class="dropdown-item" href="/supportersapply/admin/supporters.sua">서포터즈 별 조회</a>
					<a class="dropdown-item" href="/supporters/admin/user.su">회원 별 조회</a>
					<a class="dropdown-item" href="/supporters/admin/adminlist.su">서포터즈 모집글 관리</a>
				</div>
			</li>
			<!-- //서포터즈 관련 -->
		</ul><!-- //navbar-nav -->
	</div><!-- //collapse navbar -->
</nav><!-- //navbar -->

