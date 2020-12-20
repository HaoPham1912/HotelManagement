import http from '../http-common';

class PromoService{
    getAll(){
        return http.get("/promo");
    }

    create(data){
        return http.post("/promo", data);
    }
}

export default new PromoService();