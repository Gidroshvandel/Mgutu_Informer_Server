(function () {
    var groupsNameArr = document.getElementsByClassName("weekdayClass");
    for(var i=0; i< groupsNameArr.length;++i){
        groupsNameArr[i].style.visibility = "hidden";
        groupsNameArr[i].style.display = "none";
        }
    console.log(getCookie("visibility"));
    if(getCookie("visibility") == undefined){
//    console.log();
    console.log(groupsNameArr);
    groupsNameArr[0].style.visibility = "visible";
    groupsNameArr[0].style.display = "inline";
//    groupsNameArr[0]
    }else{
        for(var i=0; i< groupsNameArr.length;++i){
            if(groupsNameArr[i].getElementsByClassName("weekDayClassName")[0].textContent===getCookie("visibility")){
                groupsNameArr[i].style.visibility = "visible";
                groupsNameArr[i].style.display = "inline";
            }
        }
    }   
}());

