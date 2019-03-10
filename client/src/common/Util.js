export default {

    encode(href) {
        return href.replace(/\//g, "%2F");
    }
}