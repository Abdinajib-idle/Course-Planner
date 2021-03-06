<!--Provided by the instructor (Dr. Brian Fraser)-->
"use strict";

const myAppObj = new Vue({
    el: "#aboutApp",
    data: {
        appName: "(waiting for server...)",
        authorName: "(waiting for server...)",
    },
    methods: {
        dumpModel: function() {
            axios.get('api/dump-model', {})
              .then(function (response) {
                console.log(response);
              })
              .catch(function (error) {
                console.log(error);
              });
        }
    }
});

// Load data from server
$(document).ready(function() {
    axios.get('api/about', {})
      .then(function (response) {
        console.log(response);
        myAppObj.appName = response.data.appName;
        myAppObj.authorName = response.data.authorName;
      })
      .catch(function (error) {
        console.log(error);
      });
});
