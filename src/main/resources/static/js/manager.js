const app = Vue.createApp({
    
    data() {
        return {
            manageType:"",
            merchandisingType:"",
            clients:[],
            products:[],
            comments:[],
            sites:[],
            events:[],
            productType:"",
            productName:"",
            stockProduct:"",
            priceProduct:"",  
            tallesProduct:[],
            imageProduct:"",
            descriptionProduct:"", 
            productSelected:"", 
            editStock:"",
            editPrice:"",
            eventType:"",
            nameEvent:"",
            bandsEvent:"",
            priceEvent:"",
            dateEvent:"",
            siteEvent:"",
            eventSelected:"", 
        }
        },

    created() {
        axios.get("/api/merchs")
        .then(res => {
            this.products = res.data
            
        }),
        axios.get("/api/comments")
        .then(res => {
            this.comments = res.data
            
        }),
        axios.get("/api/clients")
        .then(res => {
            this.clients = res.data
            
        }),
        axios.get("/api/events")
        .then(res => {
            this.events = res.data
            console.log(this.events)
        })
        axios.get("/api/sites")
        .then(res => {
            this.sites = res.data
        })
        
    },

    methods: {

        datoName(producto){            
            if (producto!=null){
            prueba=this.products.filter((e=>e.name==producto))              
            return prueba[0]
            }
        },
        datoNameEvent(event){   
          
          if (event!=null){
          prueba=this.events.filter((e=>e.nameEvent==event))         
          return prueba[0]
          }
      },                
        addMerchandising(){
          
            Swal.fire({
                title: '¿Do you want to create the product?',
                text: "",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Yes!'
              }).then((result) => {
                if (result.isConfirmed) {
                    axios.post('/api/merchs/createMerch',

                    "productType="+this.productType+"&"+"productName="+this.productName+"&"+"descriptionProduct="+this.descriptionProduct+"&"+

                    "tallesProduct="+this.tallesProduct+"&"+"stockProduct="+this.stockProduct+"&"+"priceProduct="+this.priceProduct,                                    
                    {headers:{'accept':'application/xml'}})
                    .then(res => Swal.fire(
                   'Successfully created product!',
                    'will be added to the shop.',
                    'success',))
                  .then(response => location.reload())                  
                  .catch(res=> Swal.fire(res.response.data,"Check information","error"))
                  
                  
                }
              })   
        },
        deleteMerchandising(){
            Swal.fire({
                title: '¿Do you want to apply changes?',
                text: "",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Yes!'
              }).then((result) => {
                if (result.isConfirmed) {
                    axios.post('/api/merchs/deleteMerch',
                    "productName="+this.productSelected,                                    
                    {headers:{'accept':'application/xml'}})
                    .then(res => Swal.fire(
                   'Product removed successfully!',
                    'Shop will be updated.',
                    'success',))
                   .then(response => location.reload())                  
                  .catch(res=> Swal.fire(res.response.data,"check the information","error"))
                  
                  
                }
              })  
            }, 

        editMerchandising(){
            Swal.fire({
                title: '¿You want to make changes to the product?',
                text: "",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Yes!'
              }).then((result) => {
                if (result.isConfirmed) {
                    axios.post('/api/merchs/editMerch',
                    "editStock="+this.editStock+"&"+"editPrice="+this.editPrice+"&"+"nameProduct="+this.productSelected,                                    
                    {headers:{'accept':'application/xml'}})
                    .then(res => Swal.fire(
                   'Successfully edited product!',
                    'Shop will be updated.',
                    'success',))
                   .then(response => location.reload())                  
                  .catch(res=> Swal.fire(res.response.data,"check the information","error"))
                  
                  
                }
              })  

        },
        addEvent(){
          Swal.fire({
            title: '¿You want to add a new event?',
            text: "",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Yes!'
          }).then((result) => {
            if (result.isConfirmed) {
                axios.post('/api/event/addEvent',
                "nameEvent="+this.nameEvent+"&"+"bandsEvent="+this.bandsEvent+"&"+"priceEvent="+this.priceEvent+"&"+"dateEvent="+this.dateEvent+"&"+"siteEvent="+this.siteEvent,                                    
                {headers:{'accept':'application/xml'}})
                .then(res => Swal.fire(
               'Event agree',
                '',
                'success',))
                .then(response => location.reload())                
              .catch(res=> Swal.fire(res.response.data,"check the information","error"))                 
              
            }
          })  
        },
        deleteEvent(event){
          Swal.fire({
              title: '¿Do you want to apply changes?',
              text: "",
              icon: 'warning',
              showCancelButton: true,
              confirmButtonColor: '#3085d6',
              cancelButtonColor: '#d33',
              confirmButtonText: 'Yes!'
            }).then((result) => {
              if (result.isConfirmed) {
                  axios.post('/api/event/deleteEvent',
                  "nameEvent="+event.nameEvent,                                    
                  {headers:{'accept':'application/xml'}})
                  .then(res => Swal.fire(
                 'Event updated',
                  '',
                  'success',))
                  .then(response => location.reload())                
                .catch(res=> Swal.fire(res.response.data,"check the information","error"))                 
                
              }
            })  
          }, 
          editEvent(event){
            Swal.fire({
                title: '¿Do you want to apply changes?',
                text: "",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Yes!'
              }).then((result) => {
                if (result.isConfirmed) {
                    axios.post('/api/event/editEvent',
                    "nameEvent="+this.eventSelected+"&"+"dateEvent="+this.dateEvent,                                    
                    {headers:{'accept':'application/xml'}})
                    .then(res => Swal.fire(
                   'Event updated',
                    '',
                    'success',))
                    .then(response => location.reload())                
                  .catch(res=> Swal.fire(res.response.data,"check the information","error"))                 
                  
                }
              })  
            },

        deleteComment(comment){
            Swal.fire({
                title: '¿Do you want to delete the comment?',
                text: "",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Yes!'
              }).then((result) => {
                if (result.isConfirmed) {
                    axios.post('/api/comment/deleteComment',
                    "commentEmail="+comment.email+"&"+"commentUser="+comment.description,                                    
                    {headers:{'accept':'application/xml'}})
                    .then(res => Swal.fire(
                   'Comment deleted!',
                    '',
                    'success',))
                    .then(response => location.reload())                
                  .catch(res=> Swal.fire(res.response.data,"check the information","error"))                 
                  
                }
              })   

        },
        deleteUser(client){
            Swal.fire({
                title: '¿You want to change client status?',
                text: "",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Yes!'
              }).then((result) => {
                if (result.isConfirmed) {
                    axios.post('/api/client/setStatusClient',
                    "email="+client.email,                                    
                    {headers:{'accept':'application/xml'}})
                    .then(res => Swal.fire(
                   'Cliente updated!',
                    '',
                    'success',))
                    .then(response => location.reload())                
                  .catch(res=> Swal.fire(res.response.data,"check the information","error"))                 
                  
                }
              })  

        },
        activeProduct(){
            Swal.fire({
              title: '¿You want to change product status?',
              text: "",
              icon: 'warning',
              showCancelButton: true,
              confirmButtonColor: '#3085d6',
              cancelButtonColor: '#d33',
              confirmButtonText: 'Yes!'
            }).then((result) => {
              if (result.isConfirmed) {
                  axios.post('/api/merchs/statusMerch',
                  "productName="+this.productSelected,                                    
                  {headers:{'accept':'application/xml'}})
                  .then(res => Swal.fire(
                'Product updated!',
                  'Shop will be updated.',
                  'success',))
                .then(response => location.reload())                  
                .catch(res=> Swal.fire(res.response.data,"check the information","error"))                
                
          }
        })  
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
        logout(){
          axios.post('/api/logout')
          .then(response=>window.location.href = "login.html")
        },
    },

    computed: {
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
