import api from "../../../services/api";
import constants from "../../../common/constants";

export default {
    addContextDimension({ commit }, data) {
        return new Promise((resolve, reject) => {
            api.post(constants.context_dimension_url, data)
                .then(response => {
                    let dimension = response.data;
                    console.log(constants.context_dimension_url + ' post success: ' + dimension);
                    commit('addContextDimension', dimension);
                    resolve(dimension);
                }, error => {
                    console.log(constants.context_dimension_url + ' post error:' + error.response.data);
                    reject(error.response.data);
                });
        });
    },
    editContextDimension({ commit }, data) {
        return new Promise((resolve, reject) => {
            api.put(data.href, data.dimension)
                .then(response => {
                    let dimension = response.data;
                    console.log(data.href + ' put success: ' + dimension);
                    commit('editContextDimension', dimension);
                    resolve(dimension);
                }, error => {
                    console.log(data.href + ' put error:' + error.response.data);
                    reject(error.response.data);
                });
        });
    },
    getAllContextDimensions({ commit }) {
        return new Promise((resolve, reject) => {
            api.get(constants.context_dimension_url)
                .then(response => {
                    let contextDimensions = response.data._embedded.contextDimensions;
                    console.log(constants.context_dimension_url + ' get success' + contextDimensions);
                    commit('addContextDimensions', contextDimensions);
                    resolve(contextDimensions);
                }, error => {
                    console.log(constants.context_dimension_url + ' get error: ' + error);
                    reject(error);
                });
        });
    }
}