import api from './axios';
export const searchDoctors = (params) => api.get('/doctors/search', { params });
export const getAllDoctors = () => api.get('/doctors');
export const getDoctorById = (id) => api.get(`/doctors/${id}`);
export const getMyDoctorProfile = () => api.get('/doctors/me');
export const updateMyDoctorProfile = (data) => api.put('/doctors/me', data);

