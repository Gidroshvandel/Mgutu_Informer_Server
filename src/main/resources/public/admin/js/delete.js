function deleteGarbage(obj) {
    var raw = obj.id.substr(3,obj.id.length),
        rawArr = raw.split("|"),
        weekDay = rawArr[0],
        lessonTime = rawArr[1];
//        groupName = getCookie("nameCookie");
    console.log(raw);
    console.log(weekDay);
    console.log(lessonTime);
//    console.log(groupName);
    post(weekDay+"|"+lessonTime,"/admin/api/schedule.delete");
};