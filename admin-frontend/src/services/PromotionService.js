import http from '../http-common';

class PromoService{
    getAll(params){
        return http.get("/api/promo",{params});
    }

    getPromotionById(id){
        return http.get(`/api/promo/${id}`);
    }

    create(data){
        return http.post("/api/promo", data);
    }

    update(id, data){
        return http.put(`/api/promo/${id}`, data);
    }

    delete(id){
        return http.delete(`/api/promo/${id}`);
    }
}

export default new PromoService();