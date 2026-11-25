import api from './axios';
export const getActiveHospitals = () => api.get('/hospitals/public');
export const getAllHospitals = () => api.get('/hospitals');
export const getHospitalById = (id) => api.get(`/hospitals/${id}`);
export const createHospital = (data) => api.post('/hospitals', data);
export const updateHospital = (id, data) => api.put(`/hospitals/${id}`, data);
export const toggleHospitalActive = (id) => api.patch(`/hospitals/${id}/toggle`);

