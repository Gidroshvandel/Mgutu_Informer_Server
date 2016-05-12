function onClick_ (obj) {
    deleteCookie("visibility");
    if(obj.value==1){
       setCookie("numberWeekDay",obj.value)
       }
    if(obj.value==2){
        setCookie("numberWeekDay",obj.value)
    }
    get(getCookie("nameCookie"),obj.value,"/admin/schedule");
}