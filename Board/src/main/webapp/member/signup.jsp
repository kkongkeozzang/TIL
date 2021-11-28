<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sign UP</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
	$(function(){
		// 아이디 입력 검사
		$("#id-check").on("click",function(){
			if($("#input-id").val()==""){
				alert("아이디를 입력해주세요.");
				return;
			}
			
			window.open("/idCheck.mem?id="+$("#input-id").val(),"","width=300px,height=200px,top=200px,left=200px");
		})
	})
</script>
<style>
	.a {
		text-align: right;
	}
	
	.row {
		outline: 1px solid black;
		margin-top: 3px;
	}
	
	button {
		padding: 0px 5px;
	}
	
	.container .row #phone2 {
		width: 80px;
	}
	
	.container .row #phone3 {
		width: 80px;
	}
	
	.container {
		margin-top: 2px;
		width: 600px;
	}
	
	.row>div {
		padding-top: 10px;
		padding-bottom: 10px;
	}
</style>
</head>

<body>
<form action="signupProc.mem" method="post">
        <div class="container">
        	<!-- title -->
            <div class="row ">
                <div class="a col-12 text-center align-self-center">회원 가입 정보 입력</div>
            </div>
            <!-- id -->
            <div class="row">
                <div class="a col-3 align-self-center" >아이디 :</div>
                <div class="col-9 align-self-center">
                    <input class="align-self-center" type="text" id="input-id">
                    <button class="align-self-center" id="id-check" type="button" >중복확인</button>
                </div>
                <div class="a col-3 align-self-center" style="padding:0"></div>
                <div class="col-9 d-flex" id="check" style="padding-top:0;padding-bottom:0;">
                    <div class="align-self-center" id="id-result"></div>
                </div>
            </div>
            <!-- id 유효성 체크 -->
            <script>
                let idFlag=false;
                let idRegex = /^[a-z][a-z0-9]{5,13}$/;
                let idCheck = function(){
                    if(idRegex.test($("#input-id").val())){
                        $("#id-result").html("사용할 수 있는 아이디입니다."); 
                        $("#id-result").css("color","green");
                        $("#id-result").css("padding-bottom","10px");
                        idFlag = true;
                    }else{
                        $("#id-result").html("사용할 수 없는 아이디입니다."); 
                        $("#id-result").css("color","red");
                        $("#id-result").css("padding-bottom","10px");
                        ifFlag = false;
                    }
                }
                $("#input-id").on("input",idCheck);
                $("#input-id").on("focus",idCheck);
                $("#input-id").on("blur",function(){
                    $("#id-result").html(""); 
                    $("#id-result").css("padding-bottom","0px");
                })
            </script>
            <!-- pw -->
            <div class="row">
                <div class="a col-3 align-self-center">패스워드 :</div>
                <div class="col-9 align-self-center">
                    <input class="align-self-center" type="password" id="input-pw">
                </div>
                <div class="a col-3 align-self-center">패스워드 확인 :</div>
                <div class="col-9 d-flex" id="check">
                    <input class="align-self-center" type="password" id="input-rpw">
                </div>
                <div class="a col-3 align-self-center" style="padding:0"></div>
                <div class="col-9 " id="check" style="padding-top:0;padding-bottom:0;">
                    <div class="align-self-center" id="match"></div>
                </div>
            </div>
    		<!-- pw 유효성 체크 -->
            <script>
                let rpw = document.getElementById("input-rpw");
                let pwFlag=false;

                let pwCheck = function(){
                    let pw = document.getElementById("input-pw");
                    let match = document.getElementById("match");
    
                    if (rpw.value == pw.value) {
                        match.innerText = "비밀번호가 일치합니다.";
                        $("#match").css("color","green");
                        $("#match").css("padding-bottom","10px");
                        pwFlag = true;
                    } else if(rpw.value == ""){
                        match.innerText = "";
                    } else {
                        match.innerText = "비밀번호가 일치하지 않습니다.";
                        $("#match").css("color","red");
                        $("#match").css("padding-bottom","10px");
                        pwFlag = false;
                    }
                }
                let pwCheckEvent = $("#input-rpw").on("input",pwCheck);
                $("#input-rpw").on("focus",function(){
                    pwCheckEvent;
                })
                $("#input-rpw").on("blur",function(){
                    $("#match").html(""); 
                    $("#match").css("padding-bottom","0px");
                })
            </script>
            <!-- 이름 -->
            <div class="row">
                <div class="a col-3 align-self-center">이름 :</div>
                <div class="col-9 align-self-center">
                    <input class="align-self-center" type="text" id="input-name">
                </div>
                <div class="a col-3 align-self-center" style="padding:0"></div>
                <div class="col-9 d-flex" id="check" style="padding-top:0;padding-bottom:0;">
                    <div class="align-self-center" id="name-result"></div>
                </div>
                <!-- 이름 유효성 체크 -->
                <script>
                    let nameFlag=false;
                    let nameRegex = /^[가-힣]{1,5}$/;
                    let nameCheck = function(){
                        if(nameRegex.test($("#input-name").val())){
                            $("#name-result").html("사용할 수 있는 이름입니다."); 
                            $("#name-result").css("color","green");
                            $("#name-result").css("padding-bottom","10px");
                            nameFlag =true;
                        }else{
                            $("#name-result").html("사용할 수 없는 이름입니다."); 
                            $("#name-result").css("color","red");
                            $("#name-result").css("padding-bottom","10px");
                            nameFlag =false;
                        }
                    }
                    $("#input-name").on("input",nameCheck);
                    $("#input-name").on("focus",nameCheck);
                    $("#input-name").on("blur",function(){
                        $("#name-result").html(""); 
                        $("#name-result").css("padding-bottom","0px");
                    })
                    
                </script>
            </div>
            <!-- 전화번호 -->
            <div class="row">
                <div class="a col-3 align-self-center">전화번호 :</div>
                <div class="col-9 align-self-center">
                    <select id="phone">
                        <option>010</option>
                        <option>011</option>
                    </select>
                    -
                    <input class="align-self-center" type="text" id="phone2">-
                    <input class="align-self-center" type="text" id="phone3">
                </div>
                <div class="a col-3 align-self-center" style="padding:0"></div>
                <div class="col-9" id="check" style="padding-top:0;padding-bottom:0;">
                    <div class="align-self-center" id="phone-result"></div>
                </div>
            </div>
            <!-- 전화번호 유효성 체크 -->
            <script>
                let phoneFlag=false;
                let phoneRegex = /^\d{4}$/;
                let phoneCheck = function(){
                    let phone2Val = phoneRegex.test($("#phone2").val());
                    let phone3Val = phoneRegex.test($("#phone3").val());
                    if(!phone2Val){
                        $("#phone-result").html("중간 번호를 확인해주세요."); 
                        $("#phone-result").css("color","red");
                        $("#phone-result").css("padding-bottom","10px");
                        phoneFlag = false;
                    }else if(!phone3Val){
                        $("#phone-result").html("마지막 번호를 확인해주세요."); 
                        $("#phone-result").css("color","red");
                        $("#phone-result").css("padding-bottom","10px");
                        phoneFlag = false;
                    }else if(phone3Val&&phone2Val){
                        $("#phone-result").html(""); 
                        phoneFlag = true;
                    }
                }
                $("#phone2").on("input",phoneCheck);
                $("#phone2").on("focus",phoneCheck);
                $("#phone2").on("blur",function(){
                    $("#phone-result").html(""); 
                    $("#phone-result").css("padding-bottom","0px");
                })
                $("#phone3").on("input",phoneCheck);
                $("#phone3").on("focus",phoneCheck);
                $("#phone3").on("blur",function(){
                    $("#phone-result").html(""); 
                    $("#phone-result").css("padding-bottom","0px");
                })
            </script>
            <!-- 이메일 -->
            <div class="row">
                <div class="a col-3 align-self-center">이메일 :</div>
                <div class="col-9 align-self-center">
                    <input type="text" id="input-email">
                </div>
                <div class="a col-3 align-self-center" style="padding:0"></div>
                <div class="col-9 d-flex" id="check" style="padding-top:0;padding-bottom:0;">
                    <div class="align-self-center" id="email-result"></div>
                </div>
            </div>
            <!-- 이메일 유효성 체크 -->
            <script>
                let emailFlag=false;
                let emailRegex = /.+?@.+?.com$/;
                let emailCheck = function(){
                    if(emailRegex.test($("#input-email").val())){
                        $("#email-result").html("사용할 수 있는 메일입니다."); 
                        $("#email-result").css("color","green");
                        $("#email-result").css("padding-bottom","10px");
                        emailFlag=true;
                    }else{
                        $("#email-result").html("사용할 수 없는 메일입니다."); 
                        $("#email-result").css("color","red");
                        $("#email-result").css("padding-bottom","10px");
                        emailFlag=false;
                    }
                }
                $("#input-email").on("input",emailCheck);
                $("#input-email").on("focus",emailCheck);
                $("#input-email").on("blur",function(){
                    $("#email-result").html(""); 
                    $("#email-result").css("padding-bottom","0px");
                })
            </script>
            <!-- 우편번호 -->
            <div class="row">
                <div class="a col-3 align-self-center">우편번호 :</div>
                <div class="col-9 align-self-center">
                    <input class="align-self-center" type="text" id="zipCode">
                    <button type="button" class="align-self-center" onclick="sample6_execDaumPostcode()">찾기</button>
                </div>
            </div>
            <div class="row">
                <div class="a col-3 align-self-center">주소1 :</div>
                <div class="col-9 align-self-center">
                    <input class="align-self-center" type="text" class="add" id="address1">
                </div>
            </div>
            <div class="row">
                <div class="a col-3 align-self-center">주소2 :</div>
                <div class="col-9 align-self-center">
                    <input class="align-self-center" type="text" class="add" id="address2">
                </div>
            </div>
    		<!-- 다음 API 사용 -->
            <script>
                let addressFlag=false;
                function sample6_execDaumPostcode() {
                    new daum.Postcode({
                        oncomplete: function (data) {
                            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
    
                            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                            var addr = ''; // 주소 변수
    
                            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                                addr = data.roadAddress;
                            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                                addr = data.jibunAddress;
                            }
    
    
                            // 우편번호와 주소 정보를 해당 필드에 넣는다.
                            document.getElementById('zipCode').value = data.zonecode;
                            document.getElementById("address1").value = addr;
                            // 커서를 상세주소 필드로 이동한다.
                            document.getElementById("address2").focus();
                            addressFlag=true;
                        }
                    }).open();
                }
            </script>
            <!-- 회원가입 버튼과 다시 입력 버튼 -->
            <div class="row">
                <div class="col-12 text-center align-self-center">
                    <button id="signup">회원가입</button>
                    <button type=reset>다시 입력</button>
                </div>
            </div>
			<!-- 회원가입 유효성 검사 -->
            <script>
            	<%-- 하나라도 입력되지 않은 게 있으면 페이지 넘김 막기--%>
	            
                $("#signup").on("click",function(){
		            let isAllFillIn = idFlag&&pwFlag&&nameFlag&&phoneFlag&&emailFlag&&addressFlag;
                    if(!isAllFillIn){
	                    alert("회원 가입 정보를 입력해주세요.");
	                    return false;
                    }
                })
            </script>
        </div>
    </form>
</body>
</html>