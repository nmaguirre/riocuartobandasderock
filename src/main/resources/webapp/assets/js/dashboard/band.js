$(document).ready(function() {
  if ($('#bandId').length) {
    $('li').removeClass('active');
    $('#band').addClass('active');
  }
  if($('#dashboard-bands-datatable').length){
    $('#dashboard-bands-datatable').DataTable({
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
        { 'data': 'genre' },
        { 'data': 'actions' }
      ],
      "ajax": {
        "data": function ( d ) {
          d.search = $('#dashboard-search').val()
        },
        "url": $('#dashboard-bands-datatable').data("url"),
        method: "POST"
      }
    });
   
    $('#dashboard-search').on('keyup change', function(){
      $('#dashboard-bands-datatable').DataTable().draw();
    });

    $('#submit').on('click', function(event){
      event.preventDefault();
      var input_name = $('#name').val();
      var input_genre = $('#genre').val();
      $.ajax({
        url: "/bands/"+id,
        method: "put",
        data: {
          name: input_name,
          genre: input_genre }
      }).done(function() {
        window.location.replace("/bands/"+id);
      });
    })


    $('#dashboard-bands-datatable').on('click','.delete', function(event){
      event.preventDefault();
      var id = this.id
      $.ajax({
        url: "/bands/"+id,
        method: "delete"
      }).done(function() {
        $('#'+id).closest('tr').remove()
        alert = `<div class="alert alert-success alert-dismissible fade in" role="alert">
                  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                  </button>
                  <strong>La banda se eliminó con exito</strong>
                </div>`
        $('#alert').html(alert);
      });
    })
  }
});