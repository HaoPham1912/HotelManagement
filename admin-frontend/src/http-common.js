import axios from 'axios';

export default axios.create({
    baseURL: "http://localhost:9090/api",
    headers: {
      "Content-type": "application/json",
      "Authorization":"Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNjA3MjMyMjkzLCJleHAiOjE2MDc4MzcwOTN9.iI9fGbCKASn7ZDQTJ7HAZsMgV4HRT7CCn3_nOaYsNEJC896Y7F_t559t1kJUWo8yCSrLrdink9Qz0h9fNDh3dw"
    }
});