import axios from 'axios';

export default axios.create({
    baseURL: "http://localhost:9090/api",
    headers: {
      "Content-type": "application/json",
      //"Authorization":"Bearer eyJhbGciOiJIUzUxMiJ9.eyJBVVRIT1JJVElFU19LRVkiOiJBRE1JTiIsInN1YiI6ImFkbWluMSIsImlhdCI6MTYwODA1MTY1MywiZXhwIjoxNjA4MDUxOTUzfQ.j28W9JPxQjXpB0J0oTMndOtNnGgujgJNmfktwZTC52NycLoguzOX9WB42sx-ocySTSoIks8UrbAPATKOyd7yIQ"   
    }
});