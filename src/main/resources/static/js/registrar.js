window.onload = function() {
	console.log("Cargado");
	document.getElementById("inputSubmitRegistro").addEventListener("click", () => {
		console.log("Click");
		/*
	    fetch("http://localhost:9000/registrarUsuario", {
			method: "POST"
		})
	    .then(res => res.json())
	    .then(data => {
		    console.log(data);
		    document.getElementById("nombre").innerHTML = 'Nombre: ' + data.nombre;
		    document.getElementById("apellidos").innerHTML = 'Apellidos: ' + data.apellidos;
		    document.getElementById("nickname").innerHTML = 'Nickname: ' + data.nickname;
		    document.getElementById("email").innerHTML = 'Email: ' + data.email;
		    document.getElementById("telefono").innerHTML = 'Telefono: ' + data.telefono;
	    });
	    */
	});
}