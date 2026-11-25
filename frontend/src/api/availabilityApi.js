import api from './axios';
export const createAvailability = (data) => api.post('/availability', data);
export const getMyAvailability = () => api.get('/availability/me');
export const getAvailabilityByDoctor = (doctorId) => api.get(`/availability/doctor/${doctorId}`);
export const getAvailableSlots = (doctorId, hospitalId, date) =>
  api.get('/availability/slots', { params: { doctorId, hospitalId, date } });
export const updateAvailability = (id, data) => api.put(`/availability/${id}`, data);
export const deleteAvailability = (id) => api.delete(`/availability/${id}`);

