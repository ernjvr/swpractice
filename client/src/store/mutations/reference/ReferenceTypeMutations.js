export default {
    addReferenceTypes(state, types) {
        console.log('mutation: addReferenceTypes');
        state.referenceTypes = types;
    },
    addReferenceType(state, type) {
        state.referenceTypes.unshift(type);
    },
    setSelectedReferenceType(state, type) {
        console.log('mutation: setSelectedReferenceType');
        console.log(type);
        state.selectedReferenceType = type;
    },
    editReferenceType(state, type) {
        let index = state.referenceTypes.map(tp => tp._links.self.href).indexOf(type._links.self.href);
        console.log('editReferenceType: index of type: ' + index);
        if (index > -1) {
            state.referenceTypes[index] = type;
        } else {
            console.log('editReferenceType: type not found in store: ' + type);
        }
    },
    removeReferenceType(state, type) {
        let index = state.referenceTypes.map(tp => tp._links.self.href).indexOf(type._links.self.href);
        console.log('removeReferenceType: index of type: ' + index);
        if (index > -1) {
            state.referenceTypes.splice(index, 1);
        } else {
            console.log('removeReferenceType: type not found in store: ' + type);
        }
    }
}