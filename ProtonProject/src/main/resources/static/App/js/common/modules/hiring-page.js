var hiredModule = {
  editAbout : function(){
    var title = "Edit About Text";
    var message = `<textarea class = "about-edit-textarea"> </textarea>`;

    bootbox.confirm({
      title : title,
      message : message,

      buttons : {
        cancel : {
          label : "Cancel",
          className : "btn-danger"
        },
        confirm : {
          label : "Confirm",
          className : "btn-success"
        }
      },

      callback : function(result){
        if(result){
          $.growl({
            title : "About updated",
            message : "Your about was be updated",
            size : 'large'
          });
          $('#about-text')[0].value = $('.about-edit-textarea').val();

          // TODO: ajax for update about
        }
      }
    })

    $('.about-edit-textarea')[0].value = $('#about-text').val();
  },

  drawPage : function(){
    //Fill about
    $('#about-text')[0].innerHTML = about;

    //Fill skills
    console.log(skills.length);
    if(skills.length > 0){
      for(var i in skills){
        var valUser = skills[i].point / (skills[i].maxScale - skills[i].minScale) * 100;
        var clazz = "";

        if(valUser == 0){
          clazz = "skill-0";
        }else if (valUser <= 25){
          clazz = "skill-25";
        }else if (valUser <= 50) {
          clazz = "skill-50";
        }else if (valUser <= 75) {
          clazz = "skill-75";
        }else if (valUser <= 90) {
          clazz = "skill-90";
        }else if(valUser <= 100){
          clazz = "skill-100";
        }

        var tt = `
          <div class = "skill-container">
            <button class = "skill-container-content skill-name"> ` + skills[i].name + ` </button>
            <button class = "skill-container-content skill-point ` + clazz + `"> ` + skills[i].point + ` <span>/` + skills[i].maxScale + `</span></button>
          </div>
        `;

        $('.skill-points-content').append(tt);

      }
    }

    /*if(qualities.length > 0){
      for(var i in qualities){
        $('.qualities-content').append(`<button class = "qualities-item">
            <span class = "qualities-name">` + qualities[i] + `</span>
            <i class = "fas fa-times-circle"></i>
        </button>`);
      }
    }*/

    if(userPhone != null){
      $('#user-phone').append('<p><i class = "fas fa-mobile-alt"></i>' + userPhone + '</p> ')
    }

    if(contactSocial){
      for(var i in contactSocial){
        $('.contact-social').append('<a href = "' + contactSocial[i].socialHref + '" class = "btn contact-social-icon"><i class = "' + contactSocial[i].socialIcon + '"></i></a>')
      }
    }

  },

  addContacts : function(){
	
  }
}
