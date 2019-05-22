export default {
    displayDialog({ commit }, data) {
        console.log('actions: displayDialog ' + data);
        commit('displayDialog', data);
    }
}