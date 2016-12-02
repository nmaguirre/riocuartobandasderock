$(document).ready(function() {
  if($('#mycarousel').length){
    $("#mycarousel").carousel(1);


    $('#mycarousel').on("click",'.slide-0', function(event){
      event.preventDefault();
      $("#mycarousel").carousel(0);
    })

    $('#mycarousel').on("click",'.slide-1', function(event){
      event.preventDefault();
      $("#mycarousel").carousel(1);
    })

    $('#mycarousel').on("click",'.slide-2', function(event){
      event.preventDefault();
      $("#mycarousel").carousel(2);
    })

    $('#mycarousel').on("click",'.slide-3', function(event){
      event.preventDefault();
      $("#mycarousel").carousel(3);
    })

    $('#mycarousel').on("click",'.slide-4', function(event){
      event.preventDefault();
      $("#mycarousel").carousel(4);
    })

    $('#mycarousel').on("click",'.slide-5', function(event){
      event.preventDefault();
      $("#mycarousel").carousel(5);
    })

    $('#mycarousel').on("click",'.link', function(event){
      event.preventDefault();
      var src = 'https://www.youtube.com/embed/JYgPk44tqa0?autoplay=1';
      $('#myModal').modal('show');
      $('#myModal iframe').attr('src', src);
      $('#myModal iframe').attr('autoplay', "1");
    });

    $('#myModal button').click(function () {
      $('#myModal iframe').removeAttr('src');
    });

  }
});