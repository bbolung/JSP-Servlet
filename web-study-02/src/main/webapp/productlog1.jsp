<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style>
        * {margin: 0 auto; padding: 0; box-sizing: border-box;}

        h1 {height: 60px; text-align: center; margin-top: 30px; margin-bottom: 10px;}

        hr {width: 98%;}
		
        #container {
            width: 90%;
            display: flex; flex-direction: row; flex-wrap: wrap;
            justify-content: center; align-items: center; 
        }

        .doll img{
            width: 270px; height: 270px; margin: 30px 40px 5px 0; padding: 15px;
            border: 2px solid #000; box-shadow: 10px 10px 5px  #666;
        }

        .doll a:hover {
            opacity: 0.6;
        }

    </style>
</head>
<body>
    <h1>원하는 상품을 클릭해 주세요!! 마구마구^^</h1>
    <hr>
    <form method="get" action="basket1">
        <div id="container">
        	<div class="doll"><a href="basket1?id=p01"><img src="./images/1.jpg" alt="doll1"></a></div>
        	<div class="doll"><a href="basket1?id=p02"><img src="./images/2.jpg" alt="doll2"></a></div>
        	<div class="doll"><a href="basket1?id=p03"><img src="./images/3.jpg" alt="doll3"></a></div>
        	<div class="doll"><a href="basket1?id=p04"><img src="./images/4.jpg" alt="doll4"></a></div>
        	<div class="doll"><a href="basket1?id=p05"><img src="./images/5.jpg" alt="doll5"></a></div>
        	<div class="doll"><a href="basket1?id=p06"><img src="./images/6.jpg" alt="doll6"></a></div>
        	<div class="doll"><a href="basket1?id=p07"><img src="./images/7.jpg" alt="doll7"></a></div>
        	<div class="doll"><a href="basket1?id=p08"><img src="./images/8.jpg" alt="doll8"></a></div>
        	<div class="doll"><a href="basket1?id=p09"><img src="./images/9.jpg" alt="doll9"></a></div>
        	<div class="doll"><a href="basket1?id=p010"><img src="./images/10.jpg" alt="doll10"></a></div>
        </div>
    </form>
    
</body>
</html>