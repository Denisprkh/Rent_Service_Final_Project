var icon = document.querySelector('.forms-date_input__icon')
    input = document.querySelector('.datepicker-here')
    
    icon.addEventListener('click', () => {input.focus()})

var btnPopup = document.querySelector('.popup__forms')
    popup = document.querySelector('.popup_container')
    closeIcon = document.querySelector('.close_popup_icon')
    body = document.querySelector('body')

    btnPopup.addEventListener('click', () => {
        popup.classList.remove('none')
        body.classList.add('hidden')
    })

    popup.onclick = function(event){
        var whoClick = event.target
        if(whoClick === popup){
            popup.classList.add('none')
            body.classList.remove('hidden')
        } else {
            if (whoClick === closeIcon){
            popup.classList.add('none')
            body.classList.remove('hidden')
            }
        }
    }

    $('#mindateinp').datepicker({
        minDate: new Date()
    })