export default {
    addReferences(state, references) {
        console.log('mutation: addReferences');
        state.references = references;
    },
    addReference(state, reference) {
        state.references.push(reference);
    },
    setSelectedReference(state, reference) {
        console.log('mutation: setSelectedReference');
        console.log(reference);
        state.selectedReference = reference;
    },
    editReference(state, reference) {
        let index = state.references.map(ref => ref._links.self.href).indexOf(reference._links.self.href);
        console.log('editReference: index of reference: ' + index);
        if (index > -1) {
            state.references[index] = reference;
        } else {
            console.log('editReference: reference not found in store: ' + reference);
        }
    },
    removeReference(state, reference) {
        let index = state.references.map(ref => ref._links.self.href).indexOf(reference._links.self.href);
        console.log('removeReference: index of reference: ' + index);
        if (index > -1) {
            state.references.splice(index, 1);
        } else {
            console.log('removeReference: reference not found in store: ' + reference);
        }
    }
}