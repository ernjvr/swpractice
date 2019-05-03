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
            value: 'view'
        },
        {
            text: il8n.tc('practice_sub_category'),
            align: 'left',
            sortable: false,
            value: 'view'
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
            value: 'view'
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
    practice_url: 'practices',
    practice_category_url: 'practice-categories',
    practice_sub_category_url: 'practice-sub-categories',
    context_dimension_url: 'context-dimensions',
}