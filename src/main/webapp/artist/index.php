<!DOCTYPE html>
<html lang="en">
<head>
<title>Mostrar Artista</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

<style type="text/css">
    table tr th, table tr td{font-size: 1.2rem;}
    .row{ margin:20px 20px 20px 20px;width: 100%;}
    .glyphicon{font-size: 20px;}
    .glyphicon-plus{float: right;}
    a.glyphicon{text-decoration: none;}
    a.glyphicon-trash{margin-left: 10px;}
    .none{display: none;}
</style>

<script>
    
    function showArtists(){
        $("#userData").html('<table></table>'); // Clean table, need to refresh with non elements in db

        resourcelocation='/artists';
         $.get(resourcelocation, function(data, status){
            var request = JSON.parse(data);
            response = '<table>';
            itemindex = 1;
            $.each(request, function(i, item) {
                   response += '<table> <tr>'+'<td>'+itemindex+'</td>'+'<td>'+item.name+'</td>'+'<td>'+item.surname+'</td>'+'<td>'+item.nickname+'</td>' + '<td> <a href="javascript:void(0);" class="glyphicon glyphicon-edit" onclick="showInEdit(\''+item.artistID+'\')"</a> <a href="javascript:void(0);" class="glyphicon glyphicon-trash" onclick="deleteArtist(\''+item.artistID+'\')"</a> </td> </tr> </table>';
                    ++itemindex;
            });           
            response += '</table>';
            
            $("#userData").html(response);
 
          });
    }

    function showInEdit(id){
            
        var resourcelocation ='/artists/'+id;
        $.ajax({
            type: 'GET',
            url: resourcelocation,
            data: '',
            success:function(res){
                var request = JSON.parse(res);
            
                $.each(request, function(i, item) {
                    $('#nameEdit').val(item.name);
                    $('#surnameEdit').val(item.surname);
                    $('#nicknameEdit').val(item.nickname);
                    $('#idEdit').val(item.artistID);
                });
                    
                $('#editForm').slideDown();
            }
        });
    }

    function addArtist() {
        name=$('#name').val();
        surname=$('#surname').val();
        nickname=$('#nickname').val();
            
        if (name+surname+nickname=='') {
            alert("Al menos debe rellenar un campo");
        } else {
         
            var url = '/artists'; 
            $.ajax({
                type: "POST",
                url: url,
                data: $("#addForm").find('.form').serialize(), // Adjuntar los campos del formulario enviado.
                success: function(data)
                {
                    showArtists(); //optional refresh list
                    $('.form')[0].reset();
                    $('.formData').slideUp();
                 },
                 error: function(x, e) {

                 }
            });
        }
    }

    function updateArtist() {            
            
        id=$('#idEdit').val();
        var resourcelocation ='/artists/'+id;

        name=$('#nameEdit').val();
        surname=$('#surnameEdit').val();
        nickname=$('#nicknameEdit').val();
            
        if (name+surname+nickname=='') {
            alert("Al menos debe rellenar un campo");
        } else {
            $.ajax({
                type: "PUT",
                url: resourcelocation,
                data: $("#editForm").find('.form').serialize(), // Adjuntar los campos del formulario enviado.
                success: function(data)
                {
                   showArtists(); //optional refresh list
                   $('.form')[0].reset();
                   $('.formData').slideUp();
                },
                error: function(x, e) {
                    //$("#dataartisaddformstatus").html('<font color="red">Result: Error</font>'); //  Show response Api Rest
                    alert('No ha realizado ninguna modificación en el registro');
                    $('.form')[0].reset();
                    $('.formData').slideUp();
                }
            });
        }
    }

    function deleteArtist(id) {            
            
        resourcelocation ='/artists/'+id;
            
        $.ajax({
            type: "DELETE",
            url: resourcelocation,
            data: '', 
            success: function(data)
            {
                showArtists(); //optional refresh list
            },
            error: function(x, e) {
                     
            }
        });

    }

    $(document).ready(function() {
        showArtists();
    });
        
</script>
</head>

<body>

<div class="container">
    <div align="center">
        <h2>
            <i class="glyphicon glyphicon-th-list"></i> Admin - Artists
            <span id=loader class="loader progress progress-striped active"><span class="bar"></span></span>
        </h2>
    </div>    
    <div class="row">
        <div class="panel panel-default users-content">
            <div class="panel-heading">Artists list<a href="javascript:void(0);" class="glyphicon glyphicon-plus" id="addLink" onclick="javascript:$('#addForm').slideToggle();"></a></div>
            <div class="panel-body none formData" id="addForm">
                <h2 id="actionLabel">Add Artist</h2>
                <form class="form" id="userForm">
                    <div class="form-group">
                        <label>Name</label>
                        <input type="text" class="form-control" name="name" id="name"/>
                    </div>
                    <div class="form-group">
                        <label>Surname</label>
                        <input type="text" class="form-control" name="surname" id="surname"/>
                    </div>
                    <div class="form-group">
                        <label>Nickname</label>
                        <input type="text" class="form-control" name="nickname" id="nickname"/>
                    </div>
                    <a href="javascript:void(0);" class="btn btn-warning" onclick="$('#addForm').slideUp();">Cancel</a>
                    <a href="javascript:void(0);" class="btn btn-success" onclick="addArtist()">Add Artist</a>
                </form>
            </div>
            <div class="panel-body none formData" id="editForm">
                <h2 id="actionLabel">Edit Artist</h2>
                <form class="form" id="userForm">
                    <div class="form-group">
                        <label>Name</label>
                        <input type="text" class="form-control" name="name" id="nameEdit"/>
                    </div>
                    <div class="form-group">
                        <label>Surname</label>
                        <input type="text" class="form-control" name="surname" id="surnameEdit"/>
                    </div>
                    <div class="form-group">
                        <label>Nickname</label>
                        <input type="text" class="form-control" name="nickname" id="nicknameEdit"/>
                    </div>
                    <input type="hidden" class="form-control" name="id" id="idEdit"/>
                    <a href="javascript:void(0);" class="btn btn-warning" onclick="$('#editForm').slideUp();">Cancel</a>
                    <a href="javascript:void(0);" class="btn btn-success" onclick="updateArtist()">Update Artist</a>
                </form>
            </div>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th></th>
                        <th>Name</th>
                        <th>Surname</th>
                        <th>Nickname</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody id="userData"></tbody>
            </table>

        </div>
    </div>
</div>
</body>
</html>
