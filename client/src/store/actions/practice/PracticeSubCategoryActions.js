import api from "../../../services/api";
import constants from "../../../common/constants";
import axios from 'axios';

export default {
    addPracticeSubCategory({ commit }, data) {
        // extract ui-formatted category
        let category = data.practiceCategory;

        // update data.practiceCategory with format restful api expects
        data.practiceCategory = category._links.self.href;

        return new Promise((resolve, reject) => {
            api.post(constants.practice_sub_category_url, data)
                .then(response => {
                    let subcategory = response.data;
                    console.log('practice-sub-category post success: ' + subcategory);
                    // update subcategory.practiceCategory with format ui expects
                    subcategory['practiceCategory'] = category;
                    commit('addPracticeSubCategory', subcategory);
                    resolve(subcategory);
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
        return new Promise((resolve, reject) => {
            api.get(constants.practice_sub_category_url)
                .then(response => {
                    let subcategories = response.data._embedded.practiceSubCategories.map(subCategory => ({
                        name: subCategory.name,
                        description: subCategory.description,
                        _links: subCategory._links,
                        practiceCategory: ''
                    }));
                    console.log('practice-sub-category get success ' + response.data._embedded.practiceSubCategories);
                    this.resolvePracticeCategoryForEachPracticeSubCategory({ commit }, subcategories);
                    resolve(subcategories);
                }, error => {
                    console.log('practice-sub-category get error: ' + error);
                    reject(error);
                });
        });
    },
    async resolvePracticeCategoryForEachPracticeSubCategory({ commit }, practiceSubCategories) {
        let practiceCategories = [];

        for(let i = 0; i < practiceSubCategories.length; i++) {
            let subCategory = practiceSubCategories[i];
            practiceCategories.push(api.get(subCategory._links.practiceCategory.href));
        }

        let result = await axios.all(practiceCategories);
        console.log('get practice category for each practice sub category result: ' + result);

        for(let i = 0; i < result.length; i++) {
            let res = result[i];
            let subCategory = practiceSubCategories[i];
            subCategory['practiceCategory'] = {
                name: res.data.name,
                _links: res.data._links
            };
        }
        commit('addPracticeSubCategories', practiceSubCategories);
    },
}