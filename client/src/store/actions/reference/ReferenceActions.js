import api from "../../../services/api";
import constants from "../../../common/constants";
import axios from 'axios';

export default {
    addReference({ commit }, data) {
        // extract ui-formatted category
        let referenceType = data.referenceType;

        // update data.referenceType with format restful api expects
        data.referenceType = referenceType._links.self.href;

        return new Promise((resolve, reject) => {
            api.post(constants.reference_url, data)
                .then(response => {
                    let reference = response.data;
                    console.log(constants.reference_url + ' post success: ' + reference);
                    // update reference.referenceType with format ui expects
                    reference['referenceType'] = referenceType;
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
                        referenceType: ''
                    }));
                    console.log(constants.reference_url + ' get success' + response.data._embedded.references);
                    this.resolveReferenceTypeForEachReference({ commit }, references);
                    resolve(references);
                }, error => {
                    console.log(constants.reference_url + ' get error: ' + error);
                    reject(error);
                });
        });
    },
    async resolveReferenceTypeForEachReference({ commit }, references) {
        let referenceTypes = [];

        for(let i = 0; i < references.length; i++) {
            let reference = references[i];
            referenceTypes.push(api.get(reference._links.referenceType.href));
        }

        let result = await axios.all(referenceTypes);

        for(let i = 0; i < result.length; i++) {
            let res = result[i];
            let reference = references[i];
            reference['referenceType'] = {
                name: res.data.name,
                _links: res.data._links
            };
        }
        commit('addReferences', references);
    }
}