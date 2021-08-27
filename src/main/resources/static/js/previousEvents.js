const app = Vue.createApp({

    data() {
        return {
            eventosPrevios: [],
            description: '',
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
                .then(alert("Comentario enviado"))
                .then(location.reload())
                .catch(error)
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
        logOut() {
            axios.post('/api/logout').then(response => window.location.href = "/web/index.html")
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

/* console.log(document.cookie.match(/JSESSIONID=[^;]+/))
var session_id = /SESS\w*ID=([^;]+)/i.test(document.cookie) ? RegExp.$1 : false;
console.log(session_id); */
/* navbar */
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