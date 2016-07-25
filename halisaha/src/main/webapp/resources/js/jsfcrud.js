function handleSubmit(args, dialog) {
    var jqDialog = jQuery('#' + dialog);
    if (args.validationFailed) {
        jqDialog.effect('shake', {times: 3}, 100);
    } else {
        PF(dialog).hide();
    }
}

function deselectRows(xhr, status, args) {
    if (args.unselecttest % 2 == 1) {
        stest.unselectAllRows();
    }
}
