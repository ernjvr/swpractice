export default {
    addContextDimensions(state, dimensions) {
        console.log('mutation: addContextDimensions');
        state.contextDimensions = dimensions;
    },
    addContextDimension(state, dimension) {
        state.contextDimensions.push(dimension);
    },
    editContextDimension(state, dimension) {
        let index = state.contextDimensions.map(dim => dim._links.self.href).indexOf(dimension._links.self.href);
        console.log('editContextDimension: index of dimension: ' + index);
        if (index > -1) {
            state.contextDimensions[index] = dimension;
        } else {
            console.log('editContextDimension: dimension not found in store: ' + dimension);
        }
    },
    removeContextDimension(state, dimension) {
        let index = state.contextDimensions.map(dim => dim._links.self.href).indexOf(dimension._links.self.href);
        console.log('removeContextDimension: index of dimension: ' + index);
        if (index > -1) {
            state.contextDimensions.splice(index, 1);
        } else {
            console.log('removeContextDimension: dimension not found in store: ' + dimension);
        }
    }
}