import api from "../../services/api";

export default {
    addPractice({ commit }, data) {
        return new Promise((resolve, reject) => {
            api.post('practice', data)
                .then(response => {
                    let practice = response.data;
                    console.log('practice post success: ' + practice);
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
                    console.log('practice put success: ' + practice);
                    // commit('editPractice', practice);
                    resolve(practice);
                }, error => {
                    console.log('practice put error:' + error.response.data);
                    reject(error.response.data);
                });
        });
    },
    getAllPractices({ commit }) {
        // return new Promise((resolve, reject) => {
        //     let practices = [];
        //     api.get('/practice')
        //         .then(response => {
        //             practices = response.data._embedded.practices;
        //             console.log('practice get success' + practices);
        //             commit('addPractices', practices);
        //             resolve(practices);
        //         }, error => {
        //             console.log('practice get error: ' + error);
        //             reject(error);
        //         });
        //     // practices = practices.map(practice => ({
        //     //     name: practice.name,
        //     //     description: practice.description,
        //     //     _links: practice._links,
        //     //     practiceCategory: {
        //     //         name: 'define'
        //     //     }
        //     // }));
        // });
        let practices = [];
        api.get('/practice')
            .then(response => {
                practices = response.data._embedded.practices;
                console.log('practice get success' + practices);
                commit('addPractices', practices);
                // resolve(practices);
            }, error => {
                console.log('practice get error: ' + error);
                // reject(error);
            });
        return practices;
    }
}