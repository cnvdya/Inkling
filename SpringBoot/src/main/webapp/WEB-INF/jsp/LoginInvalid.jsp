<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%-- 

<h2>From JSP page </h2>
<%@ page language="java"
    contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<body>
    Message : ${msg}
    
   <!--  <form action="createCollection.do" method="post">
<div class=âform-groupâ>
<button type="submit" style="width: 50%; font-size:1.1em;" >
<b><font color="Orange">Create Collection</font></b></button>


</div>
</form>

 <form action="addImage.do" method="post">
<div class=âform-groupâ>
<button type="submit" style="width: 50%; font-size:1.1em;" >
<b><font color="Orange">Add Images to Collection</font></b></button>


</div>
</form>
    <form action="listCollection.do" method="post">
<div class=âform-groupâ>
<button type="submit" style="width: 50%; font-size:1.1em;" >
<b><font color="Orange">List Images In Collection</font></b></button>


</div>
</form>

    <form action="deleteCollection.do" method="post">
<div class=âform-groupâ>
<button type="submit" style="width: 50%; font-size:1.1em;" >
<b><font color="Orange">Delete Images In Collection</font></b></button>


</div>
</form>
 -->
 
 <form action="login.do" method="post" >
<div class=âform-groupâ>

<label for=âexampleInputEmail1â³><font color="Blue">Username</font></label>
<input type="text" name="name" class=âform-controlâ placeholder="EnterName">
</div>
<div class=âform-groupâ>
<label for=âexampleInputPassword1â³><font color="Blue">Password</font></label>&nbsp;
<input type="password" class=âform-controlâ  name="password" placeholder=âPasswordâ>
</div>
 <button type="submit" style="width: 100%; font-size:1.1em;" class="btn btn-large btn btn-success btn-lg btn-block" ><b><font color="Blue">Login</font></b></button>
                                                   

</form>


</body>
</html>
 --%>
<style>

body{
    background:url('http://www.wallpaperup.com/uploads/wallpapers/2012/10/21/20181/cad2441dd3252cf53f12154412286ba0.jpg');
    padding:50px;
}



.login-form{
    background:rgba(255,255,255,0.8);
	padding:20px;
	border-top:3px solid#3e4043;
}
.innter-form{
	padding-top:20px;
}
.final-login li{
	width:50%;
}

.nav-tabs {
    border-bottom: none !important;
}

.nav-tabs>li{
	color:#222 !important;
}
.nav-tabs>li.active>a, .nav-tabs>li.active>a:hover, .nav-tabs>li.active>a:focus {
    color: #fff;
    background-color: #d14d42;
    border: none !important;
    border-bottom-color: transparent;
	border-radius:none !important;
}
.nav-tabs>li>a {
    margin-right: 2px;
    line-height: 1.428571429;
    border: none !important;
    border-radius:none !important;
	text-transform:uppercase;
	font-size:16px;
}

.social-login{
	text-align:center;
	font-size:12px;
}
.social-login p{
	margin:15px 0;
}
.social-login ul{
	margin:0;
	padding:0;
	list-style-type:none;
}
.social-login ul li{
	width:33%;
	float:left;
    clear:fix;
}
.social-login ul li a{
	font-size:13px;
	color:#fff;
	text-decoration:none;
	padding:10px 0;
	display:block;
}
.social-login ul li:nth-child(1) a{
	background:#3b5998;
}
.social-login ul li:nth-child(2) a{
	background:#e74c3d;
}
.social-login ul li:nth-child(3) a{
	background:#3698d9;
}
.sa-innate-form input[type=text], input[type=password], input[type=file], textarea, select, email{
    font-size:13px;
	padding:10px;
	border:1px solid#ccc;
	outline:none;
	width:100%;
	margin:8px 0;
	
}
.sa-innate-form input[type=submit]{
    border:1px solid#e64b3b;
	background:#e64b3b;
	color:#fff;
	padding:10px 25px;
	font-size:14px;
	margin-top:5px;
	}
	.sa-innate-form input[type=submit]:hover{
	border:1px solid#db3b2b;
	background:#db3b2b;
	color:#fff;
	}
	
	.sa-innate-form button{
	border:1px solid#e64b3b;
	background:#e64b3b;
	color:#fff;
	padding:10px 25px;
	font-size:14px;
	margin-top:5px;
	}
	.sa-innate-form button:hover{
	border:1px solid#db3b2b;
	background:#db3b2b;
	color:#fff;
	}
    .sa-innate-form p{
        font-size:13px;
        padding-top:10px;
    }

</style>
<html>
<head>
<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body background="http://www.wallpaperup.com/uploads/wallpapers/2012/10/21/20181/cad2441dd3252cf53f12154412286ba0.jpg" style="padding:50px">


<div class="container">
	<div class="row">
		<h2>Welcome to Inkling </h2>
	</div>
</div>
<br>
<br>
<div class="container">
<div class="row">
<div class="col-md-4 col-md-offset-4">
<div class="form-body">
						<!-- Uncomment to create collection-->
						<!-- <form action="addImage.do" method="post">
						<div class=âform-groupâ>
						<button type="submit" style="width: 50%; font-size:1.1em;" >
						<b><font color="Orange">Add Images to Collection</font></b></button>
						</div>
						</form> 
						Uncomment to delete collection
						<form action="deleteCollection.do" method="post">
						<div class=âform-groupâ>
						<button type="submit" style="width: 50%; font-size:1.1em;" >
						<b><font color="Orange">Delete Images In Collection</font></b></button>
						</div>
						</form>
						Uncomment to create collection
						<form action="createCollection.do" method="post">
						<div class=âform-groupâ>
						<button type="submit" style="width: 50%; font-size:1.1em;" >
						<b><font color="Orange">Create Collection</font></b></button>
						</div>
						</form>
						 -->
						
    <div class="tab-content">
        <div id="sectionA" class="tab-pane fade in active">
        <div class="innter-form">
            <form class="sa-innate-form" method="post" action="login.do">
            <label>Username</label>
            <input type="text" name="name">
            <label>Password</label>
            <input type="password" name="password">
            <button type="submit">Sign In</button>
            <div class=âform-groupâ>${message}</div>
            </form>
            </div>
           
           
        </div>
        
    </div>
    </div>
    </div>
    </div>
    </div>

</body>
</html>
