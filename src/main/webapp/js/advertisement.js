[].forEach.call(document.querySelectorAll('input[id="id_files"]'), function(inp) {
    inp.addEventListener('change', function() {
        var reader = new FileReader(),
            img = this.previousElementSibling;
        this.nextElementSibling.classList.add('none')
        img.classList.remove('none')
        reader.onload = function() {
            img.src = reader.result;
        };
        reader.readAsDataURL(this.files[0]);
    }, false);
});
