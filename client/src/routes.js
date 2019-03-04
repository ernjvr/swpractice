import Home from './components/Home.vue';
import PracticeCategoryIndex from './components/pratice-category/Index.vue';
import PracticeCategoryCreate from './components/pratice-category/Create.vue';
import PracticeCategoryShow from './components/pratice-category/Show.vue';
import PracticeCategoryEdit from './components/pratice-category/Edit.vue';

export default [
    {
        path: '/home',
        name: 'home',
        component: Home
    },
    {
        path: '/practice-category',
        name: 'practice-category.index',
        component: PracticeCategoryIndex
    },
    {
        path: '/practice-category/:id',
        name: 'practice-category.show',
        component: PracticeCategoryShow,
        props: true
    },
    {
        path: '/practice-category/create',
        name: 'practice-category.create',
        component: PracticeCategoryCreate
    },
    {
        path: '/practice-category/:id/edit',
        name: 'practice-category.edit',
        component: PracticeCategoryEdit,
        props: true
    },
    {
        path: '/',
        redirect: '/home',
    },
];
