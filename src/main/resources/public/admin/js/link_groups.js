(function () {
    var groupsNameArr = document.getElementsByClassName("groupsNameClass");
    deleteCookie("numberWeekDay");
    deleteCookie("visibility");    
    console.log(groupsNameArr[0].textContent);
    for (var index = 0; index < groupsNameArr.length; ++index) {
        var container = groupsNameArr[index];
        console.log(container);  
        container.innerHTML = '<a class="mdl-navigation__link" href="#" onclick="setCookieAndLink('+"'"+groupsNameArr[index].textContent+"','/admin/schedule'"+');return false;">'+groupsNameArr[index].textContent+'</a>'
    }
}());