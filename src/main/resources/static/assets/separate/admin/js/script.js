
$(document).ready(function () {
    $(".data-table").each(function (_, table) {
        $(table).DataTable();
    });
});

$(document).ready(function () {
    document.getElementById("clickOnReady").click();
    document.querySelector("#example1_length > label > select > option:nth-child(4)").value = 15;
    document.querySelector("#example1_length > label > select > option:nth-child(4)").textContent = '15';
});
