export default {
    addPracticeSubCategories(state, subcategories) {
        console.log('mutation: addPracticeSubCategories');
        state.practiceSubCategories = subcategories;
    },
    addPracticeSubCategory(state, subcategory) {
        state.practiceSubCategories.push(subcategory);
    },
    setSelectedPracticeSubCategory(state, subcategory) {
        console.log('mutation: setSelectedPracticeSubCategory');
        console.log(subcategory);
        state.selectedPracticeSubCategory = subcategory;
    },
    editPracticeSubCategory(state, subcategory) {
        let index = state.practiceSubCategories.map(sub => sub._links.self.href).indexOf(subcategory._links.self.href);
        console.log('editPracticeSubCategory: index of category: ' + index);
        if (index > -1) {
            state.practiceSubCategories[index] = subcategory;
        } else {
            console.log('editPracticeSubCategory: category not found in store: ' + subcategory);
        }
    },
    removePracticeSubCategory(state, subcategory) {
        let index = state.practiceSubCategories.map(sub => sub._links.self.href).indexOf(subcategory._links.self.href);
        console.log('removePracticeSubCategory: index of category: ' + index);
        if (index > -1) {
            state.practiceSubCategories.splice(index, 1);
        } else {
            console.log('removePracticeSubCategory: category not found in store: ' + subcategory);
        }
    }
}