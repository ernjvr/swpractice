export default {
    addPracticeCategories(state, categories) {
        console.log('mutation: addPracticeCategories');
        state.practiceCategories = categories;
    },
    addPracticeCategory(state, category) {
        state.practiceCategories.unshift(category);
    },
    setSelectedPracticeCategory(state, category) {
        console.log('mutation: setSelectedPracticeCategory');
        console.log(category);
        state.selectedPracticeCategory = category;
    },
    editPracticeCategory(state, category) {
        let index = state.practiceCategories.map(cat => cat._links.self.href).indexOf(category._links.self.href);
        console.log('editPracticeCategory: index of category: ' + index);
        if (index > -1) {
            state.practiceCategories[index] = category;
        } else {
            console.log('editPracticeCategory: category not found in store: ' + category);
        }
    },
    removePracticeCategory(state, category) {
        let index = state.practiceCategories.map(cat => cat._links.self.href).indexOf(category._links.self.href);
        console.log('removePracticeCategory: index of category: ' + index);
        if (index > -1) {
            state.practiceCategories.splice(index, 1);
        } else {
            console.log('removePracticeCategory: category not found in store: ' + category);
        }
    }
}