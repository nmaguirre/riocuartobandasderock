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
        window.location.replace("/bands");
      });
    })
  }
  else{
    if ($('#noSearch').length){
      $('#search').remove()
    }
  }
});