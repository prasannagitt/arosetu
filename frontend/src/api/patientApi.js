import api from './axios';
export const getMyPatientProfile = () => api.get('/patients/me');
export const updateMyPatientProfile = (data) => api.put('/patients/me', data);
export const getAllPatients = () => api.get('/patients');

