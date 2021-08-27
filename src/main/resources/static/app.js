const app = Vue.createApp({

    data() {
        return {}
    },

    created() {},

    methods: {},

    computed: {},

})

app.mount("#app")


/* navbar */
const navToggle = document.querySelector(".nav-toggle");
const navMenu = document.querySelector(".nav-menu-nav");

navToggle.addEventListener("click", () => {
    navMenu.classList.toggle("nav-menu_visible");

    if (navMenu.classList.contains("nav-menu_visible")) {
        navToggle.setAttribute("aria-label", "Cerrar menú");
    } else {
        navToggle.setAttribute("aria-label", "Abrir menú");
    }
});


/* chat   */
const popup = document.querySelector('.chat-popup');
const chatBtn = document.querySelector('.chat-btn');
const submitBtn = document.querySelector('.submit');
const chatArea = document.querySelector('.chat-area');
const inputElm = document.querySelector('input');
const emojiBtn = document.querySelector('#emoji-btn');
const picker = new EmojiButton();


// Emoji selection  
window.addEventListener('DOMContentLoaded', () => {

    picker.on('emoji', emoji => {
        document.querySelector('input').value += emoji;
    });

    emojiBtn.addEventListener('click', () => {
        picker.togglePicker(emojiBtn);
    });
});

//   chat button toggler 

chatBtn.addEventListener('click', () => {
    popup.classList.toggle('show');
})

// send msg 
submitBtn.addEventListener('click', () => {
    let userInput = inputElm.value;

    let temp = `<div class="out-msg">
    <span class="my-msg">${userInput}</span>
    <img src="./assets/avatarChat2.png" class="avatar">
    </div>`;

    chatArea.insertAdjacentHTML("beforeend", temp);
    inputElm.value = '';

})