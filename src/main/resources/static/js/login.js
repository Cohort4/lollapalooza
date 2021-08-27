const app = Vue.createApp({

    data() {
        return {
            signUp: false,
            signU: "or Sign Up",
            sign: "Sign In",
            email: "",
            psw:"",
            firstName:"",
            lastName:"",
        }
    },

    created() {
    },

    methods: {
        /* Metodo para renderizado del login */
        signUpOrIn() {
            this.signUp = !this.signUp;
            if (this.signUp) {
                this.signU = "or Sign In"
                this.sign = "Sign Up"
            } else {
                this.signU = "or Sign Up"
                this.sign = "Sign In"
            }
        },
        /* SIGN IN - SIGN UP */
        login(sing) {
            /* Login */
            if (sing == "Sign In") {
                axios.post('/api/login', "email=" + this.email + "&password=" + this.psw,
                    { headers: { 'content-type': 'application/x-www-form-urlencoded' } })
                    .then(() => { swal("Log in successfully") 
                     setTimeout(() => { 
                         if (this.email == "admin@lollapalooza.com"){
                            window.location.href = "/manager.html"
                        } else {
                            window.location.href = "/index.html"

                        } 
                    }, 1000) })
                    .catch(err => swal(err.data.response))
            } 
            /* Registro */
            if (sing == "Sign Up") {
                axios.post('/api/clients', "firstName=" + this.firstName + "&lastName=" + this.lastName + "&email=" + this.email + "&password=" + this.psw,
                 { headers: { 'content-type': 'application/x-www-form-urlencoded' } })
                    .then(() => {
                        /* Login cliente nuevo */
                        axios.post('/api/login', "email=" + this.email + "&password=" + this.psw,
                            { headers: { 'content-type': 'application/x-www-form-urlencoded' } })
                            .then(() => {
                                swal("User Created & Log in successfully"); 
                                setTimeout(() => { window.location.href = "/index.html"}, 3000)
                            })
                            .catch(err => swal(err.data.response))
                    })
                    .catch(err => swal(err.response.data))
            }
        }
    },

   

    

    computed: {
    },

})

app.mount("#app")
