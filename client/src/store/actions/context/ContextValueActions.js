import api from "../../../services/api";
import constants from "../../../common/constants";

export default {
    addContextValue({ commit }, data) {
        return new Promise((resolve, reject) => {
            api.post(constants.context_value_url, data)
                .then(response => {
                    let value = response.data;
                    console.log(constants.context_value_url + ' post success: ' + value);
                    commit('addContextValue', value);
                    resolve(value);
                }, error => {
                    console.log(constants.context_value_url + ' post error:' + error.response.data);
                    reject(error.response.data);
                });
        });
    },
    editContextValue({ commit }, data) {
        return new Promise((resolve, reject) => {
            api.put(data.href, data.value)
                .then(response => {
                    let value = response.data;
                    console.log(data.href + ' put success: ' + value);
                    commit('editContextValue', value);
                    resolve(value);
                }, error => {
                    console.log(data.href + ' put error:' + error.response.data);
                    reject(error.response.data);
                });
        });
    },
    getAllContextValues({ commit }) {
        return new Promise((resolve, reject) => {
            api.get(constants.context_value_url)
                .then(response => {
                    let contextValues = response.data._embedded.contextValues;
                    console.log(constants.context_value_url + ' get success' + contextValues);
                    commit('addContextValues', contextValues);
                    resolve(contextValues);
                }, error => {
                    console.log(constants.context_value_url + ' get error: ' + error);
                    reject(error);
                });
        });
    }
}