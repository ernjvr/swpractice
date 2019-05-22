import api from "../../../services/api";
import constants from "../../../common/constants";

export default {
    addPracticeCategory({ commit }, data) {
        return new Promise((resolve, reject) => {
            api.post(constants.practice_category_url, data)
                .then(response => {
                    let category = response.data;
                    console.log('practice-category post success: ' + category);
                    commit('addPracticeCategory', category);
                    resolve(category);
                }, error => {
                    console.log('practice-category post error:' + error.response.data);
                    reject(error.response.data);
                });
        });
    },
    setSelectedPracticeCategory({ commit }, data) {
        console.log('actions: setSelectedPracticeCategory');
        console.log(data);
        commit('setSelectedPracticeCategory', data);
    },
    editPracticeCategory({ commit }, data) {
        return new Promise((resolve, reject) => {
            api.patch(data.href, data.category)
                .then(response => {
                    let category = response.data;
                    console.log('practice-category put success: ' + category);
                    commit('editPracticeCategory', category);
                    resolve(category);
                }, error => {
                    console.log('practice-category put error:' + error.response.data);
                    reject(error.response.data);
                });
        });
    },
    getAllPracticeCategories({ commit }) {
        return new Promise((resolve, reject) => {
            api.get(constants.practice_category_url)
                .then(response => {
                    let practiceCategories = response.data._embedded.practiceCategories;
                    console.log('practice-category get success' + practiceCategories);
                    commit('addPracticeCategories', practiceCategories);
                    resolve(practiceCategories);
                }, error => {
                    console.log('practice-category get error: ' + error);
                    reject(error);
                });
        });
    }
}