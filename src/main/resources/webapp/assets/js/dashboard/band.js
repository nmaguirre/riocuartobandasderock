$(document).ready(function() {
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

    $('li').removeClass('active');
    $('#band').addClass('active');

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