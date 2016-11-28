<!DOCTYPE html>
<html lang="en">
<head>
<title>Mostrar Banda</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

<style type="text/css">
    table tr th, table tr td{font-size: 1.2rem;}
    .row{ margin:20px 20px 20px 20px;width: 100%;}
    .glyphicon{font-size: 25px;}
    .glyphicon-plus{float: right;}
    a.glyphicon{text-decoration: none;}
    a.glyphicon-trash{margin-left: 10px;}
    .none{display: none;}
    .table td{
        text-align: center;   
    }
    .table th{
        text-align: center;   
    }
    .table-row{
        cursor:pointer;
    }
    /*.glyphicon-trash{
        pointer-events: none;
        cursor: default;
        color: gray;
    }
    .glyphicon-edit{
        pointer-events: none;
        cursor: default;
        color: gray;
    }*/

</style>

<script>
    
    var findResourceTag = '/bands/findbyname/'; // Default value

    function showBands(resourcelocation){
        $("#userData").html('<table></table>'); // Clean table, need to refresh with non elements in db

        resourcelocation='/bands';
         $.get(resourcelocation, function(data, status){
            if (typeof data === "undefined") {
                response = '<tr><td colspan="5"> No Bands found...</td></tr>'
            }else{
                var request = JSON.parse(data);
                response = '<table>';
                itemindex = 1;
                $.each(request, function(i, item) {
                       response += '<tr onclick="showArtistMInDialog(\''+item.bandID+'\')">'+'<td>'+itemindex+'</td>'+'<td>'+item.name+'</td>'+'<td>'+item.genre+'</td>'+'<td> <a href="javascript:void(0);" class="glyphicon glyphicon-edit" onclick="showInEdit(\''+item.bandID+'\'); event.stopImmediatePropagation();"</a> <a href="javascript:void(0);" class="glyphicon glyphicon-trash" onclick="deleteBand(\''+item.bandID+'\'); event.stopImmediatePropagation();"</a> </td> </tr>';
                        ++itemindex;
                });           
                response += '</table>';
            }
            
            $("#userData").html(response);
 
          });
    }

    function showArtistMInDialog(id){
        $("#artistMModal").modal() 
        $("#artistMData").html('<table></table>'); // Clean table, need to refresh with non elements in db

        resourcelocation='/bands'+id;
         $.get(resourcelocation, function(data, status){
            if (typeof data === "undefined") {
                response = '<tr><td colspan="5"> No Artist found...</td></tr>'
            }else{
                var request = JSON.parse(data);
                response = '<table>';
                itemindex = 1;
                $.each(request, function(i, item) {
                       response += '<tr>'+'<td>'+itemindex+'</td>'+'<td>'+item.name+'</td>'+'<td>'+item.genre+'</td> </tr>';
                        ++itemindex;
                });           
                response += '</table>';    
            }

            $("#artistMData").html(response);
 
          });
    }

    function showInEdit(id){
            
        var resourcelocation ='/bands';
        $.ajax({
            type: 'GET',
            url: resourcelocation,
            data: '',
            success:function(res){
                var request = JSON.parse(res);
            
                $.each(request, function(i, item) {
                    $('#nameEdit').val(item.name);
                    $('#genreEdit').val(item.genre);
                    $('#idEdit').val(item.bandID);
                });
                    
                $('#editForm').slideDown();
            }
        });
    }

    function addBand() {
    	var url = '/bands/'; 
        name=$('#name').val();
        genre=$('#genre').val();
            
        console.log(name);
        console.log(genre);    
            
        if (name+genre=='') {
            alert("Al menos debe rellenar un campo");
        }
        else {
            $.ajax({
                type: "POST",
                url: url,
                data: $("#addForm").find('.form').serialize(), // Adjuntar los campos del formulario enviado.
                success: function(data)
                {
                    showBands('/bands'); //optional refresh list
                    $('.form')[0].reset();
                    $('.formData').slideUp();
                 },
                 error: function(x, e) {
                 	alert("error "+x+" "+e);
                 }
            });
        }
    }

    function updateBand() {            
            
        id=$('#idEdit').val();
        var resourcelocation ='/bands';

        name=$('#nameEdit').val();
        genre=$('#genreEdit').val();
            
        if (name+genre=='') {
            alert("Al menos debe rellenar un campo");
        } else {
            $.ajax({
                type: "PUT",
                url: resourcelocation,
                data: $("#editForm").find('.form').serialize(), // Adjuntar los campos del formulario enviado.
                success: function(data)
                {
                   showBands('/bands'); //optional refresh list
                   $('.form')[1].reset();
                   $('.formData').slideUp();
                },
                error: function(x, e) {
                    //$("#dataartisaddformstatus").html('<font color="red">Result: Error</font>'); //  Show response Api Rest
                    alert('No ha realizado ninguna modificación en el registro');
                    $('.form')[1].reset();
                    $('.formData').slideUp();
                }
            });
        }
    }

    function deleteBand(name) {            
            
        resourcelocation = '/bands/'+name; 
            
        $.ajax({
            type: "DELETE",
            url: resourcelocation,
            data: '', 
            success: function(data)
            {
                showBands('/bands'); //optional refresh list
            },
            error: function(x, e) {
                     
            }
        });

    }

   function showSearchMenu(by) {            
        $('.form')[2].reset();
        $('#searchForm').slideToggle();        
        switch(by){
            case "name":
                document.getElementById("nameSearch").disabled = false;
                document.getElementById("genreSearch").disabled = true;
                findResourceTag = '/bands/findbyname/';
                break;
            case "genre":
                document.getElementById("nameSearch").disabled = true;
                document.getElementById("genreSearch").disabled = false;
                findResourceTag = '/bands/findbygenre/';
                break;
            case "all":
                document.getElementById("nameSearch").disabled = false;
                document.getElementById("genreSearch").disabled = false;
                findResourceTag = '/bands/find/';
        }
        
   }

   function executeSearch() {    
        switch(findResourceTag){
            case "/bands/findbyname/": 
                var resourcelocation = findResourceTag+$('#nameSearch').val();
                showBands(resourcelocation);
                break;
            case "/bands/findbygenre/":
                var resourcelocation = findResourceTag+$('#genreSearch').val();
                showBands(resourcelocation);
                break;
            case "/bands/find/":
                $.ajax({
                    type: "GET",
                    url: findResourceTag,
                    data: $("#searchForm").find('.form').serialize(), // Adjuntar los campos del formulario enviado.
                    success: function(data)
                    {
                        if (typeof data === "undefined") {
                            response = '<tr><td colspan="5"> No Bands found...</td></tr>'
                        }else{
                            var request = JSON.parse(data);
                            response = '<table>';
                            itemindex = 1;
                            $.each(request, function(i, item) {
                                   response += '<tr onclick="showArtistMInDialog(\''+item.bandID+'\')">'+'<td>'+itemindex+'</td>'+'<td>'+item.name+'</td>'+'<td>'+item.genre+'</td>'+'<td>'+'<td> <a href="javascript:void(0);" class="glyphicon glyphicon-edit" onclick="showInEdit(\''+item.bandID+'\'); event.stopImmediatePropagation();"</a> <a href="javascript:void(0);" class="glyphicon glyphicon-trash" onclick="deleteBand(\''+item.bandID+'\'); event.stopImmediatePropagation();"</a> </td> </tr>';
                                    ++itemindex;
                            });           
                            response += '</table>';
                        }
                        
                        $("#userData").html(response);
                     },
                     error: function(x, e) {
                        alert("Se ha producido un error");
                     }
                });
        }

        $('.form')[2].reset();
        $('#searchForm').slideToggle(); 
   }

    /**
     * Vertically center Bootstrap 3 modals so they aren't always stuck at the top
     */

    $(function() {
        function reposition() {
            var modal = $(this),
                dialog = modal.find('.modal-dialog');
            modal.css('display', 'block');
            
            // Dividing by two centers the modal exactly, but dividing by three 
            // or four works better for larger screens.
            dialog.css("margin-top", Math.max(0, ($(window).height() - dialog.height()) / 2));
        }
        // Reposition when a modal is shown
        $('.modal').on('show.bs.modal', reposition);
        // Reposition when the window is resized
        $(window).on('resize', function() {
            $('.modal:visible').each(reposition);
        });
    });

    $(document).ready(function() {
        showBands('/bands');
    });
        
