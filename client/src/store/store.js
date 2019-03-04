import Vue from 'vue';
import Vuex from 'vuex';
import api from  '../services/api';

Vue.use(Vuex);

export default new Vuex.Store({
    strict: true,
    state: {
        practiceCategories: []
    },
    mutations: {
        addPracticeCategories(state, categories) {
            console.log('mutation: addPracticeCategories');
            state.practiceCategories = categories;
        },
        addPracticeCategory(state, category) {
            state.practiceCategories.push(category);
        },
        editPracticeCategory(state, category) {
            let index = state.practiceCategories.map(cat => cat._links.self.href).indexOf(category._links.self.href);
            console.log('editPracticeCategory: index of category: ' + index);
            if (index) {
                state.practiceCategories[index] = category;
            } else {
                console.log('editPracticeCategory: category not found in store: ' + category);
            }
        },
        removePracticeCategory(state, category) {
            let index = state.practiceCategories.map(cat => cat._links.self.href).indexOf(category._links.self.href);
            console.log('removePracticeCategory: index of category: ' + index);
            if (index) {
                state.practiceCategories.splice(index, 1);
            } else {
                console.log('removePracticeCategory: category not found in store: ' + category);
            }
        },
    },
    actions: {
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
});