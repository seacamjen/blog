
$(function(){
  $("#showComments").onClick(function(event){
    event.preventDefault();
    $(".commentSection").slideToggle();
  });
})
