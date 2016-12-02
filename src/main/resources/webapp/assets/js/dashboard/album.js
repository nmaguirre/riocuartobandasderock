$(document).ready(function() {
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
  }
});