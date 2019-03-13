import Vue from 'vue';
import Vuex from 'vuex';
import PracticeCategoryMutations from './mutations/PracticeCategoryMutations';
import PracticeMutations from './mutations/PracticeMutations';
import PracticeCategoryActions from './actions/PracticeCategoryActions';
import PracticeActions from './actions/PracticeActions';

Vue.use(Vuex);

export default new Vuex.Store({
    strict: true,
    state: {
        practiceCategories: [],
        practices: [],
        selectedPractice: {}
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
        addPractices(state, practices) {
            PracticeMutations.addPractices(state, practices);
        },
        addPractice(state, practice) {
            PracticeMutations.addPractice(state, practice);
        },
        setSelectedPractice(state, practice) {
            PracticeMutations.setSelectedPractice(state, practice);
        },
        editPractice(state, practice) {
            PracticeMutations.editPractice(state, practice);
        },
        removePractice(state, practice) {
            PracticeMutations.removePractice(state, practice);
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
        },
        getPracticeCategory({ commit }, data) {
            return PracticeCategoryActions.getPracticeCategory({ commit }, data);
        },
        addPractice({ commit }, practice) {
            return PracticeActions.addPractice({ commit }, practice);
        },
        setSelectedPractice({ commit }, practice) {
            PracticeActions.setSelectedPractice({ commit }, practice);
        },
        editPractice({ commit }, data) {
            return PracticeActions.editPractice({ commit }, data);
        },
        getAllPractices({ commit }) {
            return PracticeActions.getAllPractices({ commit });
        },
    }
});