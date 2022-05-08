/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

function sleep (time) {
  return new Promise((resolve) => setTimeout(resolve, time));
}

function edit(id, email, password) {
    Swal.fire({
    title: '-Editar Usuario-',
    icon: 'warning',
    confirmButtonColor: '#FACEA8',
    html: '<input style="display:none" id="ed_id" type="text" value="'+id+'"><input id="email" type="email" value="'+email+'" placeholder="'+ email +'"  class="swal2-input"><br><input  class="swal2-input" value="'+password+'" id="password" type="password" placeholder="'+ password +'">',
    inputAttributes: {
      autocapitalize: 'off'
    },
    showCancelButton: true,
    confirmButtonText: 'Editar',
    showLoaderOnConfirm: true,
    preConfirm: (users) => {
      var ed_email = $('#email').val();
      var ed_password = $('#password').val(); 
      var ed_id = $('#ed_id').val(); 
      //Llamamos por Fetch al AdminUser
      return fetch("/ProyectoBD/AdminUser", {
      method: "POST",
      headers: {
        "Content-Type": "application/text",
        'ed_email': ed_email,
        'ed_password': ed_password,
        'ed_id': ed_id,
        'action': 'edit'
      }
      }).then(response => {
          if (!response.ok) {
            throw new Error(response.statusText);
          }
          return response.json();
        })
        .catch(error => {
          Swal.showValidationMessage(
            `Request failed: ${error}`
          );
        });
    },
    allowOutsideClick: () => !Swal.isLoading()
    }).then((result) => {
        if (result.isConfirmed) {
            myjson = JSON.parse(JSON.stringify(result.value.data));
            if (myjson[0]['status'] === '500'){
            icono="error";
            }else{
                icono = "success";
            }
            Swal.fire({
                icon: icono,
                title: myjson[0]['msg']
            });
        }
        sleep(1200).then(() => {
            console.log('Despues del sleep');
            location.reload();
        });
    });
}
function del(id) {
    Swal.fire({
    title: 'Deseas eliminar el registro ' + id + '?',
    icon: 'error',
    html: '<input style="display:none" id="del_id" type="text" value="'+id+'">',
    inputAttributes: {
      autocapitalize: 'off'
    },
    showCancelButton: true,
    confirmButtonColor: '#F27474',
    confirmButtonText: 'Eliminar',
    showLoaderOnConfirm: true,
    preConfirm: (users) => {
      var del_id = $('#del_id').val(); 
      //Llamamos por Fetch al AdminUser
      return fetch("/ProyectoBD/AdminUser", {
      method: "POST",
      headers: {
        "Content-Type": "application/text",
        'del_id': del_id,
        'action': 'delete'
      }
      }).then(response => {
          if (!response.ok) {
            throw new Error(response.statusText);
          }
          return response.json();
        })
        .catch(error => {
          Swal.showValidationMessage(
            `Request failed: ${error}`
          );
        });
    },
    allowOutsideClick: () => !Swal.isLoading()
    }).then((result) => {
        if (result.isConfirmed) {
            myjson = JSON.parse(JSON.stringify(result.value.data));
            if (myjson[0]['status'] === '500'){
            icono="error";
            }else{
                icono = "success";
            }
            Swal.fire({
              icon: icono,
              title: myjson[0]['msg']
            });
        }
        sleep(1200).then(() => {
            console.log('Despues del sleep');
            location.reload();
        });
    });
    
}