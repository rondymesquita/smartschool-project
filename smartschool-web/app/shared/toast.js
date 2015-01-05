angular.module('SmartschoolApp').factory('toast', function () {

    options = {
        "closeButton": true,
        "debug": false,
        "progressBar": true,
        "positionClass": "toast-top-right",
        "onclick": null,
        "showDuration": "300",
        "hideDuration": "1000",
        "timeOut": "0",
        "extendedTimeOut": "0",
        "showEasing": "swing",
        "hideEasing": "linear",
        "showMethod": "fadeIn",
        "hideMethod": "fadeOut",
        "iconClasses": {
            error: 'toast-error',
            info: 'toast-info',
            success: 'toast-success',
            warning: 'toast-warning'
        },
    };

    return {
        success: function (text) {
            toastr.options = options;
            toastr.success(text,"Success");
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
