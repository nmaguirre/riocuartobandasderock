$(document).ready(function() {
  $('#mycarousel').on("click",'.artists-item', function(event){
    event.preventDefault();
    $.ajax({
      'url': '/landing/artist/show',
      'method': "GET",
      'data': {
        "id": this.id
      }
    }).done(function(data) {  
      $('#artist-name').empty();
      $('#artist-surname').empty();
      $('#artist-nickname').empty();
      $('#artist-name').text(data.name);
      $('#artist-surname').text(data.surname);
      $('#artist-nickname').text(data.nickname);
      $("#mycarousel").carousel(3);
    });

  })
});