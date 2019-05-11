import { il8n } from '../il8n';

export default {
    practice_headers: [
        {
            text: il8n.tc('name'),
            align: 'left',
            sortable: false,
            value: 'name'
        },
        {
            text: il8n.tc('description'),
            align: 'left',
            sortable: false,
            value: 'description'
        },
        {
            text: il8n.tc('practice_category'),
            align: 'left',
            sortable: false,
            value: 'practiceCategory.name'
        },
        {
            text: il8n.tc('practice_sub_category'),
            align: 'left',
            sortable: false,
            value: 'practiceSubCategory.name'
        },
        {
            text: il8n.tc('reference'),
            align: 'left',
            sortable: false,
            value: 'reference.reference'
        },
        {
            text: il8n.tc('action'),
            align: 'left',
            sortable: false,
            value: 'view'
        },
    ],
    practice_sub_category_headers: [
        {
            text: il8n.tc('name'),
            align: 'left',
            sortable: false,
            value: 'name'
        },
        {
            text: il8n.tc('description'),
            align: 'left',
            sortable: false,
            value: 'description'
        },
        {
            text: il8n.tc('practice_category'),
            align: 'left',
            sortable: false,
            value: 'practiceCategory.name'
        },
        {
            text: il8n.tc('action'),
            align: 'left',
            sortable: false,
            value: 'view'
        },
    ],
    practice_category_headers: [
        {
            text: il8n.tc('name'),
            align: 'left',
            sortable: false,
            value: 'name'
        },
        {
            text: il8n.tc('action'),
            align: 'left',
            sortable: false,
            value: 'view'
        },
    ],
    context_dimension_headers: [
        {
            text: il8n.tc('name'),
            align: 'left',
            sortable: false,
            value: 'name'
        },
        {
            text: il8n.tc('action'),
            align: 'left',
            sortable: false,
            value: 'view'
        },
    ],
    context_value_headers: [
        {
            text: il8n.tc('value'),
            align: 'left',
            sortable: false,
            value: 'value'
        },
        {
            text: il8n.tc('description'),
            align: 'left',
            sortable: false,
            value: 'description'
        },
        {
            text: il8n.tc('action'),
            align: 'left',
            sortable: false,
            value: 'view'
        },
    ],
    reference_headers: [
        {
            text: il8n.tc('author'),
            align: 'left',
            sortable: false,
            value: 'author'
        },
        {
            text: il8n.tc('year'),
            align: 'left',
            sortable: false,
            value: 'year'
        },
        {
            text: il8n.tc('reference'),
            align: 'left',
            sortable: false,
            value: 'reference'
        },
        {
            text: il8n.tc('reference_type'),
            align: 'left',
            sortable: false,
            value: 'referenceType.name'
        },
        {
            text: il8n.tc('action'),
            align: 'left',
            sortable: false,
            value: 'view'
        },
    ],
    reference_type_headers: [
        {
            text: il8n.tc('name'),
            align: 'left',
            sortable: false,
            value: 'name'
        },
        {
            text: il8n.tc('action'),
            align: 'left',
            sortable: false,
            value: 'view'
        },
    ],
    practice_url: 'practices',
    practice_category_url: 'practice-categories',
    practice_sub_category_url: 'practice-sub-categories',
    context_dimension_url: 'context-dimensions',
    context_value_url: 'context-values',
    reference_type_url: 'reference-types',
    reference_url: 'references',
}