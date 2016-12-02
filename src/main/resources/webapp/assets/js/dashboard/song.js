$(document).ready(function() {
  if($('#dashboard-songs-datatable').length){
    $('#dashboard-songs-datatable').DataTable({
      "bSort": false,
      "processing": true,
      "serverSide": true,
      "bFilter": false,
      "bLengthChange": true,
      "dom": '<"top"i>rt<"bottom"flp><"clear">',
      "bInfo": false,
      "pageLength": 10,
      'columns': [
        { 'data': 'name' },
        { 'data': 'duration' },
        { 'data': 'actions' }
      ],
      "ajax": {
        "data": function ( d ) {
          d.search = $('#dashboard-search').val()
        },
        "url": $('#dashboard-songs-datatable').data("url"),
        method: "POST"
      }
    });

    $('#dashboard-search').on('keyup change', function(){
      $('#dashboard-songs-datatable').DataTable().draw();
    });

    $('li').removeClass('active');
    $('#song').addClass('active');

    $('#dashboard-songs-datatable').on('click','.delete', function(event){
      event.preventDefault();
      var id = this.id
      $.ajax({
        url: "/songs/"+id,
        method: "delete"
      }).done(function() {
        window.location.replace("/songs");
      });
    })
  }
  else{
    if ($('#noSearch').length){
      $('#search').remove()
    }
  }
});