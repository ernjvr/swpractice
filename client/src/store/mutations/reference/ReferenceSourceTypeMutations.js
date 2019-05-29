export default {
    addReferenceSourceTypes(state, types) {
        console.log('mutation: addReferenceSourceTypes');
        state.referenceSourceTypes = types;
    },
    addReferenceSourceType(state, type) {
        state.referenceSourceTypes.unshift(type);
    },
    setSelectedReferenceSourceType(state, type) {
        console.log('mutation: setSelectedReferenceSourceType');
        console.log(type);
        state.selectedReferenceSourceType = type;
    },
    editReferenceSourceType(state, type) {
        let index = state.referenceSourceTypes.map(tp => tp._links.self.href).indexOf(type._links.self.href);
        console.log('editReferenceSourceType: index of type: ' + index);
        if (index > -1) {
            state.referenceSourceTypes[index] = type;
        } else {
            console.log('editReferenceSourceType: type not found in store: ' + type);
        }
    },
    removeReferenceSourceType(state, type) {
        let index = state.referenceSourceTypes.map(tp => tp._links.self.href).indexOf(type._links.self.href);
        console.log('removeReferenceSourceType: index of type: ' + index);
        if (index > -1) {
            state.referenceSourceTypes.splice(index, 1);
        } else {
            console.log('removeReferenceSourceType: type not found in store: ' + type);
        }
    }
}