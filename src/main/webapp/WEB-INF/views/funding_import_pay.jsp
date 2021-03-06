<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>creakok-funding pay</title>
</head>
<body>    

	<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
	<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<script>
        var IMP = window.IMP; // 생략가능
        IMP.init('imp94223712'); // imp90966824  'iamport' 대신 부여받은 "가맹점 식별코드"를 사용  
			   IMP.request_pay({     
			    pg : 'inicis', // version 1.1.0부터 지원.
			    pay_method : 'card',
			    merchant_uid : 'merchant_' + new Date().getTime(),
			    name :'${funding_payinfo.funding_subject}',
			    amount : '${funding_payinfo.funding_payinfo_amountpay}',
			    buyer_email : '${funding_payinfo.member_email}',
			    buyer_name : '${funding_payinfo.funding_payinfo_name}',
			    buyer_tel : '${funding_payinfo.funding_payinfo_phonenumber}',
			    buyer_addr : '서울특별시 강남구 삼성동',
			    buyer_postcode : '123-456',
			    m_redirect_url : 'https://www.yourdomain.com/payments/complete'
			}, function(rsp) {
			    if ( rsp.success ) {
			        var msg = '결제가 완료되었습니다.';
			        msg += '고유ID : ' + rsp.imp_uid;
			        msg += '상점 거래ID : ' + rsp.merchant_uid;
			        msg += '결제 금액 : ' + rsp.paid_amount;
			        msg += '카드 승인번호 : ' + rsp.apply_num;
			       
			        location.href="funding_pay_success.do?success_num="+rsp.imp_uid+"&success_id="+rsp.merchant_uid+"&success_amount="+rsp.paid_amount+"&success_card_num="+rsp.apply_num+"&success_pay="+rsp.success+
	        		"&buyer_name="+rsp.buyer_name+"&product_name="+rsp.name+"&amount="+rsp.amount+"&buyer_email="+rsp.buyer_email+"&buyer_phone="+rsp.buyer_tel+
	        		"&buyer_addr="+rsp.buyer_addr+"&buyer_postcode="+rsp.buyer_postcode;
			    } else {
			        var msg = '결제에 실패하였습니다.';
			        msg += '에러내용 : ' + rsp.error_msg;
			        location.href="funding_pay_fail.do?error_msg="+rsp.error_msg;
			    }
			    //alert(msg);
			});
        </script>
</body>
</html>