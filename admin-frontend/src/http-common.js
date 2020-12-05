import axios from 'axios';

export default axios.create({
    baseURL: "http://localhost:9090/api",
    headers: {
      "Content-type": "application/json",
      "Authorization":"Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNjA3MTc2ODY2LCJleHAiOjE2MDc3ODE2NjZ9.6kDw_BBG9_hoKu7wY7wWnDpx-2_D6Tl50RD7iEzAn7JSOG3Xt92CVQx9L8DQmUjj1_k6gU3_wGKkdxzms1-iAw"
    }
});