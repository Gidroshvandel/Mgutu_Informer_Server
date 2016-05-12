(function () {
    if(getCookie("visibility") == undefined){
    var groupsNameArr = document.getElementsByClassName("weekdayClass");
//    console.log();
    console.log(groupsNameArr);
    groupsNameArr[0].style.visibility = "visible";
    groupsNameArr[0].style.display = "inline";
//    groupsNameArr[0]
    }
    
}());

