import Vue from 'vue';
import Vuex from 'vuex';
import PracticeCategoryMutations from './mutations/practice/PracticeCategoryMutations';
import PracticeSubCategoryMutations from './mutations/practice/PracticeSubCategoryMutations';
import PracticeMutations from './mutations/practice/PracticeMutations';
import PracticeCategoryActions from './actions/practice/PracticeCategoryActions';
import PracticeSubCategoryActions from './actions/practice/PracticeSubCategoryActions';
import PracticeActions from './actions/practice/PracticeActions';
import ContextDimensionMutations from "./mutations/context/ContextDimensionMutations";
import ContextDimensionActions from "./actions/context/ContextDimensionActions";
import ReferenceTypeMutations from "./mutations/reference/ReferenceTypeMutations";
import ReferenceTypeActions from "./actions/reference/ReferenceTypeActions";

Vue.use(Vuex);

export default new Vuex.Store({
    strict: true,
    state: {
        referenceTypes: [],
        contextDimensions: [],
        practiceCategories: [],
        practiceSubCategories: [],
        practices: [],
        selectedPractice: {}
    },
    mutations: {
        addReferenceTypes(state, types) {
            ReferenceTypeMutations.addReferenceTypes(state, types);
        },
        addReferenceType(state, type) {
            ReferenceTypeMutations.addReferenceType(state, type);
        },
        editReferenceType(state, type) {
            ReferenceTypeMutations.editReferenceType(state, type);
        },
        removeReferenceType(state, type) {
            ReferenceTypeMutations.removeReferenceType(state, type);
        },
        addContextDimensions(state, dimensions) {
            ContextDimensionMutations.addContextDimensions(state, dimensions);
        },
        addContextDimension(state, dimension) {
            ContextDimensionMutations.addContextDimension(state, dimension);
        },
        editContextDimension(state, dimension) {
            ContextDimensionMutations.editContextDimension(state, dimension);
        },
        removeContextDimension(state, dimension) {
            ContextDimensionMutations.removeContextDimension(state, dimension);
        },
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
        addReferenceType({ commit }, type) {
            return ReferenceTypeActions.addReferenceType({ commit }, type);
        },
        editReferenceType({ commit }, data) {
            return ReferenceTypeActions.editReferenceType({ commit }, data);
        },
        getAllReferenceTypes({ commit }) {
            return ReferenceTypeActions.getAllReferenceTypes({ commit });
        },
        addContextDimension({ commit }, dimension) {
            return ContextDimensionActions.addContextDimension({ commit }, dimension);
        },
        editContextDimension({ commit }, data) {
            return ContextDimensionActions.editContextDimension({ commit }, data);
        },
        getAllContextDimensions({ commit }) {
            return ContextDimensionActions.getAllContextDimensions({ commit });
        },
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