<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Homepage</title>
    <link href="css/vendor/bootstrap.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="css/loginpage.css" rel="stylesheet">
</head>

<body bgcolor="#1e90ff">
<center>
    <div class ="container" style="height: 754px; width: 1574px; background-image: url(../../images/Householdtracker.jpg);">
        <font color="white">
            <strong>
                <font size="200">
                    Welcome!
                   <br>
                </font>
                <button id="logout">Logout</button>
            </strong>
        </font>
    </div>
</center>

<!-- Bootstrap core JavaScript
  ================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script>window.jQuery || document.write('<script src="js/vendor/jquery.min.js"><\/script>')</script>
<script src="js/vendor/bootstrap.min.js"></script>
<script type="text/javascript">
    $(function () {
        $("#logout").click(function (event) {

            var posting = $.post("account/logout") ;

            posting.done(function() {
                    window.location.href ="account/loginpage.html";
            });
        });

   });
</script>

</body>
</html>