(function () {
//    console.log(getCookie("name"));
//    document.getElementById("groupName").appendChild(document.createTextNode(getCookie("name")));
}());

function setSchedule() {

    var groupName = document.getElementById("groupName").textContent,
        weekDayName = document.getElementsByClassName("weekDayClassName"),
        lessonTimeName = document.getElementsByClassName("lessonTimeClass"),
         lectureHall = document.getElementsByClassName("lectureHallClass"),
         teacher = document.getElementsByClassName("teacherClass"),
         discipline = document.getElementsByClassName("disciplineClass"),
         employmentType = document.getElementsByClassName("employmentTypeClass"),
         weekArr = [],
         a = lessonTimeName.length /weekDayName.length,
        b = a,
         j = 0,
         i,
            lessArr,
            inp,
            less,
            week,
            res;
//        console.log(discipline[0].value);
            for (i = 0; i < weekDayName.length; ++i) {
                            lessArr = [];
                            for(j; j < a; ++j){
                                               inp = new ScheduleElements(lectureHall[j].value,teacher[j].value, discipline[j].value, employmentType[j].value);
                                               console.log(inp);
                                                less = new lessonTime(lessonTimeName[j].textContent, inp);
                                                lessArr.push(less);
                                                }
                            a += b;
                            week = new weekDay(weekDayName[i].textContent, lessArr);
                            weekArr.push(week);
                                                    }
            res = new respounse(groupName, weekArr);
            var leaderStr = JSON.stringify(res);
            console.log(leaderStr);
            post(leaderStr,"/api/schedule.put");
//  var in1 = new input("101","Nikitin","Math");
//    var in2 = new input("1012","Nikitin2","Math2");
//    var les = new lessonTime("12",[in1.input,in2.input])
//    var les2 = new lessonTime("12",[in1.input,in2.input])
//    var week = new weekDay("Mondey", [les,les2]);
//    var week2 = new weekDay("Вторник", [les,les2]);
//     var res = new respounse("12is1",[week, week2]);
//    
//    var leaderStr = JSON.stringify(res);
//    console.log(leaderStr);
};

function post(msg,url) {
//    var url = el.getAttribute('href');
//    var msg = prompt('Сообщение');
    
   var form = document.createElement("form");
    form.setAttribute("method", 'post');
    form.setAttribute("action", url);
    setCookie("name",msg);
    var input = document.createElement("input");
    input.setAttribute("type", "hidden");
    input.setAttribute("name", 'name');
    input.setAttribute("value", msg);

    form.appendChild(input);
    form.submit();
};

function respounse(groupName, weekDay) {
    this.groupName = groupName;
    this.weekDay = weekDay;
    
//    var respounse = 
//        {
//            "groupName": groupName,
//            "weekDay": weekDay
//        };    
    
};
function weekDay(weekDayName, lessonTime) {
    this.weekDayName = weekDayName;
    this.lessonTime = lessonTime;
    
//    var weekDay = 
//        {
//            "nameWeekDay": nameWeekDay,
//            "lessonTime": lessonTime
//        };    
    
};
function lessonTime(time, scheduleElements) {
    this.time = time;
    this.scheduleElements = scheduleElements;
    
//    this.lessonTime = 
//        {
//            "time": time,
//            "input": input
//        };    
    
};
function ScheduleElements(lectureHall, teacher, discipline, employmentType) {
    this.lectureHall = lectureHall;
    this.teacher = teacher;
    this.discipline = discipline;
    this.employmentType = employmentType;
//    this.ScheduleElements =
//        {
//            "lectureHall":lectureHall,
//            "teacher":teacher,
//            "discipline":discipline,
//            "employmentType":employmentType
//        };
};
//
//var respounse = 
//        {
//            "groupName": "",
//            "weekDay":
//            [
//            ],
//        };
//
//var lessonTime =  {
//    "time":"",
//    "input":
//        {
//            "lectureHall":"","teacher":"","discipline":""
//        }
//};   
//    
//var weekDay = {
//    "nameWeekDay":"",
//    "lessonTime":
//        [
//
//        ],
//};