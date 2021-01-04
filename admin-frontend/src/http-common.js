import axios from 'axios';

export default axios.create({
    baseURL: "http://localhost:9090",
    headers: {
      "Content-type": "application/json",
      //"Authorization":"Bearer eyJhbGciOiJIUzUxMiJ9.eyJBVVRIT1JJVElFU19LRVkiOiJBRE1JTixFTVBMT1lFRSIsInN1YiI6ImFkbWluIiwiaWF0IjoxNjA5NzczMDUzLCJleHAiOjE2MDk3NzMzNTN9.mkjW3D6pxrZPgZ_7jDGCKcI18yM1Ft9XghUcYbA037P1P6u-kF4oIAvoPsYv0PhvWF9mQIcxQyJcsnTBfHLu6w"   
    }
});
