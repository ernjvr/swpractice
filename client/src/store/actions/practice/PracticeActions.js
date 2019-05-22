import api from "../../../services/api";
import constants from "../../../common/constants";
import axios from 'axios';

export default {
    addPractice({ commit }, data) {
        // extract ui-formatted category
        let practiceSubCategory = data.practiceSubCategory;
        let reference = data.reference;

        // update data.practiceCategory & data.reference with format restful api expects
        data.practiceSubCategory = practiceSubCategory._links.self.href;
        data.reference = reference === '' ? '' : reference._links.self.href;

        return new Promise((resolve, reject) => {
            api.post(constants.practice_url, data)
                .then(response => {
                    let practice = response.data;
                    console.log('practice post success: ');
                    console.log(practice);
                    practice['practiceSubCategory'] = practiceSubCategory;
                    practice['practiceCategory'] = {
                        name: practiceSubCategory.practiceCategory.name,
                        _links: practiceSubCategory.practiceCategory._links
                    };
                    practice['reference'] = reference;
                    console.log(practice);
                    commit('addPractice', practice);
                    resolve(practice);
                }, error => {
                    console.log('practice post error:' + error.response.data);
                    reject(error.response.data);
                });
        });
    },
    setSelectedPractice({ commit }, data) {
        console.log('actions: setSelectedPractice');
        console.log(data);
        commit('setSelectedPractice', data);
    },
    editPractice({ commit }, data) {
        console.log('editPractice data parameter');
        console.log(data);
        return new Promise((resolve, reject) => {
            api.patch(data.href, data.practice)
                .then(response => {
                    let practice = response.data;
                    console.log('practice patch success: ' + practice);
                    resolve(practice);
                }, error => {
                    console.log('practice patch error:' + error.response.data);
                    reject(error.response.data);
                });
        });
    },
    getAllPractices({ commit }) {
        return new Promise((resolve, reject) => {
            api.get(constants.practice_url)
                .then(response => {
                    let practices = response.data._embedded.practices.map(practice => ({
                        name: practice.name,
                        description: practice.description,
                        _links: practice._links,
                        practiceSubCategory: '',
                        practiceCategory: '',
                        reference: '',
                    }));
                    console.log('practice get success' + practices);
                    this.resolveCategoryForEachPractice({ commit }, practices);
                    resolve(practices);
                }, error => {
                    console.log('practice get error: ' + error);
                    reject(error);
                });
        });
    },
    async resolveCategoryForEachPractice({ commit }, practices) {
        let practiceSubCategories = [];
        let practiceCategories = [];

        for(let i = 0; i < practices.length; i++) {
            let practice = practices[i];
            practiceSubCategories.push(api.get(practice._links.practiceSubCategory.href));
            this.resolveReference(practice._links.reference.href, practice, i);
        }
        let subCatResult = await axios.all(practiceSubCategories);

        for(let i = 0; i < subCatResult.length; i++) {
            let res = subCatResult[i];
            practiceCategories.push(api.get(res.data._links.practiceCategory.href));
        }
        let catResult = await axios.all(practiceCategories);

        for(let i = 0; i < subCatResult.length; i++) {
            let subCatRes = subCatResult[i];
            let catRes = catResult[i];
            let practice = practices[i];
            let practiceSubCategory = {
                name: subCatRes.data.name,
                _links: subCatRes.data._links
            };
            let practiceCategory = {
                name: catRes.data.name,
                _links: catRes.data._links
            };
            practice['practiceSubCategory'] = practiceSubCategory;
            practice['practiceCategory'] = practiceCategory;
            practices[i] = practice;
        }
        commit('addPractices', practices);
    },
    async resolveReference(url, practice, index) {
        await api.get(url)
            .then(response => {
                practice['reference'] = {
                    reference: response.data.reference,
                    _links: response.data._links
                };
            }, error => {
                // since practices may optionally have a reference ignore 404 but log anything else
                if (!error.toString().includes('404')) {
                    console.log('reference get error: ' + error);
                }
            })
            .then(response => {
                console.log(response);
            });
    }
}