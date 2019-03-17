import Vue from 'vue';
import Vuex from 'vuex';
import PracticeCategoryMutations from './mutations/PracticeCategoryMutations';
import PracticeSubCategoryMutations from './mutations/PracticeSubCategoryMutations';
import PracticeMutations from './mutations/PracticeMutations';
import PracticeCategoryActions from './actions/PracticeCategoryActions';
import PracticeSubCategoryActions from './actions/PracticeSubCategoryActions';
import PracticeActions from './actions/PracticeActions';

Vue.use(Vuex);

export default new Vuex.Store({
    strict: true,
    state: {
        practiceCategories: [],
        practiceSubCategories: [],
        practices: [],
        // is this used?
        selectedPractice: {},
        selectedPracticeSubCategory: {}
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
        addPracticeSubCategories(state, subcategories) {
            PracticeSubCategoryMutations.addPracticeSubCategories(state, subcategories);
        },
        addPracticeSubCategory(state, subcategory) {
            PracticeSubCategoryMutations.addPracticeSubCategory(state, subcategory);
        },
        editPracticeSubCategory(state, subcategory) {
            PracticeSubCategoryMutations.editPracticeSubCategory(state, subcategory);
        },
        removePracticeSubCategory(state, subcategory) {
            PracticeSubCategoryMutations.removePracticeSubCategory(state, subcategory);
        },
        setSelectedPracticeSubCategory(state, practice) {
            PracticeSubCategoryMutations.setSelectedPracticeSubCategory(state, practice);
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
        addPracticeSubCategory({ commit }, category) {
            return PracticeSubCategoryActions.addPracticeSubCategory({ commit }, category);
        },
        setSelectedSubCategory({ commit }, practice) {
            PracticeSubCategoryActions.setSelectedSubCategory({ commit }, practice);
        },
        editPracticeSubCategory({ commit }, data) {
            return PracticeSubCategoryActions.editPracticeSubCategory({ commit }, data);
        },
        getAllPracticeSubCategories({ commit }) {
            return PracticeSubCategoryActions.getAllPracticeSubCategories({ commit });
        },
        setSelectedPracticeSubCategory({ commit }, practice) {
            PracticeSubCategoryActions.setSelectedPracticeSubCategory({ commit }, practice);
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