</script>
</head>

<body>

<nav class="navbar navbar-inverse">
      <div class="container-fluid">
        <div class="navbar-header">
          <a class="navbar-brand" href="#">Artist TEAM</a>
        </div>
        <ul class="nav navbar-nav">
          <li><a href="http://localhost:4567/artistadmin/index.php">Home</a></li>
          <li><a href="http://localhost:4567/artistadmin/artist.php">Artist</a></li>
          <li><a href="http://localhost:4567/artistadmin/album.php">Album</a></li>
          <li class="active"><a href="http://localhost:4567/artistadmin/band.php">Band</a></li>
          <li><a href="http://localhost:4567/artistadmin/song.php">Song</a></li>
        </ul>
      </div>
    </nav>

<div class="container">
    <div align="center">
        <h2>
            <i class="glyphicon glyphicon-th-list"></i> Admin - Bands
            <span id=loader class="loader progress progress-striped active"><span class="bar"></span></span>
        </h2>
    </div>    
    <div class="row">
        <div class="panel panel-default users-content">
            <div class="panel-heading"><i class="glyphicon glyphicon-th-list"></i>  Band list
	<div class="btn-group pull-right" role="group">
    <button type="button" class="btn btn-default" href="javascript:void(0);" onclick="javascript:showBands('/bands')">Show all</button>
	<button type="button" class="btn btn-default" href="javascript:void(0);" onclick="javascript:$('#addForm').slideToggle(); $('.form')[0].reset();">Add</button>
	  <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	    Search <span class="caret"></span>
	  </button>
	  <ul class="dropdown-menu">
	    <li><a href="javascript:void(0);" onclick="javascript:$(showSearchMenu('name'));">By name</a></li>
	    <li><a href="javascript:void(0);" onclick="javascript:$(showSearchMenu('genre'));">By genre</a></li>
	    <li role="separator" class="divider"></li>
	    <li><a href="javascript:void(0);" onclick="javascript:$(showSearchMenu('all'));">By all fields</a></li>
	  </ul>

	</div>

	    </div>
            <div class="panel-body none formData" id="addForm">
                <h2 id="actionLabel">Add Band</h2>
                <form class="form" id="userForm">
                    <div class="form-group">
                        <label>Name</label>
                        <input type="text" class="form-control" name="name" id="name"/>
                    </div>
                    <div class="form-group">
                        <label>Genre</label>
                        <input type="text" class="form-control" name="genre" id="genre"/>
                    </div>
                    <a href="javascript:void(0);" class="btn btn-warning" onclick="$('#addForm').slideUp();">Cancel</a>
                    <a href="javascript:void(0);" class="btn btn-success" onclick="addBand()">Add Band</a>
                </form>
            </div>

            <div class="panel-body none formData" id="editForm">
                <h2 id="actionLabel">Edit Band</h2>
                <form class="form" id="userForm">
                    <div class="form-group">
                        <label>Name</label>
                        <input type="text" class="form-control" name="name" id="nameEdit"/>
                    </div>
                    <div class="form-group">
                        <label>Genre</label>
                        <input type="text" class="form-control" name="genre" id="genreEdit"/>
                    </div>
                    <input type="hidden" class="form-control" name="id" id="idEdit"/>
                    <a href="javascript:void(0);" class="btn btn-warning" onclick="$('#editForm').slideUp(); ; $('.form')[1].reset();">Cancel</a>
                    <a href="javascript:void(0);" class="btn btn-success" onclick="updateBand() ; $('.form')[1].reset();">Update Band</a>
                </form>
            </div>

	    <div class="panel-body none formData" id="searchForm">
                <h2 id="actionLabel">Search Band</h2>
                <form class="form" id="userForm">
                    <div class="form-group">
                        <label>Name</label>
                        <input type="text" class="form-control" name="name" id="nameSearch"/>
                    </div>
                    <div class="form-group">
                        <label>Genre</label>
                        <input type="text" class="form-control" name="genre" id="genreSearch"/>
                    </div>
                    <input type="hidden" class="form-control" name="id" id="idSearch"/>
                    <a href="javascript:void(0);" class="btn btn-warning" onclick="$('#searchForm').slideUp();">Cancel</a>
                    <a href="javascript:void(0);" class="btn btn-success" onclick="executeSearch()">Search Band</a>
                </form>
            </div>
            <table class="table collection table table-bordered table-striped table-hover" id="mainTable">
                <thead>
                    <tr>
                        <th></th>
                        <th>Name</th>
                        <th>Genre</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody id="userData" class="table-row"></tbody>
            </table>

        </div>
    </div>
</div>

<!-- Modal -->
<div id="bandMModal" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Band Member</h4>
      </div>
      <div class="modal-body">
        <table class="table collection table table-bordered table-striped table-hover" id="bandMTable">
                <thead>
                    <tr>
                        <th></th>
                        <th>Name</th>
                        <th>Genre</th>
                    </tr>
                </thead>
                <tbody id="bandMData"></tbody>
            </table>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>

  </div>
</div>

</body>
</html>