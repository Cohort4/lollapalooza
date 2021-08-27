const app = Vue.createApp({

    data() {
        return {
            precioUnitario: 30,
            cantidad: 0,
            servicefee: 0.75,
            total: 0,
            subtotal: 0,
            subtotalservicefee: 0,
        }
    },

    created() {},

    methods: {
        restCantidad() {
            if (this.cantidad > 0)
                this.cantidad = this.cantidad - 1;
        },
        sumCantidad() {
            this.cantidad = this.cantidad + 1;
        },
        subTotalFunc() {
            this.subtotal = this.precioUnitario * this.cantidad;
            return this.subtotal;
        },
        serviceFeeFunc() {
            this.subtotalservicefee = this.servicefee * this.cantidad;
            return this.subtotalservicefee;
        },
        totalFunc() {
            if (this.cantidad == 0) {
                return 0;
            } else {
                this.total = this.subtotal + this.subtotalservicefee;
                return this.total;

            }
        },
        formatBalance(balance) {
            if (balance == null) {
                return
            }
            let amount = new Intl.NumberFormat('es-US', {
                style: 'currency',
                currency: 'USD',
            })
            return amount.format(balance)
        },
        logOut() {
            axios.post('/api/logout').then(response => window.location.href = "/web/index.html")
                .catch(error => Swal.fire(error.response.data))
        }
    },

    computed: {},

})

app.mount("#app")
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