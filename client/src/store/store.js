import Vue from 'vue';
import Vuex from 'vuex';
import PracticeCategoryMutations from './mutations/PracticeCategoryMutations';
import PracticeCategoryActions from './actions/PracticeCategoryActions';

Vue.use(Vuex);

export default new Vuex.Store({
    strict: true,
    state: {
        practiceCategories: [],
        practices: []
    },
    mutations: {
        addPracticeCategories(state, categories) {
            PracticeCategoryMutations.addPracticeCategories(state, categories);
        },
        addPracticeCategory(state, category) {
            PracticeCategoryMutations.addPracticeCategory(state, category);
        },
        editPracticeCategory(state, category) {
            PracticeCategoryMutations.editPracticeCategory(state, category);
        },
        removePracticeCategory(state, category) {
            PracticeCategoryMutations.removePracticeCategory(state, category);
        },
    },
    actions: {
        addPracticeCategory({ commit }, category) {
            return PracticeCategoryActions.addPracticeCategory({ commit }, category);
        },
        editPracticeCategory({ commit }, data) {
            return PracticeCategoryActions.editPracticeCategory({ commit }, data);
        },
        getAllPracticeCategories({ commit }) {
            return PracticeCategoryActions.getAllPracticeCategories({ commit });
        }
    }
});