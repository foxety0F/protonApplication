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

          $.ajax({
            method : "POST",
            url : "/hiring/setAbout",
            data : {
              briefId : briefId,
              about :  $('.about-edit-textarea').val()
            }
          }).done(function(data){
            $.growl.notice({
              title : "About updated",
              message : data,
              size : 'large'
            });
            $('#about-text').val($('.about-edit-textarea').val());
          }).fail(function(data){
            $.growl.error({
              title : "About updated",
              message : data.responseJSON.message,
              size : 'large'
            });
          })

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

    if(userPhone != null){
      $('#user-phone').append('<p><i class = "fas fa-mobile-alt"></i>' + userPhone + '</p> ')
    }

    if(contactSocial){
      for(var i in contactSocial){
        $('.contact-social').append('<a href = "' + contactSocial[i].socialHref + '" class = "btn contact-social-icon" target = "_blank"><i class = "' + contactSocial[i].socialIcon + '"></i></a>')
      }
    }

  },

  addContacts : function(){
    var message = "<table id = 'socialNewEditTable'>";
    message += '<tbody>'
    for(var i = 0; i < contactConfig.length; i++){
      message += '<tr> <td><i class = "' + contactConfig[i].icon + '"> </i></td>' 
               + '<td>' + contactConfig[i].name + '</td>' 
               + '<td> <input id = "contactConfig' + contactConfig[i].id + '" class = "contactSocialInput" > </td>'
               + '<td> <button class ="btn btn-success" data-idforupdate = "contactConfig' + contactConfig[i].id + '" data-socpoint="' + contactConfig[i].id + '" onclick="hiredModule.updateContacts(this)"> <i class="far fa-check-circle"></i></button></td>'
               + '</tr>';
    }
    message += '</tbody></table>';

    bootbox.dialog({
      message : message
    })

    for(var i = 0; i < contactSocial.length; i++){
      $("#contactConfig" + contactSocial[i].contactId).val(contactSocial[i].socialHref);
      $("[data-idforupdate=contactConfig" + contactSocial[i].contactId).attr("data-contactid", contactSocial[i].id)
    }
  },

  updateContacts : function(node){
    var contactId = node.dataset.contactid;
    contactId = contactId == undefined ? null : contactId;
    var socialPoint = node.dataset.socpoint;
    var val = $('#' + node.dataset.idforupdate).val();
    
    $.ajax({
      method : "POST",
      url : "/hiring/setSocialInfo", 
      data : {
        socialId : parseInt(socialPoint),
        value : val,
        briefId : briefId,
        contactId : contactId
      }
    }).done(function(data){
       $.growl.notice({
          title : "Social info updated",
          message : data,
          size : 'large'
        });
    }).fail(function(data){
      $.growl.error({
        title : "Social info not updated",
        message : data.responseJSON.message,
        size : 'large'
      });
    })
  },

  drawExperience : function(){
    for(var i = 0; i < experience.length; i++){
      var message = "";
      var from = experience[i].startDate != null ? (months[new Date(experience[i].startDate).getMonth()] + " " + new Date(experience[i].startDate).getFullYear()) : "";
      var to = experience[i].endDate != null ? (months[new Date(experience[i].endDate).getMonth()] + " " + new Date(experience[i].endDate).getFullYear()) : "";

      message += '<div class="experience-content">';
      message += '<p class="experience-content-title-name"> <input id="title-name-exp-' + experience[i].experienceId + '" onchange = "hiredModule.experienceChangeTitle(this)" value="' + experience[i].titleName + '" class="experience-text-input" data-experienceid="' + experience[i].experienceId + '" /> </p>';
      message += '<p class="experience-content-workplace"> <input id="workplace-exp-' + experience[i].experienceId + '" onchange = "hiredModule.experienceChangeCompany(this)" value="' + experience[i].companyName + '" class="experience-text-input" data-experienceid="' + experience[i].experienceId + '" /> </p>';
      message += '<p class="experience-content-from-to">'
      message += 'From';
      message += '<input class="datepicker experience-datepicker" onchange = "hiredModule.experienceChangeDateFrom(this)" id="date-from-exp-' + experience[i].experienceId + '" value="' + from + '" data-experienceid="' + experience[i].experienceId + '" data-date-format="MM yyyy"></br>';
      if(!experience[i].current){
        message += 'To';
        message += '<input class="datepicker experience-datepicker" onchange = "hiredModule.experienceChangeDateTo(this)" id="date-to-exp-' + experience[i].experienceId + '" data-experienceid="' + experience[i].experienceId + '" data-date-format="MM yyyy" value="' + to + '">';
      }
      message += '</p>';
      message += '<h5 class="experience-description"> Description experience :</h5>';
      message += '<textarea class="experience-content-about" onchange = "hiredModule.experienceChangeDescription(this)" data-experienceid="' + experience[i].experienceId + '">' + experience[i].description + '</textarea>';
      message += '</div>';
      $('#experience-content-div').append(message);
    }
  },

  experienceChangeTitle : function(that){
    var experienceId = that.dataset.experienceid;
    $.ajax({
      url : '/hiring/updateExperienceTitle',
      method : "POST",
      data : {
        briefId : briefId,
        expId : experienceId,
        titleName : that.value
      }
    }).done(function(data){
       $.growl.notice({
          message : data,
          size : 'large'
        });
    }).fail(function(data){
      $.growl.error({
        message : data.responseJSON.message,
        size : 'large'
      });
    })
  },

  experienceChangeCompany : function(that){
    var experienceId = that.dataset.experienceid;
    $.ajax({
      url : '/hiring/updateExperienceCompany',
      method : "POST",
      data : {
        briefId : briefId,
        expId : experienceId,
        companyName : that.value
      }
    }).done(function(data){
       $.growl.notice({
          message : data,
          size : 'large'
        });
    }).fail(function(data){
      $.growl.error({
        message : data.responseJSON.message,
        size : 'large'
      });
    })
  },

  experienceChangeDescription : function(that){
    var experienceId = that.dataset.experienceid;
    $.ajax({
      url : '/hiring/updateExperienceDescription',
      method : "POST",
      data : {
        briefId : briefId,
        expId : experienceId,
        description : that.value
      }
    }).done(function(data){
       $.growl.notice({
          message : data,
          size : 'large'
        });
    }).fail(function(data){
      $.growl.error({
        message : data.responseJSON.message,
        size : 'large'
      });
    })
  },


  experienceChangeDateFrom : function(that){
    var experienceId = that.dataset.experienceid;
    $.ajax({
      url : '/hiring/updateExperienceDateFrom',
      method : "POST",
      data : {
        briefId : briefId,
        expId : experienceId,
        dateFrom : new Date(that.value)
      }
    }).done(function(data){
       $.growl.notice({
          message : data,
          size : 'large'
        });
    }).fail(function(data){
      $.growl.error({
        message : data.responseJSON.message,
        size : 'large'
      });
    })
  },

  experienceChangeDateTo : function(that){
    var experienceId = that.dataset.experienceid;
    $.ajax({
      url : '/hiring/updateExperienceDateTo',
      method : "POST",
      data : {
        briefId : briefId,
        expId : experienceId,
        dateTo : new Date(that.value)
      }
    }).done(function(data){
       $.growl.notice({
          message : data,
          size : 'large'
        });
    }).fail(function(data){
      $.growl.error({
        message : data.responseJSON.message,
        size : 'large'
      });
    })
  }
}
