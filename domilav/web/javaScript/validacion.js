
var mayusReg = "/^[0-9a-zA-Z]+$/";


$(document).ready(function(){
$("#validaDatos").click(function(){

if ($("#nombre").val().length < 3){
     var mensaje = document.getElementById('validaDatos');
     mensaje.innerHTML = "<p>INGRESE UN NOMBRE MAYOR O IGUAL A TRES LETRAS. </p>";
        return false;
} else if ($("#nombre").val().toUpperCAse()){
alert("Solo se admiten letras mayusculas");
        return false;
}
// IsNaN = Toma los valores y compara si son numericos.
else if ($("#tel").val().length < 7 || isNaN($("#tel").val())){
alert("El telefono debe tener mas de 6 caracteres.");
        return false;
} 
else if ($("#correo").val().indexOf('@', 0) === - 1 || $("#correo").val().indexOf('.', 0) === - 1) {
alert("El formato del correo es incorrecto");
        return false;
}

});
        });
