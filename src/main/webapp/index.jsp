<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>IIIBR</title>

<style>
body {
	background: #2574b0 url(images/bg-blueprint.png);
}

.viewCenter {
	background: rgba(255, 255, 255, 1);
	border-radius: 5px;
	box-shadow: 0px 0px 20px #000000;
	position: absolute;
	left: 50%;
	top: 50%;
	border:
}

#div1 {
	width: 300px;
	height: 480px;
	margin-left: -150px;
	margin-top: -240px;
}

#div2 {
	margin: 60px 5px;
}

#div3 {
	margin: 50px 5px;
	text-align: center;
}

.title1 {
	font-family: "Lucida Sans Unicode", "Lucida Grande", 微軟正黑體;
	font-size: 26px;
	font-weight: 900;
	text-align: center;
	color: #000000;
	text-shadow: 0px 0px 5px #cccccc;
}

.inputTanker {
	margin: 20px 8px;
	border-radius: 5px;
	height: 40px;
	weight: 230px;
	transition: all 500ms ease;
}

.inputMark {
	margin: 1px;
	padding: 5px;
	width: 30px;
	height: 30px;
	background: #eeeeee;
	border-radius: 5px 0px 0px 5px;
	position: absolute;
}

.inputText {
	font-family: "Lucida Sans Unicode", "Lucida Grande", 微軟正黑體;
	font-size: 18px;
	text-shadow: 0px 0px 2px #cccccc;
	color: #444444;
	padding: 5px 20px 5px 50px;
	width: 200px;
	height: 30px;
	border-radius: 5px;
	border: 1px solid #00B4FF;
	transition: all 500ms ease;
	float: left;
}

.inputText:FOCUS {
	box-shadow: 0px 0px 20px #ff7400;
	border: 1px solid #ff7400;
	outline: none;
}

.button1 {
	box-shadow: inset 0 -3px 0 #2f69c2;
	background-image: -webkit-linear- border-radius: 5px;
	border: 2px solid #4285f4;
	background-color: #4285f4;
	padding: 5px 10px;
	font-family: "Lucida Sans Unicode", "Lucida Grande", 微軟正黑體;
	font-weight: 900;
	color: #ffffff;
}

.button1:HOVER {
	box-shadow: inset 0 -3px 0 #55B4FF, 0 0 5px #00B4FF;
	background-image: -webkit-linear-
}

.button1:ACTIVE {
	box-shadow: inset 0 -3px 0 #FFFFFF, 0 0 5px #55B4FF;
	background-image: -webkit-linear- border:2px solid #4285f4;
}
</style>

</head>
<body>
	<div class="viewCenter" id="div1">
		<div class="title1" id="div2">-IIIBR起始頁面demo-</div>
		<form action="" method="post">
			<div id="div3">
				<div class="inputTanker">
					<div class="inputMark">
						<img alt="Accound" class="icon" width="25px" height="30px" src="<c:url value="/images/icon/user.png" />">
					</div>
					<input class="inputText" type="text" name="account"
						placeholder="請輸入帳號" autocomplete="off">
				</div>
				<div class="inputTanker">
					<div class="inputMark">
						<img alt="Password" class="icon" width="25px" height="30px" src="<c:url value="/images/icon/closed-lock.png" />">
					</div>
					<input class="inputText" type="password" name="password"
						placeholder="請輸入密碼" autocomplete="off">
				</div>
				<input class="button1" type="submit" name="submit" value="登入">
			</div>
		</form>
	</div>
</body>
</html>