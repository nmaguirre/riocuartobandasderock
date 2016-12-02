$(document).ready(function() {
  if($('#dashboard-artists-datatable').length){
    $('#dashboard-artists-datatable').DataTable({
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
        { 'data': 'surname' },
        { 'data': 'nickname' },
        { 'data': 'actions' }
      ],
      "ajax": {
        "data": function ( d ) {
          d.search = $('#dashboard-search').val()
        },
        "url": $('#dashboard-artists-datatable').data("url"),
        method: "POST"
      }
    });

    $('#dashboard-search').on('keyup change', function(){
      $('#dashboard-artists-datatable').DataTable().draw();
    });

    $('li').removeClass('active');
    $('#artist').addClass('active');

    $('#dashboard-artists-datatable').on('click','.delete', function(event){
      event.preventDefault();
      var id = this.id
      $.ajax({
        url: "/artists/"+id,
        method: "delete"
      }).done(function() {
        window.location.replace("/artists");
      });
    })
  }
  else{
    if ($('#noSearch').length){
      $('#search').remove()
    }
  }
});