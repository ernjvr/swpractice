import api from "../../../services/api";
import constants from "../../../common/constants";
import axios from 'axios';

export default {
    addReference({ commit }, data) {
        // extract ui-formatted category
        let referenceSourceType = data.referenceSourceType;

        // update data.referenceSourceType with format restful api expects
        data.referenceSourceType = referenceSourceType._links.self.href;

        return new Promise((resolve, reject) => {
            api.post(constants.reference_url, data)
                .then(response => {
                    let reference = response.data;
                    console.log(constants.reference_url + ' post success: ' + reference);
                    // update reference.referenceSourceType with format ui expects
                    reference['referenceSourceType'] = referenceSourceType;
                    commit('addReference', reference);
                    resolve(reference);
                }, error => {
                    console.log(constants.reference_url + ' post error:' + error.response.data);
                    reject(error.response.data);
                });
        });
    },
    setSelectedReference({ commit }, data) {
        console.log('actions: setSelectedReference');
        console.log(data);
        commit('setSelectedReference', data);
    },
    editReference({ commit }, data) {
        return new Promise((resolve, reject) => {
            api.patch(data.href, data.reference)
                .then(response => {
                    let reference = response.data;
                    console.log(constants.reference_url + ' patch success: ' + reference);
                    commit('editReference', reference);
                    resolve(reference);
                }, error => {
                    console.log(constants.reference_url + ' patch error:' + error.response.data);
                    reject(error.response.data);
                });
        });
    },
    getAllReferences({ commit }) {
        return new Promise((resolve, reject) => {
            api.get(constants.reference_url)
                .then(response => {
                    let references = response.data._embedded.references.map(reference => ({
                        author: reference.author,
                        year: reference.year === 0 ? 'n.d': reference.year,
                        reference: reference.reference,
                        _links: reference._links,
                        referenceSourceType: ''
                    }));
                    console.log(constants.reference_url + ' get success' + response.data._embedded.references);
                    this.resolveReferenceSourceTypeForEachReference({ commit }, references);
                    resolve(references);
                }, error => {
                    console.log(constants.reference_url + ' get error: ' + error);
                    reject(error);
                });
        });
    },
    async resolveReferenceSourceTypeForEachReference({ commit }, references) {
        let referenceSourceTypes = [];

        for(let i = 0; i < references.length; i++) {
            let reference = references[i];
            referenceSourceTypes.push(api.get(reference._links.referenceSourceType.href));
        }

        let result = await axios.all(referenceSourceTypes);

        for(let i = 0; i < result.length; i++) {
            let res = result[i];
            let reference = references[i];
            reference['referenceSourceType'] = {
                name: res.data.name,
                _links: res.data._links
            };
        }
        commit('addReferences', references);
    }
}