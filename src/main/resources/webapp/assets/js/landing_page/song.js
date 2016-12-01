$(document).ready(function() {
  $('#mycarousel').on("click",'.songs-item', function(event){
    event.preventDefault();
    $.ajax({
      'url': '/landing/song/show',
      'method': "GET",
      'data': {
        "id": this.id
      }
    }).done(function(data) {  
      $('#song-name').empty();
      $('#song-duration').empty();

      $('#song-name').text(data.name);
      $('#song-duration').text(data.duration);

      $("#mycarousel").carousel(5);
    });

  })
});