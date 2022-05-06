/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */



function edit(id, email, password) {
Swal.fire({
  title: 'Editar Registro Nro '+id,
  input: '',
  inputAttributes: {
    autocapitalize: 'off'
  },
  html: '<input id="email" type="email" value="'+email+'" placeholder="'+ email +'"  class="swal2-input"><br><input  class="swal2-input" value="'+password+'" id="password" type="password" placeholder="'+ password +'">',
  showCancelButton: true,
  confirmButtonText: 'Editar',
  showLoaderOnConfirm: true,
  preConfirm: function(result) {
    var ed_email = $('#email').val();
    var ed_password = $('#password').val();  
    console.log(ed_password);
    var request = $.ajax({
  url: "/ProyectoBD/AdminUser",
  type: "POST",
  data: {
                           ed_email : ed_email,
                           ed_password : ed_password,
                           action : 'edit'
                            },
  dataType: "html"
});
                     
                       
                    
                
  },
  allowOutsideClick: () => !Swal.isLoading()
});
  }


function del(id) {
    Swal.fire({
        title: 'Deseas Eliminar el registro numero ' + id + '?',
        text: "",
        icon: 'error',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Eliminar!'
    }).then((result) => {

        if (result.value) {
            Swal.fire(
                    
                    'Registro eliminado!',
                    'success',
                    
                    )
                  window.location.href = "AdminUser?action=delete&id=" + id;
        }

    })
}