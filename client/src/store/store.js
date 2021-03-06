import Vue from 'vue';
import Vuex from 'vuex';
import GlobalMutations from './mutations/global/GlobalMutations';
import GlobalActions from './actions/global/GlobalActions';
import PracticeCategoryMutations from './mutations/practice/PracticeCategoryMutations';
import PracticeSubCategoryMutations from './mutations/practice/PracticeSubCategoryMutations';
import PracticeMutations from './mutations/practice/PracticeMutations';
import PracticeCategoryActions from './actions/practice/PracticeCategoryActions';
import PracticeSubCategoryActions from './actions/practice/PracticeSubCategoryActions';
import PracticeActions from './actions/practice/PracticeActions';
import ContextDimensionMutations from "./mutations/context/ContextDimensionMutations";
import ContextDimensionActions from "./actions/context/ContextDimensionActions";
import ContextValueMutations from "./mutations/context/ContextValueMutations";
import ContextValueLevelActions from "./actions/context/ContextValueLevelActions";
import ReferenceSourceTypeMutations from "./mutations/reference/ReferenceSourceTypeMutations";
import ReferenceSourceTypeActions from "./actions/reference/ReferenceSourceTypeActions";
import ReferenceMutations from "./mutations/reference/ReferenceMutations";
import ReferenceActions from "./actions/reference/ReferenceActions";

Vue.use(Vuex);

export default new Vuex.Store({
    strict: true,
    state: {
        references: [],
        referenceSourceTypes: [],
        contextDimensions: [],
        contextValueLevels: [],
        practiceCategories: [],
        practiceSubCategories: [],
        practices: [],
        selectedPractice: {},
        selectedPracticeCategory: {},
        selectedPracticeSubCategory: {},
        selectedReference: {},
        selectedReferenceSourceType: {},
        selectedContextValueLevel: {},
        displayDialog: false
    },
    mutations: {
        addReferences(state, references) {
            ReferenceMutations.addReferences(state, references);
        },
        addReference(state, reference) {
            ReferenceMutations.addReference(state, reference);
        },
        editReference(state, reference) {
            ReferenceMutations.editReference(state, reference);
        },
        removeReference(state, reference) {
            ReferenceMutations.removeReference(state, reference);
        },
        setSelectedReference(state, reference) {
           ReferenceMutations.setSelectedReference(state, reference);
        },
        addReferenceSourceTypes(state, types) {
            ReferenceSourceTypeMutations.addReferenceSourceTypes(state, types);
        },
        addReferenceSourceType(state, type) {
            ReferenceSourceTypeMutations.addReferenceSourceType(state, type);
        },
        editReferenceSourceType(state, type) {
            ReferenceSourceTypeMutations.editReferenceSourceType(state, type);
        },
        removeReferenceSourceType(state, type) {
            ReferenceSourceTypeMutations.removeReferenceSourceType(state, type);
        },
        setSelectedReferenceSourceType(state, type) {
            ReferenceSourceTypeMutations.setSelectedReferenceSourceType(state, type);
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
        addContextValueLevels(state, values) {
            ContextValueMutations.addContextValueLevels(state, values);
        },
        addContextValueLevel(state, value) {
            ContextValueMutations.addContextValueLevel(state, value);
        },
        editContextValueLevel(state, value) {
            ContextValueMutations.editContextValueLevel(state, value);
        },
        removeContextValueLevel(state, value) {
            ContextValueMutations.removeContextValueLevel(state, value);
        },
        setSelectedContextValueLevel(state, value) {
            ContextValueMutations.setSelectedContextValueLevel(state, value);
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
        setSelectedPracticeCategory(state, category) {
            PracticeCategoryMutations.setSelectedPracticeCategory(state, category);
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
        displayDialog(state, display) {
            GlobalMutations.displayDialog(state, display);
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
        addReference({ commit }, reference) {
            return ReferenceActions.addReference({ commit }, reference);
        },
        setSelectedReference({ commit }, reference) {
            ReferenceActions.setSelectedReference({ commit }, reference);
        },
        editReference({ commit }, data) {
            return ReferenceActions.editReference({ commit }, data);
        },
        getAllReferences({ commit }) {
            return ReferenceActions.getAllReferences({ commit });
        },
        addReferenceSourceType({commit}, type) {
            return ReferenceSourceTypeActions.addReferenceSourceType({commit}, type);
        },
        setSelectedReferenceSourceType({commit}, type) {
            ReferenceSourceTypeActions.setSelectedReferenceSourceType({commit}, type);
        },
        editReferenceSourceType({commit}, data) {
            return ReferenceSourceTypeActions.editReferenceSourceType({commit}, data);
        },
        getAllReferenceSourceTypes({commit}) {
            return ReferenceSourceTypeActions.getAllReferenceSourceTypes({commit});
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
        addContextValueLevel({commit}, value) {
            return ContextValueLevelActions.addContextValueLevel({ commit }, value);
        },
        setSelectedContextValueLevel({commit}, value) {
            return ContextValueLevelActions.setSelectedContextValueLevel({commit}, value);
        },
        editContextValueLevel({commit}, data) {
            return ContextValueLevelActions.editContextValueLevel({commit}, data);
        },
        getAllContextValueLevels({commit}) {
            return ContextValueLevelActions.getAllContextValueLevels({commit});
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
        setSelectedPracticeCategory({ commit }, category) {
            PracticeCategoryActions.setSelectedPracticeCategory({ commit }, category);
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
        displayDialog({ commit }, display) {
            GlobalActions.displayDialog({ commit }, display);
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