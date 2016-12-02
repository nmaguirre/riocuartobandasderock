$(document).ready(function() {
  if ($('#albumId').length) {
    $('li').removeClass('active');
    $('#album').addClass('active');
  }
  if($('#dashboard-albums-datatable').length){
   $('#dashboard-albums-datatable').DataTable({
      "bSort": false,
      "processing": true,
      "serverSide": true,
      "bFilter": false,
      "bLengthChange": true,
      "dom": '<"top"i>rt<"bottom"flp><"clear">',
      "bInfo": false,
      "pageLength": 10,
      'columns': [
        { 'data': 'title' },
        { 'data': 'release' },
        { 'data': 'actions' }
      ],
      "ajax": {
        "data": function ( d ) {
          d.search = $('#dashboard-search').val()
        },
        "url": $('#dashboard-albums-datatable').data("url"),
        method: "POST"
      }
    });

    $('#dashboard-search').on('keyup change', function(){
      $('#dashboard-albums-datatable').DataTable().draw();
    });

    $('#submit').on('click', function(event){
      event.preventDefault();
      var input_title = $('#title').val();
      var input_release_date = $('#release_date').val();
      var input_band_id = $('#band_id').val();
      $.ajax({
        url: "/albums/"+id,
        method: "put",
        data: {
          title: input_title,
          release_date: input_release_date,
          band_id: input_band_id
        }
      }).done(function() {
        window.location.replace("/albums/"+id);
      });
    })

    $('#dashboard-albums-datatable').on('click','.delete', function(event){
      event.preventDefault();
      var id = this.id
      $.ajax({
        url: "/albums/"+id,
        method: "delete"
      }).done(function() {
        $('#'+id).closest('tr').remove()
        alert = `<div class="alert alert-success alert-dismissible fade in" role="alert">
                  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                  </button>
                  <strong>El album se eliminó con exito</strong>
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