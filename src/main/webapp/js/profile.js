
var btnEditProfile = document.querySelector('#profile-edit')

btnEditProfile.addEventListener('click', () => {
    btnEditProfile.classList.add('none')
    addInput()
    inputValue()
    addBtn()
})


var profileInfo = []


var addInput = function() {
    info = document.querySelectorAll('[data-type = profile-info]')
    info.forEach((element) => {
        profileInfo.push(element.innerText)
        element.classList.add('none')
    });
}

var inputValue = function(){
    input = document.querySelectorAll('[data-type = profile-input]')

    for (i = 0; i < input.length; i++) {
        input[i].value = profileInfo[i]
        input[i].classList.remove('none')
    }
}

var addBtn = function() {
    document.querySelectorAll('#profile-edit-btn').forEach((elem) => {
        elem.classList.remove('none')
    })
}


var cancel = document.querySelector('[data-type = profile-cancel]')


cancel.addEventListener('click', () => {
    info = document.querySelectorAll('[data-type = profile-info]')
    info.forEach((element) => {
        element.classList.remove('none')
    });

    input = document.querySelectorAll('[data-type = profile-input]')

    for (i = 0; i < input.length; i++) {
        input[i].classList.add('none')
    }
    document.querySelectorAll('#profile-edit-btn').forEach((elem) => {
        elem.classList.add('none')
    })
    btnEditProfile.classList.remove('none')
})





