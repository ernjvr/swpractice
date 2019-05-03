import api from "../../../services/api";
import constants from "../../../common/constants";

export default {
    addReferenceType({ commit }, data) {
        return new Promise((resolve, reject) => {
            api.post(constants.reference_type_url, data)
                .then(response => {
                    let type = response.data;
                    console.log(constants.reference_type_url + ' post success: ' + type);
                    commit('addReferenceType', type);
                    resolve(type);
                }, error => {
                    console.log(constants.reference_type_url + ' post error:' + error.response.data);
                    reject(error.response.data);
                });
        });
    },
    editReferenceType({ commit }, data) {
        return new Promise((resolve, reject) => {
            api.put(data.href, data.type)
                .then(response => {
                    let type = response.data;
                    console.log(data.href + ' put success: ' + type);
                    commit('editReferenceType', type);
                    resolve(type);
                }, error => {
                    console.log(data.href + ' put error:' + error.response.data);
                    reject(error.response.data);
                });
        });
    },
    getAllReferenceTypes({ commit }) {
        return new Promise((resolve, reject) => {
            api.get(constants.reference_type_url)
                .then(response => {
                    let referenceTypes = response.data._embedded.referenceTypes;
                    console.log(constants.reference_type_url + ' get success' + referenceTypes);
                    commit('addReferenceTypes', referenceTypes);
                    resolve(referenceTypes);
                }, error => {
                    console.log(constants.reference_type_url + ' get error: ' + error);
                    reject(error);
                });
        });
    }
}