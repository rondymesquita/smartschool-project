angular.module('SmartschoolApp').factory('modal', function () {

    return {
        success: function (text) {
            toastr.options = options;
            toastr.success(text,"Successo");
        },
        error: function (text) {
            toastr.options = options;
            toastr.error(text, "Erro");
        },
        info: function (text) {
            toastr.options = options;
            toastr.info(text, "Informação");
        },
        warning: function (text) {
            toastr.options = options;
            toastr.warning(text, "Aviso");
        }
    };
});
