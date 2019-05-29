export default {
    addContextValueLevels(state, values) {
        console.log('mutation: addContextValueLevels');
        state.contextValueLevels = values;
    },
    addContextValueLevel(state, value) {
        state.contextValueLevels.unshift(value);
    },
    setSelectedContextValueLevel(state, value) {
        console.log('mutation: setSelectedContextValueLevel');
        console.log(value);
        state.selectedContextValueLevel = value;
    },
    editContextValueLevel(state, value) {
        let index = state.contextValueLevels.map(val => val._links.self.href).indexOf(value._links.self.href);
        console.log('editContextValueLevel: index of value: ' + index);
        if (index > -1) {
            state.contextValueLevels[index] = value;
        } else {
            console.log('editContextValueLevel: value not found in store: ' + value);
        }
    },
    removeContextValueLevel(state, value) {
        let index = state.contextValueLevels.map(val => val._links.self.href).indexOf(value._links.self.href);
        console.log('removeContextValueLevel: index of value: ' + index);
        if (index > -1) {
            state.contextValueLevels.splice(index, 1);
        } else {
            console.log('removeContextValueLevel: value not found in store: ' + value);
        }
    }
}