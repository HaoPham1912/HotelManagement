import axios from 'axios';

export default axios.create({
    baseURL: "http://localhost:9090/api",
    headers: {
      "Content-type": "application/json",
      "Authorization":"Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNjA3MTc1MzE5LCJleHAiOjE2MDc3ODAxMTl9.cUaKD9sl9_Nlb2M6Rc_SjGRLmCBKhT8hgWhXQTGD0tgrB-rumqwfonTwoce7rHNCXYvrb9LusNGBYJhsUMNnVA"
      
    }
});