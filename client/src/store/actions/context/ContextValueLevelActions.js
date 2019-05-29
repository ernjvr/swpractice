import api from "../../../services/api";
import constants from "../../../common/constants";

export default {
    addContextValueLevel({ commit }, data) {
        return new Promise((resolve, reject) => {
            api.post(constants.context_value_level_url, data)
                .then(response => {
                    let value = response.data;
                    console.log(constants.context_value_level_url + ' post success: ' + value);
                    commit('addContextValueLevel', value);
                    resolve(value);
                }, error => {
                    console.log(constants.context_value_level_url + ' post error:' + error.response.data);
                    reject(error.response.data);
                });
        });
    },
    setSelectedContextValueLevel({ commit }, data) {
        console.log('actions: setSelectedContextValueLevel');
        console.log(data);
        commit('setSelectedContextValueLevel', data);
    },
    editContextValueLevel({ commit }, data) {
        return new Promise((resolve, reject) => {
            api.patch(data.href, data.value)
                .then(response => {
                    let value = response.data;
                    console.log(data.href + ' put success: ' + value);
                    commit('editContextValueLevel', value);
                    resolve(value);
                }, error => {
                    console.log(data.href + ' put error:' + error.response.data);
                    reject(error.response.data);
                });
        });
    },
    getAllContextValueLevels({ commit }) {
        return new Promise((resolve, reject) => {
            api.get(constants.context_value_level_url)
                .then(response => {
                    let contextValues = response.data._embedded.contextValueLevels;
                    console.log(constants.context_value_level_url + ' get success' + contextValues);
                    commit('addContextValueLevels', contextValues);
                    resolve(contextValues);
                }, error => {
                    console.log(constants.context_value_level_url + ' get error: ' + error);
                    reject(error);
                });
        });
    }
}