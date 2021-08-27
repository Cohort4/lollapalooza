const app = Vue.createApp({

    data() {
        return {
            eventosPrevios: [],
            description: '',
            logged: sessionStorage.getItem("LOGGED") != null ? sessionStorage.getItem("SESSIONSTATUS") : "false"
        }
    },

    created() {
        axios.get('/api/previousevent')
            .then(res => {
                this.eventosPrevios = res.data
                this.getJSessionId();
                console.log(this.eventosPrevios)
            })
    },

    methods: {
        postNewComment(x) {
            axios.post(`/api/addComment`, `id=${x}&description=${this.description}`)
                .then(() => {

                    alert("Comentario enviado")
                    location.reload()
                })
                .catch(() =>

                    Swal.fire({
                        title: 'You have to be logged in to comment',
                        text: "Do you want to log in?",
                        icon: 'warning',
                        showCancelButton: true,
                        confirmButtonColor: '#3085d6',
                        cancelButtonColor: '#d33',
                        confirmButtonText: 'Yes, I want to log in'
                    }).then((result) => {
                        if (result.isConfirmed) {
                            window.location.href = ("/login.html")
                        }
                    }))
        },
        editComment(x) {
            axios.post(`/api/editComment`, `description=${this.description}&idComment=${x}`)
                .then(res => console.log(res.response.data))
                .catch(error => Swal.fire(error.response.data))
                .then(alert("Comentario editado"))
                .then(location.reload())
        },
        deleteComment(x) {
            axios.post(`/api/deleteComment`, `idComment=${x}`)
                .then(res => console.log(res.response.data))
                .catch(error => Swal.fire(error.response.data))
                .then(alert("Comentario eliminado"))
                .then(location.reload())
        },
        loggedOut() {
            sessionStorage.clear();
            axios.post('/api/logout').then(response => window.location.href = "/index.html")
                .catch(error => Swal.fire(error.response.data))
                
        },
        getJSessionId() {
            var jsId = document.cookie.match(/JSESSIONID=[^;]+/);
            if (jsId != null) {
                if (jsId instanceof Array)
                    jsId = jsId[0].substring(11);
                else
                    jsId = jsId.substring(11);
            }
            console.log(jsId)
        }
    },

    computed: {},

})

app.mount("#app")