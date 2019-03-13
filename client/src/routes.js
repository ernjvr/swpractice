import Home from './components/Home.vue';

import PracticeCategoryIndex from './components/pratice-category/Index.vue';
import PracticeCategoryCreate from './components/pratice-category/Create.vue';
import PracticeCategoryShow from './components/pratice-category/Show.vue';
import PracticeCategoryEdit from './components/pratice-category/Edit.vue';

import PracticeIndex from './components/practice/Index.vue';
import PracticeCreate from './components/practice/Create.vue';
import PracticeShow from './components/practice/Show.vue';
import PracticeEdit from './components/practice/Edit.vue';

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
        path: '/practice',
        name: 'practice.index',
        component: PracticeIndex
    },
    {
        path: '/practice/:id',
        name: 'practice.show',
        component: PracticeShow,
        props: true
    },
    {
        path: '/practice/create',
        name: 'practice.create',
        component: PracticeCreate
    },
    {
        path: '/practice/:id/edit',
        name: 'practice.edit',
        component: PracticeEdit,
        props: true
    },
    {
        path: '/*',
        redirect: '/home',
    },
];
