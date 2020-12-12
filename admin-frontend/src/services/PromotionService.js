import http from '../http-common';

class PromoService{
    getAll(){
        return http.get("/promo");
    }
}

export default new PromoService();