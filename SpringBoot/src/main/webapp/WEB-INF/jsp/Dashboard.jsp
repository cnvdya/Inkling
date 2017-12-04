<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>


<html>
<style>
  /*body{*/
    /*background:url('http://www.wallpaperup.com/uploads/wallpapers/2012/10/21/20181/cad2441dd3252cf53f12154412286ba0.jpg');*/
    /*padding:50px;*/
  /*}*/



  body{
    /*#\background:url('http://www.wallpaperup.com/uploads/wallpapers/2012/10/21/20181/cad2441dd3252cf53f12154412286ba0.jpg');*/
    padding:50px;

    background-image: url('https://www.wallpaperup.com/uploads/wallpapers/2015/01/23/599146/699b0c8b59880ebbd42ff554d937d857.jpg');
    background-position: center;
    background-size: cover;

  }

  #login-dp{
    min-width: 250px;
    padding: 14px 14px 0;
    overflow:hidden;
    background-color:rgba(255,255,255,.8);
  }
  #login-dp .help-block{
    font-size:12px
  }
  #login-dp .bottom{
    background-color:rgba(255,255,255,.8);
    border-top:1px solid #ddd;
    clear:both;
    padding:14px;
  }
  #login-dp .social-buttons{
    margin:12px 0
  }
  #login-dp .social-buttons a{
    width: 49%;
  }
  #login-dp .form-group {
    margin-bottom: 10px;
  }
  .btn-fb{
    color: #fff;
    background-color:#3b5998;
  }
  .btn-fb:hover{
    color: #fff;
    background-color:#496ebc
  }
  .btn-tw{
    color: #fff;
    background-color:#55acee;
  }
  .btn-tw:hover{
    color: #fff;
    background-color:#59b5fa;
  }
  @media(max-width:768px){
    #login-dp{
      background-color: inherit;
      color: #fff;
    }
    #login-dp .bottom{
      background-color: inherit;
      border-top:0 none;
    }
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

  .navbar-custom {
    color: #FFFFFF;
    background-color: #CC3333;
  }
</style>
<style>
  img {
    border: 1px solid #ddd;
    border-radius: 4px;
    padding: 20px;
    width: 290px;
  }
  #myImg {
    border-radius: 5px;
    cursor: pointer;
    transition: 0.3s;
  }

  #myImgAll {
    border-radius: 5px;
    cursor: pointer;
    transition: 0.3s;
  }

  #myImgEvent {
    border-radius: 5px;
    cursor: pointer;
    transition: 0.3s;
  }

  #myImg:hover {opacity: 0.7;}
  #myImgAll:hover {opacity: 0.7;}
  #myImgEvent:hover {opacity: 0.7;}
  .myImg:hover {opacity: 0.7;}
  /* The Modal (background) */
  .modal {
    display: none; /* Hidden by default */
    position: fixed; /* Stay in place */
    z-index: 1; /* Sit on top */
    padding-top: 100px; /* Location of the box */
    left: 0;
    top: 0;
    width: 100%; /* Full width */
    height: 100%; /* Full height */
    overflow: auto; /* Enable scroll if needed */
    background-color: rgb(0,0,0); /* Fallback color */
    background-color: rgba(0,0,0,0.9); /* Black w/ opacity */
  }

  /* Modal Content (image) */
  .modal-content {
    margin: auto;
    display: block;
    width: 60%;
    max-width: 700px;
  }

  /* Caption of Modal Image */
  #caption {
    margin: auto;
    display: block;
    width: 80%;
    max-width: 700px;
    text-align: center;
    color: #ccc;
    padding: 10px 0;
    height: 150px;
  }

  /* Add Animation */
  .modal-content, #caption {
    -webkit-animation-name: zoom;
    -webkit-animation-duration: 0.6s;
    animation-name: zoom;
    animation-duration: 0.6s;
  }

  @-webkit-keyframes zoom {
    from {-webkit-transform:scale(0)}
    to {-webkit-transform:scale(1)}
  }

  @keyframes zoom {
    from {transform:scale(0)}
    to {transform:scale(1)}
  }

  /* The Close Button */
  .close {
    position: absolute;
    top: 15px;
    right: 35px;
    color: #f1f1f1;
    font-size: 40px;
    font-weight: bold;
    transition: 0.3s;
  }

  .close:hover,
  .close:focus {
    color: #bbb;
    text-decoration: none;
    cursor: pointer;
  }

  /* 100% Image Width on Smaller Screens */
  @media only screen and (max-width: 700px){
    .modal-content {
      width: 100%;
    }
  }



