$("button-remove").click(function(e) {
    e.preventDefault();
    $.ajax({
        type: "DELETE",
        url: "/books/",
        data: {
            id: $(this).val(),
            access_token: $("#access_token").val()
        },
        success: function(result) {
            alert('ok');
        },
        error: function(result) {
            alert('error');
        }
    });
});

$("button-put").click(function(e) {
    e.preventDefault();
    $.ajax({
        type: "PUT",
        url: "/books/",
        data: {
            id: $(this).val(),
            access_token: $("#access_token").val()
        },
        success: function(result) {
            alert('ok');
        },
        error: function(result) {
            alert('error');
        }
    });
});