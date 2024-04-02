window.onload = function() {
	for (let i = 0; i < 12; i++) {
		let article = document.createElement("article");
		article.innerHTML = `
			<video src="img/video.mp4" controls></video>
			<div>
				<p class="titulo">Título vídeo</p>
				<p class="canal">Canal</p>
			</div>
		`;
		document.querySelector("#contenedor").append(article);
	}
}

/*
fetch("https://rickandmortyapi.com/api/character")
.then(res => res.json())
.then(data => {
    for (let i=0; i<data.results.length; i++) {
        let article=document.createElement("article");
        article.innerHTML=`
        <div class="image-container">
            <img src="${data.results[i].image}">
        </div>
        <h2>${data.results[i].name}</h2>
        <span>${data.results[i].status}</span>
        <span>${data.results[i].location.name}</span>
        `;
        contenedorPersonajes.appendChild(article);
    }
});
*/