</style>
<head>

  <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<%-- <body>
             
                  




    
<form action="speech.do" method="post">
<div class=”form-group”>
<button type="submit" style="width: 50%; font-size:1.1em;" >
<b><font color="Orange">Click for Welcome Message</font></b></button>


</div>
</form>
<form action="chatBot.do" method="post">
<div class=”form-group”>
<button type="submit" style="width: 50%; font-size:1.1em;" >
<b><font color="Orange">Talking to InklingBot</font></b></button>

</div>
</form>

<br>
<br>
<br>
                

</body> --%>


<body>
<script>console.log('name passed to ui ${name}')</script>

<script>
    var currlabel="smile";
    console.log(currlabel);
    if('${curlabel}' != null ||'${curlabel}' != undefined ){
        console.log('${curlabel}');
        currlabel='${curlabel}';
    }
    function getlabelresult(label) {
        window.location.href="http://localhost:8080/alexadetectlabels?labelTag="+label;
    }

    function refresh() {
        var xhttp = new XMLHttpRequest();
        xhttp.open("GET", "https://4ofiss7q6l.execute-api.us-east-1.amazonaws.com/prod/", true);
        xhttp.send();
        xhttp.addEventListener("readystatechange", processRequest, false);
        console.log(currlabel);
        function processRequest(e) {
            console.log('starting process');
            if (xhttp.status == 200) {
                var response = JSON.parse(xhttp.responseText);
                console.log('response  ===== ' +response);
                var numberString = response.InklingPictures.toLowerCase();
                console.log('currlabel ======= '+currlabel);
                console.log('numberString ======= '+numberString);
                console.log('if value :');

                if(numberString!=(currlabel) ) {
                    console.log('came inside if');
                    currlabel=numberString;
                    getlabelresult(currlabel);}
                console.log(numberString);
            }
        }
        setTimeout(refresh, 3000);
    }
    refresh();
</script>

<audio autoplay>
  <source src="<c:out value = "${pollyURL} "/>" type="audio/mpeg">
</audio>

<div class="row">
  <div class="col-sm-6">
    <h3 style="float:left; color: #FFFFFF"><strong>Inkling</strong></h3>
  </div>
  <div class="col-sm-6">
    <h3 style="float:right; color: #FFFFFF"><strong>Welcome ${name}!</strong></h3>
  </div>
  <script>console.log('name printed to ui ${name}')</script>
</div>

<%--<div align = "right"><a href="#">Logout</a></div>--%>
<nav class="navbar navbar-default navbar-inverse" role="navigation">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->


    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">

        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Events <span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="../events?event=Christmas">Christmas</a></li>
            <li><a href="../events?event=ThanksGiving">ThanksGiving</a></li>
            <li><a href="../events?event=Anniversary">Anniversary</a></li>
            <li><a href="../events?event=Halloween">Halloween</a></li>
          </ul>
        </li>
      </ul>

      <form class="navbar-form navbar-left" role="search" action="detectlabels.do">
        <div class="form-group">
          <input type="text" name = "labelTag" class="form-control" placeholder="Search by Tags">
        </div>
        <button id = "searchButton" type="submit" class="btn btn-default">Submit</button>
      </form>
      <form class="sa-innate-form" action="listCollection.do" method="post">

        <button type="submit" style="width: 25%; font-size:1.1em;" >
          <b><font color="">List My Images</font></b></button>

        <div style="float: right ; margin-top: 20px">
          <a href=${'logout.do'}>
            <div style="color: #FFFFFF"><b>Logout</b></div>
          </a>
        </div>
      </form>




      <%--<form class="navbar-form navbar-right" action="logout.do" method="get">--%>
        <%--<div class="form-group">--%>
          <%--<input type="submit" value="Logout"></input>--%>
        <%--</div>--%>
      <%--</form>--%>

      <!--    <img src = "https://raw.githubusercontent.com/adenix/SpinnerCSS/master/spinner.gif" id ="loadingIcon"/> -->
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>





