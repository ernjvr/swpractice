import api from "../../../services/api";
import constants from "../../../common/constants";

export default {
    addReference({ commit }, data) {
        return new Promise((resolve, reject) => {
            api.post(constants.reference_url, data)
                .then(response => {
                    let reference = response.data;
                    console.log(constants.reference_url + ' post success: ' + reference);
                    commit('addReference', reference);
                    resolve(reference);
                }, error => {
                    console.log(constants.reference_url + ' post error:' + error.response.data);
                    reject(error.response.data);
                });
        });
    },
    setSelectedReference({ commit }, data) {
        console.log('actions: setSelectedReference');
        console.log(data);
        commit('setSelectedReference', data);
    },
    editReference({ commit }, data) {
        return new Promise((resolve, reject) => {
            api.patch(data.href, data.reference)
                .then(response => {
                    let reference = response.data;
                    console.log(constants.reference_url + ' patch success: ' + reference);
                    commit('editReference', reference);
                    resolve(reference);
                }, error => {
                    console.log(constants.reference_url + ' patch error:' + error.response.data);
                    reject(error.response.data);
                });
        });
    },
    getAllReferences({ commit }) {
        let references = [];
        api.get(constants.reference_url)
            .then(response => {
                references = response.data._embedded.references;
                console.log(constants.reference_url + ' get success' + references);
                commit('addReferences', references);
            }, error => {
                console.log(constants.reference_url + ' get error: ' + error);
            });
        return references;
    }
}