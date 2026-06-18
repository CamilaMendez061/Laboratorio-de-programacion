// -------- abre imagen en nueva ventana ---------
const botonSorpresa = document.getElementById('btn_sorpresa');

botonSorpresa.addEventListener('click', () => {
    window.open('img/sorpresa_gatos.jpg', '_blank');
})


// -------- contador de likes ----------
const botonLike = document.getElementById('btn_like');
const contadorLikes = document.getElementById('contadorLikes');

let cantLikes = localStorage.getItem('likes') ? parseInt(localStorage.getItem('likes')) : 0;

contadorLikes.innerText = cantLikes;

botonLike.addEventListener('click', () => {
    cantLikes++;
    contadorLikes.innerText = cantLikes;
    localStorage.setItem('likes', cantLikes);
})