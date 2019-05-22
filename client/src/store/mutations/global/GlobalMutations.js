export default {
    displayDialog(state, data) {
        console.log('mutation: displayDialog ' + data);
        state.displayDialog = data;
    }
}