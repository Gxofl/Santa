package com.spring.ex.utility;

public class Paging {
	//페이징 관련 변수	
	//pageSize는 한 페이지에 보여줄 레코드 갯수 , 한 페이지에 3개의 레코드를 보여주겠다
	//pageCount는 한 번에 3페이지 씩 보여주겠다
	private int totalCount = 0 ; //총 레코드 건수
	private int totalPage = 0 ; //전체 페이지 수
	private int pageNumber = 0 ; //보여줄 페이지 넘버(표현 가능한 페이지는 1부터 totalPage까지이다.),//내가 선택한 페이지번호(=보여줄 페이지 넘버)
	private int pageSize = 0 ; //한 페이지에 보여줄 건수, //한번에 보여주고 싶은 페이지 길이(=건수) 
	private int beginRow = 0 ; //현재 페이지의 시작 행
	private int endRow = 0 ; //현재 페이지의 끝 행
	private int pageCount = 3 ; // 한 화면에 보여줄 페이지 링크 수 (페이지 갯수), 한번에 3페이지씩
	private int beginPage = 0 ; //페이징 처리 시작 페이지 번호
	private int endPage = 0 ; //페이징 처리 끝 페이지 번호
	private int offset = 0 ; //어디서 부터 가져올지
	private int limit = 0 ; //행을 얼마나 가져올지, limit = pageSize ; // 한 페이지에 보여줄 레코드 갯수 = 3
	private String url = "" ; //예시 ==>  http://localhost:8989/MyServlet/list.do
	private String pagingHtml = "";//하단의 숫자 페이지 링크
	//private String pagingStatus = ""; //상단 우측의 현재 페이지 위치 표시
	//검색을 위한 변수 추가
	private String whatColumn = "" ; //검색 모드(작성자, 글제목, 전체 검색은 all) 등등
	private String keyword = "" ; //검색할 단어 

