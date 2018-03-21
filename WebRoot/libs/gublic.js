function setSize() {
	var iHtml = document.querySelector('html');
	var w = iHtml.getBoundingClientRect().width;
	w = w > 750 ? 750 : w;
	iHtml.style.fontSize = w / 37.5 + 'px';
}
setSize();
document.querySelector('body').style.display = "block";
window.addEventListener('resize', setSize, false);
window.addEventListener('orientantionchange', setSize, false);

