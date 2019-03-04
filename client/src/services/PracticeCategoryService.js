import api from './api';

export default {
    create(category) {
        return api.post('practice-category', category);
    },
    readAll() {
        api.get('practice-category');
    }
}