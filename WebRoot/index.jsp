<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!doctype html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>天猫工商信息执照识别-深度学习技术实战应用 - Krry</title>
		<meta name="Keywords" content="关键词,关键词">
		<meta name="description" content="">
		<style type="text/css">
			*{margin:0;padding:0;}
			body{font-size:12px;font-family:"微软雅黑";color:#666;}
			img{border:none;}

			/*top start*/
			.top{width:100%;height:59px;background:#000;}
			.top .t-header{width:90%;height:59px;margin:0 auto;}
			.top .t-header .t-logo{float:left;margin-top:8px;}
			.top .t-header .t-desc{float:right;line-height:59px;font-size:18px;color:#FFF;}
			/*end top*/

			/*banner start*/
			.banner{width:100%;height:315px;background:url("images/banner.png");background-size:cover;padding-top:35px;}
			.banner .b-box{width:980px;height:285px;margin:0 auto;}
			.banner .b-box .b-view{float:left;position:relative;overflow:hidden;}
			.banner .b-box .b-now{width:453px;height:282px;background:url("images/1.jpg");float:right;border-radius:15px;position:relative;}
			.banner .b-box .b-now span{position:absolute;font-size:16px;font-weight:bold;}
			.banner .b-box .b-now #name{top:43px;left:83px;}
			.banner .b-box .b-now #sex{top:79px;left:83px;}
			.banner .b-box .b-now #nation{top:81px;left:192px;}
			.banner .b-box .b-now #year{top:117px;left:83px;}
			.banner .b-box .b-now #month{top:117px;left:164px;}
			.banner .b-box .b-now #day{top:117px;left:211px;}
			.banner .b-box .b-now #addr{top:156px;left:83px;}
			.banner .b-box .b-now #num{top:254px;left:156px;}
			.messagesss{font-size: 80px;font-weight: 12;font-family: cursive;color: white;line-height: 285px;text-align: center;}
			/*end banner*/

			/*footer start*/
			.footer{width:100%;height:332px;}
			.footer .f-box{width:980px;height:332px;margin:0 auto;}
			.footer .f-box h1{font-size:30px;font-weight:300;padding: 70px 0 20px 100px;}
			.footer .f-box p{font-size:16px;padding-left:100px;line-height:30px;}
			.footer .f-box .left{float:left;position:relative;}
			.footer .f-box .left img{position: absolute;left: 242px;top: 4px;cursor: pointer;}
			.footer .f-box .right{float:right}
			.fadeIns{display:none;}
			/*end footer*/

			.banner .b-box .b-view .b-scan{width:100%;height:100%;background:url("images/intro.png");position:absolute;background-size:cover;}

		</style>

	</head>
<body>

	<!--top start-->
	<div class="top">
		<div class="t-header">
			<div class="t-logo">
				<a href="#">
					<img src="images/logo.png" alt="乐诗" height="38" />
				</a>
			</div>
			<div class="t-desc">天猫工商信息执照识别 - 深度学习</div>
		</div>
	</div>
	<!--end top-->

	<!--banner start-->
	<div class="banner">
		<div class="b-box">
			<h2 class="messagesss">正在识别并写入...</h2>
		</div>
	</div>
	<!--end banner-->
	
	<!--footer start-->
	<div class="footer">
		<div class="f-box">
			<h1>天猫工商信息执照识别</h1>
			<p>
				<span class="left fadeIns">识别写入完成！点击下载Excel
					<a href="images/1.jpg" title="点击下载" download>
						<img alt="点击下载" src="images/download.png" width="20">
					</a>
				</span>
				<span class="right">识别图片文件夹路径：https://www.ainyi.com/krry_ai_msgShop/msgImg</span><br/>
				<span class="left">让用户了解每个天猫店铺的信息</span>
			</p>
		</div>
	</div>
	<!--end footer-->


<!--引入jQuery官方类库-->
<script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
	
	$(function(){
		
		// 请求服务器端数据
		query();
	});
	
	// 请求服务器
	function query(){
		var path = "/msgImg";
		$.ajax({
			type:"post",
			url:"data.jsp",
			data:{"path":path},
			success:function(data){
				$(".fadeIns").fadeIn(800);
				$(".messagesss").text("识别成功！下方下载");
				$(".fadeIns a").attr("href","msg/msg.xls");
			}
			
		});
		
	}


</script>

</body>
</html>