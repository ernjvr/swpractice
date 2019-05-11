export default {
    addContextValues(state, values) {
        console.log('mutation: addContextValues');
        state.contextValues = values;
    },
    addContextValue(state, value) {
        state.contextValues.push(value);
    },
    editContextValue(state, value) {
        let index = state.contextValues.map(val => val._links.self.href).indexOf(value._links.self.href);
        console.log('editContextValue: index of value: ' + index);
        if (index > -1) {
            state.contextValues[index] = value;
        } else {
            console.log('editContextValue: value not found in store: ' + value);
        }
    },
    removeContextValue(state, value) {
        let index = state.contextValues.map(val => val._links.self.href).indexOf(value._links.self.href);
        console.log('removeContextValue: index of value: ' + index);
        if (index > -1) {
            state.contextValues.splice(index, 1);
        } else {
            console.log('removeContextValue: value not found in store: ' + value);
        }
    }
}