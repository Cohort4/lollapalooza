const app = Vue.createApp({

    data() {
        return {
            logged: sessionStorage.getItem("LOGGED") != null ? sessionStorage.getItem("SESSIONSTATUS") : "false"
        }
    },

    created() {
    },

    methods: {
      
        loggedOut() {
            sessionStorage.clear()
            axios.post('/api/logout').then(response => window.location.href = "/index.html")
                .catch(error => Swal.fire(error.response.data))
            
           
        },
         
    },
    computed: {
    },

})

app.mount("#app")

