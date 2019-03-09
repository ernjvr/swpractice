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
            PracticeCategoryActions.addPracticeCategory({ commit }, category);
        },
        editPracticeCategory({ commit }, category) {
            PracticeCategoryActions.editPracticeCategory({ commit }, category);
        },
        getAllPracticeCategories({ commit }) {
            return PracticeCategoryActions.getAllPracticeCategories({ commit });
        }
    }
});