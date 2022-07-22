

$('#input-dni').click(function(){
    //validar('#input-dni');
    $('#input-dni').keydown(function(event){
        //alert(event.keyCode);
        if((event.keyCode < 48 || event.keyCode > 57) && (event.keyCode < 96 || event.keyCode > 105) && event.keyCode !==190  && event.keyCode !==110 && event.keyCode !==8 && event.keyCode !==9  ){
            return false;
        }
    });
});

$('#input-fono').click(function(){
   // validar('#input-fono');
    $('#input-fono').keydown(function(event){
        //alert(event.keyCode);
        if((event.keyCode < 48 || event.keyCode > 57) && (event.keyCode < 96 || event.keyCode > 105) && event.keyCode !==190  && event.keyCode !==110 && event.keyCode !==8 && event.keyCode !==9  ){
            return false;
        }
    });
});

//Validamos y solo permite introducir numeros.
$(function validar(inputaingresar){
   
});

function verificarxdni(){
	//alert("funciona");
	var dni = $("#input-dni").val();	
	//alert (dni);	
	if(dni.length == 8){
		
		Swal.fire({
		    icon: 'info',
		    title: 'Procesando...',
		    text: '',
		    showConfirmButton: false,
		    timer: 3500
		});	
	
	$.ajax({
		type: "POST",
		url:"/verificarxDNI/"+dni,
		 dataType:"json",
		success: function(res) {
			console.log(res.data);
			if(res.data == null){
				
				Swal.fire({
				  icon: 'error',
				  title: 'Error...',
				  text: 'El DNI ingresado no existe.',
				  confirmButtonText: 'OK',
		  		confirmButtonColor: '#7066e0',
				});		
				
			}else{
				
			var dni = res.data.numero;
			var nombres = res.data.nombres;
			var apellidoPaterno = res.data.apellido_paterno;
			var apellidoMaterno = res.data.apellido_materno;
			var fechanacimiento = res.data.fecha_nacimiento;
			var sexo = res.data.sexo;
			var estadocivil = res.data.estado_civil;
			var direccion = res.data.direccion_completa;
			/*
			var dni = res.dni;
			var dni = res.dni;
			var dni = res.dni;
			*/
			
			if(dni == '' || nombres==''){
				
				Swal.fire({
				  icon: 'error',
				  title: 'Error...',
				  text: 'No se pudo obtener sus datos...',
				  confirmButtonText: 'OK',
		  		confirmButtonColor: '#7066e0',
				});		
				
			}else{
				
				Swal.fire(
				  'Verificado!',
				  'Se obtuvo sus datos sin problemas',
				  'success'
				);
				
				$("#input-nombres").val(nombres);
				$("#input-apellidoPat").val(apellidoPaterno);
				$("#input-apellidoMat").val(apellidoMaterno);
				
				$("#input-fecha").val(fechanacimiento);
				$("#input-sexo").val(sexo);
				$("#input-estado").val(estadocivil);
				$("#input-direccion").val(direccion);
				
				$("#btn-continuar-set1").attr("style","display:block!important;");
				
				
				
			}
		}
	 }
		
	});
	
	}else{
		
		Swal.fire({
		  icon: 'error',
		  title: 'Error...',
		  text: 'El valor ingresado no es el correcto.',
		  confirmButtonText: 'OK',
  		confirmButtonColor: '#7066e0',
		});		
		
	}
}

$('#btn-continuar-set1').click(function(){	
	
	if($("#input-fono").val() != "" && $("#input-email").val() != "" ){
		
		Swal.fire({
		    icon: 'info',
		    title: 'Procesando...',
		    text: '',
		    showConfirmButton: false,
		    timer: 1000
		})
			var dni = $("#input-dni").val();
			var fono = $("#input-fono").val();
			var email = $("#input-email").val();
						
			$.ajax({
				url:"/saveEmailFono",
				type: 'POST',
				 data:  {dni:dni,fono:fono,email:email},
				success: function(res) {
					console.log(res);
					
					$("#set1").attr("style","display:none");
	   				$("#set2").attr("style","display:block!important"); 
				}
			});			
   		 
	}else{
		
		Swal.fire({
		  icon: 'error',
		  title: 'Error...',
		  text: 'Los campos Correo Electrónico y Telefono son necesarios',
		  confirmButtonText: 'OK',
  		confirmButtonColor: '#7066e0',
		});		
    
	}
   
});


$('#btn-continuar-set2').click(function(){	
	
	var dni = $("#input-dni").val();
	var sobremi = $("#txt-desc-mi").val();
	var carrera = $("#input-carrera").val();
	var conocimientos = $("#input-conocimientos").val();
	var idiomas = $("#input-idiomas").val();
	var institucion = $("#input-institucion").val();
	var fechainsc = $("#input-fechainst").val();
	var descedu = $("#txt-desc-edu").val();
	
	if( sobremi != '' && carrera != '' && conocimientos != '' && idiomas != '' && institucion != '' && fechainsc != '' && descedu != ''){
		
		Swal.fire({
		    icon: 'info',
		    title: 'Procesando...',
		    text: '',
		    showConfirmButton: false,
		    timer: 1000
		})
									
			$.ajax({
				url:"/saveset2",
				type: 'POST',
				 data:  {dni:dni,sobremi:sobremi,carrera:carrera,conocimientos:conocimientos,idiomas:idiomas,institucion:institucion,fechainsc:fechainsc,descedu:descedu},
				success: function(res) {
					console.log(res);
					
					$("#set2").attr("style","display:none");
	   				$("#set3").attr("style","display:block!important"); 
				}
			});			
   		
   		 
	}else{
		
		Swal.fire({
		  icon: 'error',
		  title: 'Error...',
		  text: 'Todos los campos son requeridos',
		  confirmButtonText: 'OK',
  		confirmButtonColor: '#7066e0',
		});		
    
	}
   
});


$('#btn-continuar-set3').click(function(){	
	
	var dni = $("#input-dni").val();
	var empresa = $("#input-empresa").val();
	var puesto = $("#input-puestoemp").val();
	var fechaslab = $("#input-fechalab").val();
	var descriplab = $("#txt-desc-labo").val();
	var linkedin = $("#input-linkedin").val();
	var twittter = $("#input-twitter").val();
	var instagram = $("#input-insta").val();
	var facebook = $("#input-faceb").val();
	
	if( empresa != '' && puesto != '' && fechaslab != '' && descriplab != '' && linkedin != '' && twittter != '' && instagram != '' && facebook != ''){
		
		Swal.fire({
		    icon: 'info',
		    title: 'Procesando...',
		    text: '',
		    showConfirmButton: false,
		    timer: 1000
		})
									
			$.ajax({
				url:"/saveset3",
				type: 'POST',
				 data:  {dni:dni,empresa:empresa,puesto:puesto,fechaslab:fechaslab,descriplab:descriplab,linkedin:linkedin,twittter:twittter,instagram:instagram,facebook:facebook},
				success: function(res) {
					console.log(res);					
					$("#set3").attr("style","display:none");					
	   				$("#set4").attr("style","display:block!important"); 
	   				$("#dnioculto").val(dni);
				}
			});			
				
   		 
	}else{
		
		Swal.fire({
		  icon: 'error',
		  title: 'Error...',
		  text: 'Todos los campos son requeridos',
		  confirmButtonText: 'OK',
  		confirmButtonColor: '#7066e0',
		});		
    
	}
   
});



