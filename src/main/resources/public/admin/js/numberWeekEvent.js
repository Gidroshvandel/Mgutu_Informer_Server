function onClick_ () {
    if(document.getElementById("NumderWeekDayId").value==1){
       setCookie("numberWeekDay",document.getElementById("NumderWeekDayId").value)
       }
    if(document.getElementById("NumderWeekDayId").value==2){
        setCookie("numberWeekDay",document.getElementById("NumderWeekDayId").value)
    }
    get(getCookie("nameCookie"),document.getElementById("NumderWeekDayId").value,"/admin/schedule");
}