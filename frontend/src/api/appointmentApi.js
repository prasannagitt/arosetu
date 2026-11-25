import api from './axios';
export const createAppointment = (data) => api.post('/appointments', data);
export const getMyAppointments = () => api.get('/appointments/me');
export const getAppointmentById = (id) => api.get(`/appointments/${id}`);
export const confirmAppointment = (id) => api.patch(`/appointments/${id}/confirm`);
export const rejectAppointment = (id) => api.patch(`/appointments/${id}/reject`);
export const cancelAppointment = (id) => api.patch(`/appointments/${id}/cancel`);
export const completeAppointment = (id) => api.patch(`/appointments/${id}/complete`);

