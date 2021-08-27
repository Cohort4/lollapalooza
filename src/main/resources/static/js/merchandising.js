const app = Vue.createApp({

    data() {
        return {
            i: false,
            cart: false,
            quantity: 0,
            total: 0,
            subtotal: 0,
            price: 20,
            numberFactura: "",
            data: [],
            cartItems: JSON.parse(sessionStorage.getItem("SESSIONSTATUS")) == null ? [] : JSON.parse(sessionStorage.getItem("SESSIONSTATUS")),
            talles: "",
            counter: "",
            item: {},
            // Filtros
            check: [],
            productType: [],
        }
    },

    created() {
        axios.get("/api/merchs")
            .then(res => {
                this.data = res.data.filter((e=>e.status==true))
                for (let i = 0; i < this.data.length; i++) {
                    if (!this.productType.includes(this.data[i].productType)) {
                        this.productType.push(this.data[i].productType)
                    }
                }    
            })
    },

    methods: {
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
             
                const newItem = Object.assign({}, item, { countMerch: 1});
                item.cart = true;
            
                this.cartItems.push(newItem);
               
                this.counter++

                sessionStorage.setItem('SESSIONSTATUS', JSON.stringify(this.cartItems))

                this.cartItems = JSON.parse(sessionStorage.getItem("SESSIONSTATUS"));
               
            }
        },
        decrement(item) {
            item.countMerch--;
            sessionStorage.setItem('SESSIONSTATUS', JSON.stringify(this.cartItems))

        },
        increment(item) {
            item.countMerch++
            sessionStorage.setItem('SESSIONSTATUS', JSON.stringify(this.cartItems))
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
        deleteProduct(index) {
            this.cartItems.splice(index, 1)
            this.counter -= 1;
            this.item.cart = false;
            sessionStorage.setItem('SESSIONSTATUS', JSON.stringify(this.cartItems))
            this.cartItems = JSON.parse(sessionStorage.getItem("SESSIONSTATUS"));
        },
        logOut() {
            axios.post('/api/logout').then(response => window.location.href = "/web/index.html")
                .catch(error => Swal.fire(error.response.data))
        },
    


/*         generatePDF(numberFactura) {

            axios.post("/api/clients/current/export/pdf", "numberFactura=" + numberFactura, { responseType: 'blob' })

            .then((response) => {
                    const url = window.URL.createObjectURL(new Blob([response.data]));
                    const link = document.createElement('a');
                    link.href = url;
                    link.setAttribute('download', 'Factura' + numberFactura + '.pdf');
                    document.body.appendChild(link);
                    link.click();
                })
                .catch(err => console.log(err))
        }

        } */

    },
    computed: {
        totalPrice() {
            let totalPrice = 0
            for (let i = 0; i < this.cartItems.length; i++) {
                totalPrice += this.cartItems[i].price * this.cartItems[i].countMerch
            }
            sessionStorage.setItem('TOTALPRICE', JSON.stringify(totalPrice));
            return totalPrice
        },
        filter () {
            let check = this.data.filter(type => this.check.includes(type.productType) || this.check.length === 0)
            return check
        }
    },

})

app.mount("#app")


/* Funcion para redireccionar cuando se esta inactivo */
function idleLogout() {
    var t;
    window.onload = resetTimer;
    window.onmousemove = resetTimer;
    window.onmousedown = resetTimer;  // catches touchscreen presses as well      
    window.ontouchstart = resetTimer; // catches touchscreen swipes as well 
    window.onclick = resetTimer;      // catches touchpad clicks as well
    window.onkeypress = resetTimer;
    window.addEventListener('scroll', resetTimer, true); // improved; see comments

    function yourFunction() {
        // your function for too long inactivity goes here
        axios.post("/api/logout")
            .then(res => window.location.href = "/index.html")
            .catch(err => console.log(err.response.data))

    }

    function resetTimer() {
        clearTimeout(t);
        t = setTimeout(yourFunction, 1000000);  // time is in milliseconds
    }
}
idleLogout();