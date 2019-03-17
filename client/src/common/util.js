import store from '../store/store';

export default {

    encode(href) {
        return href.replace(/\//g, "%2F");
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
}