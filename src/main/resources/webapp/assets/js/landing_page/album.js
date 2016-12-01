$(document).ready(function() {
  $('#mycarousel').on("click",'.albums-item', function(event){
    event.preventDefault();
    $.ajax({
      'url': '/landing/album/show',
      'method': "GET",
      'data': {
        "id": this.id
      }
    }).done(function(data) {  
      $('#album-title').empty();
      $('#album-release').empty();
      $('#album-song').empty();

      $('#album-title').text(data.title);
      $('#album-release').text(data.release);

      for (var i = 0; i < data.songs.length; i++) {
        $('#album-song').append(data.songs[i].name+"<br>");
      }
      $("#mycarousel").carousel(4);
    });

  })
});