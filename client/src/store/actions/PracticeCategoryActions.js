import api from "../../services/api";

export default {
    addPracticeCategory({ commit }, data) {
        return new Promise((resolve, reject) => {
            api.post('practice-category', data)
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
    editPracticeCategory({ commit }, data) {
        return new Promise((resolve, reject) => {
            api.put(data.href, data.category)
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
            api.get('/practice-category')
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