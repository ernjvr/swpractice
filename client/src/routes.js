import Home from './components/Home.vue';

import PracticeCategoryIndex from './components/pratice-category/Index.vue';
import PracticeCategoryCreate from './components/pratice-category/Create.vue';
import PracticeCategoryShow from './components/pratice-category/Show.vue';
import PracticeCategoryEdit from './components/pratice-category/Edit.vue';

import PracticeSubCategoryIndex from './components/practice-sub-category/Index.vue';
import PracticeSubCategoryCreate from './components/practice-sub-category/Create.vue';
import PracticeSubCategoryShow from './components/practice-sub-category/Show.vue';
import PracticeSubCategoryEdit from './components/practice-sub-category/Edit.vue';

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
        path: '/practice-sub-category',
        name: 'practice-sub-category.index',
        component: PracticeSubCategoryIndex
    },
    {
        path: '/practice-sub-category/:id',
        name: 'practice-sub-category.show',
        component: PracticeSubCategoryShow,
        props: true
    },
    {
        path: '/practice-sub-category/create',
        name: 'practice-sub-category.create',
        component: PracticeSubCategoryCreate
    },
    {
        path: '/practice-sub-category/:id/edit',
        name: 'practice-sub-category.edit',
        component: PracticeSubCategoryEdit,
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
