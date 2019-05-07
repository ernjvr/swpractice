import store from '../store/store';

export default {

    encode(href) {
        return href.replace(/\//g, "%2F");
    },
    getSelectedReference(selectedReference) {
        let reference = store.state.references.find(ref => { return ref._links.self.href === selectedReference});
        console.log('util getSelectedReference');
        console.log(reference);
        return reference;
    },
    getSelectedReferenceType(selectedReferenceType) {
        let type = store.state.referenceTypes.find(type => { return type._links.self.href === selectedReferenceType});
        console.log('util getSelectedReferenceType');
        console.log(type);
        return type;
    },
    getSelectedPracticeCategory(selectedPracticeCategory) {
        let category = store.state.practiceCategories.find(category => { return category._links.self.href === selectedPracticeCategory});
        console.log('util getSelectedPracticeCategory');
        console.log(category);
        return category;
    },
    getSelectedPracticeSubCategory(selectedPracticeSubCategory) {
        let subcategory = store.state.practiceSubCategories.find(subcategory => { return subcategory._links.self.href === selectedPracticeSubCategory});
        console.log('util getSelectedPracticeSubCategory');
        console.log(subcategory);
        return subcategory;
    },
    changeSort: function(column) {
        if (this.pagination.sortBy === column) {
            this.pagination.descending = !this.pagination.descending;
        } else {
            this.pagination.sortBy = column;
            this.pagination.descending = false;
        }
    },
    pagination: {
        sortBy: ''
    },
}