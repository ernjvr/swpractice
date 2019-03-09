import api from "../../services/api";

export default {
    addPracticeCategory({ commit }, category) {
        commit('addPracticeCategory', category);
    },
    editPracticeCategory({ commit }, category) {
        commit('editPracticeCategory', category);
    },
    getAllPracticeCategories({ commit }) {
        console.log('store: getAllPracticeCategories');
        return new Promise((resolve, reject) => {
            console.log('promise');
            api.get('/practice-category')
                .then(response => {
                    commit('addPracticeCategories', response.data._embedded.practiceCategories);
                    resolve(response.data._embedded.practiceCategories);
                    console.log(response.data._embedded.practiceCategories);
                }, error => {
                    console.log('error: ' + error);
                    reject(error);
                });
        });
    }
}