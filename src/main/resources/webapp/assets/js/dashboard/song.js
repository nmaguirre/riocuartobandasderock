$(document).ready(function() {
  if ($('#songId').length) {
    $('li').removeClass('active');
    $('#song').addClass('active');
  }
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

    $('#submit').on('click', function(event){
      event.preventDefault();
      var input_name = $('#name').val();
      var input_duration = $('#duration').val();
      $.ajax({
        url: "/songs/"+id,
        method: "put",
        data: {
          name: input_name,
          duration: input_duration }
      }).done(function() {
        window.location.replace("/songs/"+id);
      });
    })

    $('#dashboard-songs-datatable').on('click','.delete', function(event){
      event.preventDefault();
      var id = this.id
      $.ajax({
        url: "/songs/"+id,
        method: "delete"
      }).done(function() {
        $('#'+id).closest('tr').remove()
        alert = `<div class="alert alert-success alert-dismissible fade in" role="alert">
                  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                  </button>
                  <strong>La canción se eliminó con exito</strong>
                </div>`
        $('#alert').html(alert);
      });
    })
  }
  else{
    if ($('#noSearch').length){
      $('#search').remove()
    }
  }
});