<div class="form-container text-center col-xs-3">

  <img src = "<c:out value="${imageURLString}"/>">

  <%-- <form class="sa-innate-form" action="detectlabels.do" method="post">
  <div class=”form-group”>
  <button type="submit" style="width: 50%; font-size:1.1em;" >
  <b><font color="">Detect Labels</font></b></button>
  </div>
  </form> --%>

</div>

<div class="col-xs-6" id="hideCarousel">
  <div id="myCarousel" class="carousel slide" data-ride="carousel" data-interval="2000">
    <!-- Indicators -->
    <ol class="carousel-indicators" align="center">
      <li data-target="#myCarousel" data-slide-to="0" class="active"> </li>
      <li data-target="#myCarousel" data-slide-to="1"></li>
      <li data-target="#myCarousel" data-slide-to="2"></li>
    </ol>

    <!-- Wrapper for slides -->
    <div class="carousel-inner">
      <div class="item active">
        <img src="https://s3.amazonaws.com/targetimages/image17_c.jpg" alt="Halloween" style="width: 800px;height: 400px"/>
      </div>

      <div class="item">
        <img src="https://s3.amazonaws.com/targetimages/image8_c.jpg" alt="Christmas" style="width: 800px;height: 400px">
      </div>

      <div class="item">
        <img src="https://s3.amazonaws.com/targetimages/image9_c.jpg" alt="Random" style="width:800px;height: 400px">
      </div>
    </div>

    <!-- Left and right controls -->
    <a class="left carousel-control" href="#myCarousel" data-slide="prev">
      <span class="glyphicon glyphicon-chevron-left"></span>
      <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#myCarousel" data-slide="next">
      <span class="glyphicon glyphicon-chevron-right"></span>
      <span class="sr-only">Next</span>
    </a>
  </div>
  <br/><br/>
</div>



<div align="center" id="showMyImages" class="col-xs-12">
  <c:if test="${showLabelImages==true}">
    <h3 style="color: #FFFFFF"><strong>Photos for ${label}</strong></h3>
  </c:if>
  <c:if test="${showImage==true}">
    <h3 style="color: #FFFFFF"><strong>Photos for ${name}</strong></h3>
  </c:if>
  <c:if test="${showEventImages==true}">
    <h3 style="color: #FFFFFF"><strong>Photos for ${label}</strong></h3>
  </c:if>
  <hr>
</div>






<div class = "col-xs-12" id = "allImages" style="padding-left:6%;">
  <% int j = 0;%>
  <c:forEach var="imgAll" items="${imageURLAll}">
    <% j++; %>
    <img id="myImgAll<%=j%>" class="myImg" src="<c:out value = "${imgAll.imageURL}"/>" />&nbsp;&nbsp;
    <% if(j==5*j){ %><br/><br/> <% } %>

    <div id="myModal" class="modal">
      <span class="close">&times;</span>
      <img class="modal-content" id="myImgAll01">
      <div id="caption"></div>
    </div>
    <script>
        // Get the modal
        var modal = document.getElementById('myModal');

        // Get the image and insert it inside the modal - use its "alt" text as a caption
        var img = document.getElementById("myImgAll<%=j%>");
        var modalImg = document.getElementById("myImgAll01");
        var captionText = document.getElementById("caption");
        img.onclick = function(){
            modal.style.display = "block";
            modalImg.src = this.src;
            captionText.innerHTML = this.alt;
        };

        // Get the <span> element that closes the modal
        var span = document.getElementsByClassName("close")[0];

        // When the user clicks on <span> (x), close the modal
        span.onclick = function() {
            modal.style.display = "none";
        }
    </script>
  </c:forEach>

