function post(msg,url) {
    
   var form = document.createElement("form");
    form.setAttribute("method", 'post');
    form.setAttribute("action", url);
//    setCookie("name",msg);
    var input = document.createElement("input");
    input.setAttribute("type", "hidden");
    input.setAttribute("name", 'name');
    input.setAttribute("value", msg);
    
    console.log(url);

    form.appendChild(input);
    form.submit();
};

//function get(msg,url) {
//    
//   var form = document.createElement("form");
//    form.setAttribute("method", 'get');
//    form.setAttribute("action", url);
////    setCookie("name",msg);
//    var input = document.createElement("input");
//    input.setAttribute("type", "hidden");
//    input.setAttribute("name", 'name');
//    input.setAttribute("value", msg);
//
//    form.appendChild(input);
//    form.submit();
//};

function get(msg,msg2,url) {
    
   var form = document.createElement("form");
    form.setAttribute("method", 'get');
    form.setAttribute("action", url);
//    setCookie("name",msg);
    var input = document.createElement("input");
    input.setAttribute("type", "hidden");
    input.setAttribute("name", 'name');
    input.setAttribute("value", msg);
    
    var input2 = document.createElement("input");
    input2.setAttribute("type", "hidden");
    input2.setAttribute("name", 'numberWeekDay');
    input2.setAttribute("value", msg2);

    form.appendChild(input);
    form.appendChild(input2);
    form.submit();
};