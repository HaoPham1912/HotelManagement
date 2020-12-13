import axios from 'axios';

export default axios.create({
    baseURL: "http://localhost:9090/api",
    headers: {
      "Content-type": "application/json",
      "Authorization":"Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNjA3ODQ0ODg0LCJleHAiOjE2MDg0NDk2ODR9.rAKPdayX6T8aQDdteqe6K0zW5LjeMqtCARKyGUuQ6Sp3XsyfU21xwbAD4ikZH9ZFuG5ye-cZkwmP4Lr80PGcfg"
    }
});