function handleSubmit(args, dialog) {
    var jqDialog = jQuery('#' + dialog);
    if (args.validationFailed) {
        jqDialog.effect('shake', {times: 3}, 100);
    } else {
        PF(dialog).hide();
    }
}

function exportChart() {
    //export image
    $('#output').empty().append(PF('chart').exportAsImage());
 
    //show the dialog
    PF('dlg').show();
}
