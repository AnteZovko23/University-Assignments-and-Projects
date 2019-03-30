// Check off Specific todos by clicking

$("ul").on("click", "li", function(){

    $(this).toggleClass("completed");
   
    
});

//Click on X to delete Todo

$("ul").on("click", "span", function(e){
    
    $(this).parent().fadeOut(500, function(){
        $(this).remove();
    });


    e.stopPropagation();
});

// Input enter
$("input[type='text']").keypress(function(e){
    if(e.which === 13){

        var todoText = $(this).val();
        $(this).val("");
        $("ul").append("<li><span><i class='fa fa-trash-alt'></i></span> " + todoText + "</li>");

    }
});

// Toggle icon
$(".fa-edit").click(function(){
    $("input[type='text'").fadeToggle(150, function(){

    });
});