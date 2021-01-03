import axios from 'axios';

export default axios.create({
    baseURL: "http://localhost:9090/api",
    headers: {
      "Content-type": "application/json",
      //"Authorization":"Bearer eyJhbGciOiJIUzUxMiJ9.eyJBVVRIT1JJVElFU19LRVkiOiJBRE1JTixFTVBMT1lFRSIsInN1YiI6ImFkbWluIiwiaWF0IjoxNjA5NjgyNzM0LCJleHAiOjE2MDk2ODMwMzR9.swOOmFlQPBw9cMWmpbWBkwGG8Ve6eGgMMdvU1XafgWuuUt0azDIcZKykAhb3KtvQrGQs09pTNXR1YGPlbv7bLA"   
    }
});
