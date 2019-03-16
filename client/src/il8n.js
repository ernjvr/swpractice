import Vue from 'vue';
import VueIl8n from 'vue-i18n';
import env from '../.env';

Vue.use(VueIl8n);

function loadLocaleMessages () {
    const locales = require.context('./locales', true, /[A-Za-z0-9-_,\s]+\.json$/i);
    const messages = {};
    locales.keys().forEach(key => {
        const matched = key.match(/([A-Za-z0-9-_]+)\./i);
        if (matched && matched.length > 1) {
            const locale = matched[1];
            messages[locale] = locales(key)
        }
    });
    return messages
}

export const il8n = new VueIl8n({
    locale: env.VUE_APP_I18N_LOCALE || 'en',
    fallbackLocale: env.VUE_APP_I18N_FALLBACK_LOCALE || 'en',
    messages: loadLocaleMessages()
});