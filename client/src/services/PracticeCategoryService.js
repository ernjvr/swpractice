import api from "./api";

export default {

    getPracticeCategory(href) {
        console.log('getPracticeCategory for '+ href);
        return new Promise((resolve, reject) => {
            api.get(href)
                .then(response => {
                    let practiceCategory = response.data;
                    console.log(href + ' get success: ' + practiceCategory);
                    console.log(practiceCategory);
                    resolve(practiceCategory);
                }, error => {
                    console.log(href + ' get error: ' + error);
                    reject(error);
                });
        });
    }
}