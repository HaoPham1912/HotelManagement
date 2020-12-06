import axios from 'axios';

export default axios.create({
    baseURL: "http://localhost:9090/api",
    headers: {
      "Content-type": "application/json",
      "Authorization":"Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNjA3MjI0NzMyLCJleHAiOjE2MDc4Mjk1MzJ9.SUftgJas1Fqa8H4PZ4ilvku9E5sYpxdQIrMYyGO0V2Gz19qTx_Yq15mBTdiz5Ofm9LtKceJf6i-mIyl283gsFw"
    }
});