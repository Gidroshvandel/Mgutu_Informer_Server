(function () {
    if(getCookie("numberWeekDay") != null){
         document.getElementById("NumderWeekDayId").innerHTML = '';
        if(getCookie("numberWeekDay")==1){
            document.getElementById("NumderWeekDayId").appendChild(document.createTextNode("Первая неделя"));
        }
        else{
            document.getElementById("NumderWeekDayId").appendChild(document.createTextNode("Вторая неделя"));
        }         
    }
    var 
//        groupName = getCookie("nameCookie"),
//        numberWeekDay = document.getElementById("NumderWeekDayId").value,
        weekDayName = document.getElementsByClassName("weekDayClassName"),
        lessonTimeName = document.getElementsByClassName("lessonTimeClass"),
        lectureHall = document.getElementsByClassName("lectureHallClass"),
        teacher = document.getElementsByClassName("teacherClass"),
        discipline = document.getElementsByClassName("disciplineClass"),
        employmentType = document.getElementsByClassName("employmentTypeClass"),
        a = lessonTimeName.length /weekDayName.length,
        b = a,
        j = 0,
        i,
        tabeleRow = document.getElementsByClassName("rowModelClass"),
        arr = [];
    
    for(var ii=0;ii<tabeleRow.length;++ii)
        {
//            for(var jj=0;jj<tabeleRow[ii].getElementsByTagName("td").length;++jj)
//            {
            arr.push(new model(tabeleRow[ii].getElementsByTagName("td")[0].textContent,
                          tabeleRow[ii].getElementsByTagName("td")[1].textContent,
                          tabeleRow[ii].getElementsByTagName("td")[2].textContent,
                          tabeleRow[ii].getElementsByTagName("td")[3].textContent,
                          tabeleRow[ii].getElementsByTagName("td")[4].textContent,
                          tabeleRow[ii].getElementsByTagName("td")[5].textContent));
//            }
        }
    console.log(arr);
        for (i = 0; i < weekDayName.length; ++i) {
            lessArr = [];
            for(j; j < a; ++j){
                for(var ii=0;ii<arr.length;++ii)
                    {
                        if(weekDayName[i].textContent === arr[ii].weekDay)
                        {
                            if(lessonTimeName[j].textContent === arr[ii].lessonTime){
                                lectureHall[j].value = arr[ii].lectureHall;
                                teacher[j].value = arr[ii].teacher;
                                discipline[j].value = arr[ii].discipline;
                                employmentType[j].value = arr[ii].employmentType;
                            }
                        }
                    }
                
            }
                
                                                               
            a += b;
        }
}());
                
function model(weekDay, lessonTime, lectureHall, teacher, discipline, employmentType) {
    this.weekDay = weekDay;
    this.lessonTime = lessonTime;
    this.lectureHall = lectureHall;
    this.teacher = teacher;
    this.discipline = discipline;
    this.employmentType = employmentType;
};