/* Resource Location for Entity and Verbs */
    var lybartist={
        name : "Aritst Team Library",
        cachejson : null, // storagee last cache request json
        lastid    : null, // last id of one register
        formmode  : 'POST', // Default mode form is POST | PUT
        formResource : null, // resource of foorm CREATE | UPDATE
        action     : null,   // action FIND SHOW CREATE UPDATE
        config     :null,    //configuration 
        resource   : null,   // resource FIND | SHOW
        resourceindex: 0,
        generateform : function () {
          autogenerate='<form id="formulario">';
          ind=0;
          for(ind=0; ind < this.config.attributes.length; ind++){
              autogenerate+='<div>'+this.cFirst(this.config.attributes[ind])+'</div>  <input type=text name="'+this.config.attributes[ind]+'" class="form-control">';
          }
          autogenerate+='<div></div><input type=submit class="btn btn-default" name=press value="Send" onclick="return lybartist.sendform();">';
          autogenerate+='<input type=reset  class="btn btn-default" name=default value="Reset">';
          autogenerate+='<input type=button  class="btn btn-default" name=hidden value="Close" onclick="return lybartist.hiddenform();">';
          autogenerate+='</form>';
          $("#autogenerateform").html(autogenerate);

          autogenerate='<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" ';
          autogenerate+=' aria-haspopup="true" aria-expanded="false">';
          autogenerate+=' Search <span class="caret"></span> </button>';

          autogenerate+='<ul class="dropdown-menu">';
          $.each(this.config.findlist, function(i,item) {
          	autogenerate+='<li><a href="javascript:void(0);" onClick="lybartist.showSearchMenu(\''+i+'\');">'+item.resource+'</a></li>';
          });
          autogenerate+='</ul>';
          $("#typeofsearch").html(autogenerate);
          
            return true;
        },
        init : function () {  // Lybrary init config
          this.formResource   = '/'+this.config.entitie;
          this.resource   = '/'+this.config.entitie;
          this.formmode = 'POST';
          this.action = 'SHOW';
          $("#titleform").html('Create '+this.config.entitie);
          $("#nameentitie").html(this.cFirst(this.config.entitie));
          this.generateform();
          return false;
        },
        changeContext  : function (oneaction,onemethod) {
          this.action = oneaction;
          this.formmode = onemethod;
          $("#statusactionform").html('');
          $("#formulario")[0].reset();
          $("#titleform").html('<h2>'+oneaction+' '+this.config.entitie+'</h2>');

          //$("#dataformaction").css('display','block');
          $("#dataformaction").slideDown();
          return false;
        },
        hiddenform : function() {
            $("#dataformaction").slideUp(); //.css('display','none');
            return false;
        },
        cFirst: function(string) {
        	return string.charAt(0).toUpperCase() + string.slice(1);
        },
        show : function () {
          if (this.action=='FIND') this.hiddenform();
          this.action = 'SHOW';
          this.resource = '/'+ this.config.entitie;
          this.showRegisters();
          return false;
        },
        showSearchMenu: function (indice) {
          this.changeContext("FIND","GET");
          this.resource='/'+this.config.entitie+'/'+this.config.findlist[indice].resource+'';
          this.resourceindex=indice;
        	return false;
        },
        createRegister : function() { // form create register
            this.changeContext("CREATE","POST");
            this.formResource='/'+this.config.entitie;

            return false;
        },
        updateregister: function (id,indexofcache) { // form update register
            this.changeContext("UPDATE","PUT");
            this.formResource='/'+this.config.entitie+'/'+id;
            this.recoveryofcache(indexofcache);
           return false;
        },
        recoveryofcache : function (indexofcache){ // storage register in cache
          firstatribute = true;
          $.each(this.cachejson[indexofcache], function(name, value) {
             if (firstatribute) {
                 lybartist.lastid = value;
                 firstatribute=false;
             } else {
                 $('input:text[name='+name+']').val(value);
             }
          });
          return false;
        },
        deleteregister : function (id) {  // delete one register of system 
            if (confirm('Really delete this register!!!')) {
              $.ajax({
                 type: "DELETE",
                 url: '/'+this.config.entitie+'/'+id,
                 data: '', 
                 success: function(data)
                 {
                     $("#statusactionform").html('<font color="green"><b>'+data+'</b></font>'); // Show response Api Rest
                     lybartist.show();
                 },
                 error: function(x, e) {
                     $("#statusactionform").html('<font color="red"><b>Error</b></font>'); //  Show response Api Rest
                 }
              });

            }
            return false; 
      },
      sendform : function () {  // send form for createRegister and updateregister
              //nickname=$('input:text[name=nickname]').val();   
              if (lybartist.action=='FIND') return this.showRegisters();    
              $("#statusactionform").html('');
              $.ajax({ // Note in body "this" not is one pointer lybartist exept in parameters
                 type: this.formmode,
                 url: this.formResource,
                 data: $("#formulario").serialize(), // example data="name=pepe&surname=argento"
                 success: function(data)
                 {
                     if (lybartist.action!='FIND') 
                        $("#statusactionform").html('<font color="green"><b>'+data+'</b></font>'); // Show response Api Rest
                     (lybartist.action=='FIND') ?  lybartist.showRegisters() : lybartist.show();
                     if (lybartist.formmode=='POST') $("#formulario")[0].reset();
                    
                 },
                 error: function(x, e) {
                     $("#statusactionform").html('<font color="red"><b>Error</b></font>'); //  Show response Api Rest
                 }
              }); // end ajax
            return false;
      },
     showRegisters : function () {
        resourcereal=this.resource;
        if (this.action=='FIND'){
          findcurrent = this.config.findlist[this.resourceindex];
          if (findcurrent.useurl.length>0){ //find with parameters in path
               argument=$('input:text[name='+findcurrent.useurl[0]+']').val();   
               resourcereal +=argument;
               if (argument==''){
                 $("#statusactionform").html('<font color="red"><b>Error parameters \''+findcurrent.useurl[0]+'\' is empty</b></font>');
               } else{
                $("#statusactionform").html('');
               }
          } else
          {
               resourcereal +='?'+ $("#formulario").serialize();
          }
        } 
        response='';
         // note lybartist is necesary reference, this is one pointer ajax object in method .GET 
         $.get(resourcereal, function(data, status){
            try{ 
                lybartist.cachejson = JSON.parse(data); // capture exception if produced
            }catch(a){
                lybartist.cachejson=null;
                $("#datatable").html('');
                $("#datacount").html('List Empty');
                return false;
            }
            response = '<table class="table collection table table-bordered table-striped table-hover"><tr><th>N</th>'; 
            getHeaders = true;
            $.each(lybartist.cachejson, function(i, item) {
                   if (getHeaders) { // get Header Names
                       $.each(item, function(name,value) { //skip first name atribute
                          if (!getHeaders) {response+= '<th>'+lybartist.cFirst(name)+'</th>';}
                              else {getHeaders=false}
                       });
                        if (lybartist.config.relationship.length>0)
                          response+='<th>Relation</th>';
                        response+='<th>Action</th></tr>';
                   }
                   response += '<tr>';
                   idvalue=null;
                   $.each(item, function(name,value) { // fill rows
                   	     if (idvalue!=null) {response+='<td>'+value+'</td>';}
	           			     	 else {idvalue=value;response+='<td>'+(i+1)+'</td>';} 
                   });
                   if (lybartist.config.relationship.length>0) //only one relation many to many
                       response +='<td><input type="button" class="btn btn-primary" value="'+lybartist.config.relationship[0]+'" onclick="lybartist.showManyToMany(\''+lybartist.config.entitie+'\',\''+idvalue+'\',\''+lybartist.config.relationship[0]+'\');"></td>';
                   response +='<td><input type="button" class="btn btn-warning" value="Edit" onclick="lybartist.updateregister(\''+idvalue+'\','+i+');">';
                   response += '&nbsp;<input type="button" class="btn btn-danger" value="Delete" onclick="lybartist.deleteregister(\''+idvalue+'\');"></td></tr>';

            });
            response += '</table>'; // note lybartist is necesary reference, this is one pointer ajax object
            $("#datacount").html('<hr class="hrclass"><b>Match '+lybartist.cachejson.length+' registers.' +((lybartist.action=='FIND')? ' filters options ': '' )+'</b><hr class="hrclass">');
            if (lybartist.cachejson.length>0) {
              $("#datatable").html(response);
            } else {
              $("#datatable").html('');
            } 
          }); // end object ajax
         return false;
        },
        showManyToMany: function (origin,id,destine) {
          $("#datacount").html('');
          $("#statusactionform").html('');
          console.log('Many to many of '+origin+'('+id+') to '+destine);
                 $.ajax({ // Note in body "this" not is one pointer lybartist exept in parameters
                 type: 'GET',
                 url: '/artists/getbandsbyId/'+id,
                 success: function(data)
                 {
                   myjson='';
                   try{ myjson = JSON.parse(data);}
                   catch(a){
                        myjson=null;
                        $("#datatable2").html('<div class="col-xs-6 col-md-4">List Empty</div>');
                        return false;
                    }
                    response = '<div class="col-xs-6 col-md-4"><h2> In Relation Table </h2></div><table class="table collection table table-bordered table-striped table-hover"><tr><th>N</th>'; 
                    getHeaders = true;
                    $.each(myjson, function(i, item) {
                       if (getHeaders) { // get Header Names
                          $.each(item, function(name,value) { //skip first name atribute
                            if (!getHeaders) {response+= '<th>'+lybartist.cFirst(name)+'</th>';}
                                else {getHeaders=false}
                          });
                        }

                            response += '<th>Action</th></tr><tr><td>'+(i+1)+'</td>';
                            idvalue=null;
                            $.each(item, function(name,value) { // fill rows
                               if (idvalue!=null) {response+='<td>'+value+'</td>';}
                               else {idvalue=value;}
                            });
                            response+='<td>OneAction</td>';
                    });
                    response +='</tr>';
                    response += '</table>';
                    $("#datatable2").html(response); // Show response Api Rest
                 },
                 error: function(x, e) {
                     $("#statusactionform").html('<font color="red"><b>Error</b></font>'); //  Show response Api Rest
                 }
              }); // end ajax
 

          return false;
        }
    }; // end Lybrary artist-team
