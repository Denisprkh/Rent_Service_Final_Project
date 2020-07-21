var profile = document.querySelector('.btn-my_profile')
profileAll = document.querySelectorAll('.btn-my_profile')

myApplications = document.querySelector('.btn-my_application')
myApplicationsAll = document.querySelectorAll('.btn-my_application')

myAds = document.querySelector('.btn-my_ads')
myAdsAll = document.querySelectorAll('.btn-my_ads')

forMyAds = document.querySelector('.btn-my_formyads')
forMyAdsAll = document.querySelectorAll('.btn-my_formyads')

allBtn = document.querySelectorAll('.left-btn__item')
allBtnText = document.querySelectorAll('.left-btn__item_text')

myProfileCard = document.querySelector('.my_profile')
myApplicationsCard = document.querySelector('.my_applications')
myAdsCard = document.querySelector('.my_ads')
applicationForMyAdsCard = document.querySelector('.application_for_my_ads')

cardBtnAll = document.querySelectorAll('.card-btn')

removeClassAll = function () {
    var
        allBtnArray = Array.from(allBtn)
    allBtnTextArray = Array.from(allBtnText)
    cardBtnAllArray = Array.from(cardBtnAll)
    for(i = 0; i < allBtnArray.length; i++) {
        allBtnArray[i].classList.remove('active')
        allBtnTextArray[i].classList.remove('active')
    }
    for(i = 0; i < cardBtnAllArray.length; i++){
        cardBtnAllArray[i].classList.add('none')
    }
}

addActive = function(btn, card){
    removeClassAll()
    var btnArray = Array.from(btn)
    for(i = 0; i < btnArray.length; i++) {
        btnArray[i].classList.add('active')
    }
    card.classList.remove('none')
}

profile.addEventListener('click', () => {addActive(profileAll, myProfileCard)})
myApplications.addEventListener('click', () => {addActive(myApplicationsAll, myApplicationsCard)})
myAds.addEventListener('click', () => {addActive(myAdsAll, myAdsCard)})
forMyAds.addEventListener('click', () => { addActive(forMyAdsAll, applicationForMyAdsCard)})


var allUsers = document.querySelector('.btn-all_users')
if (allUsers === null){
    allUsers = null
} else{
    var allUsersAll = document.querySelectorAll('.btn-all_users')
    allUsersCard = document.querySelector('.all_users')
    allUsers.addEventListener('click', () => {addActive(allUsersAll, allUsersCard)})
}