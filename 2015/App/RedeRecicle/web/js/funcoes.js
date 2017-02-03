
$(document).ready(function () {
    $("div.descricao").hide();
    $("div.default").slideDown();
    $(".gatilho").click(function () {
        $("div.descricao").slideUp();
        var target = $(this).attr('target');
        $("div." + target).slideDown();
    });
});
$("#postar").hide();
$("#postarOn").click(function () {
    $("#postarOn").hide();
    $("#postar").slideDown();
});
$("#postarOff").click(function () {
    $("#postar").slideUp();
    $("#postarOn").show();
});
function checkSize(max_img_size)
{
    var input = document.getElementById("upload");
    if (input.files && input.files.length == 1)
    {
        if (input.files[0].size > max_img_size)
        {
            alert("O Arquivo é Superior a " + (max_img_size / 1024 / 1024) + "MB. Por favor selecione um arquivo JPG menor que 2MB!");
            return false;
        }
    }

    return true;
}
function validacao_peso() {
   var peso = document.getElementById('peso').value;
   if ( isNaN( peso ) ) { 
      alert('O campo peso, possui caracteres nao aceitos. Por favor informe um número.');
      return false; 
   }
   return true; 
}

//              MASCARAS

$("#numeroTelefone").mask("(99) 9999-9999");
$("#cnpj").mask("99.999.999/9999-99");
$("#cep").mask("99999-999");