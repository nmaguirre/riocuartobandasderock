$(document).ready(function() {
  $('#mycarousel').on("click",'.artists-item', function(event){
    console.log("ARTISTS-ITEM")
    event.preventDefault();
    $.ajax({
      'url': '/landing/artist/show',
      'method': "GET",
      'data': {
        "id": this.id
      }
    }).done(function(data) {  
      console.log("Volvi de buscar banda")
      $('#artist-name').empty();
      $('#artist-nickname').empty();
      $('#artist-band').empty();

      $('#artist-name').text(data.name+" "+data.surname);
      $('#artist-nickname').text(data.nickname);

      for (var i = 0; i < data.bands.length; i++) {
        var band = "<div class='track'>"
       band += "<div class='track__number'>"+(i+1)+"</div>"
       band += "<div class='track__added'>"
       band += "<i class='ion-checkmark-round added'></i></div>"
       band += "<div class='track__title'>"+data.bands[i].name+"</div>"
       band += "<div class='track__explicit'>"+data.bands[i].genre+"</div>"
       band += "<div class='track__popularity'>"
       band += "<i class='ion-arrow-graph-up-right'></i>"
       band += "</div></div>"

        $('#artist-band').append(band);
      }

      $("#mycarousel").carousel(3);
    });

  })
});