(function () {
    var container = document.getElementById('s1');
    console.log(container);
    container.innerHTML += '<input onclick="" type="submit" value="123">';
    console.log(container);
}());