</div>
<div align="center" id="showMyImages" class="col-xs-12">
  <c:if test="${showImage==true}">
    <script>
        document.getElementById("hideCarousel").remove();
        document.getElementById("allImages").remove();
    </script>
    <% int i = 0; %>
    <c:forEach var="img" items="${imageURL}">
      <% i++; %>
      <img id = "myImg<%=i%>" class="myImg" src = "<c:out value="${img.imageURL}"/>">
      <!-- The Modal -->
      <div id="myModal" class="modal">
        <span class="close">&times;</span>
        <img class="modal-content" id="img01">
        <div id="caption"></div>
      </div>
      <script>
          // Get the modal
          var modal = document.getElementById('myModal');

          // Get the image and insert it inside the modal - use its "alt" text as a caption
          var img = document.getElementById("myImg<%=i%>");
          var modalImg = document.getElementById("img01");
          var captionText = document.getElementById("caption");
          img.onclick = function(){
              modal.style.display = "block";
              modalImg.src = this.src;
              captionText.innerHTML = this.alt;
          };

          // Get the <span> element that closes the modal
          var span = document.getElementsByClassName("close")[0];
          '<a class = "download" href=""></a>'
          // When the user clicks on <span> (x), close the modal
          span.onclick = function() {
              modal.style.display = "none";
          }
      </script>
    </c:forEach>
  </c:if>
</div>


<div class="col-xs-12" align="center">
  <c:if test="${showEventImages==true}">
    <c:if test="${showImage==true}">
      <script>
          document.getElementById("showMyImages").remove();
      </script>
    </c:if>
    <script>
        document.getElementById("hideCarousel").remove();
        document.getElementById("allImages").remove();
    </script>
    <% int k = 0; %>
    <c:forEach var="img" items="${imageURLEvent}">
      <% k++; %>
      <img id = "myImgEvent<%=k%>" class="myImg" src = "<c:out value="${img.imageURL}"/>">
      <!-- The Modal -->
      <div id="myModal" class="modal">
        <span class="close">&times;</span>
        <img class="modal-content" id="imgEvent01">
        <div id="caption"></div>
      </div>
      <script>
          // Get the modal
          var modal = document.getElementById('myModal');

          // Get the image and insert it inside the modal - use its "alt" text as a caption
          var img = document.getElementById("myImgEvent<%=k%>");
          var modalImg = document.getElementById("imgEvent01");
          var captionText = document.getElementById("caption");
          img.onclick = function(){
              modal.style.display = "block";
              modalImg.src = this.src;
              captionText.innerHTML = this.alt;
          };

          // Get the <span> element that closes the modal
          var span = document.getElementsByClassName("close")[0];

          // When the user clicks on <span> (x), close the modal
          span.onclick = function() {
              modal.style.display = "none";
          }
      </script>
    </c:forEach>
  </c:if>
</div>

<div class="col-xs-12" align="center">

  <c:if test="${showLabelImages==true}">
    <script>
        //document.getElementById("loadingIcon").remove();
        document.getElementById("hideCarousel").remove();
        document.getElementById("allImages").remove();
        //document.getElementById("showMyImages").remove();
    </script>
    <% int k = 0; %>
    <c:forEach var="img" items="${imageDetectLabel}">
      <% k++; %>
      <img id = "myImgLabel<%=k%>" class="myImg" src = "<c:out value="${img.imageURL}"/>">
      <!-- The Modal -->
      <div id="myModal" class="modal">
        <span class="close">&times;</span>
        <img class="modal-content" id="imgLabel01">
        <div id="caption"></div>
      </div>
      <script>
          // Get the modal
          var modal = document.getElementById('myModal');

          // Get the image and insert it inside the modal - use its "alt" text as a caption
          var img = document.getElementById("myImgLabel<%=k%>");
          var modalImg = document.getElementById("imgLabel01");
          var captionText = document.getElementById("caption");
          img.onclick = function(){
              modal.style.display = "block";
              modalImg.src = this.src;
              captionText.innerHTML = this.alt;
          };

          // Get the <span> element that closes the modal
          var span = document.getElementsByClassName("close")[0];

          // When the user clicks on <span> (x), close the modal
          span.onclick = function() {
              modal.style.display = "none";
          }
      </script>
    </c:forEach>
  </c:if>
</div>

</body>





</html>
