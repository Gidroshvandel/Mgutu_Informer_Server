function setCookieAndLink(msg,url) {
   var form = document.createElement("form");
    form.setAttribute("method", 'get');
    setCookie("nameCookie",msg);
    console.log(url);
    form.setAttribute("action", url);
    var input = document.createElement("input");
    input.setAttribute("type", "hidden");
    input.setAttribute("name", 'name');
    input.setAttribute("value", msg);

    form.appendChild(input);
    form.submit();
};