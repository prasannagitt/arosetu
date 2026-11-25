import api from './axios';
export const getAllPharmacies = () => api.get('/pharmacies');
export const getActivePharmacies = () => api.get('/pharmacies/active');
export const getPharmacyById = (id) => api.get(`/pharmacies/${id}`);
export const createPharmacy = (data) => api.post('/pharmacies', data);
export const updatePharmacy = (id, data) => api.put(`/pharmacies/${id}`, data);
export const togglePharmacyActive = (id) => api.patch(`/pharmacies/${id}/toggle`);

