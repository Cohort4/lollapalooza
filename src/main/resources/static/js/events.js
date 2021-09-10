const app = Vue.createApp({

    data() {
        return {
            events: [],
            meses: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sept", "Oct", "Nov", "Dec"],
            idTicket: "",
            counter: JSON.parse(sessionStorage.getItem("COUNTER")) == null ? 0 : JSON.parse(sessionStorage.getItem("COUNTER")),
            i: false,
            cart: false,
            cartItems: JSON.parse(sessionStorage.getItem("SESSIONSTATUS")) == null ? [] : JSON.parse(sessionStorage.getItem("SESSIONSTATUS")),
            total: 0,
            quantity: 0,
            subtotal: 0,
            item: {},
            logged: sessionStorage.getItem("LOGGED") != null ? sessionStorage.getItem("SESSIONSTATUS") : "false"
        }
    },

    created() {
        axios.get("/api/events")
            .then(res => {
                this.events = res.data.filter(event => event.status == true)
            })
    },

    methods: {
        diaEvent(date) {
            return date.split("").splice(8, 10).join("");
        },
        mesEvent(mDate) {
            mDate = mDate.split("").splice(5, 7).join("")
            var numeroMes = parseInt(mDate);
            if (!isNaN(numeroMes) && numeroMes >= 1 && numeroMes <= 12) {
                mDate = this.meses[numeroMes - 1];
            }
            return mDate;


        },
        add() {
            this.i = !this.i
        },

        addToCart(item) {

            this.item = item;

            if (JSON.parse(sessionStorage.getItem("SESSIONSTATUS")) != null) {

                let value = JSON.parse(sessionStorage.getItem("SESSIONSTATUS")).filter(x => x.id == item.id)
                
                if (value.length == 1) {
                   item.cart = true;
                }
            }

            if (!item.cart) {

                const newItem = Object.assign({}, item, { count: 1});

                item.cart = true;

                this.cartItems.push(newItem);

                this.counter++
                sessionStorage.setItem('COUNTER', JSON.stringify(this.counter))

                sessionStorage.setItem('SESSIONSTATUS', JSON.stringify(this.cartItems))

                this.cartItems = JSON.parse(sessionStorage.getItem("SESSIONSTATUS"));

            }

        },
        decrement(item) {
            item.count-- || item.countMerch--;
            this.counter--
            sessionStorage.setItem('SESSIONSTATUS', JSON.stringify(this.cartItems))
            sessionStorage.setItem('COUNTER', JSON.stringify(this.counter))
            
            
        },
        increment(item) {
            item.count++ || item.countMerch++
            this.counter++
            sessionStorage.setItem('SESSIONSTATUS', JSON.stringify(this.cartItems))
            sessionStorage.setItem('COUNTER', JSON.stringify(this.counter))
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
        unitCant(item){
            return item.count || item.countMerch
        },
        cantTotal(){
            
        },
        deleteProduct(index) {
            this.cartItems.splice(index, 1)
            totalproduct=index.count || index.countMerch
            this.counter = this.counter - totalproduct;
            sessionStorage.setItem('COUNTER', JSON.stringify(this.counter))
            this.item.cart = false;
            sessionStorage.setItem('SESSIONSTATUS', JSON.stringify(this.cartItems))
            this.cartItems = JSON.parse(sessionStorage.getItem("SESSIONSTATUS"));
        },
        loggedOut() {
            sessionStorage.clear();
            axios.post('/api/logout').then(response => window.location.href = "/index.html")
                .catch(error => Swal.fire(error.response.data))
            
        },
        accordionCollapse(id){
            for (let i = 0; i < this.collapse.length; i++) {
                if(i == (id-1)) {
                    let collapse = flush - collapse + this.collapse[i];
                }
                
            }
            return collapse;
        },
       
    },
    computed: {
        totalPrice() {
            let totalPrice = 0
            for (let i = 0; i < this.cartItems.length; i++) {
                totalPrice += this.cartItems[i].price * (this.cartItems[i].count || this.cartItems[i].countMerch)
            }
            sessionStorage.setItem('TOTALPRICE', JSON.stringify(totalPrice));
            return totalPrice
        },
    },

})

app.mount("#app")

/* const navToggle = document.querySelector(".nav-toggle");
const navMenu = document.querySelector(".nav-menu-nav");

navToggle.addEventListener("click", () => {
    navMenu.classList.toggle("nav-menu_visible");

    if (navMenu.classList.contains("nav-menu_visible")) {
        navToggle.setAttribute("aria-label", "Cerrar menú");
    } else {
        navToggle.setAttribute("aria-label", "Abrir menú");
    }
}); */

// const h2 = document.getElementsByClassName('_2jnik')

// console.log(h2)

// h2.addEventListener('click', () => {
//     h2.classList.toggle('hide')
//     // const date = document.querySelector('.date')
//     // date.classList.toggle('hide')
// })