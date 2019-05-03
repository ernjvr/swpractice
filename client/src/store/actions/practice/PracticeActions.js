import api from "../../../services/api";
import constants from "../../../common/constants";

export default {
    addPractice({ commit }, data) {
        return new Promise((resolve, reject) => {
            api.post(constants.practice_url, data)
                .then(response => {
                    let practice = response.data;
                    console.log('practice post success: ' + practice);
                    commit('addPractice', practice);
                    resolve(practice);
                }, error => {
                    console.log('practice post error:' + error.response.data);
                    reject(error.response.data);
                });
        });
    },
    setSelectedPractice({ commit }, data) {
        console.log('actions: setSelectedPractice');
        console.log(data);
        commit('setSelectedPractice', data);
    },
    editPractice({ commit }, data) {
        console.log('editPractice data parameter');
        console.log(data);
        return new Promise((resolve, reject) => {
            api.patch(data.href, data.practice)
                .then(response => {
                    let practice = response.data;
                    console.log('practice patch success: ' + practice);
                    resolve(practice);
                }, error => {
                    console.log('practice patch error:' + error.response.data);
                    reject(error.response.data);
                });
        });
    },
    getAllPractices({ commit }) {
        let practices = [];
        api.get(constants.practice_url)
            .then(response => {
                practices = response.data._embedded.practices;
                console.log('practice get success' + practices);
                commit('addPractices', practices);
            }, error => {
                console.log('practice get error: ' + error);
            });
        return practices;
    }
}