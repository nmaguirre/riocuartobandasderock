$(document).ready(function() {
  if($('#index-band').length){
   $('#index-band').DataTable({
      "bSort": false,
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
        'data': {
          "search": $('#search-band').val()
        },
        "url": $('#index-band').data("url"),
        method: "POST"
      }
    });
   
    $('#search-band').on('keyup change', function(){
      $('#index-band').DataTable().draw();
    });
  }

});