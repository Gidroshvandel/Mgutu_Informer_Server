(function () {
    document.getElementById("header_title").innerHTML = '';
    document.getElementById("header_title").appendChild(document.createTextNode("Группа: "+getCookie("nameCookie")));
}());