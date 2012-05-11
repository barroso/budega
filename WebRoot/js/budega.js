var indiceGasto = 0;
var budega = {
	init : function() {
		this.accordion.init();
		this.boxDialog.init();
		this.alertMessage.init();
		this.mascaras.init();
		this.paginacao.init();
		this.maxlengthTextArea.init();
	},
	addVideo : function() {
		var $formNewVideo = $("#formNewVideo"),
			url = $("#url").val(), $urlVideo = $("#videoUrl"),
			urlQueryString = url.replace(/^[^v]+v.(.{11}).*/, "$1"); 
		$urlVideo.val(urlQueryString);
		return true;
	},
	addGasto : function(gastoId, gastoText, valor) {
		var table = $("#tabelaDeGastos tbody"),
			html = "<tr id='"+ Number(gastoId) +"'><input type='hidden' name='grupoDespesa.despesas[" + indiceGasto + "].gasto.id' value='"+ Number(gastoId) +"' />" +
				"<td class='gastoDescricao'><img align='absMiddle' onclick='excluirDespesa("+ Number(gastoId) +");' title='Excluir' src='/budega/imagens/icones/excluir.png'/> "+ gastoText +"</td><td>" +
				"<input class='span1-75' type='text' name='grupoDespesa.despesas[" + indiceGasto + "].valor' maxlength='8' value='"+ Number(valor) +"' />" +
				"</td></tr>";
		
		if( !(gastoId) ) {
			budega.boxDialog.show({ 
				message : "Escolha um gasto.",
				width : 220,
				height : 120
			});
			return;
		}
		
		var tableFindHidden = (table.find("input:hidden[value='"+ gastoId +"']"));
		if( tableFindHidden && tableFindHidden.length > 0 ) {
			budega.boxDialog.show({
				message : "Gasto já adicionado.",
				width : 220,
				height : 120
			});
		} else {			
			$("#gastoId").val("");
			$("#valor").val("");
			indiceGasto++;
			table.append(html);
		}
		$("#gastoId").focus();
	},
	pesquisarUsuario : function() {
		var $usuarioSearch = $("#usuarioSearch"),
			unidade = $usuarioSearch.find("select").val(),
			filtro = $usuarioSearch.find(":radio:checked").val(),
			search = $usuarioSearch.find(":text").val() || "todos",
			url = "/budega/usuarios/filtro/"+ filtro +"/unidade/"+ unidade +"/"+ search;
		$("#listUsuarios").load(url);
	},
	verUsuario : function( id ) {
		$("#usuarioShow").html("");
		var url = "/budega/usuarios/"+ id +"/show";
			sHtml = $("#usuarioShow").load(url);
		this.boxDialog.show({
			title 	: "Informações do usuário",
			message : sHtml,
			width 	: 600,
			height 	: 400,
			buttons : { Fechar : function() { $("#dialog").dialog("close"); } }
		});
	},
	verficarDisponibilidade : function() {
		$(".alert-message").remove();
		$usuarioLogin = $("#usuarioLogin");
		if( $usuarioLogin.val() ) {
			$.get("/budega/usuarios/verificardisponibilidade/"+ $usuarioLogin.val())
			.success(function( isLoginExiste ) {
				if( isLoginExiste.boolean )
					$usuarioLogin.after("<span class='alert-message block-message error'>Login indisponível.</span>");
				else
					$usuarioLogin.after("<span class='alert-message block-message success'>Login disponível.</span>");
			})
			.error(function() {
				budega.boxDialog.show({ message : "Erro ao verificar a disponibilidade do login."	});
			});
		} else {
			$usuarioLogin.after("<span class='alert-message block-message info'>Digite o login para ser pesquisado.</span>");
		}
	},
	pesquisarReservasByUsuario : function( usuarioId ) {
		var url = "/budega/reservas/usuarios/"+ usuarioId;
		$("#listReservas").load(url);
	},
	blocosIndex : function() {
		$("#sortable").sortable({ handle : ".portlet-header" });
		$( ".portlet" ).addClass( "ui-widget ui-widget-content ui-helper-clearfix ui-corner-all" )
			.find(".portlet-header").addClass("ui-widget-header ui-corner-all")
			.prepend("<span class='ui-icon ui-icon-minusthick'></span>")
			.end().find( ".portlet-content" );
		$( ".portlet-header .ui-icon" ).click(function() {
			$( this ).toggleClass( "ui-icon-minusthick" ).toggleClass( "ui-icon-plusthick" );
			$( this ).parents( ".portlet:first" ).find( ".portlet-content" ).toggle();
		});
	},
	loadConteudo : function() {
		var url = "/budega/";
		$(".bloco").each(function() {
			$( this ).load(url + $( this ).attr("blocoid"));
		});
	},
	loadVideos : function() {
		var url = "/budega/vds";
		$(".videos").load(url);
	},
	loadAniversariantes : function() {
		var url = "/budega/aniversariantes";
		$(".aniversariantes").load(url);
	},
	scrollAniversariantes : function() {
		var date = new Date(), aniversariante,
			topoDaTabela = $(".aniversariantes").offset().top, topoDaTrDoAniversarianteDeHoje,
			dia = ( date.getDate() < 9 ) ? date.getDate() : date.getDate();
			$(".aniversariantes tr").each(function() {
				if( $(this).attr("dia") && $(this).attr("dia") >= dia ) {
					aniversariante = $(this);
					return;
				}
			});
			topoDaTrDoAniversarianteDeHoje = aniversariante.offset().top;
		$(".aniversariantes").scrollTop( topoDaTrDoAniversarianteDeHoje - topoDaTabela );
	},
	loadReservas : function() {
		var url = "/budega/reservas";
		$("#listReservas").load(url);
	},
	filtroReservas : function() {
		var recurso = $("#reservaRecursoId").val(),
			data = $("#dataFiltro").val(),
			url = "/budega/reservas/recursos/"+ recurso;
			if( data ) {
				var dataTime = new Date( this.formatDataEn(data) );
				url += "/data/"+ dataTime.getTime();
			}
		$("#listReservas").load(url);
	},
	gravarReserva : function() {
		if( $("#formNewReserva").valid() ) {
			var isValidHoraReservaInicio = this.validateTime($("#horaReservaInicio").val()),
				isValidHoraReservaFim = this.validateTime($("#horaReservaFim").val()),
				isDataInicioMenorQueDataFim = this.formataDatasReservas(); 
			if( isValidHoraReservaInicio && isValidHoraReservaFim && isDataInicioMenorQueDataFim ) $("#formNewReserva").submit();
			else {
				this.boxDialog.show({
					message : "Houve algum problema com as datas especificadas, verifique e tente gravar novamente."
				});
			}
		}
	},
	validateDate : function( data ) {
		var valid = false,
			regex = new RegExp("^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/((19|20)[0-9]{2})$"),
			matches = regex.exec(data);
		if( matches != null ) {
			var day = parseInt(matches[1], 10),
				month = (parseInt(matches[2], 10)) - 1,
				year = parseInt(matches[3], 10),
				date = new Date(year, month, day, 0, 0, 0);
			valid = ( date.getDate() == day ) && ( date.getMonth() == month ) && ( date.getFullYear() == year );
		}
		return valid;
	},
	validateTime : function( hora ) {
	    var valid = false,
	    	regex = new RegExp("^([0-9]{2}):([0-9]{2})$"),
	    	matches = regex.exec(hora);
	    if ( matches != null ) {
	        var hour = parseInt(matches[1], 10),
	        	minute = parseInt(matches[2], 10),
	        	date = new Date(0, 0, 0, hour, minute, 0);
	        valid = ( date.getHours() == hour ) && ( date.getMinutes() == minute );
	    }
	    return valid;
	},
	formataDatasReservas : function() {
		var valorDataInicio = $("#dataReservaInicio").val() +' '+ $("#horaReservaInicio").val(),
			valorDataFim = $("#dataReservaFim").val() +' '+ $("#horaReservaFim").val(),
			dataInicio = new Date( this.formatDataEn( valorDataInicio ) ),
			dataFim = new Date( this.formatDataEn( valorDataFim ) );
		if( dataInicio.getTime() < dataFim.getTime() ) {
			$("#reservaInicio").val( dataInicio.getTime() );
			$("#reservaFim").val( dataFim.getTime() );
			return true;
		}
		return false;
	},
	impedirQueDateFuturaSejaMenorQueDataPrevia : function ( datas, id ) {
		var dates = $( datas ).datepicker({
			showOtherMonths: true, selectOtherMonths: true,
			onSelect : function( selectedDate ) {
				var option = this.id == id ? "minDate" : "maxDate",
					instance = $( this ).data( "datepicker" ),
					date = $.datepicker.parseDate(instance.settings.dateFormat ||
						$.datepicker._defaults.dateFormat,
						selectedDate, instance.settings );
				dates.not( this ).datepicker( "option", option, date );
			}
		});
	},
	mascaras : {
		init : function() {
			_$datas = $(".dateForm");
			_$telefone = $(".telefone");
			_$placa = $(".placa");
			_$hora = $(".horaForm");
			_$cep = $(".cep");
			_$datas.mask("99/99/9999");
			_$telefone.mask("(99) 9999-9999");
			_$placa.mask("aaa-9999");
			_$hora.mask("99:99");
			_$cep.mask("99.999-999");
		}
	},
	formatDataEn : function ( data ){
		return data.replace(/^(\d{2})\/(\d{2})\/(\d{4})/, "$3/$2/$1");
	},
	formatDataToEdit : {
		formatDate : function( data ) {
			//Slice pegando apenas a data.
			return data.slice(0,10).replace(/^(\d{4})\-(\d{2})\-(\d{2})/, "$3/$2/$1");
		},
		formatHora : function( data ) {
			//Slice pegando apenas a hora.
			return data.slice(11,16);
		}
	},
	validForm : function(form) {
		var $form = $(form), rules = {}, messages = {}, $elements, 
			itensObrigatoriosList = [], itensEmailList = [];
		$elements = $form.find("input[type != hidden], select, textarea");
		$elements.each(function() {
			if( ( $(this).attr("class") ) && ( $(this).attr("class").match("required") ) ) {
				itensObrigatoriosList.push( $(this).attr("name") );
				$(this).closest(".clearfix").find("label").append("<strong>*</strong>");
			} else if( ( $(this).attr("class") ) && ( $(this).attr("class").match("email") ) ) {
				itensEmailList.push( $(this).attr("name") );
			}
		});
		itensObrigatoriosList.forEach( function ( elemento ) {
			rules[elemento] = { required : true };
			messages[elemento] = { required : "" };
		});
		itensEmailList.forEach( function ( elemento ) {
			rules[elemento] = { email : true };
			messages[elemento] = { email : "Digite um e-mail válido." };
		});
		$form.validate({
			rules : rules,
			messages : messages,
			highlight : function( element ) {
				$( element ).closest(".clearfix").addClass("error");
			},
			unhighlight: function( element ) {
				$( element ).closest(".clearfix").removeClass("error");
			}
		});
	},
	formDelete : function( elementDelete ) {
		$form = $("#formDelete"),
			novaActionDelete = $form.attr("action").replace("/?", "/"+ elementDelete);
		$form.attr("action", novaActionDelete);
		this.boxDialog.show({
			title : "Confirmação",
			message : "Tem certeza que deseja exluir?",
			width : 250,
			height : 150,
			buttons : {
				"Sim" : function (){ $form.submit(); }, 
				"Não" : function (){ budega.boxDialog.close(); }
			}
		});
	},
	formStatusComentario : function( id, status ) {
		$form = $("#formStatusComentario"),
			novaActionComentario = $form.attr("action").replace("{id}", id).replace("{status}", status);
		$form.attr("action", novaActionComentario);
		$form.submit();
	},
	editToPassword : {
		init : function() {
			this._validForm();
		},
		_validForm : function() {
			$("#formUpdateSenha").validate({
				rules : {
					'senhaAtual' : {
						required : true,
						minlength : 3
					},
					'novaSenha' : {
						required : true,
						minlength : 3
					},
					'confirmar_senha' : {
						required : true,
						minlength : 3,
						equalTo : "#novaSenha"
					}
				},
				messages : {
					'senhaAtual' : {
						required : "Digite a senha atual.",
						minlength : "Tamanho mínimo da senha é 3."
					},
					'novaSenha' : {
						required : "Digite a nova senha.",
						minlength : "Tamanho mínimo da senha é 3."
					},
					'confirmar_senha' : {
						required : "Digite a confirmação da senha.",
						minlength : "Tamanho mínimo da senha é 3",
						equalTo : "Senhas não conferem."
					}
				}
			});
		}
	},
	paginacao : {
		_$ : null,
		init : function() {
			this._$ = $('#page_container');
			this._$.pajinate({
				items_per_page : 6,
				nav_label_first : '<<',
				nav_label_last : '>>',
				nav_label_prev : '<',
				nav_label_next : '>',
				abort_on_small_lists: true
			});
		}
	},
	alertMessage : {
		_$close : null,
		init : function () {
			this._$close = $(".close");
			this._$close.click(function() {
				$(".alert-message").remove();
			});
		}
	},
	accordion : {
		_$ : null,
		init : function (){
			this._$ = $(".accordion");
			this._$.accordion({autoHeight: false});
		}
	},
	maxlengthTextArea : {
		init : function() {
			$("textarea[maxlength]").keypress(function(event){
				var key = event.which;
				//todas as teclas incluindo enter
				if(key >= 33 || key == 13) {
					var maxLength = $(this).attr("maxlength");
					var length = this.value.length;
					if(length >= maxLength) {
						event.preventDefault();
					}
				}
			});
		}
	},
	boxDialog : {
		_$ : null,
		init : function (){
			this._$ = $("#dialog");
		},
		close : function (){
			this._$.dialog("close").dialog("destroy").html("");
		},
		show : function ( data ){
			this._$.html( data.message )
				.dialog({
				resizable 	: false,
				modal 		: (data.modal === undefined) || data.modal,
				message 	: data.message,
				title 		: data.title || "Informação do sistema",
				width 		: data.width || 350,
				height 		: data.height || 200,
				buttons 	: data.buttons || {
					Fechar	: function (){ budega.boxDialog.close(); }
				}
			});
		}
	}
};
$(function() {
	budega.init();
});