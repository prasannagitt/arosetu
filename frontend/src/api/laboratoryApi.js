import api from './axios';
export const getAllLaboratories = () => api.get('/laboratories');
export const getActiveLaboratories = () => api.get('/laboratories/active');
export const getLaboratoryById = (id) => api.get(`/laboratories/${id}`);
export const createLaboratory = (data) => api.post('/laboratories', data);
export const updateLaboratory = (id, data) => api.put(`/laboratories/${id}`, data);
export const toggleLaboratoryActive = (id) => api.patch(`/laboratories/${id}/toggle`);

