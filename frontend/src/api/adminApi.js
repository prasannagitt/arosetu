import api from './axios';
export const getStats = () => api.get('/admin/stats');
export const getAllUsers = () => api.get('/admin/users');
export const toggleUser = (id) => api.patch(`/admin/users/${id}/toggle`);
export const getPendingDoctors = () => api.get('/admin/doctors/pending');
export const approveDoctor = (id) => api.patch(`/admin/doctors/${id}/approve`);
export const rejectDoctor = (id) => api.patch(`/admin/doctors/${id}/reject`);

