import store from '../store/store';
import util from './util';

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
    changeSort(column) {
        if (this.pagination.sortBy === column) {
            this.pagination.descending = !this.pagination.descending;
        } else {
            this.pagination.sortBy = column;
            this.pagination.descending = false;
        }
    },
    pages(pagination) {
        if (pagination.rowsPerPage == null || pagination.totalItems == null) {
            return 0;
        }
        return Math.ceil(pagination.totalItems / pagination.rowsPerPage);
    },
    navigateTo(route) {
        if(this.infoDialog) {
            this.infoDialog.infoVisibility = false;
        }
        this.$router.push(route);
    },
    acceptInfoDialog(route) {
        if(this.navigateToIndexPage) {
            this.navigateToIndexPage = false;
            this.navigateTo(route);
        }
        this.infoDialog.infoVisibility = false;
    },
    displayInfoDialog(infoType, title, text, detail) {
        this.infoDialog.title = title;
        this.infoDialog.text = text;
        this.infoDialog.detail = detail;
        this.infoDialog.infoType = infoType;
        this.infoDialog.infoVisibility = true;
    },
    displayAddError(error) {
        if (error.toLowerCase().includes('already exists')) {
            util.displayInfoDialog('error', this.$t('error_add_title'), error,
                this.$t('error_already_exist_detail'));
        } else {
            util.displayInfoDialog('error', this.$t('error_add_title'), this.$t('error_unknown_text'),
                this.$t('error_unknown_detail'));
        }
    },
    displayEditError(error, not_found_text, not_found_detail) {
        if (error.toLowerCase().includes('resource not found')) {
            util.displayInfoDialog('error', this.$t('error_update_title'), this.$t(not_found_text),
                this.$t(not_found_detail));
            this.navigateToIndexPage = true;
        } else if (error.toLowerCase().includes('already exists')) {
            util.displayInfoDialog('error', this.$t('error_update_title'), this.$t(error),
                this.$t('error_already_exist_detail'));
        } else {
            util.displayInfoDialog('error', this.$t('error_delete_title'), this.$t('error_unknown_text'),
                this.$t('error_unknown_detail'));
        }
    },
    displayDeleteError(error, integrity_violation_text, integrity_violation_detail, not_found_text, not_found_detail) {
        if (error.response.data.toLowerCase().includes('integrity violation')) {
            util.displayInfoDialog('error', this.$t('error_delete_title'), this.$t(integrity_violation_text),
                this.$t(integrity_violation_detail));
        } else if (error.response.data.toLowerCase().includes('resource not found')) {
            util.displayInfoDialog('error', this.$t('error_delete_title'), this.$t(not_found_text),
                this.$t(not_found_detail));
            this.navigateToIndexPage = true;
        } else {
            util.displayInfoDialog('error', this.$t('error_delete_title'), this.$t('error_unknown_text'),
                this.$t('error_unknown_detail'));
        }
    },
    showDialog(show) {
        this.$store.dispatch('displayDialog', show).then(_ => {
            console.log('displayDialog success');
        } , error => {
            console.log('displayDialog error: ' + error);
        });
    },
    pagination: {
        sortBy: ''
    },
    infoDialog: {
        title: '',
        text: '',
        detail: '',
        infoType: '',
        infoVisibility: false,
    },
}