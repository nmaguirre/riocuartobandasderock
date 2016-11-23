/* Resource Location for Entity and Verbs */
    var lybartist={
        name : "Aritst Team Library",
        cachejson : null, // storagee last cache request json
        lastid    : null, // last id of one register
        formmode  : 'POST', // Default mode form is POST | PUT
        formResource : null, // resource of foorm CREATE | UPDATE
        resource   : null,   // resource FIND | SHOW
        action     : null,   // action FIND SHOW CREATE UPDATE
        entities  : ['artists','albums','users','bands','songs'], // entities of system
        actionentities : {SHOW :'/', UPDATE : '/ID', },
        currentEntitie : null,
        init : function () {  // Lybrary init config
          this.currentEntitie = this.entities[0];
          this.formResource   = '/'+this.currentEntitie;
          this.resource   = '/'+this.currentEntitie;
          this.formmode = 'POST';
          this.action = 'SHOW';
          $("#titleform").html('Create '+this.currentEntitie);
          return false;
        },
        changeContext  : function (oneaction,onemethod) {
          this.action = oneaction;
          this.formmode = onemethod;
          $("#statusactionform").html('');
          $("#formulario")[0].reset();
          $("#titleform").html(oneaction+' '+this.currentEntitie);

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
          this.resource = '/'+ this.currentEntitie;
          this.showRegisters();
          return false;
        },
        findbyall : function() {
            this.changeContext("FIND","GET");
            this.resource='/'+this.currentEntitie+'/findbyallattributes/';
          return false;
        },
        createRegister : function() { // form create register
            this.changeContext("CREATE","POST");
            this.formResource='/'+this.currentEntitie;

            return false;
        },
        updateregister: function (id,indexofcache) { // form update register
            this.changeContext("UPDATE","PUT");
            this.formResource='/'+this.currentEntitie+'/'+id;
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
           // console.log(name);
          });
          return false;
        },
        deleteregister : function (id) {  // delete one register of system 
            if (confirm('Really delete this register!!!')) {
              $.ajax({
                 type: "DELETE",
                 url: '/'+this.currentEntitie+'/'+id,
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
        if (this.action=='FIND') resourcereal +='?'+ $("#formulario").serialize();
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
            response = '<table><tr>'; 
            getHeaders = true;
            $.each(lybartist.cachejson, function(i, item) {
                   if (getHeaders) { // get Header Names
                       $.each(item, function(name,value) { //skip first name atribute
                          if (!getHeaders) {response+= '<th>'+lybartist.cFirst(name)+'</th>';}
                              else {getHeaders=false}
                       });
                        response+='<th>Action</th></tr>';
                   }
                   response += '<tr>';
                   idvalue=null;
                   $.each(item, function(name,value) { // fill rows
                   	     if (idvalue!=null) {response+='<td>'+value+'</td>';}
					     	else {idvalue=value;} 
                   });
                   response +='<td><input type="button" value="Edit" onclick="lybartist.updateregister(\''+idvalue+'\','+i+');">';
                   response += '<input type="button" value="Delete" onclick="lybartist.deleteregister(\''+idvalue+'\');"></td></tr>';

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
        }
    }; // end Lybrary artist-team

      $(window).ready(function() {
        lybartist.init();
        lybartist.showRegisters();
      });