	public int getTotalCount() {
		return totalCount;
	}


	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}


	public int getTotalPage() {
		return totalPage;
	}


	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}


	public int getPageNumber() {
		return pageNumber;
	}


	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}


	public int getPageSize() {
		return pageSize;
	}


	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}


	public int getBeginRow() {
		return beginRow;
	}


	public void setBeginRow(int beginRow) {
		this.beginRow = beginRow;
	}


	public int getEndRow() {
		return endRow;
	}


	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}


	public int getPageCount() {
		return pageCount;
	}


	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}


	public int getBeginPage() {
		return beginPage;
	}


	public void setBeginPage(int beginPage) {
		this.beginPage = beginPage;
	}


	public int getEndPage() {
		return endPage;
	}


	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}


	public int getOffset() {
		return offset;
	}


	public void setOffset(int offset) {
		this.offset = offset;
	}


	public int getLimit() {
		return limit;
	}


	public void setLimit(int limit) {
		this.limit = limit;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public String getPagingHtml() {
		System.out.println("pagingHtml:"+pagingHtml);
		
		return pagingHtml;
//		pagingHtml:
//			&nbsp;<font color='red'>1</font>&nbsp;&nbsp;<a href='/ex/list.ab?pageNumber=2&pageSize=2&whatColumn=null&keyword=null'>2</a>&nbsp;&nbsp;<a href='/ex/list.ab?pageNumber=3&pageSize=2&whatColumn=null&keyword=null'>3</a>&nbsp;&nbsp;<a href='/ex/list.ab?pageNumber=4&pageSize=2&whatColumn=null&keyword=null'>4</a>&nbsp;&nbsp;<a href='/ex/list.ab?pageNumber=5&pageSize=2&whatColumn=null&keyword=null'>5</a>&nbsp;&nbsp;<a href='/ex/list.ab?pageNumber=6&pageSize=2&whatColumn=null&keyword=null'>6</a>&nbsp;&nbsp;<a href='/ex/list.ab?pageNumber=7&pageSize=2&whatColumn=null&keyword=null'>7</a>&nbsp;&nbsp;<a href='/ex/list.ab?pageNumber=8&pageSize=2&whatColumn=null&keyword=null'>8</a>&nbsp;&nbsp;<a href='/ex/list.ab?pageNumber=9&pageSize=2&whatColumn=null&keyword=null'>9</a>&nbsp;&nbsp;<a href='/ex/list.ab?pageNumber=10&pageSize=2&whatColumn=null&keyword=null'>10</a>&nbsp;&nbsp;<a href='/ex/list.ab?pageNumber=11&pageSize=2&whatColumn=null&keyword=null'>다음</a>&nbsp;&nbsp;<a href='/ex/list.ab?pageNumber=22&pageSize=2&whatColumn=null&keyword=null'>맨 끝</a>&nbsp;

	}


	public void setPagingHtml(String pagingHtml) {
		this.pagingHtml = pagingHtml;
	}


	public String getWhatColumn() {
		return whatColumn;
	}


	public void setWhatColumn(String whatColumn) {
		this.whatColumn = whatColumn;
	}


	public String getKeyword() {
		return keyword;
	}


	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	//===============================================================================
	//매개변수 있는 생성자
	public Paging(
			String _pageNumber, 
			String _pageSize,  
			int totalCount,
			String url, 
			String whatColumn, 
			String keyword,
			String whologin) {		

		//선택한 페이지번호가 null이면
		//만약 페이지번호(_pageNumber )가 null이면 1페이지 부터 볼 수 있게 1로 지정하고,
		//String인 _pageNumber 을 int형으로 형변환하여 Paging클래스의 멤버변수 pageNumber에 설정 
		if(  _pageNumber == null || _pageNumber.equals("null") || _pageNumber.equals("")  ){
			
			System.out.println("_pageNumber:"+_pageNumber); // null
			_pageNumber = "1" ;
		}
		this.pageNumber = Integer.parseInt( _pageNumber ) ; 
		//====================================================================
		if( _pageSize == null || _pageSize.equals("null") || _pageSize.equals("") ){
			_pageSize = "3" ; // 한 페이지에 보여줄 레코드 갯수
		}		
		this.pageSize = Integer.parseInt( _pageSize ) ;
		
		this.limit = pageSize ; // 한 페이지에 보여줄 레코드 갯수 = 3
		
		this.totalCount = totalCount ; //한 화면에 보여줄 페이지 링크 수 ,한번에 3페이지씩
		
		//====================================================================
		//
		this.totalPage = (int)Math.ceil((double)this.totalCount / this.pageSize) ;
		/*
		 totalPage 	: 4, 전체 페이지 수 
		 totalCount : 10, 총 레코드 건수(전체 갯수)
		 pageSize 	: 3 , 한 페이지에 보여줄 건수
		 
		 소수점 올림 (CEIL 함수) : CEIL 함수는 소수점 값이 있는 경우 무조건 올려서 다음 정수 값을 반환한다
		 
		 (double)this.totalCount -> 3.3333 -> (int)Math.ceil((double)this.totalCount : 4(올림)
		 => 한번에 3페이지씩 보이도록하고 총 10페이지가 있다.
		 1page 1,2,3 | 2page 4,5,6 | 3page 7,8,9 | 4page 10
		 
		 1 2 3 [다음]
		 [이전] 4 5 6 [다음]
		 [이전] 7 8 
	
		 */
		
		//====================================================================
		//시작할 beginrow 와 종료할 endrow
		this.beginRow = ( this.pageNumber - 1 )  * this.pageSize  + 1 ;
		this.endRow =  this.pageNumber * this.pageSize ;
		/*
		 totalPage 	: 전체 페이지 수
		 pageNumber : 내가 선택한 페이지번호
		 pageSize 	: 3 , 한 페이지에 보여줄 건수
		 -----------------
		 1페이지를 클릭했다면?
		 pageNumber : 1
		 pageSize 	: 3(변하지않음)
		 beginRow	: 1
		 endRow		: 3
		 
		 2페이지를 클릭했다면?
		 pageNumber : 2
		 pageSize 	: 3(변하지않음)
		 beginRow	: 4
		 endRow		: 6 
		 
		 */
		
		//pageNumber
		/*
		 
		totalPage	: 전체 페이지 수
		pageNumber : 내가 선택한 페이지번호
		pageSize : 한 페이지에 보여줄 건수
		
		- 만약 총 3페이지까지 존재하다가, 레코드 하나를 삭제하여 3페이지→2페이지로 변경되었을 때, 
		this.pageNumber > this.totalPage, 내가 선택한 페이지번호(pageNumber )가 전체 페이지 수(totalPage)보다 크면
		this.pageNumber = this.totalPage ; 마지막 페이지와 같게 조정함
		 */
		if( this.pageNumber > this.totalPage ){
			//내가 선택한 페이지번호(pageNumber )가 전체 페이지 수(totalPage)보다 크면
			this.pageNumber = this.totalPage ;
			//마지막 페이지와 같게 조정함
		}
		
		//=============================================
		//offset 중요★★
		this.offset = ( pageNumber - 1 ) * pageSize ; 
		/*
		(17-1)*3 => 16*3 = 48
		 offset : 건너뛸 갯수, 어디서 부터 가져올지(가져올 데이터의 시작 인덱스)
		(1-1)*3 => 0 , 건너뛸 페이지 없다
		(2-1)*3 => 3 , 2페이지 클릭하면 ,3페이지에 보여줄 정보
		(3-1)*3 => 6
		 
		 */
		//=============================================
		
		if( this.endRow > this.totalCount ){
			this.endRow = this.totalCount  ;
		}
		
		this.beginPage = ( this.pageNumber - 1 ) / this.pageCount * this.pageCount + 1  ;
		this.endPage = this.beginPage + this.pageCount - 1 ;
		/*
		pageCount : 한 화면에 보여줄 페이지 링크 수 (페이지 갯수)
		beginPage 	: (5-1)/3*3+1 => 1*3+1 =>4
		endPage 	: 4 + 3 -1 =>6
		*/
		System.out.println("pageNumber:"+pageNumber+"/totalPage:"+totalPage);	
		
		if( this.endPage > this.totalPage ){
			this.endPage = this.totalPage ;
		}
		
		System.out.println("pageNumber2:"+pageNumber+"/totalPage2:"+totalPage);	
		this.url = url ; //  /ex/list.ab
		this.whatColumn = whatColumn ;
		this.keyword = keyword ;
		System.out.println("whatColumn:"+whatColumn+"/keyword:"+keyword);
		
		this.pagingHtml = getPagingHtml(url) ;
	
	}//매개변수 있는 생성자
	
	private String getPagingHtml( String url ){ //페이징 문자열을 만든다.
		System.out.println("getPagingHtml url:"+url); 
		// getPagingHtml url:/ex/list.ab
		
		String result = "" ;
		String added_param = "&whatColumn=" + whatColumn + "&keyword=" + keyword ; // &whatColumn=singer&keyword=아
		//url : list.tv
		//pageNumber
		if (this.beginPage != 1) { // 앞쪽, pageSize:한 화면에 보이는 레코드 수
			result += "&nbsp;<a href='" + url  
					+ "?pageNumber=" + ( 1 ) + "&pageSize=" + this.pageSize 
					+ added_param + "'>맨 처음</a>&nbsp;" ;
			result += "&nbsp;<a href='" + url 
					+ "?pageNumber=" + (this.beginPage - 1 ) + "&pageSize=" + this.pageSize 
					+ added_param + "'>이전</a>&nbsp;" ;
		}
		
		//가운데
		//123  456
		for (int i = this.beginPage; i <= this.endPage ; i++) {
			if ( i == this.pageNumber ) {
				result += "&nbsp;<font color='red'>" + i + "</font>&nbsp;"	;
						
			} else {
				result += "&nbsp;<a href='" + url   
						+ "?pageNumber=" + i + "&pageSize=" + this.pageSize 
						+ added_param + "'>" + i + "</a>&nbsp;" ;
				
			}
		}
		
		System.out.println("result:"+result); 
		System.out.println();
		
		if ( this.endPage != this.totalPage) { // 뒤쪽
			
			result += "&nbsp;<a href='" + url  
					+ "?pageNumber=" + (this.endPage + 1 ) + "&pageSize=" + this.pageSize 
					+ added_param + "'>다음</a>&nbsp;" ;
			
			result += "&nbsp;<a href='" + url  
					+ "?pageNumber=" + (this.totalPage ) + "&pageSize=" + this.pageSize 
					+ added_param + "'>맨 끝</a>&nbsp;" ;
		}		
		System.out.println("result2:"+result);
		
		return result ;
	}	
	
}

