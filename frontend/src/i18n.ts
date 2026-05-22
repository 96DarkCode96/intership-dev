import i18n from 'i18next';
import {initReactI18next} from 'react-i18next';

import translationEN from './locales/en/translation.json';
import translationCS from './locales/cs/translation.json';

const resources = {
    en: {translation: translationEN},
    cs: {translation: translationCS}
};

i18n
    .use(initReactI18next) // Passes i18n down to react-i18next
    .init({
        resources,
        lng: 'en', // Default language
        fallbackLng: 'cs', // Use if the chosen language is missing keys
        interpolation: {
            escapeValue: false // React already protects against XSS
        }
    });

export default i18n;