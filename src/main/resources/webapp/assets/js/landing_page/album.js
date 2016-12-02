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
        var track = "<div class='track'>"
        track += "<div class='track__number'>"+(i+1)+"</div>"
        track += "<div class='track__added'>"
        track += "<i class='ion-checkmark-round added'></i></div>"
        track += "<div class='track__title'>"+data.songs[i].name+"</div>"
        track += "<div class='track__explicit'>"+data.songs[i].duration+"</div>"
        track += "<div class='track__popularity'>"
        track += "<i class='ion-arrow-graph-up-right'></i>"
        track += "</div></div>"

        $('#album-song').append(track);
      }
      $("#mycarousel").carousel(4);
    });

  })
});