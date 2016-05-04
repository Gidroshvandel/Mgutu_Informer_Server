(function () {
    var tbody = document.getElementById('myTable').getElementsByTagName("tbody")[0].getElementsByTagName("tr");
    
    console.log(tbody[0].getElementsByTagName("td")[0].textContent);
    for (var index = 0; index < tbody.length-1; ++index) {
        var container = tbody[index].getElementsByTagName("td")[0];
        console.log(container);  
        container.innerHTML = '<a href="javascript://" onclick="post('+"'"+tbody[index].getElementsByTagName("td")[0].textContent+"','/schedule'"+');return false;">'+tbody[index].getElementsByTagName("td")[0].textContent+'</a>'
        container.innerHTML += '<input onclick="post('+"'"+tbody[index].getElementsByTagName("td")[0].textContent+"','/api/groups.delete'"+')" type="submit" value="-">';
    }
    console.log(container);
//    var tabelE = document.getElementById("tabel");
//    var row = document.createElement("tr");
//    var elemTd = document.createElement("td");
//    var form = document.querySelector("form");
//    var content = document.createTextNode("text")
//        elemTd.appendChild(content);
//        row.appendChild(elemTd);
    
       // tbody.appendChild(row);
}());
//<script type="text/javascript">
//function addRow(id){
//var tbody = document.getElementById(id).getElementsByTagName("TBODY")[0];
//var row = document.createElement("TR")
//var td1 = document.createElement("TD")
//td1.appendChild(document.createTextNode("column 1"))
//var td2 = document.createElement("TD")
//td2.appendChild (document.createTextNode("column 2"))
//row.appendChild(td1);
//row.appendChild(td2);
//tbody.appendChild(row);
//}
//</script>
//
//<p>Скрипт добавляет строку в таблицу, после нажатия на ссылку.</p>
//    <tr>
//        <td><form action="/deleteGroups" accept-charset="UTF-8" method="POST">
//            <textarea name="groupsName">a[index]</textarea>
//            <input type="submit" value="-">
//        </form>
//        </td>
//    </tr>