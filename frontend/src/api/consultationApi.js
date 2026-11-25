import api from './axios';
export const createConsultation = (data) => api.post('/consultations', data);
export const getMyConsultations = () => api.get('/consultations/me');
export const getConsultationById = (id) => api.get(`/consultations/${id}`);
export const updateConsultation = (id, data) => api.put(`/consultations/${id}`, data);

