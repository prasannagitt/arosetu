import api from './axios';
export const createPrescription = (data) => api.post('/prescriptions', data);
export const getMyPrescriptions = () => api.get('/prescriptions/me');
export const getPrescriptionById = (id) => api.get(`/prescriptions/${id}`);
export const prescribe = (id) => api.patch(`/prescriptions/${id}/prescribe`);
export const sendToPharmacy = (id, pharmacyId) => api.patch(`/prescriptions/${id}/send-to-pharmacy?pharmacyId=${pharmacyId}`);
export const markReady = (id) => api.patch(`/prescriptions/${id}/ready`);
export const markDispensed = (id) => api.patch(`/prescriptions/${id}/dispense`);
export const getByPharmacy = (pharmacyId) => api.get(`/prescriptions/pharmacy/${pharmacyId}`);

