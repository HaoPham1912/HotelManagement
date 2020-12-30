import http from '../http-common';

class PromoService{
    getAll(params){
        return http.get("/promo",{params});
    }

    getPromotionById(id){
        return http.get(`/promo/${id}`);
    }

    create(data){
        return http.post("/promo", data);
    }

    update(id, data){
        return http.put(`promo/${id}`, data);
    }

    delete(id){
        return http.delete(`/promo/${id}`);
    }
}

export default new PromoService();