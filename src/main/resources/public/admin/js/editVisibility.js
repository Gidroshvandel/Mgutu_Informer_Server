function editVisibility(obj) {
    var groupsNameArr = document.getElementsByClassName("weekdayClass");
    console.log(groupsNameArr[0].getElementsByClassName("weekDayClassName")[0].textContent);
    console.log(getCookie("visibility"));
    if(getCookie("visibility") == undefined){
        groupsNameArr[0].style.visibility = "hidden";
        groupsNameArr[0].style.display = "none";
    }else{
        for(var i=0; i< groupsNameArr.length;++i){
           if(groupsNameArr[i].getElementsByClassName("weekDayClassName")[0].textContent===getCookie("visibility")){
            groupsNameArr[i].style.visibility = "hidden";
            groupsNameArr[i].style.display = "none";
           } 
        }
    }  
    for(var i=0; i< groupsNameArr.length;++i){
        if(groupsNameArr[i].getElementsByClassName("weekDayClassName")[0].textContent===obj.textContent){
            groupsNameArr[i].style.visibility = "visible";
            groupsNameArr[i].style.display = "inline";
            setCookie("visibility",obj.textContent);
        }
    }
//    console.log(raw);
    
//    console.log(groupName);
};