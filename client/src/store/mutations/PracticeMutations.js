export default {
    addPractices(state, practices) {
        console.log('mutation: addPractices');
        state.practices = practices;
    },
    addPractice(state, practice) {
        state.practices.push(practice);
    },
    setSelectedPractice(state, practice) {
        console.log('mutation: setSelectedPractice');
        console.log(practice);
        state.practice = practice;
    },
    editPractice(state, practice) {
        let index = state.practices.map(cat => cat._links.self.href).indexOf(practice._links.self.href);
        console.log('editPractice: index of practice: ' + index);
        if (index > -1) {
            state.practices[index] = practice;
        } else {
            console.log('editPractice: practice not found in store: ' + practice);
        }
    },
    removePractice(state, practice) {
        let index = state.practices.map(cat => cat._links.self.href).indexOf(practice._links.self.href);
        console.log('removePractice: index of practice: ' + index);
        if (index > -1) {
            state.practices.splice(index, 1);
        } else {
            console.log('removePractice: practice not found in store: ' + practice);
        }
    }
}