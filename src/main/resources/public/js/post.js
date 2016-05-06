function post(msg,url) {
    
   var form = document.createElement("form");
    form.setAttribute("method", 'post');
    form.setAttribute("action", url);
//    setCookie("name",msg);
    var input = document.createElement("input");
    input.setAttribute("type", "hidden");
    input.setAttribute("name", 'name');
    input.setAttribute("value", msg);

    form.appendChild(input);
    form.submit();
};