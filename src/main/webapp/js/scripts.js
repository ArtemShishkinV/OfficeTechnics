function changeBlock(workerId) {
    $.post("changeBlock", {id: workerId}, function () {
        console.log("changed!");
    }).fail(function () {
        console.log("error");
    });

    updateBlock("#users");
}

function refuseTender(deviceId) {
    $.post("refuseTender", {id: deviceId}, function () {
        console.log("changed!");
    }).fail(function () {
        console.log("error");
    });

    updateBlock("#devices")
}

function deleteUser(workerId) {
    $.post("deleteUser", {id: workerId}, function () {
        console.log("deleted!");
    }).fail(function () {
        console.log("error");
    });

    updateBlock("#users");
}

function deleteDevice(deviceId) {
    $.post("deleteDevice", {id: deviceId}, function () {
        console.log("device has been deleted!");
    }).fail(function () {
        console.log("error!");
    });

    updateBlock("#devices");
}

function createDevice() {
    let title = document.getElementById("deviceTitle").value;
    $.post("createDevice", {title: title}, function () {
        console.log("the device is created!");
    }).fail(function () {
        console.log("error!");
    });

    updateBlock("#devices");
}

function handleProblem(problemId, deviceId) {
    var radio = $("input[type=radio][name=radio-" + deviceId + "]").filter(":checked")[0];

    var type = radio.value;

    console.log(deviceId);
    console.log(type);

    if (radio) {
        $.post("handleProblem", {problemId: problemId, type: type}, function () {
            console.log("success");
        }).fail(function () {
            console.log("error!");
        });

        updateBlock("#tenders");
    }
}

function deleteProblem(problemId) {

    $.post("deleteProblem", {problemId: problemId}, function () {
        console.log("success");
    }).fail(function () {
        console.log("error!");
    });

    updateBlock("#tenders");
}

function handleTender(tenderId, deviceId) {
    var radio = $("input[type=radio][name=radio-" + deviceId + "]").filter(":checked")[0];

    if (radio) {
        $.post("handleTender", {tenderId: tenderId, type: radio.value}, function () {
            console.log("success");
        }).fail(function () {
            console.log("error!");
        })

        updateBlock("#tenders");
    }
}

function deleteTender(tenderId) {
    $.post("deleteTender", {tenderId: tenderId}, function () {
        console.log("success");
    }).fail(function () {
        console.log("error!");
    });

    updateBlock("#tenders");
}

function updateBlock(block) {
    // $(document).ready(function () {
    //
    //     $.ajaxSetup({
    //         cache: false
    //     });
    //
    //     $(block).load(window.location.href + " " + block, "");
    // });
    window.location.reload();
}
