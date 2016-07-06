var helloit = helloit || {};

helloit.createNavbar = function () {
    var $result = $('\
    <nav class="navbar navbar-light bg-faded main-navbar">\
    <button class="navbar-toggler hidden-sm-up" type="button" data-toggle="collapse"\
    data-target="#exCollapsingNavbar2">\
        &#9776;\
    </button>\
    <div class="collapse navbar-toggleable-xs" id="exCollapsingNavbar2">\
        <a class="navbar-brand" href="#">Welcome!</a>\
    <ul class="nav navbar-nav">\
        <li class="nav-item">\
        <a class="nav-link" href="index.html">Home <span class="sr-only">(current)</span></a>\
    </li>\
       <li class="nav-item">\
        <a class="nav-link" href="expense.html">Expense </a>\
    </li>\
     <li class="nav-item pull-xs-right">\
        <a id="logout" class="nav-link">Logout</a>\
    </li>\
    </ul>\
\
    </div>\
    </nav>\
    ');

    $result.find("#logout").click(function () {

        var posting = $.post("account/logout") ;

        posting.done(function() {
            window.location.href ="account/loginpage.html";
        });
    });

    return $result;
};