$(document).ready(function() {
  if($('#index-band-table').length){
   $('#index-band-table').DataTable({
      "bSort": false,
      // "drawCallback": pepepe(),
      "processing": true,
      "serverSide": true,
      "bFilter": false,
      "bLengthChange": true,
      "dom": '<"top"i>rt<"bottom"flp><"clear">',
      "bInfo": false,
      "pageLength": 4,
      'columns': [
        { 'data': 'name' },
        { 'data': 'genre' }
      ],
      "ajax": {
        "data": function ( d ) {
          d.search = $('#search-band').val()
        },
        "url": $('#index-band-table').data("url"),
        method: "POST"
      }
    });
   
    $('#search-band').on('keyup change', function(){
      $('#index-band-table').DataTable().draw();
    });
  }
  

  $('#mycarousel').on("click",'.bands-item', function(event){
    event.preventDefault();
    $.ajax({
      'url': '/landing/band/show',
      'method': "GET",
      'data': {
        "id": this.id
      }
    }).done(function(data) {  
      $('#band-name').empty();
      $('#band-genre').empty();
      $('#band-member').empty();
      $('#band-album').empty();

      $('#band-name').html(data.name);
      $('#band-genre').html(data.genre);
      for (var i = 0; i < data.members.length; i++) {
        artist = "<a href='#' class='related-artist'>"
        artist += "<span class='related-artist__img'>"
        artist += "<img src='https://s3-us-west-2.amazonaws.com/s.cdpn.io/7022/hoodie.jpg' alt='Hoodie Allen' />"
        artist += "</span>"
        artist += "<span class='related-artist__name'>"+data.members[i].name+"</span>"
        artist += "</a><br>"

        $('#band-member').append(artist);
        // $('#band-member').append(data.members[i].name+"<br>");
      }
      for (var i = 0; i < data.albums.length; i++) {
        var track = "<div class='track'>"
        track += "<div class='track__number'>"+(i+1)+"</div>"
        track += "<div class='track__added'>"
        track += "<i class='ion-checkmark-round added'></i></div>"
        track += "<div class='track__title'>"+data.albums[i].title+"</div>"
        track += "<div class='track__popularity'>"
        track += "<i class='ion-arrow-graph-up-right'></i>"
        track += "</div></div>"

        // $('#band-album').append(data.albums[i].title+"<br>");
        $('#band-album').append(track);
      }
      $("#mycarousel").carousel(2);
    });
  })


  // function pepepe(){
  //   console.log("entree puto");
  //   $('table tbody tr.dt-link td').on('click', function(e){
  //     console.log("EJECUTE");
  //     if(e.target.tagName != 'I' && e.target.tagName != 'A'){
  //       location.href = $(this).parent('tr').data('url');
  //     }
  //   });
  // }


    // $('table tbody tr.dt-link td').click(function(e){
    //   if(e.target.tagName != 'I' && e.target.tagName != 'A'){
    //     location.href = $(this).parent('tr').data('url');
    //   }
    // });
});