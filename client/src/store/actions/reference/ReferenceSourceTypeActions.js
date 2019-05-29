import api from "../../../services/api";
import constants from "../../../common/constants";

export default {
    addReferenceSourceType({ commit }, data) {
        return new Promise((resolve, reject) => {
            api.post(constants.reference_source_type_url, data)
                .then(response => {
                    let type = response.data;
                    console.log(constants.reference_source_type_url + ' post success: ' + type);
                    commit('addReferenceSourceType', type);
                    resolve(type);
                }, error => {
                    console.log(constants.reference_source_type_url + ' post error:' + error.response.data);
                    reject(error.response.data);
                });
        });
    },
    setSelectedReferenceSourceType({ commit }, data) {
        console.log('actions: setSelectedReferenceSourceType');
        console.log(data);
        commit('setSelectedReferenceSourceType', data);
    },
    editReferenceSourceType({ commit }, data) {
        return new Promise((resolve, reject) => {
            api.patch(data.href, data.type)
                .then(response => {
                    let type = response.data;
                    console.log(data.href + ' put success: ' + type);
                    commit('editReferenceSourceType', type);
                    resolve(type);
                }, error => {
                    console.log(data.href + ' put error:' + error.response.data);
                    reject(error.response.data);
                });
        });
    },
    getAllReferenceSourceTypes({ commit }) {
        return new Promise((resolve, reject) => {
            api.get(constants.reference_source_type_url)
                .then(response => {
                    let referenceTypes = response.data._embedded.referenceSourceTypes;
                    console.log(constants.reference_source_type_url + ' get success' + referenceTypes);
                    commit('addReferenceSourceTypes', referenceTypes);
                    resolve(referenceTypes);
                }, error => {
                    console.log(constants.reference_source_type_url + ' get error: ' + error);
                    reject(error);
                });
        });
    }
}