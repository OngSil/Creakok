<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head> 
    <meta charset="UTF-8">
    <meta name="description" content="">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- The above 4 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    
    <!-- Title -->
    <title>CREAKOK</title>

    <!-- Favicon -->
    <link rel="icon" href="img/core-img/favicon.ico">

    <!-- Core Stylesheet -->
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    
    
    <!-- BOTO TEST -->
    <meta name="description" content="Boto Photo Studio HTML Template">
    <meta name="keywords" content="photo, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Stylesheets -->
    <link rel="stylesheet" href="css/css_boto/bootstrap.min.css"/>
    <link rel="stylesheet" href="css/css_boto/font-awesome.min.css"/>
    <link rel="stylesheet" href="css/css_boto/slicknav.min.css"/>
    <link rel="stylesheet" href="css/css_boto/fresco.css"/>
    <link rel="stylesheet" href="css/css_boto/slick.css"/>
  
    <!-- Main Stylesheets -->
    <link rel="stylesheet" href="css/css_boto/style.css"/>

    <!-- hcbae Stylesheets -->
    <link rel="stylesheet" href="css/hcbae_tumblbug_part.css"/>
    <link rel="stylesheet" href="css/hcbae_wadiz_part.css">
    <link rel="stylesheet" href="css/hcbae_css.css">
  
	<!-- summernote -->
	<script src="/js/summernote/summernote-lite.js"></script>
	<script src="/js/summernote/lang/summernote-ko-KR.js"></script>

	<link rel="stylesheet" href="/css/summernote/summernote-lite.css">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script>
</head>

<body>
   <!-- Preloader -->
   <div class="preloader d-flex align-items-center justify-content-center">
        <div class="preloader-circle"></div>
        <div class="preloader-img">
            <img src="img/core-img/creakok.png" alt="">
        </div>
    </div>

   <!-- ##### Header Area Start ##### -->
    <header class="header-area">
    <div id="header_div">
    <jsp:include page="creakok_header.jsp" flush="true"/>
    </div>
    </header>
    <!-- ##### Header Area End ##### -->
        
    
    <div class="Membership__MembershipWrapper-o1o1he-0 irjBzn">
 		<h3 style="text-align:center;margin-bottom:40px;font-size:18pt">펀딩 문의글 수정하</h3>
        
	    <div class="submit_a_review_area mt-50" style="width: 60%; margin: auto;" >
	    	<form method="post" action="funding_qna.edit" onsubmit="return goWrite()">
                                   <div class="row">
                                            <div class="col-12">
                                                <div class="form-group d-flex align-items-center">
                                                    
                                                </div>
                                            </div>
                                        
                                        </div>  
		    				      <div>
                                     <label for="reviewer_name">작성자</label>&nbsp;&nbsp;
                                    <input name="review_writer" style="display:inline-block;width:30%;margin-right:5%" class="form-control" id="reviewer_name" 
                                   value="${funding_qna_detail.member_name}" readonly>
                                  
                                </div>
                                
                                 <div style="margin-top:10px">
                                    <label for="review_subject" style="margin-right:2%">제목</label>
                                    <input name="review_subject" style="display:inline-block;width:90%" class="form-control" id="review_subject" 
                                   value="${funding_qna_detail.funding_qna_subject}">
                                   <input type="hidden" name="funding_qna_index" value="${funding_qna_detail.funding_qna_index}"/>
                                   <input type="hidden" name="funding_index" value="${funding_qna_detail.funding_index}"/>
                                </div>
                                
				<br>
				<textarea id="summernote" name="review_content">${funding_qna_detail.funding_qna_content}</textarea>
				
				<p style="width:100%;text-align:center; margin-top:20px;">
    				<input id="subBtn" type="submit" style="padding:3px;background-color:#fc5230;color:white;border:0;border-radius:4px;width:100px" value="작성하기" />
    				<a href="funding_qna.write?funding_index=${funding_qna_detail.funding_index}#fix_point"><input id="subBtn2" type="button"  style="padding:3px;background-color:white;color:#fc5230;border:1px solid #fc5230;border-radius:4px;width:100px" value="목록으로"/></a>
				
                </p>			 		
			</form>
		</div>
    </div>
    
                 
    
    <script>
      $('#summernote').summernote({
        placeholder: 'Hello stand alone ui',
        tabsize: 2,
      minHeight: 370,
      maxHeight: null,
      focus: true,
        toolbar: [
          ['font', ['bold', 'underline', 'clear']],
          ['insert', ['picture']]
        ]
      });
    </script>
      
      <script>
		function goWrite() {
			var summernote = $("#summernote").val();
		
			if(summernote == ''){
				alert("내용을 입력해주세요");
				return false;
			}
		}
	</script>
  
      
  <!-- Footer Bottom Area -->
        <div class="footer-bottom-area"style="background-color: whitesmoke !important; padding-top:50px; ">
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <div class="border-line"></div>
                    </div>
                    <!-- Copywrite Text -->
                    <div class="col-12 col-md-6" >
                        <div class="copywrite-text"style="background-color: whitesmoke !important; ">
                            <img src="img/core-img/creakok.png" alt=""><br/>
                            <p>creakok@gmail.com  |  02.707.1480<br/>
                                평일 10:00~17:00 (점심시간 12:00~13:00)<br/>
                                토/일/공휴일 휴무</p>
                            <p>(주)크리콕 | 소속 : 비트캠프 신촌센터  | 호스팅 제공자 : (주)CJ ENM<br/>
                                서울 마포구 백범로 23 구프라자 3층</p>
                            <p>ⓒ CREAKOK All rights reserved.</p>
                        </div>
                    </div>
                    <!-- Footer Nav -->
                </div>
            </div>
        </div>

<!-- Footer Bottom Area End ##### -->

    <!-- ##### All Javascript Files ##### -->
    <!-- jQuery-2.2.4 js -->
    <script src="js/jquery/jquery-2.2.4.min.js"></script>
    <!-- Popper js -->
    <script src="js/bootstrap/popper.min.js"></script>
    <!-- Bootstrap js -->
    <script src="js/bootstrap/bootstrap.min.js"></script>
    <!-- All Plugins js -->
    <script src="js/plugins/plugins.js"></script>
    <!-- Active js -->
    <script src="js/active.js"></script>
</body>

</html>