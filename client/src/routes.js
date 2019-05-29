import Home from './components/Home.vue';

import ReferenceIndex from './components/reference/Index.vue';
import ReferenceCreate from './components/reference/Create.vue';
import ReferenceShow from './components/reference/Show.vue';
import ReferenceEdit from './components/reference/Edit.vue';

import ReferenceSourceTypeIndex from './components/reference-source-type/Index.vue';
import ReferenceSourceTypeCreate from './components/reference-source-type/Create.vue';
import ReferenceSourceTypeShow from './components/reference-source-type/Show.vue';
import ReferenceSourceTypeEdit from './components/reference-source-type/Edit.vue';

import ContextDimensionIndex from './components/context-dimension/Index.vue';
import ContextDimensionCreate from './components/context-dimension/Create.vue';
import ContextDimensionShow from './components/context-dimension/Show.vue';
import ContextDimensionEdit from './components/context-dimension/Edit.vue';

import ContextValueLevelIndex from './components/context-value-level/Index.vue';
import ContextValueLevelCreate from './components/context-value-level/Create.vue';
import ContextValueLevelShow from './components/context-value-level/Show.vue';
import ContextValueLevelEdit from './components/context-value-level/Edit.vue';

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
        path: '/reference',
        name: 'reference.index',
        component: ReferenceIndex
    },
    {
        path: '/reference/:id',
        name: 'reference.show',
        component: ReferenceShow,
        props: true
    },
    {
        path: '/reference/create',
        name: 'reference.create',
        component: ReferenceCreate
    },
    {
        path: '/reference/:id/edit',
        name: 'reference.edit',
        component: ReferenceEdit,
        props: true
    },
    {
        path: '/reference-source-type',
        name: 'reference-source-type.index',
        component: ReferenceSourceTypeIndex
    },
    {
        path: '/reference-source-type/:id',
        name: 'reference-source-type.show',
        component: ReferenceSourceTypeShow,
        props: true
    },
    {
        path: '/reference-source-type/create',
        name: 'reference-source-type.create',
        component: ReferenceSourceTypeCreate
    },
    {
        path: '/reference-source-type/:id/edit',
        name: 'reference-source-type.edit',
        component: ReferenceSourceTypeEdit,
        props: true
    },
    {
        path: '/context-dimension',
        name: 'context-dimension.index',
        component: ContextDimensionIndex
    },
    {
        path: '/context-dimension/:id',
        name: 'context-dimension.show',
        component: ContextDimensionShow,
        props: true
    },
    {
        path: '/context-dimension/create',
        name: 'context-dimension.create',
        component: ContextDimensionCreate
    },
    {
        path: '/context-dimension/:id/edit',
        name: 'context-dimension.edit',
        component: ContextDimensionEdit,
        props: true
    },
    {
        path: '/context-value-level',
        name: 'context-value-level.index',
        component: ContextValueLevelIndex
    },
    {
        path: '/context-value-level/:id',
        name: 'context-value-level.show',
        component: ContextValueLevelShow,
        props: true
    },
    {
        path: '/context-value-level/create',
        name: 'context-value-level.create',
        component: ContextValueLevelCreate
    },
    {
        path: '/context-value-level/:id/edit',
        name: 'context-value-level.edit',
        component: ContextValueLevelEdit,
        props: true
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
