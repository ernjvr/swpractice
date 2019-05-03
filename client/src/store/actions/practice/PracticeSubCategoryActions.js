import api from "../../../services/api";
import constants from "../../../common/constants";

export default {
    addPracticeSubCategory({ commit }, data) {
        return new Promise((resolve, reject) => {
            api.post(constants.practice_sub_category_url, data)
                .then(response => {
                    let category = response.data;
                    console.log('practice-sub-category post success: ' + category);
                    commit('addPracticeSubCategory', category);
                    resolve(category);
                }, error => {
                    console.log('practice-sub-category post error:' + error.response.data);
                    reject(error.response.data);
                });
        });
    },
    setSelectedPracticeSubCategory({ commit }, data) {
        console.log('actions: setSelectedPracticeSubCategory');
        console.log(data);
        commit('setSelectedPracticeSubCategory', data);
    },
    editPracticeSubCategory({ commit }, data) {
        return new Promise((resolve, reject) => {
            api.patch(data.href, data.practiceSubCategory)
                .then(response => {
                    let subcategory = response.data;
                    console.log('practice-sub-category patch success: ' + subcategory);
                    commit('editPracticeSubCategory', subcategory);
                    resolve(subcategory);
                }, error => {
                    console.log('practice-sub-category patch error:' + error.response.data);
                    reject(error.response.data);
                });
        });
    },
    getAllPracticeSubCategories({ commit }) {
        let subcategories = [];
        api.get(constants.practice_sub_category_url)
            .then(response => {
                subcategories = response.data._embedded.practiceSubCategories;
                console.log('practice-sub-category get success' + subcategories);
                commit('addPracticeSubCategories', subcategories);
            }, error => {
                console.log('practice-sub-category get error: ' + error);
            });
        return subcategories;
    }
}