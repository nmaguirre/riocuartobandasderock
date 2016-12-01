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

      $('#band-name').text(data.name);
      $('#band-genre').text(data.genre);
      for (var i = 0; i < data.members.length; i++) {
        $('#band-member').append(data.members[i].name+"<br>");
      }
      for (var i = 0; i < data.albums.length; i++) {
        $('#band-album').append(data.albums[i].title+"<br>");
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