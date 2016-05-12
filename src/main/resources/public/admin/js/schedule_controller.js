function setSchedule() {

    var groupName = getCookie("nameCookie"),
        weekDayName = document.getElementsByClassName("weekDayClassName"),
        lessonTimeName = document.getElementsByClassName("lessonTimeClass"),
        lectureHall = document.getElementsByClassName("lectureHallClass"),
        teacher = document.getElementsByClassName("teacherClass"),
        discipline = document.getElementsByClassName("disciplineClass"),
        employmentType = document.getElementsByClassName("employmentTypeClass"),
        numberWeekDay = getCookie("numberWeekDay"),
        
//        numberWeekDay = e.options[e.selectedIndex].value,
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
    console.log(numberWeekDay);
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
            week = new weekDay(weekDayName[i].textContent, numberWeekDay, lessArr);
            weekArr.push(week);
            }
        res = new respounse(groupName, weekArr);
        var leaderStr = JSON.stringify(res);
        console.log(leaderStr);
        post(leaderStr,"/admin/api/schedule.post");
//        get(groupName,numberWeekDay,"/admin/schedule");
};

function respounse(groupName, weekDay) {
    this.groupName = groupName;
    this.weekDay = weekDay;    
};
function weekDay(weekDayName, numberWeekDay, lessonTime) {
    this.weekDayName = weekDayName;
    this.numberWeekDay = numberWeekDay;
    this.lessonTime = lessonTime;   
    
};
function lessonTime(time, scheduleElements) {
    this.time = time;
    this.scheduleElements = scheduleElements;   
    
};
function ScheduleElements(lectureHall, teacher, discipline, employmentType) {
    this.lectureHall = lectureHall;
    this.teacher = teacher;
    this.discipline = discipline;
    this.employmentType = employmentType;
};

